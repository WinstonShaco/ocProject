package com.winston.rest.test;

import com.winston.common.util.JsonUtil;
import com.winston.common.web.SpringBeanFactory;
import com.winston.rest.business.ICourseSectionBusiness;
import com.winston.rest.dto.CourseSectionDto;
import junit.framework.TestCase;

import java.io.IOException;
import java.util.Map;

/**
 * @Author: 于新泽
 * @Date: Created in 15:52 2018/5/9.
 * @site :
 */

public class CourseSectionBusinessTest extends TestCase {

    public void testGetCourseSections(){

        ICourseSectionBusiness icsb = (ICourseSectionBusiness)SpringBeanFactory.getBean("ICourseSectionBusinessImpl");
        Map<Long,CourseSectionDto> map = icsb.getAllCourseSections(1l);

        try{
            System.out.println(JsonUtil.toJson(map).toString());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
