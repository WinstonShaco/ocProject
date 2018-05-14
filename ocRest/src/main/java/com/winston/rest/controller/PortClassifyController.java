package com.winston.rest.controller;

import com.winston.common.web.JsonView;
import com.winston.rest.business.IClassifyBusiness;
import com.winston.rest.dto.ClassifyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: 于新泽
 * @Date: Created in 15:56 2018/5/8.
 * @site :
 */

@Controller
public class PortClassifyController {

    @Autowired
    private IClassifyBusiness iClassifyBusiness;

    @RequestMapping("/getClassifyJson")
    @ResponseBody
    public String getClassifyJson(HttpServletRequest request){
        try{
            Map<String,ClassifyDto> map = iClassifyBusiness.getAllClassity();
            //要返回一个list 首先就要定义一个list
            List<ClassifyDto> list = new ArrayList<ClassifyDto>();
            //对map 进行遍历
            for(String key : map.keySet()){
                list.add(map.get(key));
            }
            String resultStr = JsonView.render(list);
            //跨域访问的，客户端js必须是jsonp
            //return request.getParamter("callback")+"("+ resultStr +")";
            //不是跨域的，正常ajax请求
            return resultStr;
        }catch (Exception e){
            return JsonView.render(17000);//代表什么错误
        }



    }
}
