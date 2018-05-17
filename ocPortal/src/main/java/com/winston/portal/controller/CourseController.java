package com.winston.portal.controller;

import com.winston.common.storage.QiniuStorage;
import com.winston.core.auth.domain.AuthUser;
import com.winston.core.auth.service.IAuthUserService;
import com.winston.core.course.domain.Course;
import com.winston.core.course.domain.CourseQueryDto;
import com.winston.core.course.service.ICourseService;
import com.winston.portal.business.ICourseBusiness;
import com.winston.portal.vo.CourseSectionVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 课程详情信息
 */
@Controller
@RequestMapping("/course")
public class CourseController {
	
	@Autowired
	private ICourseBusiness iCourseBusiness;
	
	@Autowired
	private ICourseService iCourseService;
	
	@Autowired
	private IAuthUserService iAuthUserService;
	
	
	/**
	 * 课程章节页面
	 * @return
	 */
	@RequestMapping("/learn/{courseId}")
	public ModelAndView learn(@PathVariable Long courseId){
		if(null == courseId)
			return new ModelAndView("error/404"); 
		
		//获取课程
		Course course = iCourseService.getById(courseId);
		if(null == course)
			return new ModelAndView("error/404"); 
		
		//获取课程章节
		ModelAndView mv = new ModelAndView("learn");
		List<CourseSectionVO> chaptSections = this.iCourseBusiness.queryCourseSection(courseId);
		mv.addObject("course", course);
		mv.addObject("chaptSections", chaptSections);
		
		//获取讲师
		AuthUser courseTeacher = this.iAuthUserService.getByUsername(course.getUsername());
		if(StringUtils.isNotEmpty(courseTeacher.getHeader())){
			courseTeacher.setHeader(QiniuStorage.getUrl(courseTeacher.getHeader()));
		}
		mv.addObject("courseTeacher", courseTeacher);
		
		//获取推荐课程
		CourseQueryDto queryEntity = new CourseQueryDto();
		queryEntity.descSortField("weight");
		queryEntity.setCount(5);//5门推荐课程
		queryEntity.setSubClassify(course.getSubClassify());
		List<Course> recomdCourseList = this.iCourseService.queryList(queryEntity);
		mv.addObject("recomdCourseList", recomdCourseList);
		
		return mv;
	}
	
}
