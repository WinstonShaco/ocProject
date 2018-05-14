package com.winston.rest.test;

import com.winston.common.util.JsonUtil;
import com.winston.common.web.SpringBeanFactory;
import com.winston.core.course.domain.Course;
import com.winston.rest.business.ICourseBusiness;
import junit.framework.TestCase;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author: 于新泽
 * @Date: Created in 10:11 2018/5/9.
 * @site :
 */

public class CourseBusinessTest extends TestCase {

    public void testGetCourse(){
        ICourseBusiness icb = (ICourseBusiness)SpringBeanFactory.getBean("ICourseBusinessImpl");

        Map<String,List<Course>> map = icb.getCourse();
        try {
            System.out.println(JsonUtil.toJson(map).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
