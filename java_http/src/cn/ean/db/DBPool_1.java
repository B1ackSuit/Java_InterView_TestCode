package cn.ean.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ean
 * @FileName DBPool_1
 * @Date 2022/1/3 7:24 PM
 **/
public class DBPool_1 {
    public static Connection createConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/xxxx", "root", "root");

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 连接
     */
    private List<Connection> idlePool;
    private List<Connection> servicePool;

    /**
     * 连接配置
     */
    private static final int MAX_SIZE = 66;
    public static final int MIN_SIZE = 6;



    /**
     * 初始化连接
     */
    private void initPool(){
        if (idlePool == null){
            idlePool = new ArrayList<>();
        }
        servicePool = new ArrayList<>();
        while(idlePool.size() < MIN_SIZE){
            idlePool.add(createConnection());
        }
    }

    /**
     * 取连接
     */
    public Connection getConn(){
        Connection connection = null;
        int index = idlePool.size() - 1;
        if(index >= 0){
            connection = idlePool.get(index);
            idlePool.remove(index);
            servicePool.add(connection);
        } else if(servicePool.size() < MAX_SIZE){
            connection = createConnection();
            return connection;
        }
        return connection;
    }

    /**
     * 存连接
     */
    public void closeConn(Connection conn){
        if(idlePool.size() + servicePool.size() > MAX_SIZE){
            try{
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        } else {
            idlePool.add(conn);
            servicePool.remove(conn);
        }

    }

    public DBPool_1(){
        this.initPool();
    }

}
