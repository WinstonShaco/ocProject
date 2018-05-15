package com.winston.portal.controller;

import com.winston.core.auth.domain.AuthUser;
import com.winston.core.auth.service.IAuthUserService;
import com.winston.core.consts.CourseEnum;
import com.winston.core.consts.domain.ConstsSiteCarousel;
import com.winston.core.consts.service.IConstsSiteCarouselService;
import com.winston.core.course.domain.Course;
import com.winston.core.course.domain.CourseQueryDto;
import com.winston.core.course.service.ICourseService;
import com.winston.portal.business.IPortalBusiness;
import com.winston.portal.vo.ConstsClassifyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.Oneway;
import javax.xml.ws.soap.Addressing;
import java.util.List;

/**
 * @Author: 于新泽
 * @Date: Created in 10:46 2018/5/6.
 * @site :
 * @note : 网站首页
 */
@Controller
@RequestMapping()
public class PortalController {

    @Autowired
    private IConstsSiteCarouselService siteCarouselService;

    @Autowired
    private IPortalBusiness iPortalBusiness;

    @Autowired
    private ICourseService icourseService;

    @Autowired
    private IAuthUserService iAuthUserService;

    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("index");

        //加载轮播
        List<ConstsSiteCarousel> carouselList = siteCarouselService.queryCarousels(4);
        mv.addObject("carouselList",carouselList);

        //课程分类(课程分类)
        List<ConstsClassifyVO> classifys = iPortalBusiness.queryAllClassify();

        //课程推荐
        iPortalBusiness.prepareRecomdCourse(classifys);
        mv.addObject("classifys",classifys);

        //获取5门实战课推荐，根据权重（weight）进行排序
        CourseQueryDto queryEntity = new CourseQueryDto();
        queryEntity.setCount(5);//5门
        queryEntity.setFree(CourseEnum.FREE_NOT.value());//非免费的：实战课
        queryEntity.descSortField("weight");//按照weight降序排列
        List<Course> actionCourseList = this.icourseService.queryList(queryEntity);
        mv.addObject("actionCourseList", actionCourseList);

        //获取5门免费课推荐，根据权重（weight）进行排序
        queryEntity.setFree(CourseEnum.FREE.value());//免费
        List<Course> freeCourseList = this.icourseService.queryList(queryEntity);
        mv.addObject("freeCourseList", freeCourseList);

        //获取7门java课程，根据权重（学习数量studyCount）进行排序
        queryEntity.setCount(7);
        queryEntity.setFree(null);//不分实战和免费类别
        queryEntity.setSubClassify("java");//java分类
        queryEntity.descSortField("studyCount");//按照studyCount降序排列
        List<Course> javaCourseList = this.icourseService.queryList(queryEntity);
        mv.addObject("javaCourseList", javaCourseList);

        //加载讲师
        List<AuthUser> recomdTeacherList = iAuthUserService.queryRecomd();
        mv.addObject("recomdTeacherList", recomdTeacherList);

        return mv;
    }
}
