package com.hzy.mybatis.config.plugins;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.Properties;

@Slf4j
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class ExamplePlugin implements Interceptor {

    /**
     * 要进行拦截的时候要执行的方法
     *
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        BoundSql boundSql = statementHandler.getBoundSql();
        ParameterHandler parameterHandler = statementHandler.getParameterHandler();
        Object parameterObject = parameterHandler.getParameterObject();
        log.info("example parameterObject [{}]", JSON.toJSONString(parameterObject));
        String sql = boundSql.getSql();
        log.info("demo 插件打印 sql:{}", sql);
        return invocation.proceed();
    }

    /**
     * 用于封装目标对象的，通过该方法我们可以返回目标对象本身，也可以返回一个它的代理，
     * 可以决定是否要进行拦截进而决定要返回一个什么样的目标对象，官方提供了示例：return Plugin.wrap(target, this);
     *
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        System.out.println("进入example plugin方法");
        return Plugin.wrap(target, this);
    }

    /**
     * 在Mybatis进行配置插件的时候可以配置自定义相关属性，即：接口实现对象的参数配置
     *
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {
        System.out.println("进入example setProperties方法");
        System.out.println(JSON.toJSONString(properties));
    }
}
