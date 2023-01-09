package ganymedprojects;

import ganymedprojects.auth.AuthenticationManager;
import ganymedprojects.channel.ChannelManager;
import ganymedprojects.crypto.CryptoWishList;
import ganymedprojects.crypto.cipher.BlockCipherFactory;
import ganymedprojects.crypto.digest.MAC;
import ganymedprojects.transport.KexManager;
import ganymedprojects.transport.TransportManager;
import ganymedprojects.util.TimeoutService;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.security.SecureRandom;
import java.util.Vector;

/**
 * @author ean
 * @FileName Connection
 * @Date 2022/12/2 18:08
 **/
public class Connection {

    public static final String identification = "Ganymed Build_210";
    private SecureRandom generator;
    private AuthenticationManager am;
    private boolean authenticated;
    private ChannelManager cm;
    private CryptoWishList cryptoWishList;
    private DHGexParameters dhgexpara;
    private final String hostname;
    private final int port;
    private TransportManager tm;
    private boolean tcpNoDelay;
    private ProxyData proxyData;
    private Vector connectionMonitors;

    public static synchronized String[] getAvailableCiphers() {
        return BlockCipherFactory.getDefaultCipherList();
    }

    public static synchronized String[] getAvailableMACs() {
        return MAC.getMacList();
    }

    public static synchronized String[] getAvailableServerHostKeyAlgorithms() {
        return KexManager.getDefaultServerHostkeyAlgorithmList();
    }

    public Connection(String hostname) {
        this(hostname, 22);
    }

    public Connection(String hostname, int port) {
        this.authenticated = false;
        this.cryptoWishList = new CryptoWishList();
        this.dhgexpara = new DHGexParameters();
        this.tcpNoDelay = false;
        this.proxyData = null;
        this.connectionMonitors = new Vector();
        this.hostname = hostname;
        this.port = port;
    }

    /** @deprecated */
    public synchronized boolean authenticateWithDSA(String user, String pem, String password) throws IOException {
        if (this.tm == null) {
            throw new IllegalStateException("Connection is not established!");
        } else if (this.authenticated) {
            throw new IllegalStateException("Connection is already authenticated!");
        } else {
            if (this.am == null) {
                this.am = new AuthenticationManager(this.tm);
            }

            if (this.cm == null) {
                this.cm = new ChannelManager(this.tm);
            }

            if (user == null) {
                throw new IllegalArgumentException("user argument is null");
            } else if (pem == null) {
                throw new IllegalArgumentException("pem argument is null");
            } else {
                this.authenticated = this.am.authenticatePublicKey(user, pem.toCharArray(), password, this.getOrCreateSecureRND());
                return this.authenticated;
            }
        }
    }

    public synchronized boolean authenticateWithKeyboardInteractive(String user, InteractiveCallback cb) throws IOException {
        return this.authenticateWithKeyboardInteractive(user, (String[])null, cb);
    }

    public synchronized boolean authenticateWithKeyboardInteractive(String user, String[] submethods, InteractiveCallback cb) throws IOException {
        if (cb == null) {
            throw new IllegalArgumentException("Callback may not ne NULL!");
        } else if (this.tm == null) {
            throw new IllegalStateException("Connection is not established!");
        } else if (this.authenticated) {
            throw new IllegalStateException("Connection is already authenticated!");
        } else {
            if (this.am == null) {
                this.am = new AuthenticationManager(this.tm);
            }

            if (this.cm == null) {
                this.cm = new ChannelManager(this.tm);
            }

            if (user == null) {
                throw new IllegalArgumentException("user argument is null");
            } else {
                this.authenticated = this.am.authenticateInteractive(user, submethods, cb);
                return this.authenticated;
            }
        }
    }

    public synchronized boolean authenticateWithPassword(String user, String password) throws IOException {
        if (this.tm == null) {
            throw new IllegalStateException("Connection is not established!");
        } else if (this.authenticated) {
            throw new IllegalStateException("Connection is already authenticated!");
        } else {
            if (this.am == null) {
                this.am = new AuthenticationManager(this.tm);
            }

            if (this.cm == null) {
                this.cm = new ChannelManager(this.tm);
            }

            if (user == null) {
                throw new IllegalArgumentException("user argument is null");
            } else if (password == null) {
                throw new IllegalArgumentException("password argument is null");
            } else {
                this.authenticated = this.am.authenticatePassword(user, password);
                return this.authenticated;
            }
        }
    }

    public synchronized boolean authenticateWithPublicKey(String user, char[] pemPrivateKey, String password) throws IOException {
        if (this.tm == null) {
            throw new IllegalStateException("Connection is not established!");
        } else if (this.authenticated) {
            throw new IllegalStateException("Connection is already authenticated!");
        } else {
            if (this.am == null) {
                this.am = new AuthenticationManager(this.tm);
            }

            if (this.cm == null) {
                this.cm = new ChannelManager(this.tm);
            }

            if (user == null) {
                throw new IllegalArgumentException("user argument is null");
            } else if (pemPrivateKey == null) {
                throw new IllegalArgumentException("pemPrivateKey argument is null");
            } else {
                this.authenticated = this.am.authenticatePublicKey(user, pemPrivateKey, password, this.getOrCreateSecureRND());
                return this.authenticated;
            }
        }
    }

    public synchronized boolean authenticateWithPublicKey(String user, File pemFile, String password) throws IOException {
        if (pemFile == null) {
            throw new IllegalArgumentException("pemFile argument is null");
        } else {
            char[] buff = new char[256];
            CharArrayWriter cw = new CharArrayWriter();
            FileReader fr = new FileReader(pemFile);

            while(true) {
                int len = fr.read(buff);
                if (len < 0) {
                    fr.close();
                    return this.authenticateWithPublicKey(user, cw.toCharArray(), password);
                }

                cw.write(buff, 0, len);
            }
        }
    }

    public synchronized void addConnectionMonitor(ConnectionMonitor cmon) {
        if (cmon == null) {
            throw new IllegalArgumentException("cmon argument is null");
        } else {
            this.connectionMonitors.addElement(cmon);
            if (this.tm != null) {
                this.tm.setConnectionMonitors(this.connectionMonitors);
            }

        }
    }

    public synchronized void close() {
        Throwable t = new Throwable("Closed due to user request.");
        this.close(t, false);
    }

    private void close(Throwable t, boolean hard) {
        if (this.cm != null) {
            this.cm.closeAllChannels();
        }

        if (this.tm != null) {
            this.tm.close(t, !hard);
            this.tm = null;
        }

        this.am = null;
        this.cm = null;
        this.authenticated = false;
    }

    public synchronized ConnectionInfo connect() throws IOException {
        return this.connect((ServerHostKeyVerifier)null, 0, 0);
    }

    public synchronized ConnectionInfo connect(ServerHostKeyVerifier verifier) throws IOException {
        return this.connect(verifier, 0, 0);
    }

    public synchronized ConnectionInfo connect(ServerHostKeyVerifier verifier, int connectTimeout, int kexTimeout) throws IOException {
        if (this.tm != null) {
            throw new IOException("Connection to " + this.hostname + " is already in connected state!");
        } else if (connectTimeout < 0) {
            throw new IllegalArgumentException("connectTimeout must be non-negative!");
        } else if (kexTimeout < 0) {
            throw new IllegalArgumentException("kexTimeout must be non-negative!");
        } else {
            final class TimeoutState {
                boolean isCancelled = false;
                boolean timeoutSocketClosed = false;

                TimeoutState() {
                }
            }

            final TimeoutState state = new TimeoutState();
            this.tm = new TransportManager(this.hostname, this.port);
            this.tm.setConnectionMonitors(this.connectionMonitors);
            TransportManager var10000 = this.tm;
            synchronized(var10000){}

            try {
                TimeoutService.TimeoutToken token = null;
                if (kexTimeout > 0) {
                    Runnable timeoutHandler = new Runnable() {
                        public void run() {
                            synchronized(state) {
                                if (!state.isCancelled) {
                                    state.timeoutSocketClosed = true;
                                    Connection.this.tm.close(new SocketTimeoutException("The connect timeout expired"), false);
                                }
                            }
                        }
                    };
                    long timeoutHorizont = System.currentTimeMillis() + (long)kexTimeout;
                    token = TimeoutService.addTimeoutHandler(timeoutHorizont, timeoutHandler);
                }

                try {
                    this.tm.initialize(this.cryptoWishList, verifier, this.dhgexpara, connectTimeout, this.getOrCreateSecureRND(), this.proxyData);
                } catch (SocketTimeoutException var10) {
                    throw (SocketTimeoutException)(new SocketTimeoutException("The connect() operation on the socket timed out.")).initCause(var10);
                }

                this.tm.setTcpNoDelay(this.tcpNoDelay);
                ConnectionInfo ci = this.tm.getConnectionInfo(1);
                if (token != null) {
                    TimeoutService.cancelTimeoutHandler(token);
                    synchronized(state) {
                        if (state.timeoutSocketClosed) {
                            throw new IOException("This exception will be replaced by the one below =)");
                        }

                        state.isCancelled = true;
                    }
                }

                return ci;
            } catch (SocketTimeoutException var12) {
                throw var12;
            } catch (IOException var13) {
                this.close(new Throwable("There was a problem during connect."), false);
                synchronized(state) {
                    if (state.timeoutSocketClosed) {
                        throw new SocketTimeoutException("The kexTimeout (" + kexTimeout + " ms) expired.");
                    }
                }

                if (var13 instanceof HTTPProxyException) {
                    throw var13;
                } else {
                    throw (IOException)(new IOException("There was a problem while connecting to " + this.hostname + ":" + this.port)).initCause(var13);
                }
            }
        }
    }

    public synchronized LocalPortForwarder createLocalPortForwarder(int local_port, String host_to_connect, int port_to_connect) throws IOException {
        if (this.tm == null) {
            throw new IllegalStateException("Cannot forward ports, you need to establish a connection first.");
        } else if (!this.authenticated) {
            throw new IllegalStateException("Cannot forward ports, connection is not authenticated.");
        } else {
            return new LocalPortForwarder(this.cm, local_port, host_to_connect, port_to_connect);
        }
    }

    public synchronized LocalStreamForwarder createLocalStreamForwarder(String host_to_connect, int port_to_connect) throws IOException {
        if (this.tm == null) {
            throw new IllegalStateException("Cannot forward, you need to establish a connection first.");
        } else if (!this.authenticated) {
            throw new IllegalStateException("Cannot forward, connection is not authenticated.");
        } else {
            return new LocalStreamForwarder(this.cm, host_to_connect, port_to_connect);
        }
    }

    public synchronized SCPClient createSCPClient() throws IOException {
        if (this.tm == null) {
            throw new IllegalStateException("Cannot create SCP client, you need to establish a connection first.");
        } else if (!this.authenticated) {
            throw new IllegalStateException("Cannot create SCP client, connection is not authenticated.");
        } else {
            return new SCPClient(this);
        }
    }

    public synchronized void forceKeyExchange() throws IOException {
        if (this.tm == null) {
            throw new IllegalStateException("You need to establish a connection first.");
        } else {
            this.tm.forceKeyExchange(this.cryptoWishList, this.dhgexpara);
        }
    }

    public synchronized String getHostname() {
        return this.hostname;
    }

    public synchronized int getPort() {
        return this.port;
    }

    public synchronized ConnectionInfo getConnectionInfo() throws IOException {
        if (this.tm == null) {
            throw new IllegalStateException("Cannot get details of connection, you need to establish a connection first.");
        } else {
            return this.tm.getConnectionInfo(1);
        }
    }

    public synchronized String[] getRemainingAuthMethods(String user) throws IOException {
        if (user == null) {
            throw new IllegalArgumentException("user argument may not be NULL!");
        } else if (this.tm == null) {
            throw new IllegalStateException("Connection is not established!");
        } else if (this.authenticated) {
            throw new IllegalStateException("Connection is already authenticated!");
        } else {
            if (this.am == null) {
                this.am = new AuthenticationManager(this.tm);
            }

            if (this.cm == null) {
                this.cm = new ChannelManager(this.tm);
            }

            return this.am.getRemainingMethods(user);
        }
    }

    public synchronized boolean isAuthenticationComplete() {
        return this.authenticated;
    }

    public synchronized boolean isAuthenticationPartialSuccess() {
        return this.am == null ? false : this.am.getPartialSuccess();
    }

    public synchronized boolean isAuthMethodAvailable(String user, String method) throws IOException {
        if (method == null) {
            throw new IllegalArgumentException("method argument may not be NULL!");
        } else {
            String[] methods = this.getRemainingAuthMethods(user);

            for(int i = 0; i < methods.length; ++i) {
                if (methods[i].compareTo(method) == 0) {
                    return true;
                }
            }

            return false;
        }
    }

    private final SecureRandom getOrCreateSecureRND() {
        if (this.generator == null) {
            this.generator = new SecureRandom();
        }

        return this.generator;
    }

    public synchronized Session openSession() throws IOException {
        if (this.tm == null) {
            throw new IllegalStateException("Cannot open session, you need to establish a connection first.");
        } else if (!this.authenticated) {
            throw new IllegalStateException("Cannot open session, connection is not authenticated.");
        } else {
            return new Session(this.cm, this.getOrCreateSecureRND());
        }
    }

    private String[] removeDuplicates(String[] list) {
        if (list != null && list.length >= 2) {
            String[] list2 = new String[list.length];
            int count = 0;

            for(int i = 0; i < list.length; ++i) {
                boolean duplicate = false;
                String element = list[i];

                for(int j = 0; j < count; ++j) {
                    if (element == null && list2[j] == null || element != null && element.equals(list2[j])) {
                        duplicate = true;
                        break;
                    }
                }

                if (!duplicate) {
                    list2[count++] = list[i];
                }
            }

            if (count == list2.length) {
                return list2;
            } else {
                String[] tmp = new String[count];
                System.arraycopy(list2, 0, tmp, 0, count);
                return tmp;
            }
        } else {
            return list;
        }
    }

    public synchronized void setClient2ServerCiphers(String[] ciphers) {
        if (ciphers != null && ciphers.length != 0) {
            ciphers = this.removeDuplicates(ciphers);
            BlockCipherFactory.checkCipherList(ciphers);
            this.cryptoWishList.c2s_enc_algos = ciphers;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public synchronized void setClient2ServerMACs(String[] macs) {
        if (macs != null && macs.length != 0) {
            macs = this.removeDuplicates(macs);
            MAC.checkMacList(macs);
            this.cryptoWishList.c2s_mac_algos = macs;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public synchronized void setDHGexParameters(DHGexParameters dgp) {
        if (dgp == null) {
            throw new IllegalArgumentException();
        } else {
            this.dhgexpara = dgp;
        }
    }

    public synchronized void setServer2ClientCiphers(String[] ciphers) {
        if (ciphers != null && ciphers.length != 0) {
            ciphers = this.removeDuplicates(ciphers);
            BlockCipherFactory.checkCipherList(ciphers);
            this.cryptoWishList.s2c_enc_algos = ciphers;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public synchronized void setServer2ClientMACs(String[] macs) {
        if (macs != null && macs.length != 0) {
            macs = this.removeDuplicates(macs);
            MAC.checkMacList(macs);
            this.cryptoWishList.s2c_mac_algos = macs;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public synchronized void setServerHostKeyAlgorithms(String[] algos) {
        if (algos != null && algos.length != 0) {
            algos = this.removeDuplicates(algos);
            KexManager.checkServerHostkeyAlgorithmsList(algos);
            this.cryptoWishList.serverHostKeyAlgorithms = algos;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public synchronized void setTCPNoDelay(boolean enable) throws IOException {
        this.tcpNoDelay = enable;
        if (this.tm != null) {
            this.tm.setTcpNoDelay(enable);
        }

    }

    public synchronized void setProxyData(ProxyData proxyData) {
        this.proxyData = proxyData;
    }

    public synchronized void requestRemotePortForwarding(String bindAddress, int bindPort, String targetAddress, int targetPort) throws IOException {
        if (this.tm == null) {
            throw new IllegalStateException("You need to establish a connection first.");
        } else if (!this.authenticated) {
            throw new IllegalStateException("The connection is not authenticated.");
        } else if (bindAddress != null && targetAddress != null && bindPort > 0 && targetPort > 0) {
            this.cm.requestGlobalForward(bindAddress, bindPort, targetAddress, targetPort);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public synchronized void cancelRemotePortForwarding(int bindPort) throws IOException {
        if (this.tm == null) {
            throw new IllegalStateException("You need to establish a connection first.");
        } else if (!this.authenticated) {
            throw new IllegalStateException("The connection is not authenticated.");
        } else {
            this.cm.requestCancelGlobalForward(bindPort);
        }
    }

    public synchronized void setSecureRandom(SecureRandom rnd) {
        if (rnd == null) {
            throw new IllegalArgumentException();
        } else {
            this.generator = rnd;
        }
    }

}
