package com.winston.portal.controller;

import com.winston.common.page.TailPage;
import com.winston.core.consts.CourseEnum;
import com.winston.core.consts.domain.ConstsClassify;
import com.winston.core.consts.service.IConstsClassifyService;
import com.winston.core.course.domain.Course;
import com.winston.core.course.service.ICourseService;
import com.winston.portal.business.IPortalBusiness;
import com.winston.portal.vo.ConstsClassifyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: 于新泽
 * @Date: Created in 12:43 2018/5/15.
 * @site :
 * @Note : 课程分类页
 */

@Controller
@RequestMapping("/course")
public class CourseListController {

    @Autowired
    private IConstsClassifyService iConstsClassifyService;

    @Autowired
    private IPortalBusiness iPortalBusiness;

    @Autowired
    private ICourseService iCourseService;
    /**
     * 课程分类页
     * @param c 分类code
     * @param sort 排序
     * @param page 分页
     */
    @RequestMapping("/list")
    public ModelAndView list(String c, String sort, TailPage<Course> page){
        ModelAndView mv = new ModelAndView("list");
        String curCode = "-1";//当前方向code
        String curSubCode = "-2";//当前分类code

        //加载所有课程分类
        Map<String,ConstsClassifyVO> classifyMap = iPortalBusiness.queryAllClassifyMap();
        //所有一级分类
        List<ConstsClassifyVO> classifysList = new ArrayList<ConstsClassifyVO>();
        for(ConstsClassifyVO vo : classifyMap.values()){
            classifysList.add(vo);
        }
        mv.addObject("classifys", classifysList);

        //当前分类
        ConstsClassify curClassify = iConstsClassifyService.getByCode(c);

        if(null == curClassify){//没有此分类，加载所有二级分类
            List<ConstsClassify> subClassifys = new ArrayList<ConstsClassify>();
            for(ConstsClassifyVO vo : classifyMap.values()){
                subClassifys.addAll(vo.getSubClassifyList());
            }
            mv.addObject("subClassifys", subClassifys);
        }else{
            if(!"0".endsWith(curClassify.getParentCode())){//当前是二级分类
                curSubCode = curClassify.getCode();
                curCode = curClassify.getParentCode();
                mv.addObject("subClassifys", classifyMap.get(curClassify.getParentCode()).getSubClassifyList());//此分类平级的二级分类
            }else{//当前是一级分类
                curCode = curClassify.getCode();
                mv.addObject("subClassifys", classifyMap.get(curClassify.getCode()).getSubClassifyList());//此分类下的二级分类
            }
        }
        mv.addObject("curCode", curCode);
        mv.addObject("curSubCode", curSubCode);

        //分页排序数据
        //分页的分类参数
        Course queryEntity = new Course();
        if(!"-1".equals(curCode)){
            queryEntity.setClassify(curCode);
        }
        if(!"-2".equals(curSubCode)){
            queryEntity.setSubClassify(curSubCode);
        }

        //排序参数
        if("pop".equals(sort)){//最热
            page.descSortField("studyCount");
        }else{
            sort = "last";
            page.descSortField("id");
        }
        mv.addObject("sort", sort);

        //分页参数
        queryEntity.setOnsale(CourseEnum.ONSALE.value());
        page = this.iCourseService.queryPage(queryEntity, page);
        mv.addObject("page", page);

        return mv;
    }
}
