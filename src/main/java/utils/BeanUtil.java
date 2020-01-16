package utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BeanUtil {

    //获取字段 - 单个

    public static <T> T getResult(ResultSet resultSet,T t) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class c = t.getClass();
        Class resultClass = resultSet.getClass();
        Field[] fields = c.getDeclaredFields();

        while (resultSet.next()){
            for (Field f:fields) {

                //反射获取值
                Method resultMethod = resultClass.getMethod("get" + getDataType(f));

                f.setAccessible(true);
                Method method = c.getMethod("set" + captureStr(f.getName()));
                method.invoke(c,resultMethod.invoke(resultClass,f.getName()));
            }
        }

        return t;
    }


    /**
     * 字符串首字母转换为大写
     * @param str
     * @return
     */
    public static String captureStr(String str) {
        char[] cs = str.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }

    /**
     * 获取数据类型
     */
    public static String getDataType(Field field){
        String[] types = field.getType().getTypeName().split(".");

        return types[types.length -1];
    }

    public static <T> List<T> getResults(ResultSet resultSet,T t) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<T> ts = new ArrayList<>();

        Class c = t.getClass();
        Class resultClass = resultSet.getClass();
        Field[] fields = c.getDeclaredFields();

        while (resultSet.next()){
            for (Field f:fields) {

                //反射获取值
                Method resultMethod = resultClass.getMethod("get" + getDataType(f));

                f.setAccessible(true);
                Method method = c.getMethod("set" + captureStr(f.getName()));
                method.invoke(c,resultMethod.invoke(resultClass,f.getName()));
            }

            ts.add(t);
        }

        return ts;
    }
}
