package cn.ean.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ean
 * @FileName DBPool
 * @Date 2022/1/2 4:55 PM
 **/
public class DBPool {
    public static Connection createConnection() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://:3306/xxx?serverTimezone=Asia/Shanghai&useSSL=true",
                    "root", "root");

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 连接池对象
     */
    private List<Connection> idlePool;
    private List<Connection> servicePool;

    /**
     * 最大最小连接数
     */
    private static final int MAX_SIZE = 20;
    private static final int MIN_SIZE = 5;

    /**
     * 初始化连接池
     */
    public void initPool(){
        if (idlePool == null) {
            idlePool = new ArrayList<>();
        }
        servicePool = new ArrayList<>();
        while (idlePool.size() < MIN_SIZE) {
            idlePool.add(createConnection());
            System.out.println("池中连接数：" + idlePool.size());
        }
    }

    /**
     * 取连接
     */
    public synchronized Connection getConnection() {
        int last_index = idlePool.size() - 1;
        Connection conn = null;
        if (last_index >= 0) {
            conn = idlePool.get(last_index);
            idlePool.remove(last_index);
            servicePool.add(conn);
        }
        else if (servicePool.size() < MAX_SIZE) {
            conn = createConnection();
            servicePool.add(conn);
        }
        return conn;
    }

    /**
     * 存连接
     */
    public synchronized void close(Connection conn) {
        if (idlePool.size() + servicePool.size() >= MAX_SIZE) {
            try{
                if (conn != null){
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            idlePool.add(conn);
            servicePool.remove(conn);
        }
    }

    public DBPool() {
        this.initPool();
    }

}
