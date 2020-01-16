package jdbc;

import meng.springframework.annotation.Autowired;
import meng.springframework.conf.bean.MybatisNode;

import java.sql.*;

public class DBManager {

    @Autowired
    private static MybatisNode mybatisNode;

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(mybatisNode.getDriverClassName());
        //获取连接
        return DriverManager.getConnection(mybatisNode.getUrl(),mybatisNode.getUserName(),mybatisNode.getPassWord());
    }

    //先打开 后关闭
    public static void closeAll(Connection connection, PreparedStatement statement){
        try{
            if (statement != null){
                statement.close();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if (connection != null){
                    connection.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    //先打开 后关闭
    public static void closeAll(Connection connection, PreparedStatement statement, ResultSet resultSet){
        try{
            if (resultSet != null){
                resultSet.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeAll(connection,statement);
        }
    }

}
