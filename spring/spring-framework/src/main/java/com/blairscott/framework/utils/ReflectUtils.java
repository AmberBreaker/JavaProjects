package com.blairscott.framework.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射工具类
 */
public class ReflectUtils {

	/**
	 * 使用构造器创建bean的实例
	 */
	public static Object createObject(Class<?> clazz, Object... args) {
		try {
			Constructor<?> constructor = clazz.getConstructor();
			return constructor.newInstance(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void setProperty(Object beanInstance, String name, Object valueToUse) {
		try {
			Class<?> clazz = beanInstance.getClass();
			Field field = clazz.getDeclaredField(name);
			field.setAccessible(true);
			field.set(beanInstance, valueToUse);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取类中属性的类型
	 * @param beanClassName 类全名
	 * @param name 注定属性名
	 * @return 指定属性类型
	 */
	public static Class<?> getTypeByFieldName(String beanClassName, String name) {
		try {
			Class<?> clazz = Class.forName(beanClassName);
			Field field = clazz.getDeclaredField(name);
			return field.getType();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 调用实例内部方法
	 * @param beanInstance 实例对象
	 * @param initMethod 实例方法
	 */
	public static void invokeMethod(Object beanInstance, String initMethod) {
		try {
			if (initMethod == null || "".equals(initMethod)) {
				return;
			}
			Class<?> clazz = beanInstance.getClass();
			Method method = clazz.getDeclaredMethod(initMethod);
			method.setAccessible(true);
			method.invoke(beanInstance);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
