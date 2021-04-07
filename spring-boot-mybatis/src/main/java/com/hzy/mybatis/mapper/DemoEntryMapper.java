package com.hzy.mybatis.mapper;

import com.hzy.mybatis.entry.DemoEntry;

public interface DemoEntryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DemoEntry record);

    int insertSelective(DemoEntry record);

    DemoEntry selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DemoEntry record);

    int updateByPrimaryKey(DemoEntry record);
}