package com.hzy.sharding.service.impl;

import com.hzy.sharding.bean.shardingdb.Order;
import com.hzy.sharding.dao.shardingdb.OrderItemRepository;
import com.hzy.sharding.dao.shardingdb.OrderRepository;
import com.hzy.sharding.service.ShardingJDBCService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@Service
public class ShardingDBServiceImpl implements ShardingJDBCService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public void testInit() throws SQLException {
        orderRepository.dropTable();
        log.info("dropTable -- order");
        orderRepository.createTableIfNotExists();
        log.info("createTableIfNotExists -- order");

        orderItemRepository.dropTable();
        log.info("dropTable -- order_item");
        orderItemRepository.createTableIfNotExists();
        log.info("createTableIfNotExists -- order_item");
    }

    @Override
    public void testInsert() {

        for (int i = 0; i < 10; i++) {
            Order order = new Order();
            order.setUserId(i);
            order.setStatus("sta_"+i);
            try {
                orderRepository.insert(order);
                log.info("insert into db {}", i % 2);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void testQuery() throws SQLException {
        List<Order> list = orderRepository.selectAll();
        log.info("list size is {}", list.size());
    }
}
