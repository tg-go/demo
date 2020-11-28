package com.nnynn.demo.service;

import com.nnynn.demo.annotation.MyService;

@MyService
public class DemoService implements IDemoService {
    public String get(String name) {
        return "My Name is " + name;
    }
}
