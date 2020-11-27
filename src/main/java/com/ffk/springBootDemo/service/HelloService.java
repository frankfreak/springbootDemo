package com.ffk.springBootDemo.service;


import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String hello(int age){
        return "Hello!" + age;
    }
}
