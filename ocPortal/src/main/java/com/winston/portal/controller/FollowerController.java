package com.winston.portal.controller;

import com.winston.common.web.JsonView;
import com.winston.common.web.SessionContext;
import com.winston.core.user.domain.UserFollows;
import com.winston.core.user.service.IUserFollowsService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 用户关注
 */
@Controller
@RequestMapping("follow")
public class FollowerController {
	
	@Autowired
	private IUserFollowsService iUserFollowsService;
	
	@RequestMapping(value = "/doFollow")
	@ResponseBody
	public String doFollow(Long followId){
		//获取当前用户
		Long curUserId = SessionContext.getUserId(); 
		UserFollows userFollows = new UserFollows();
		
		userFollows.setUserId(curUserId);
		userFollows.setFollowId(followId);
		List<UserFollows> list = iUserFollowsService.queryAll(userFollows);
		
		if(CollectionUtils.isNotEmpty(list)){
			iUserFollowsService.delete(list.get(0));
			return new JsonView(0).toString();
		}else{
			iUserFollowsService.createSelectivity(userFollows);
			return new JsonView(1).toString();//已经关注
		}
	}
	
	/**
	 * 是否已经关注
	 */
	@RequestMapping(value = "/isFollow")
	@ResponseBody
	public String isFollow(Long followId){
		//获取当前用户
		Long curUserId = SessionContext.getUserId(); 
		UserFollows userFollows = new UserFollows();
		
		userFollows.setUserId(curUserId);
		userFollows.setFollowId(followId);
		List<UserFollows> list = iUserFollowsService.queryAll(userFollows);
		
		if(CollectionUtils.isNotEmpty(list)){//已经关注
			return new JsonView(1).toString();
		}else{
			return new JsonView(0).toString();
		}
	}
	
}


