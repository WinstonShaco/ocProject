package com.winston.portal.business;

import com.winston.portal.vo.ConstsClassifyVO;

import java.util.List;
import java.util.Map;

/**
 * @Author: 于新泽
 * @Date: Created in 19:14 2018/5/10.
 * @site :
 */

public interface IPortalBusiness {

    /**
     * 获取所有，其中包括一级分类和二级分类
     */
    List<ConstsClassifyVO> queryAllClassify();

    /**
     * 获取所有分类
     */
    Map<String,ConstsClassifyVO> queryAllClassifyMap();

    /**
     * 为分类设置课程推荐
     */
    void prepareRecomdCourse(List<ConstsClassifyVO> classifyVOList);
}
