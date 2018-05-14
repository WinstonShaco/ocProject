package com.winston.rest.dto;

import com.winston.core.consts.domain.ConstsClassify;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 于新泽
 * @Date: Created in 19:29 2018/5/8.
 * @site :
 */

public class ClassifyDto extends ConstsClassify {

    //二级分类
    private List<ConstsClassify> subClassify = new ArrayList<ConstsClassify>();

    public List<ConstsClassify> getSubClassify() {
        return subClassify;
    }

    public void setSubClassify(List<ConstsClassify> subClassify) {
        this.subClassify = subClassify;
    }
}
