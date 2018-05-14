package com.winston.portal.controller;

import com.winston.common.web.JsonView;
import com.winston.core.auth.domain.AuthUser;
import com.winston.core.auth.service.IAuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: 于新泽
 * @Date: Created in 10:21 2018/5/7.
 * @site :
 * @note : 登陆 & 注册
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IAuthUserService authUserService;

    //注册页面
    @RequestMapping("/register")
    public ModelAndView register(){
        return new ModelAndView("auth/register");
    }

    //实现注册
    @RequestMapping("/doRegister")
    public String doRegister(AuthUser authUser){

        //首先查找 看有没有这个用户名
        AuthUser tmpUser = authUserService.getByUsername(authUser.getUsername());

        //返回的是1 有这个用户名
        //返回的是0 写进数据库
        if(tmpUser != null){
            return JsonView.render(1);
        }else{
            authUserService.createSelectivity(authUser);
            return JsonView.render(0);
        }
    }
}
