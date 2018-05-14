package com.winston.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: 于新泽
 * @Date: Created in 14:02 2018/5/8.
 * @site :
 */

@Controller
@RequestMapping()
public class RestPortalController {

    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("index");
    }
}
