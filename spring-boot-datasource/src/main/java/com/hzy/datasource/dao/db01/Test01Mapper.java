package com.hzy.datasource.dao.db01;

import com.hzy.datasource.bean.bo.db01.Test01;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface Test01Mapper {

    List<Test01> selectAll();

    List<Test01> select01();
}
