package com.hzy.datasource.dao.db02;

import com.hzy.datasource.bean.bo.db02.Test02;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface Test02Mapper {

    List<Test02> selectAll();

}
