package com.winston.service;

import java.util.Map;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import com.winston.common.web.SpringBeanFactory;
import com.winston.test.dao.TestDao;

public class AppTest extends TestCase {
	Logger log = Logger.getLogger(AppTest.class);
	
	public void testApp() {
		TestDao testDao = (TestDao) SpringBeanFactory.getBean("testDao");
		Map<String,Object> map = testDao.testQuery();
		log.info("### curDate = " + map.get("curdate"));
	}
	
}
