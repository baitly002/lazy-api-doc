package com.dashu.lazyapidoc.annotation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 类文档说明
 * @param className 类名称
 * @param title 类标题
 * @param remark 类描述备注
 * @param path 请求路径
 * @param methodDocList 方法文档
 */
public class ClassDoc implements Serializable{

	private String className;
	private String title;
	private String remark;
	private String path;
	private List<MethodDoc> methodDocList;
	private Map<String, Object> genericParamterMapping;
	private boolean privateStatus;

	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public List<MethodDoc> getMethodDocList() {
		return methodDocList;
	}
	public void setMethodDocList(List<MethodDoc> methodDocList) {
		this.methodDocList = methodDocList;
	}
	public Map<String, Object> getGenericParamterMapping() {
		return genericParamterMapping;
	}
	public void setGenericParamterMapping(Map<String, Object> genericParamterMapping) {
		this.genericParamterMapping = genericParamterMapping;
	}

	public boolean isPrivateStatus() {
		return privateStatus;
	}

	public void setPrivateStatus(boolean privateStatus) {
		this.privateStatus = privateStatus;
	}
}
