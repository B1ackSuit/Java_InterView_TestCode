package ssh;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;


public class SSHTools {

    private Connection ethzConn;

    public static String ipAddr;

    private Charset charset = StandardCharsets.UTF_8;

    public static String username;

    public static String password;

    public SSHTools() {

    }

    /**
     * 登录
     * @return 成功失败信息（后续优化）
     */
    public boolean login() {
        if (null == ipAddr || null == username || null == password) {
            System.out.println("ip/username/password 中有null，检查配置");
            return false;
        }
        ethzConn = new Connection(ipAddr);
        try {
            // 连接
            ethzConn.connect();
            // 认证
            return ethzConn.authenticateWithPassword(username, password);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public StringBuilder exec(String cmds) {
        InputStream in = null;
        StringBuilder result = new StringBuilder();
        try {
            if (this.login()) {
                // 打开一个会话
                Session ethzSession = ethzConn.openSession();
                ethzSession.execCommand(cmds);
                in = ethzSession.getStdout();
                result = this.processStdout(in, this.charset);
                // 获取出错的结果
                if ("".equals(result.toString().trim())) {
                    result = this.processStdout(ethzSession.getStderr(), this.charset);
                }
                ethzConn.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return result;
    }

    /**
     * 解析流获取字符串信息
     * @param in
     * @param charset
     * @return 脚本的输出结果
     */
    private StringBuilder processStdout(InputStream in, Charset charset) {
        byte[] buf = new byte[1024];
        StringBuilder sb = new StringBuilder();
        try {
            // 此方法是休息10秒后最后一次性输出2行数据
            int length;
            while ((length = in.read(buf)) != -1) {
                sb.append(new String(buf, 0, length));
            }

            // 这个会按照脚本一步一步执行，中途有休息10秒。
            BufferedReader reader = null;
            String result = null;
            reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            while ((result = reader.readLine()) != null) {
                System.out.println(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb;
    }
}