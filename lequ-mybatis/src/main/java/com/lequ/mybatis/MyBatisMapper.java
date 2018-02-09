package com.lequ.mybatis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Replaced by @com.gome.stage.mybatis.annotation.MyBatisComponent.
 * 
 * @author liuyangming
 */
@Deprecated
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface MyBatisMapper {

	public String beanNamePrefix();

	public int sessionFactory() default MyBatisConstants.CATB_ONLY;

}
