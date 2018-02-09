package com.lequ.mybatis.spring;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.lequ.mybatis.annotation.MyBatisAutowired;
import com.lequ.mybatis.annotation.MyBatisComponent;
import com.lequ.mybatis.common.DBContext;



public class MyBatisBeanProcessor implements BeanPostProcessor, BeanFactoryPostProcessor, ApplicationContextAware {
	private static final Logger logger = LoggerFactory.getLogger(MyBatisBeanProcessor.class);
	private ApplicationContext applicationContext;

	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

		Class<?> beanClass = bean.getClass();
		List<Field> fields = this.getFieldByClass(beanClass);
		for (int j = 0; j < fields.size(); j++) {
			Field field = fields.get(j);
			MyBatisAutowired injected = field.getAnnotation(MyBatisAutowired.class);
			if (injected != null) {
				String clazzName = field.getType().getName();
				String beanId = String.format("%s@%s", clazzName, injected.context());
				try {
					Object depends = this.applicationContext.getBean(beanId);
					field.setAccessible(true);
					field.set(bean, depends);
					logger.info(String.format("inject for bean(%s): %s.%s= %s(%s).", beanName, bean.getClass()
							.getCanonicalName(), field.getName(), beanId, depends));
				} catch (IllegalArgumentException ex) {
					logger.error(String.format("cannot inject for bean(%s): %s.%s= %s.", beanName, bean.getClass()
							.getCanonicalName(), field.getName(), beanId));
					throw new FatalBeanException(ex.getMessage(), ex);
				} catch (IllegalAccessException ex) {
					logger.error(String.format("cannot inject for bean(%s): %s.%s= %s.", beanName, bean.getClass()
							.getCanonicalName(), field.getName(), beanId));
					throw new FatalBeanException(ex.getMessage(), ex);
				}
			}

		}

		return bean;
	}

	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		BeanDefinitionRegistry registry = (BeanDefinitionRegistry) beanFactory;
		String[] beanNameArray = registry.getBeanDefinitionNames();

		for (int i = 0; i < beanNameArray.length; i++) {
			String beanName = beanNameArray[i];
			BeanDefinition beanDef = registry.getBeanDefinition(beanName);
			String beanClassName = beanDef.getBeanClassName();
			Class<?> beanClass = this.loadClassIfNecessary(beanClassName);
			List<Field> fields = this.getFieldByClass(beanClass);

			for (int j = 0; j < fields.size(); j++) {
				Field field = fields.get(j);
				// Type genericType =
				field.getGenericType();
			}
		}

		List<Object> results = this.getClassesByClassLoader(cl);
		for (int i = 0; i < results.size(); i++) {
			Class<?> clazz = (Class<?>) results.get(i);

			MyBatisComponent annotation = clazz.getAnnotation(MyBatisComponent.class);
			if (annotation != null && clazz.isInterface()) {
				String clazzName = clazz.getName();

				final DBContext[] contexts = annotation.contexts();

				for (int m = 0; m < contexts.length; m++) {
					DBContext context = contexts[m];
					if (context == null) {
						continue;
					}

					String value = context.name();

					RootBeanDefinition beanDef = new RootBeanDefinition();
					beanDef.setBeanClass(org.mybatis.spring.mapper.MapperFactoryBean.class);
					MutablePropertyValues mpv = new MutablePropertyValues();
					beanDef.setPropertyValues(mpv);

					String beanId = String.format("%s@%s", clazzName, value);
					String sessionFactoryBeanName = String.format("sqlSessionFactory%s", context.name());

					mpv.addPropertyValue("sqlSessionFactory", this.applicationContext.getBean(sessionFactoryBeanName));
					mpv.addPropertyValue("mapperInterface", clazz);

					registry.registerBeanDefinition(beanId, beanDef);
				}

			}
		}

	}

	private Class<?> loadClassIfNecessary(String name) throws BeansException {
		try {
			return Thread.currentThread().getContextClassLoader().loadClass(name);
		} catch (ClassNotFoundException ex) {
			throw new FatalBeanException(ex.getMessage(), ex);
		}
	}

	private List<Object> getClassesByClassLoader(ClassLoader current) {
		List<Object> results = new ArrayList<Object>();
		Vector<Object> classes = this.getLoadedClassesByClassLoader(current);
		results.addAll(classes);
		ClassLoader parent = current.getParent();
		if (parent != null) {
			List<Object> classes2 = this.getClassesByClassLoader(parent);
			results.addAll(classes2);
		}
		return results;
	}

	@SuppressWarnings("unchecked")
	private Vector<Object> getLoadedClassesByClassLoader(ClassLoader cl) {
		try {
			Field field = ClassLoader.class.getDeclaredField("classes");
			field.setAccessible(true);
			return (Vector<Object>) field.get(cl);
		} catch (Exception ex) {
			return null;
		}
	}

	private List<Field> getFieldByClass(Class<?> clazz) {
		List<Field> fieldLst = new ArrayList<Field>();
		Class<?> current = clazz;
		while (Object.class.equals(current) == false) {
			Field[] fields = current.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				fieldLst.add(field);
			}
			current = current.getSuperclass();
		}
		return fieldLst;
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
