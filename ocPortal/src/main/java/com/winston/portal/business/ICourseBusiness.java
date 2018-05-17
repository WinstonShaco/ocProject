package com.winston.portal.business;

import com.winston.portal.vo.CourseSectionVO;

import java.util.List;

public interface ICourseBusiness {

	/**
	 * 获取课程章节
	 */
	List<CourseSectionVO> queryCourseSection(Long courseId);
	
}
