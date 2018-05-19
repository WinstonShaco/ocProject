package com.winston.portal.controller;

import com.winston.common.web.JsonView;
import com.winston.common.web.SessionContext;
import com.winston.core.consts.CourseEnum;
import com.winston.core.user.domain.UserCollections;
import com.winston.core.user.service.IUserCollectionsService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 用户收藏
 */
@Controller
@RequestMapping("/collections")
public class CollectionsController{

	@Autowired
	private IUserCollectionsService iUserCollectionsService;

	@RequestMapping(value = "/doCollection")
	@ResponseBody
	public String doCollection(Long courseId){
		//获取当前用户
		Long curUserId = SessionContext.getUserId(); 
		UserCollections userCollections = new UserCollections();
		
		userCollections.setUserId(curUserId);
		userCollections.setClassify(CourseEnum.COLLECTION_CLASSIFY_COURSE.value());//课程收藏
		userCollections.setObjectId(courseId);
		List<UserCollections> list = iUserCollectionsService.queryAll(userCollections);
		
		if(CollectionUtils.isNotEmpty(list)){
			iUserCollectionsService.delete(list.get(0));
			return new JsonView(0).toString();
		}else{
			iUserCollectionsService.createSelectivity(userCollections);
			return new JsonView(1).toString();//已经收藏
		}
	}
	
	/**
	 * 是否已经收藏
	 * @param courseId
	 * @return
	 */
	@RequestMapping(value = "/isCollection")
	@ResponseBody
	public String isCollection(Long courseId){
		//获取当前用户
		Long curUserId = SessionContext.getUserId(); 
		UserCollections userCollections = new UserCollections();
		
		userCollections.setUserId(curUserId);
		userCollections.setClassify(CourseEnum.COLLECTION_CLASSIFY_COURSE.value());//课程收藏
		userCollections.setObjectId(courseId);
		List<UserCollections> list = iUserCollectionsService.queryAll(userCollections);
		
		if(CollectionUtils.isNotEmpty(list)){//已经收藏
			return new JsonView(1).toString();
		}else{
			return new JsonView(0).toString();
		}
	}
	
}
