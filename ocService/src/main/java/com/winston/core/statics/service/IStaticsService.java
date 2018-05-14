package com.winston.core.statics.service;

import com.winston.core.statics.domain.CourseStudyStaticsDto;
import com.winston.core.statics.domain.StaticsVO;

/**
 * 报表统计
 */
public interface IStaticsService {
	/**
	*统计课程学习情况
	**/
	public StaticsVO queryCourseStudyStatistics(CourseStudyStaticsDto queryEntity);
	
}
