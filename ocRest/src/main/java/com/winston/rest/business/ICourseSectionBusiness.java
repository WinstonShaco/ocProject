package com.winston.rest.business;

import com.winston.rest.dto.CourseSectionDto;

import java.util.Map;

/**
 * @Author: 于新泽
 * @Date: Created in 14:57 2018/5/9.
 * @site :
 * @note : 课程章节
 */

public interface ICourseSectionBusiness {
    /**
     * 获取某个课程的章节数据
     * @param courseId
     * @return
     */

    Map<Long,CourseSectionDto> getAllCourseSections(Long courseId);

}
