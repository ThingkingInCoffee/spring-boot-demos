package com.hzy.datasource.service.impl;

import com.alibaba.fastjson.JSON;
import com.hzy.datasource.bean.bo.db01.Test01;
import com.hzy.datasource.bean.bo.db02.Test02;
import com.hzy.datasource.dao.db01.Test01Mapper;
import com.hzy.datasource.dao.db02.Test02Mapper;
import com.hzy.datasource.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class TestServiceImpl implements TestService {

    @Autowired
    private Test01Mapper test01Mapper;
    @Autowired
    private Test02Mapper test02Mapper;

    @Override
    public void queryData() {
        long start = System.currentTimeMillis();
        List<Test01> list01 = test01Mapper.selectAll();
        List<Test02> list02 = test02Mapper.selectAll();
        long end = System.currentTimeMillis();
        log.info("list 01 is {}", JSON.toJSONString(list01));
        log.info("list 02 is {}", JSON.toJSONString(list02));
        log.info("time is {}", end - start);
    }
}
