package com.nnynn.mybatis.mapper;

import com.nnynn.mybatis.model.Demo;

import java.util.List;

/**
 * @author bo.luo
 * @date 2020/11/24 15:52
 */
public interface DemoMapper {

    List<Demo> selectAll();
}
