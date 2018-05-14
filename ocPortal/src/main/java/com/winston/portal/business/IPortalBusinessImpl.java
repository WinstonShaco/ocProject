package com.winston.portal.business;

import com.winston.core.consts.domain.ConstsClassify;
import com.winston.core.consts.service.IConstsClassifyService;
import com.winston.core.course.domain.Course;
import com.winston.core.course.domain.CourseQueryDto;
import com.winston.core.course.service.ICourseService;
import com.winston.portal.vo.ConstsClassifyVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 于新泽
 * @Date: Created in 19:55 2018/5/10.
 * @site :
 */
@Service
public class IPortalBusinessImpl implements IPortalBusiness{

    @Autowired
    private IConstsClassifyService iConstsClassifyService;

    @Autowired
    private ICourseService iCourseService;

    /**
     * 获取所有，其中包括一级分类和二级分类
     */
    @Override
    public List<ConstsClassifyVO> queryAllClassify() {
        List<ConstsClassifyVO> resultList = new ArrayList<ConstsClassifyVO>();
        for(ConstsClassifyVO vo : this.queryAllClassifyMap().values()){
            resultList.add(vo);
        }
        return resultList;
    }

    /**
     * 获取所有分类
     */
    @Override
    public Map<String, ConstsClassifyVO> queryAllClassifyMap() {
        Map<String, ConstsClassifyVO> resultMap = new HashMap<String,ConstsClassifyVO>();
        //首先查询出所有分类
        List<ConstsClassify> list = iConstsClassifyService.queryAll();
        //遍历器对上面查询出的分类进行遍历
        Iterator<ConstsClassify> it = list.iterator();

        while (it.hasNext()){
            ConstsClassify cc = it.next();
            //根据数据库中的数据来判断 是0就是一级分类
            if("0".equals(cc.getParentCode())){
                ConstsClassifyVO vo = new ConstsClassifyVO();
                BeanUtils.copyProperties(cc,vo);
                resultMap.put(vo.getCode(),vo);
            }else{//二级分类
                if(null != resultMap.get(cc.getParentCode())){
                    //添加到子分类中
                    resultMap.get(cc.getParentCode()).getSubClassifyList().add(cc);
                }
            }
        }
        return resultMap;
    }

    /**
     * 为分类设置课程推荐
     */
    @Override
    public void prepareRecomdCourse(List<ConstsClassifyVO> classifyVoList) {
        if(CollectionUtils.isNotEmpty(classifyVoList)){
            for(ConstsClassifyVO item : classifyVoList){
                CourseQueryDto queryEntity = new CourseQueryDto();
                //查5个
                queryEntity.setCount(5);
                //按着降序排列
                queryEntity.descSortField("weight");
                queryEntity.setClassify(item.getCode());//分类code

                List<Course> tmpList = this.iCourseService.queryList(queryEntity);
                //推荐列表
                if(CollectionUtils.isNotEmpty(tmpList)){
                    item.setRecomdCourseList(tmpList);
                }
            }
        }
    }
}
