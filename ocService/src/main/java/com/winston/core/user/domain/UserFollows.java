package com.winston.core.user.domain;

import com.winston.common.orm.BaseEntity;

/**
 * 用户关注
 */
public class UserFollows extends BaseEntity{

	private static final long serialVersionUID = -275116124638094439L;
	
	/**
	*用户id
	**/
	private Long userId;

	/**
	*关注的用户id
	**/
	private Long followId;

	public Long getUserId(){
		return userId;
	}
	public void setUserId(Long userId){
		this.userId = userId;
	}

	public Long getFollowId(){
		return followId;
	}
	public void setFollowId(Long followId){
		this.followId = followId;
	}



}

