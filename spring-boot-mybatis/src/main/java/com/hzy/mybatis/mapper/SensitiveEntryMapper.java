package com.hzy.mybatis.mapper;

import com.hzy.mybatis.entry.SensitiveEntry;

public interface SensitiveEntryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SensitiveEntry record);

    int insertSelective(SensitiveEntry record);

    SensitiveEntry selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SensitiveEntry record);

    int updateByPrimaryKey(SensitiveEntry record);
}