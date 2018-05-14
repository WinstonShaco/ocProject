package com.winston.rest.business;

import com.winston.common.storage.QiniuStorage;
import org.apache.commons.lang.StringUtils;
import com.winston.common.web.JsonView;
import com.winston.core.consts.CourseEnum;
import com.winston.core.course.domain.Course;
import com.winston.core.course.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 于新泽
 * @Date: Created in 10:01 2018/5/9.
 * @site :
 */

@Component
public class ICourseBusinessImpl implements ICourseBusiness {

    @Autowired
    private ICourseService iCourseService;

    @Override
    public Map<String, List<Course>> getCourse() {
        Map<String,List<Course>> returnMap = new HashMap<String,List<Course>>();

        Course queryEntity = new Course();


        //免费好课
        queryEntity.setFree(CourseEnum.FREE.value());
        List<Course> freeCourse = iCourseService.getFiveCourses(queryEntity);
        for(Course item : freeCourse){
            if(StringUtils.isNotEmpty(item.getPicture())){
                item.setPicture(QiniuStorage.getUrl(item.getPicture()));
            }
        }
        returnMap.put("freeCourse",iCourseService.getFiveCourses(queryEntity));
        //实战推荐

        queryEntity.setFree(CourseEnum.FREE_NOT.value());
        returnMap.put("actionCourse",iCourseService.getFiveCourses(queryEntity));
        return returnMap;
    }

}
