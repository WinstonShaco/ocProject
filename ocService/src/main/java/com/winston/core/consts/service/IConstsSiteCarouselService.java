package com.winston.core.consts.service;

import com.winston.common.page.TailPage;
import com.winston.core.consts.domain.ConstsSiteCarousel;

import java.util.List;


public interface IConstsSiteCarouselService {

	/**
	*根据id获取
	**/
	public ConstsSiteCarousel getById(Long id);

	/**
	*获取所有
	**/
	public List<ConstsSiteCarousel> queryCarousels(Integer count);

	/**
	*分页获取
	**/
	public TailPage<ConstsSiteCarousel> queryPage(ConstsSiteCarousel queryEntity, TailPage<ConstsSiteCarousel> page);

	/**
	*创建
	**/
	public void create(ConstsSiteCarousel entity);
	
	/**
	 * 创建新记录
	 */
	public void createSelectivity(ConstsSiteCarousel entity);

	/**
	*根据id更新
	**/
	public void update(ConstsSiteCarousel entity);

	/**
	*根据id 进行可选性更新
	**/
	public void updateSelectivity(ConstsSiteCarousel entity);

	/**
	*物理删除
	**/
	public void delete(ConstsSiteCarousel entity);

	/**
	*逻辑删除
	**/
	public void deleteLogic(ConstsSiteCarousel entity);



}

