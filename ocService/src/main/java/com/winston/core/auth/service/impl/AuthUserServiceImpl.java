package com.winston.core.auth.service.impl;

import com.winston.common.page.TailPage;
import com.winston.core.auth.dao.AuthUserDao;
import com.winston.core.auth.domain.AuthUser;
import com.winston.core.auth.service.IAuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AuthUserServiceImpl implements IAuthUserService{


	@Autowired
	private AuthUserDao entityDao;

	public void createSelectivity(AuthUser entity){
		entityDao.createSelectivity(entity);
	}

	/**
	*根据username获取
	**/
	public AuthUser getByUsername(String username){
		return entityDao.getByUsername(username);
	}



	public AuthUser getById(Long id){
		return entityDao.getById(id);
	}

	/**
	*根据username和password获取
	**/
	public AuthUser getByUsernameAndPassword(AuthUser authUser){
		return entityDao.getByUsernameAndPassword(authUser);
	}

	/**
	*获取首页推荐5个讲师
	**/
	public List<AuthUser> queryRecomd(){
		return entityDao.queryRecomd();
	}

	public TailPage<AuthUser> queryPage(AuthUser queryEntity ,TailPage<AuthUser> page){
		//首先获得总条数
	    Integer itemsTotalCount = entityDao.getTotalItemsCount(queryEntity);
		List<AuthUser> items = entityDao.queryPage(queryEntity,page);
		page.setItemsTotalCount(itemsTotalCount);
		page.setItems(items);
		return page;
	}

	

	public void update(AuthUser entity){
		entityDao.update(entity);
	}

	public void updateSelectivity(AuthUser entity){
		entityDao.updateSelectivity(entity);
	}

	public void delete(AuthUser entity){
		entityDao.delete(entity);
	}

	public void deleteLogic(AuthUser entity){
		entityDao.deleteLogic(entity);
	}



}

