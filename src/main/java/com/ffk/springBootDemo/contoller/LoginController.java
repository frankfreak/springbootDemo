package com.ffk.springBootDemo.contoller;


import com.ffk.springBootDemo.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private LoginService loginService;

    @RequestMapping(value={"/", "/index"})
    public String index(Map<String, Object> map){
        map.put("msg", "请登录！");
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        Map<String, Object> map){
        if(null == email || email.length() == 0){
            map.put("msg","邮箱不能为空！！");
            return "login";
        }
        if(loginService.loginByEmail(email, password)){
            return "success";
        }
        map.put("msg","密码不正确！");
        return "login";
    }

    @RequestMapping("/register")
    public String register(Map<String, Object> map){
        return "register";
    }


}
