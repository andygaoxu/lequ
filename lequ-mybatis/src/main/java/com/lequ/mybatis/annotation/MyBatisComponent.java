package com.lequ.mybatis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.lequ.mybatis.common.DBContext;


/**
 * 用于定义一个Mapper对象, 被定义的Mapper通过@MyBatisAutowired注解自动注入.
 * 
 * @author liuyangming
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface MyBatisComponent {

	/**
	 * 定义Mapper需要绑定的DB上下文环境. 如DataBaseContext.CATB | DataBaseContext.CATX, 表示在CATB & CATX上定义该Mapper.
	 * 
	 * @return 需要绑定的DB上下文环境集合.
	 */
	public DBContext[] contexts();

}
