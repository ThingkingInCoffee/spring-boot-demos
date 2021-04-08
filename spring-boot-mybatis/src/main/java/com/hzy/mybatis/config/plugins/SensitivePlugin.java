package com.hzy.mybatis.config.plugins;

import com.hzy.mybatis.common.annotation.SensitiveBean;
import com.hzy.mybatis.common.utils.CheckSensitive;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 拦截增改查操作，进行脱敏处理
 */

@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class SensitivePlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement statement = (MappedStatement) invocation.getArgs()[0];
        // 获取该sql语句的类型，例如update，insert
        String methodName = invocation.getMethod().getName();
        // 获取该sql语句放入的参数
        Object parameter = invocation.getArgs()[1];
        // 如果是查询操作，并且返回值不是敏感实体类对象，并且传入参数不为空，就直接调用执行方法，返回这个方法的返回值
        // 方法中可以判断这个返回值是否是多条数据，如果有数据，就代表着是select 操作，没有就代表是update insert delete，
        // 因为mybatis的dao层不能为非select操作设置返回值
        if (statement.getResultMaps().size() > 0) {
            // 获取到这个返回值的类属性
            Class<?> type = statement.getResultMaps().get(0).getType();
            // 返回值没有带敏感实体类对象注解
            if (!type.isAnnotationPresent(SensitiveBean.class)) {
                // 直接执行语句并返回
                return invocation.proceed();
            }
        }
        // 如果该参数为空，就不进行判断敏感实体类，直接执行sql
        // 并且
        // 如果判断该参数带有敏感实体类的注解，才对这个实体类进行扫描查看是否有加密解密的注解
        if (parameter != null && CheckSensitive.sensitiveBean(parameter)) {
            // 如果有就扫描是否是更新操作和是否有加密注解
            // 如果是更新或者插入时，就对数据进行加密后保存在数据库
            if (StringUtils.equalsIgnoreCase("update", methodName) ||
                    StringUtils.equalsIgnoreCase("insert", methodName)) {
                // 对参数内含注解的字段进行加密
                CheckSensitive.encryptField(parameter);
            }
        }

        // 继续执行sql语句,调用当前拦截的执行方法
        Object returnValue = invocation.proceed();
        try {
            // 当返回值类型为数组集合时，就判断是否需要进行数据解密
            if (returnValue instanceof ArrayList<?>) {
                List<?> list = (List<?>) returnValue;
                // 判断结果集的数据是否为空
                if (list == null || list.size() < 1) {
                    return returnValue;
                }
                Object object = list.get(0);
                // 为空值就返回数据
                if (object == null) {
                    return returnValue;
                }
                // 判断第一个对象是否有解密注解
                Field[] fields = object.getClass().getDeclaredFields();
                // 定义一个临时变量
                int len;
                if (fields != null && 0 < (len = fields.length)) {
                    for (Object o : list) {
                        //调用解密,在这个方法中针对某个带有解密注解的字段进行解密
                        CheckSensitive.decryptField(o);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return returnValue;
        }
        return returnValue;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
