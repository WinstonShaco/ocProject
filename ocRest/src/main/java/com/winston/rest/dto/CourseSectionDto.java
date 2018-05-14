package com.winston.rest.dto;

import com.winston.core.course.domain.CourseSection;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 于新泽
 * @Date: Created in 15:01 2018/5/9.
 * @site :
 */

public class CourseSectionDto extends CourseSection{

    //小节的列表
    private List<CourseSection> sectionList = new ArrayList<CourseSection>();

    public List<CourseSection> getSectionList() {
        return sectionList;
    }

    public void setSectionList(List<CourseSection> sectionList) {
        this.sectionList = sectionList;
    }
}
