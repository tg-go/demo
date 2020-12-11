package com.nnynn.mybatis.mapper;

import com.nnynn.mybatis.model.Demo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author bo.luo
 * @date 2020/11/24 11:19
 */
public class MybatisDemo {

    public static void main(String[] args) throws IOException {

        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        System.out.println(inputStream);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DemoMapper demoMapper = sqlSession.getMapper(DemoMapper.class);
        List<Demo> demoList = demoMapper.selectAll();
        demoList.forEach(demo -> System.out.println(demo.toString()));


    }
}
