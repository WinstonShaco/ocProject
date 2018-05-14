package com.winston.rest.business;

import com.winston.rest.business.IClassifyBusiness;
import com.winston.core.consts.domain.ConstsClassify;
import com.winston.core.consts.service.IConstsClassifyService;
import com.winston.rest.dto.ClassifyDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Author: 于新泽
 * @Date: Created in 19:36 2018/5/8.
 * @site :
 * @note : 课程分类业务块
 */
@Component
public class IClassifyBusinessImpl implements IClassifyBusiness {

    @Autowired
    private IConstsClassifyService constsClassifyService;

    @Override
    public Map<String, ClassifyDto> getAllClassity() {

        Map<String, ClassifyDto> returnMap = new HashMap<String,ClassifyDto>();
        //首先查询出所有的分类
        List<ConstsClassify> list = constsClassifyService.queryAll();
        //遍历器对上面查询出的分类进行遍历
        Iterator<ConstsClassify> it = list.iterator();

        while (it.hasNext()){
            ConstsClassify item = it.next();
            if("0".equals(item.getParentCode())){//根据数据库中的数据来判断 是0就是一级分类
                //ClassifyDto 是继承自 ConstsClassify
                ClassifyDto dto = new ClassifyDto();
                //这里面把item 中的值拷贝到dto
                BeanUtils.copyProperties(item, dto);
                returnMap.put(item.getCode(),dto);
            }else{//二级分类
                if(null != returnMap.get(item.getParentCode())){
                    returnMap.get(item.getParentCode()).getSubClassify().add(item);
                }
            }
        }
        return returnMap;
    }
}
