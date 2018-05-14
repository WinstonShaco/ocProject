package com.winston.rest.business;

import com.winston.common.web.JsonView;
import com.winston.core.course.domain.Course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 于新泽
 * @Date: Created in 10:01 2018/5/9.
 * @site :
 */

public interface ICourseBusiness {

    Map<String,List<Course>> getCourse();
}
