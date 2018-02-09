package com.lequ.mybatis.spring;

import java.util.Properties;

import org.mybatis.spring.SqlSessionFactoryBean;

 
public class MyBatisSessionFactoryBean extends SqlSessionFactoryBean {

	public void setDiamondProperties(Properties properties) {
		this.setConfigurationProperties(properties);
	}

}
