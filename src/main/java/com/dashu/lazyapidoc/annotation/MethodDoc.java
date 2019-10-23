package com.dashu.lazyapidoc.annotation;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class MethodDoc implements Serializable{

	/**
	 * 方法名，不是全名
	 */
	private String methodName;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 备注描述
	 */
	private String remark;
	/**
	 * 请求地址
	 */
	private String path;
	/**
	 * 请求方式
	 */
	private String httpMethod;
	/**
	 * 方法详细备注
	 * 返回示例：public abstract T com.dashu.base.common.generic.service.GenericBaseService.selectOne(java.util.Map<java.lang.String, java.lang.Object>)
	 */
	private String methodInfo;
	/**
	 * 返回类型
	 */
	private String returnType;
	/**
	 * 返回类型详细情况，用于含泛型注入的参数， 如List<T>, 则本处为T;Map<String, Object>,则本处为String,Object
	 */
	private String returnArgs;
	/**
	 * 返回数据的说明
	 */
	private String returnDoc;
	/**
	 * 方法参数详细内容列表
	 */
	private List<ParameterInfo> docs;
	/**
	 * 方法运行所必须要的参数
	 */
	private String[] requireParameters;

	/**
	 * 非必须的参数，除此之外的为必须
	 */
	private String[] unRequireParameters;

	/**
	 * 忽略掉多余的参数
	 */
	private String[] ignoreParameters;

	/**
	 * 仅包含的参数
	 */
	private String[] includeParameters;

	private ParameterFilter parameterFilter;

	/**
	 * 返回类型的原始数据
	 */
	private Type rawType;

	/**
	 * 作用范围 default true：方法中所有参数，包括JAVABEAN
	 * 					false:仅仅方法上的doc注解
	 */
	private boolean scope;

	/**
	 * 泛RequestMapping注解相关信息
	 */
	private RequestMappingBean requestMappingBean;

	private ReturnFilter returnFilter;

	/**
	 * 是否私有
	 */
	private boolean privateStatus;
	
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
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
	public String getHttpMethod() {
		return httpMethod;
	}
	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}
	public String getMethodInfo() {
		return methodInfo;
	}
	public void setMethodInfo(String methodInfo) {
		this.methodInfo = methodInfo;
	}
	public String getReturnType() {
		return returnType;
	}
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	public String getReturnArgs() {
		return returnArgs;
	}
	public void setReturnArgs(String returnArgs) {
		this.returnArgs = returnArgs;
	}
	public String getReturnDoc() {
		return returnDoc;
	}
	public void setReturnDoc(String returnDoc) {
		this.returnDoc = returnDoc;
	}
	public List<ParameterInfo> getDocs() {
		return docs;
	}
	public void setDocs(List<ParameterInfo> docs) {
		this.docs = docs;
	}
	public String[] getRequireParameters() {
		return requireParameters;
	}
	public void setRequireParameters(String[] requireParameters) {
		this.requireParameters = requireParameters;
	}

	public Type getRawType() {
		return rawType;
	}

	public void setRawType(Type rawType) {
		this.rawType = rawType;
	}

	public boolean isScope() {
		return scope;
	}

	public void setScope(boolean scope) {
		this.scope = scope;
	}

	public String[] getIgnoreParameters() {
		return ignoreParameters;
	}

	public void setIgnoreParameters(String[] ignoreParameters) {
		this.ignoreParameters = ignoreParameters;
	}

	public RequestMappingBean getRequestMappingBean() {
		return requestMappingBean;
	}

	public void setRequestMappingBean(RequestMappingBean requestMappingBean) {
		this.requestMappingBean = requestMappingBean;
	}

	public ReturnFilter getReturnFilter() {
		return returnFilter;
	}

	public void setReturnFilter(ReturnFilter returnFilter) {
		this.returnFilter = returnFilter;
	}

	public String[] getIncludeParameters() {
		return includeParameters;
	}

	public void setIncludeParameters(String[] includeParameters) {
		this.includeParameters = includeParameters;
	}

	public ParameterFilter getParameterFilter() {
		return parameterFilter;
	}

	public void setParameterFilter(ParameterFilter parameterFilter) {
		this.parameterFilter = parameterFilter;
	}

	public String[] getUnRequireParameters() {
		return unRequireParameters;
	}

	public void setUnRequireParameters(String[] unRequireParameters) {
		this.unRequireParameters = unRequireParameters;
	}

	public boolean isPrivateStatus() {
		return privateStatus;
	}

	public void setPrivateStatus(boolean privateStatus) {
		this.privateStatus = privateStatus;
	}
	//	public void addDoc(Doc doc) {
//		if(this.docs==null) {
//			this.docs = new ArrayList<Doc>();
//		}
//		this.docs.add(doc);
//	}
}
