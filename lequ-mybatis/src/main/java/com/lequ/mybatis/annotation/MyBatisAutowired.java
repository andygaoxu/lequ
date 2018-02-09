package com.lequ.mybatis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.lequ.mybatis.common.DBContext;



/**
 * 用于为service/dao注入mapper对象(需用@MyBatisComponent定义).
 * 
 * @author liuyangming
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface MyBatisAutowired {

	public DBContext context();

}
