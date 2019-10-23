package com.dashu.lazyapidoc.annotation;

import java.lang.annotation.*;

/**
 * 注解在类上，只能有一个<br/>
 * 注解在方法上，可以多个<br/>
 * 成员属性如下：<br/>
 * ： value 方法参数中文名称（参数含义）/类标题<br/>
 * ： [name][key] 方法参数英文名称/类名称，配置其中一个即可<br/>
 * ： dataType 参数类型 默认为空<br/>
 * ： required 参数是否必须 默认true<br/>
 * ： remark 参数备注说明 默认为空/类描述说明<br/>
 * ： example 示例<br/>
 * ： defaultValue 默认值<br/>
 */
@Target({ElementType.TYPE, ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ApiDoc.class)
@Documented
public @interface Doc {
//	@ApiModelProperty(value = "岗位", required = false, example = "请开发人员输入示例", dataType = "String")
//	@ApiImplicitParam(required = true, name = "ids", value = "学员编号以逗号分隔", dataType = "String", paramType = "query")})
	/**
	 * 方法参数名 类名 default empty
	 * 与key一样
	 */
	String name() default "";
	/**
	 * 方法参数名 类名 default empty
	 * 与name一样
	 */
	String key() default "";
	/**
	 * 参数中文含义 标题
	 */
	String value();
	/**
	 * 描述备注说明 default empty
	 */
	String remark() default "";
	/**
	 * 类型 default empty
	 */
	String dataType() default "";

	/**
	 * 示例
	 */
	String example() default "";

	/**
	 * 默认值
	 */
	String defaultValue() default "";

	/**
	 * 是否必须 default false
	 */
	boolean required() default false;

	/**
	 * 是否忽略 default false
	 */
	boolean ignore() default false;

	/**
	 * 作用范围 default true：方法中所有参数，包括JAVABEAN
	 * 					false:仅仅方法上的doc注解
	 */
	boolean scope() default true;

	/**
	 * 是否私有，作用在接口方法或类上，属性无效
	 * @return
	 */
	boolean privateStatus() default false;
}