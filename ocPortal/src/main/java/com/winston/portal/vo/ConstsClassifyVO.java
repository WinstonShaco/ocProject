package com.winston.portal.vo;

import com.winston.core.consts.domain.ConstsClassify;
import com.winston.core.course.domain.Course;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 于新泽
 * @Date: Created in 19:06 2018/5/10.
 * @site :
 */

public class ConstsClassifyVO extends ConstsClassify {

    //子分类列表
    private List<ConstsClassify> subClassifyList = new ArrayList<ConstsClassify>();

    //课程推荐列表
    private List<Course> recomdCourseList;

    public List<ConstsClassify> getSubClassifyList() {
        return subClassifyList;
    }

    public void setSubClassifyList(List<ConstsClassify> subClassifyList) {
        this.subClassifyList = subClassifyList;
    }

    public List<Course> getRecomdCourseList() {
        return recomdCourseList;
    }

    public void setRecomdCourseList(List<Course> recomdCourseList) {
        this.recomdCourseList = recomdCourseList;
    }
}
