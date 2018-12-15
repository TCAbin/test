package cn.com.gree.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sso")
public class LoginController {


    @RequestMapping("login")
    public String login(){
        return "login-four";
    }

}
