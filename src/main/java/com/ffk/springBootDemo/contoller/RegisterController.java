package com.ffk.springBootDemo.contoller;

import com.ffk.springBootDemo.domain.User;
import com.ffk.springBootDemo.service.RegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class RegisterController {

    private static Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    RegisterService registerService;
    @RequestMapping("/user/register")
    public String newUserRegister(@RequestParam("userName")String userName,
                                  @RequestParam("email") String email,
                                  @RequestParam("password") String password,
                                  @RequestParam("passwordRenter")String passwordRenter,
                                  @RequestParam(value = "terms", required = false) String terms,
                                  Map<String, Object> map){

        if(null == terms) {
            map.put("msg", "请阅读读并同意使用条款！");
            return "register";
        }
        if(null == userName || userName.length() == 0){
            map.put("msg", "用户名不能为空！");
            return "register";
        }
        if(null == email || email.length() == 0){
            map.put("msg", "邮箱地址不能为空！");
            return "register";
        }
        if(null == password || password.length() == 0){
            map.put("msg", "密码不能为空！");
            return "register";
        }
        if(!password.equals(passwordRenter)){
            map.put("msg", "重新输入密码不匹配！");
            return "register";
        }
        if(!registerService.checkUserName(userName)){
            map.put("msg", "该用户名已存在！");
            return "register";
        }
        if(!registerService.checkUserEmail(email)){
            map.put("msg", "该邮箱已存在！");
            return "register";
        }
        User newUser = new User();
        newUser.setUserName(userName);
        newUser.setPassword(password);
        newUser.setEmail(email);
        if(registerService.registerUser(newUser));
        return "success";
    }
}
