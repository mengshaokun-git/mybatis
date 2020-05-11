package proxy;

import enums.MethodType;
import error.enums.SqlMethodError;
import jdbc.OperationManager;
import mapperscan.SqlObject;
import meng.springframework.annotation.Autowired;
import meng.springframework.container.Container;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class JDBCProxy implements MethodInterceptor {

    @Autowired
    private OperationManager operationManager;

    String className = null;
    Object object = null;


    //给目标对象创建一个代理对象
    public Object getInstance(Class c) {
        className = c.getTypeName();
        object = c;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(c);
        // 回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

        Container methodContainer = SqlObject.getMethodContainer();
        Container sqlContainer = SqlObject.getSqlContainer();
        String methodAndSqlKey = className + method.getName();

        //sql类型 insert delete update select
        String methodType = null;
        //sql语句
        String sql = null;
        if (methodContainer.get(methodAndSqlKey).equals(null)){
            throw new RuntimeException(SqlMethodError.MTTHOD_NOT_EXIST.getDescription());
        }else {
            methodType = methodContainer.get(methodAndSqlKey).toString();
        }

        if (sqlContainer.get(methodAndSqlKey).equals(null)){
            throw new RuntimeException(SqlMethodError.SQL_NOT_EXIST.getDescription());
        }else {
            sql = sqlContainer.get(methodAndSqlKey).toString();
        }

        if (methodType.equals(MethodType.INSERT.getValue())){
            return OperationManager.insert(sql);
        }else if (methodType.equals(MethodType.DELETE.getValue())){
            return OperationManager.delete(sql);
        }else if (methodType.equals(MethodType.UPDATE.getValue())){
            return OperationManager.update(sql);
        }else if (methodType.equals(MethodType.SELECT.getValue())){
            return OperationManager.select(sql,object);
        }

        return null;
    }



}
