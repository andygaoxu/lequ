package com.lequ.mybatis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Replaced by @com.gome.stage.mybatis.annotation.MyBatisAutowired.
 * 
 * @author liuyangming
 */
@Deprecated
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface MyBatisInjected {

	public String beanName();

}
