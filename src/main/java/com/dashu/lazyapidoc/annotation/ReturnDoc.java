package com.dashu.lazyapidoc.annotation;

import java.lang.annotation.*;

/**
 * 定义返回的数据类型
 * value : 直接返回的数据格式
 * clazz : 返回的javabean类 暂只支持简单bean
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ReturnDoc {

	String value();
	Class<?> clazz() default Class.class;
}
