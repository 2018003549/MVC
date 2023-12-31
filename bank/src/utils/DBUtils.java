package utils;

import java.sql.*;
import java.util.ResourceBundle;

public class DBUtils {
//    声明静态变量
    //绑定资源文件
    private static ResourceBundle bundle=ResourceBundle.getBundle("resources.jdbc");
    private static String driver=bundle.getString("driver");
    private static String url=bundle.getString("url");
    private static String user=bundle.getString("username");
    private static String password=bundle.getString("password");
    static {
        //注册驱动,只用注册一次
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private static ThreadLocal<Connection> local=new ThreadLocal<>();
    //获取数据库连接对象
    public static Connection getConnection() throws SQLException {
        Connection connection=local.get();
        if(connection==null){
            //当前线程未绑定connection对象就创建对象，并且存入集合
        connection= DriverManager.getConnection(url, user, password);
        local.set(connection);
        }
        return connection;
    }
    //释放资源
    public static void close(Connection conn, Statement ps, ResultSet rs){
        if(conn!=null){
            try {
                conn.close();
                local.remove();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }if(ps!=null){
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }if(rs!=null){
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
