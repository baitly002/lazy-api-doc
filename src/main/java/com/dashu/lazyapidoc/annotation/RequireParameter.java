package com.dashu.lazyapidoc.annotation;

import java.lang.annotation.*;

/**
 * 定义必须传的参数<br/>
 * 也可排除某些参数为非必传，其余为必传
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequireParameter {
	String value() default "";
	String exclude() default "";
	String[] require() default {};
}
