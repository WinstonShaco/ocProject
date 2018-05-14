package com.winston.rest.business;

import com.winston.rest.dto.ClassifyDto;

import java.util.Map;

/**
 * @Author: 于新泽
 * @Date: Created in 19:36 2018/5/8.
 * @site :
 */

public interface IClassifyBusiness  {

    Map<String,ClassifyDto> getAllClassity();
}
