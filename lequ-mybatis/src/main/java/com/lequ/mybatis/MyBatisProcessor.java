package com.lequ.mybatis;

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

 
public class MyBatisProcessor implements BeanPostProcessor, BeanFactoryPostProcessor, ApplicationContextAware {
	private static final Logger logger = LoggerFactory.getLogger(MyBatisProcessor.class);
	private ApplicationContext applicationContext;

	@SuppressWarnings("deprecation")
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

		Class<?> beanClass = bean.getClass();
		List<Field> fields = this.getFieldByClass(beanClass);
		for (int j = 0; j < fields.size(); j++) {
			Field field = fields.get(j);
			MyBatisInjected injected = field.getAnnotation(MyBatisInjected.class);
			if (injected != null) {
				String beanId = injected.beanName();
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

	@SuppressWarnings("deprecation")
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

			MyBatisMapper annotation = clazz.getAnnotation(MyBatisMapper.class);
			if (annotation != null && clazz.isInterface()) {
				String prefix = annotation.beanNamePrefix();

				List<String> factoryLst = new ArrayList<String>();
				final int sessionFactoryFlags = annotation.sessionFactory();
				if (sessionFactoryFlags == MyBatisConstants.CATB_ONLY) {

					RootBeanDefinition beanDef = new RootBeanDefinition();
					beanDef.setBeanClass(org.mybatis.spring.mapper.MapperFactoryBean.class);
					MutablePropertyValues mpv = new MutablePropertyValues();
					beanDef.setPropertyValues(mpv);
					mpv.addPropertyValue("sqlSessionFactory", this.applicationContext.getBean(String.format(
							"sqlSessionFactory%s", MyBatisConstants.SUFFIX_CATB)));
					mpv.addPropertyValue("mapperInterface", clazz);
					registry.registerBeanDefinition(prefix, beanDef);
				} else {
					if ((sessionFactoryFlags & MyBatisConstants.CATB) == MyBatisConstants.CATB) {
						factoryLst.add(MyBatisConstants.SUFFIX_CATB);
					}
					if ((sessionFactoryFlags & MyBatisConstants.CATX) == MyBatisConstants.CATX) {
						factoryLst.add(MyBatisConstants.SUFFIX_CATX);
					}
					if ((sessionFactoryFlags & MyBatisConstants.PROD) == MyBatisConstants.PROD) {
						factoryLst.add(MyBatisConstants.SUFFIX_PROD);
					}
					if ((sessionFactoryFlags & MyBatisConstants.PRODX) == MyBatisConstants.PRODX) {
						factoryLst.add(MyBatisConstants.SUFFIX_PRODX);
					}
					if ((sessionFactoryFlags & MyBatisConstants.PRODX_W) == MyBatisConstants.PRODX_W) {
						factoryLst.add(MyBatisConstants.SUFFIX_PRODX_W);
					}
					if ((sessionFactoryFlags & MyBatisConstants.MYSQL) == MyBatisConstants.MYSQL) {
						factoryLst.add(MyBatisConstants.SUFFIX_MYSQL);
					}

					for (int m = 0; m < factoryLst.size(); m++) {
						String factory = factoryLst.get(m);
						if (factory == null || factory.trim().length() == 0) {
							continue;
						}

						String value = factory.trim();

						StringBuilder beanIdBer = new StringBuilder();
						StringBuilder ssnMgrBer = new StringBuilder();

						beanIdBer.append(prefix);
						beanIdBer.append(value);

						ssnMgrBer.append(value);

						RootBeanDefinition beanDef = new RootBeanDefinition();
						beanDef.setBeanClass(org.mybatis.spring.mapper.MapperFactoryBean.class);
						MutablePropertyValues mpv = new MutablePropertyValues();
						beanDef.setPropertyValues(mpv);
						mpv.addPropertyValue(
								"sqlSessionFactory",
								this.applicationContext.getBean(String.format("sqlSessionFactory%s",
										ssnMgrBer.toString())));
						mpv.addPropertyValue("mapperInterface", clazz);
						registry.registerBeanDefinition(beanIdBer.toString(), beanDef);
					}
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
