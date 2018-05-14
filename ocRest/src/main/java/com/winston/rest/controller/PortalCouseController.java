package com.winston.rest.controller;

import com.winston.common.web.JsonView;
import com.winston.core.course.domain.Course;
import com.winston.rest.business.ICourseBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 于新泽
 * @Date: Created in 9:19 2018/5/9.
 * @site :
 */

@Controller
@RequestMapping("/course")
public class PortalCouseController {

    @Autowired
    private ICourseBusiness iCourseBusiness;

    //实战推荐 免费好课
    @RequestMapping("/getCourse")
    @ResponseBody
    public String getCourse(){

        try{
            return JsonView.render(iCourseBusiness.getCourse());
        }catch(Exception e){
            return JsonView.render(17000);
        }



    }
}
