package com.winston.rest.test;

import com.winston.common.util.JsonUtil;
import com.winston.common.web.SpringBeanFactory;
import com.winston.rest.business.IClassifyBusiness;
import com.winston.rest.dto.ClassifyDto;
import junit.framework.TestCase;

import java.io.IOException;
import java.util.Map;

/**
 * @Author: 于新泽
 * @Date: Created in 20:04 2018/5/8.
 * @site :
 */

public class ClassifyBusinessTest extends TestCase{

    public void testGetClassify(){
        IClassifyBusiness bis = (IClassifyBusiness)SpringBeanFactory.getBean("IClassifyBusinessImpl");
        Map<String,ClassifyDto> map = bis.getAllClassity();
        try {
            System.out.println(JsonUtil.toJson(map).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
