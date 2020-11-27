package com.nnynn.mybatis.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author bo.luo
 * @date 2020/11/24 11:19
 */
public class MybatisDemo {

    public static void main(String[] args) throws IOException {

        InputStream inputStream = Resources.getResourceAsStream("");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DemoMapper demoMapper = sqlSession.getMapper(DemoMapper.class);


    }
}
