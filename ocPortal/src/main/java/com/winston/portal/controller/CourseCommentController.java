package com.winston.portal.controller;

import com.winston.common.page.TailPage;
import com.winston.core.course.domain.CourseComment;
import com.winston.core.course.service.ICourseCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 课程评论管理
 */
@Controller
@RequestMapping("/courseComment")
public class CourseCommentController {
	
	@Autowired
	private ICourseCommentService iCourseCommentService;
	
	/**
	 * 加载评论&答疑
	 * sectionId：章节id
	 * courseId ：课程id
	 * type : 类型
	 * @return
	 */
	@RequestMapping("/segment")
	public ModelAndView segment(CourseComment queryEntity , TailPage<CourseComment> page){
		if(null == queryEntity.getCourseId() || queryEntity.getType() == null)
			return new ModelAndView("error/404"); 
		
		ModelAndView mv = new ModelAndView("commentSegment");
		TailPage<CourseComment> commentPage = this.iCourseCommentService.queryPage(queryEntity, page);
		mv.addObject("page", commentPage);
		return mv;
	}
	
}

