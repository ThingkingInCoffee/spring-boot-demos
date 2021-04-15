package com.hzy.mybatis.mapper;

import com.hzy.mybatis.entry.DemoEntry;
import com.hzy.mybatis.entry.SensitiveEntry;

public interface DemoEntryMapper {
    // @Select 需要在使用注解方式时开启，xml方式时关闭
//    @Select("SELECT * FROM demo_entry WHERE id = #{id}")
    DemoEntry selectByPrimaryKey(Integer id);

    DemoEntry selectDemo(Integer id);

    int insert(DemoEntry demoEntry);

    int insertDemo(DemoEntry demoEntry);

    int updateDemo(String id, String username);

    int deleteDemo();
}