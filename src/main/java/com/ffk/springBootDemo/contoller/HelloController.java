package com.ffk.springBootDemo.contoller;

import com.ffk.springBootDemo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class HelloController {

    @Autowired
    HelloService helloService;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam int age){
        return helloService.hello(age);
    }

    @RequestMapping("/hi")
    @ResponseBody
    public String hello(){
        return "hello";
    }

}
