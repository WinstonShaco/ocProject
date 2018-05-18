package com.winston.portal.controller;

import com.winston.common.storage.QiniuStorage;
import com.winston.common.web.SessionContext;
import com.winston.core.auth.domain.AuthUser;
import com.winston.core.auth.service.IAuthUserService;
import com.winston.core.course.domain.Course;
import com.winston.core.course.domain.CourseQueryDto;
import com.winston.core.course.domain.CourseSection;
import com.winston.core.course.service.ICourseSectionService;
import com.winston.core.course.service.ICourseService;
import com.winston.core.user.domain.UserCourseSection;
import com.winston.core.user.service.IUserCourseSectionService;
import com.winston.portal.business.ICourseBusiness;
import com.winston.portal.vo.CourseSectionVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
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

	@Autowired
	private ICourseSectionService iCourseSectionService;

	@Autowired
	private IUserCourseSectionService iUserCourseSectionService;
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

		//当前学习的章节
		UserCourseSection userCourseSection = new UserCourseSection();
		userCourseSection.setCourseId(course.getId());
		userCourseSection.setUserId(SessionContext.getUserId());
		userCourseSection = this.iUserCourseSectionService.queryLatest(userCourseSection);
		if(null != userCourseSection){
			CourseSection curCourseSection = this.iCourseSectionService.getById(userCourseSection.getSectionId());
			mv.addObject("curCourseSection", curCourseSection);
		}
		return mv;
	}

	/**
	 * 视频学习页面
	 * @return
	 */
	@RequestMapping("/video/{sectionId}")
	public ModelAndView video(@PathVariable Long sectionId){
		if(null == sectionId)
			return new ModelAndView("error/404");

		CourseSection courseSection = iCourseSectionService.getById(sectionId);
		if(null == courseSection)
			return new ModelAndView("error/404");

		//课程章节
		ModelAndView mv = new ModelAndView("video");
		List<CourseSectionVO> chaptSections = this.iCourseBusiness.queryCourseSection(courseSection.getCourseId());
		mv.addObject("courseSection", courseSection);
		mv.addObject("chaptSections", chaptSections);

		//学习记录
		UserCourseSection userCourseSection = new UserCourseSection();
		userCourseSection.setUserId(SessionContext.getUserId());
		userCourseSection.setCourseId(courseSection.getCourseId());
		userCourseSection.setSectionId(courseSection.getId());
		UserCourseSection result = iUserCourseSectionService.queryLatest(userCourseSection);

		if(null == result){//如果没有，插入
			userCourseSection.setCreateTime(new Date());
			userCourseSection.setCreateUser(SessionContext.getUsername());
			userCourseSection.setUpdateTime(new Date());
			userCourseSection.setUpdateUser(SessionContext.getUsername());

			iUserCourseSectionService.createSelectivity(userCourseSection);
		}else{
			result.setUpdateTime(new Date());
			iUserCourseSectionService.update(result);
		}
		return mv;
	}
}
