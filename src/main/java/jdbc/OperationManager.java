package jdbc;

import meng.springframework.annotation.Component;
import utils.BeanUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * 增删改查管理类
 * @author MengShaokun
 */
@Component
public class OperationManager {

    //增加
    public static int insert(String sql){
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = DBManager.getConnection();
            statement = connection.prepareStatement(sql);
            return statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.closeAll(connection,statement);
        }
        return 0;
    }

    //删除
    public static int delete(String sql){
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = DBManager.getConnection();
            statement = connection.prepareStatement(sql);
            return statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.closeAll(connection,statement);
        }
        return 0;
    }

    //更新
    public static int update(String sql){
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = DBManager.getConnection();
            statement = connection.prepareStatement(sql);
            return statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.closeAll(connection,statement);
        }
        return 0;
    }


    //查询单个
    public static <T> T selectOne(String sql,T t){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBManager.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if (resultSet.getFetchSize() > 1){
                throw new RuntimeException("需要查询一条，结果查询出来" + resultSet.getFetchSize() +"条！");
            }
            return BeanUtil.getResult(resultSet,t);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.closeAll(connection,statement,resultSet);
        }

        return null;
    }

    //查询多个
    public static <T> List<T> select(String sql,T t){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try{
            connection = DBManager.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            return BeanUtil.getResults(resultSet,t);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBManager.closeAll(connection,statement,resultSet);
        }
        return null;
    }
}
