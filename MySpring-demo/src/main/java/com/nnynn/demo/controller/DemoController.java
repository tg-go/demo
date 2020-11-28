package com.nnynn.demo.controller;

import com.nnynn.demo.annotation.MyAutowired;
import com.nnynn.demo.annotation.MyController;
import com.nnynn.demo.annotation.MyRequestMapping;
import com.nnynn.demo.annotation.MyRequestParam;
import com.nnynn.demo.service.IDemoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MyController
@MyRequestMapping("/demo")
public class DemoController {

    @MyAutowired
    private IDemoService demoService;

    @MyRequestMapping("/query")
    public void query(HttpServletRequest request, HttpServletResponse response, @MyRequestParam("name") String name) {
        String result = demoService.get(name);
        try {
            response.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @MyRequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response,
                    @MyRequestParam("a") Integer a, @MyRequestParam("b") Integer b) {
        try {
            response.getWriter().write(a + "+" + b + "=" + (a + b));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @MyRequestMapping("/remove")
    public void remove(HttpServletRequest request, HttpServletResponse response, @MyRequestParam("id") Integer id) {

    }


}
