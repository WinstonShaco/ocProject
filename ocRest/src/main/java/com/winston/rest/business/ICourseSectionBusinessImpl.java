package com.winston.rest.business;

import com.winston.core.course.domain.CourseSection;
import com.winston.core.course.service.ICourseSectionService;
import com.winston.rest.dto.CourseSectionDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 于新泽
 * @Date: Created in 15:14 2018/5/9.
 * @site :
 */
@Component
public class ICourseSectionBusinessImpl implements ICourseSectionBusiness{

    @Autowired
    private ICourseSectionService iCourseSectionService;

    public Map<Long, CourseSectionDto> getAllCourseSections(Long courseId) {
        CourseSection queryEntity = new CourseSection();
        queryEntity.setCourseId(courseId);
        List<CourseSection> list = iCourseSectionService.queryAll(queryEntity);

        Map<Long,CourseSectionDto> returnMap = new HashMap<Long,CourseSectionDto>();
        for(CourseSection item : list){
            if(Long.valueOf(0).equals(item.getParentId())){
                CourseSectionDto dto = new CourseSectionDto();
                BeanUtils.copyProperties(item, dto);
                returnMap.put(dto.getId(), dto);//章的信息放到map中
            }else{
                //小节
                returnMap.get(item.getParentId()).getSectionList().add(item);
            }
        }

        return returnMap;
    }

//    @Override
//    public Map<Long, CourseSectionDto> getAllCourseSections(Long courseId) {
//
//        CourseSection queryEntity = new CourseSection();
//        queryEntity.setCourseId(courseId);
//        List<CourseSection> list = iCourseSectionService.queryAll(queryEntity);
//
//        Map<Long,CourseSectionDto> returnMap = new HashMap<Long,CourseSectionDto>();
//        for(CourseSection item : list){
//            if(Long.valueOf(0).equals(item.getParentId())){
//                CourseSectionDto dto = new CourseSectionDto();
//                BeanUtils.copyProperties(item,dto);
//                //把章的信息放到map中
//                returnMap.put(dto.getId(),dto);
//            }else{//不为0 就是章中的小节
//                returnMap.get(item.getParentId()).getSectionList().add(item);
//            }
//        }
//        return returnMap;
//    }
}
