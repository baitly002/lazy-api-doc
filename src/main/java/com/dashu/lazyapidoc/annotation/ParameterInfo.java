package com.dashu.lazyapidoc.annotation;

import java.io.Serializable;
import java.lang.reflect.Type;

public class ParameterInfo implements Serializable{
	/**
	 * 参数名称 英文
	 */
	public String name;
	/**
	 * 参数类型
	 */
	public String type;
	/**
	 * 状态， 0无效 1有效
	 */
	public String status;
	/**
	 * 是否必须 0非必须  1必须
	 */
	public String require;
	/**
	 * 参数名称 中文
	 */
	public String chineseName;
	/**
	 * 参数备注
	 */
	public String remark;
	/**
	 * 格式化
	 */
	public String format;
	
	/**
	 * 默认值
	 */
	public String defaultValue;

	/**
	 * 示例
	 */
	public String example;

	/**
	 * 是否忽略，默认FALSE
	 */
	public Boolean ignore;

	/**
	 * 原始类型
	 */
	public Type rawType;
	
	public ParameterInfo() {
		
	}
	public ParameterInfo(String name, Type rawtype) {
		this.name = name;
		this.rawType=rawtype;
		this.type = rawType.getTypeName();
		this.status = "1";
		this.require = "0";
		this.chineseName = "";
		this.remark = "";
		this.format = "";
		this.defaultValue = "";
		this.example = "";
		this.ignore = false;
	}

//	public ParameterInfo(String name, String type) {
//		this.name = name;
//		this.type = type;
//		this.status = "1";
//		this.require = "1";
//		this.chineseName = "";
//		this.remark = "";
//		this.format = "";
//		this.defaultValue = "";
//		this.example = "";
//	}
	public String getName() {
		return name;
	}
	public ParameterInfo setName(String name) {
		this.name = name;
		return this;
	}
	public String getType() {
		return type;
	}
	public ParameterInfo setType(String type) {
		this.type = type;
		return this;
	}
	public String getStatus() {
		return status;
	}
	public ParameterInfo setStatus(String status) {
		this.status = status;
		return this;
	}
	public String getRequire() {
		return require;
	}
	public ParameterInfo setRequire(String require) {
		this.require = require;
		return this;
	}
	public String getChineseName() {
		return chineseName;
	}
	public ParameterInfo setChineseName(String chineseName) {
		this.chineseName = chineseName;
		return this;
	}
	public String getRemark() {
		return remark;
	}
	public ParameterInfo setRemark(String remark) {
		this.remark = remark;
		return this;
	}
	public String getFormat() {
		return format;
	}
	public ParameterInfo setFormat(String format) {
		this.format = format;
		return this;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public ParameterInfo setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
		return this;
	}

	public String getExample() {
		return example;
	}

	public ParameterInfo setExample(String example) {
		this.example = example;
		return this;
	}

	public Type getRawType() {
		return rawType;
	}

	public ParameterInfo setRawType(Type rawType) {
		this.rawType = rawType;
		return this;
	}

	public Boolean getIgnore() {
		return ignore;
	}

	public void setIgnore(Boolean ignore) {
		this.ignore = ignore;
	}
}
