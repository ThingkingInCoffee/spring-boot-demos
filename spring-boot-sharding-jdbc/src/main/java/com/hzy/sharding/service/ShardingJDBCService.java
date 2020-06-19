package com.hzy.sharding.service;

import java.sql.SQLException;

public interface ShardingJDBCService {

    void testInit() throws SQLException;

    void testInsert() throws SQLException;

    void testQuery() throws SQLException;

}
