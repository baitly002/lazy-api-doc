package com.dashu.lazyapidoc.process;

import com.dashu.lazyapidoc.annotation.*;
import com.dashu.lazyapidoc.util.ClassUtil;
import com.dashu.lazyapidoc.util.DocArrayUtils;
import com.dashu.lazyapidoc.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.*;
import java.util.*;

public class CreateApi {
    public static Logger logger = LoggerFactory.getLogger(CreateApi.class);

    public static void main(String[] args) throws ClassNotFoundException {
        ApiConfig.host = args[0];
        ApiConfig.project = args[1];
        ApiConfig.maxlevel = Integer.parseInt(args[2]);
        String scanBase = args[3];
        List<Class<?>> classes = ClassUtil.getClasses(scanBase);
        ApiConfig.rootClass = args[4];
        //以下四行简单测试用
//        List<Class<?>> classes = new ArrayList<>();
//        classes.clear();
//        Class<?> cla = Class.forName("com.dashu.repair.core.web.DeviceController");
//        classes.add(cla);
        parser(classes);
    }

    public static void parser(List<Class<?>> classes){
        for(Class clazz : classes) {
            try {
                RequestMapping requestMapping = (RequestMapping)clazz.getAnnotation(RequestMapping.class);
                if(requestMapping != null) {
                    logger.info(">>>>>>>>正在处理类:"+clazz.getName());
                    ClassDoc classDoc = new ClassDoc();
                    String val[] = requestMapping.value();
                    if(val.length>0) {
                        classDoc.setPath(val[0]);
                    } else {
                        classDoc.setPath("");
                    }
//            		classDoc.setPath(requestMapping.value());
                    classDoc.setClassName(clazz.getName());
                    Doc classApiParam = (Doc) clazz.getAnnotation(Doc.class);
                    if(classApiParam != null) {
                        classDoc.setTitle(classApiParam.value());
                        classDoc.setRemark(classApiParam.remark());
                    }else {
                        classDoc.setTitle(clazz.getSimpleName());
                    }
                    ApiDoc classApiDoc = (ApiDoc) clazz.getAnnotation(ApiDoc.class);
                    if(classApiDoc!=null) {
                        logger.error("类"+clazz.getName()+"上有重复注解＠Doc, 请检查！");
                    }
                    Map<String, Object> genericParamterMapping = new HashMap<>();
                    Map<Integer, String> genericParamterKeyMapping = new HashMap<>();
                    Map<Integer, Object> genericParamterValueMapping = new HashMap<>();
                    //对泛型的支持，取得泛型的真实参数
                    Type[] types = clazz.getGenericInterfaces();
                    for(int i=0; i<types.length; i++){
                        ParameterizedType pp = (ParameterizedType)types[i];
                        for(Type type : pp.getActualTypeArguments()){
                            genericParamterValueMapping.put(i, type.getTypeName());
                        }
                    }
                    Class[] cs = clazz.getInterfaces();
                    for(Class intf : cs){
                        TypeVariable[] tvs = intf.getTypeParameters();
                        for(int i=0; i<tvs.length; i++){//
                            genericParamterKeyMapping.put(i, tvs[i].getName());
                        }
                    }
                    for(int i=0; i<genericParamterKeyMapping.size(); i++){
                        //泛型参数对应关系
//        				genericParamterMapping.put(genericParamterKeyMapping.get(i), genericParamterValueMapping.get(i));
                        genericParamterMapping.put(clazz.getName()+"["+genericParamterKeyMapping.get(i), genericParamterValueMapping.get(i));

                        logger.info(">>>>>>>>注入泛型父类的参数:"+genericParamterKeyMapping.get(i)+"==="+genericParamterValueMapping.get(i));
                    }
                    classDoc.setGenericParamterMapping(genericParamterMapping);
//        			AnnotatedType[] annotatedTypes = clazz.getAnnotatedInterfaces();//处理父类信息
//        			if(annotatedTypes!=null && annotatedTypes.length>0) {
//        				for(AnnotatedType annotatedType : annotatedTypes) {
//        					logger.info("父类及注入泛型参数:"+annotatedType.getType().getTypeName());
//        				}
//        			}

                    List<MethodDoc> mothodDocs = new ArrayList<>();
                    Method[] methods = clazz.getMethods();
                    for(Method method : methods){
                        RequestMappingBean requestMappingBean = parseMethod(method);
                        if(requestMappingBean!=null) {
                            logger.info(">>>>>>>>>>>>正在处理方法:"+method.getName());
                            MethodDoc methodDoc = new MethodDoc();
                            methodDoc.setRequestMappingBean(requestMappingBean);
                            ReturnFilter returnFilter = method.getAnnotation(ReturnFilter.class);
                            if(returnFilter!=null){
                                methodDoc.setReturnFilter(returnFilter);
                            }
                            methodDoc.setScope(true);//默认作用全部范围参数
                            String[] requireParameters = {};
                            String[] unRequireParameters = {};
                            String[] ignoreParameters = {};
                            String[] includeParameters = {};

                            methodDoc.setMethodInfo(method.toGenericString());
                            List<Doc> docs = new ArrayList<Doc>();
                            //处理方法返回值数据
                            Type returnType = method.getGenericReturnType();
                            methodDoc.setRawType(returnType);
                            if (returnType instanceof ParameterizedType){//如果是泛型类型， 是否带有尖括号如List<String> 而不带尖括号的List则由下面else处理
                                ParameterizedType rt = (ParameterizedType) returnType;
                                Type rawType = rt.getRawType();
                                logger.info(">>>>>>>>>>>>>>>>方法的返回类型是generic:"+rawType.getTypeName());
                                methodDoc.setReturnType(rawType.getTypeName());
                                Type[] gtypes = rt.getActualTypeArguments();// 泛型类型列表
                                String returnArgs = "";
                                for (Type type : gtypes) {
                                    Object arg = genericParamterMapping.get(type.getTypeName());
                                    if(arg!=null) {
                                        returnArgs = returnArgs +"," + (String)arg;
                                    }else {
                                        returnArgs = returnArgs + "," + type.getTypeName();
                                    }
                                }
                                if(StringUtils.isNotBlank(returnArgs)) {
                                    methodDoc.setReturnArgs(returnArgs.substring(1));
                                    logger.info(">>>>>>>>>>>>>>>>注入参数是:"+returnArgs.substring(1));
                                }
                            }else {
                                String rt = method.getAnnotatedReturnType().getType().getTypeName();
                                Object rtobj = genericParamterMapping.get(rt);
                                if(rtobj!=null) {
                                    methodDoc.setReturnType((String)rtobj);
                                }else {
                                    methodDoc.setReturnType(rt);
                                }
//        		            	Class cla = method.getReturnType();
//        		            	methodDoc.setReturnType(cla.getName());
//        		            	logger.info(method.getName()+"方法的返回类型是:"+cla.getName());
                            }
//            					System.out.println("返回类型3:"+returnType.getTypeName());
                            methodDoc.setMethodName(method.getName());
//        					String valm[] = requestMapping.value();
                            String valm[] = requestMappingBean.getValue();
                            if(valm.length>0) {
                                methodDoc.setPath(valm[0]);
                            } else {
                                methodDoc.setPath("");
                            }
//        					methodDoc.setPath(methodRequestMapping.value());
                            RequestMethod httpMethods[] = requestMappingBean.getMethod();
                            if(httpMethods.length>0) {
                                methodDoc.setHttpMethod(httpMethods[0].name());
                            }else {
                                methodDoc.setHttpMethod("");//如果没设置请求方式，则为空
                            }

                            //处理方法上的文档注解
                            ApiDoc methodApiDoc = (ApiDoc) method.getAnnotation(ApiDoc.class);
                            Doc methodApiParam = (Doc) method.getAnnotation(Doc.class);
                            if(methodApiParam != null) {
                                docs.add(methodApiParam);
                            }
                            if(methodApiDoc!=null){
                                Doc params[] = methodApiDoc.value();
                                for(Doc param : params) {
                                    if(!param.scope()){
                                        methodDoc.setScope(false);
                                    }
                                    docs.add(param);
                                }
                            }

                            //处理方法参数
                            Parameter[] parameters = method.getParameters();
                            Map<String, ParameterInfo> parameterMaps = new LinkedHashMap<>();
                            for (Parameter parameter : parameters){
                                String name = parameter.getName();
                                String pt = parameter.getAnnotatedType().getType().getTypeName();
                                Object ptobj = genericParamterMapping.get(pt);
                                if(ptobj!=null) {
                                    pt = (String)ptobj;
                                }
//                                ParameterInfo param = new ParameterInfo(name, pt);
                                ParameterInfo param = new ParameterInfo(name, parameter.getAnnotatedType().getType());
                                param.setRequire("0");
                                //如果参数上有＠RequestParam注解
                                RequestParam requestParam = parameter.getAnnotation(RequestParam.class);
                                if(requestParam!=null) {
                                    if(StringUtils.isNotBlank(requestParam.name())) {
                                        name = requestParam.name();
                                    }
                                    if(StringUtils.isNotBlank(requestParam.value())) {
                                        name = requestParam.value();
                                    }
                                    param.setName(name);
                                    if(requestParam.required()) {
                                        param.setRequire("1");
                                        requireParameters = DocArrayUtils.add(requireParameters, name);
                                    }
                                    if(StringUtils.isNotBlank(requestParam.defaultValue())) {
                                        param.setDefaultValue(requestParam.defaultValue());
                                    }
                                }
                                //如果参数上有＠PathVariable注解
                                PathVariable pathVariable = parameter.getAnnotation(PathVariable.class);
                                if(pathVariable!=null) {
                                    if(StringUtils.isNotBlank(pathVariable.name())) {
                                        name = pathVariable.name();
                                    }
                                    if(StringUtils.isNotBlank(pathVariable.value())) {
                                        name = pathVariable.value();
                                    }
                                    param.setName(name);
                                    if(pathVariable.required()) {
                                        param.setRequire("1");
                                        requireParameters = DocArrayUtils.add(requireParameters, name);
                                    }
                                }

                                parameterMaps.put(name, param);
                            }
                            //处理方法上的@RequireParameter注解
                            RequireParameter requireParameter = method.getAnnotation(RequireParameter.class);
                            if(requireParameter!=null) {
                                requireParameters = requireParameter(requireParameter, requireParameters, parameterMaps, method);
                            }

                            //处理方法上的@IgnoreParameter注解
                            IgnoreParameter ignoreParameter = method.getAnnotation(IgnoreParameter.class);
                            if(ignoreParameter != null){
                                ignoreParameters = ignoreParameter(ignoreParameter, ignoreParameters, parameterMaps, method);
                            }

                            //处理方法上的@ParameterFilter
                            ParameterFilter parameterFilter = method.getAnnotation(ParameterFilter.class);
                            if(parameterFilter!=null){
                                methodDoc.setParameterFilter(parameterFilter);
                                includeParameters = includeParameter(parameterFilter, includeParameters, parameterMaps, method);
                                unRequireParameters = parameterFilter.unRequires();
                            }
                            methodDoc.setUnRequireParameters(unRequireParameters);

                            //合并文档信息
                            for(Doc doc : docs) {
                                if(StringUtils.isBlank(doc.name())){
                                    methodDoc.setTitle(doc.value());
                                    methodDoc.setRemark(doc.remark());
                                }else {
                                    ParameterInfo param =parameterMaps.get(doc.name());
                                    if(param!=null) {
                                        param.setChineseName(doc.value()).setRemark(doc.remark()).setRequire(doc.required()?"1":"0").setIgnore(doc.ignore());
                                        if(doc.required()) {
                                            requireParameters = DocArrayUtils.add(requireParameters, doc.name());
                                        }
                                    }
                                }
                            }
                            List<ParameterInfo> paramterList = new ArrayList<>();
                            if(methodDoc.isScope()){
                                for(Map.Entry<String, ParameterInfo> entry : parameterMaps.entrySet()) {
                                    if(!TypeFormat.ignoreParameter(entry.getValue().getRawType())){
                                        paramterList.add(entry.getValue());
                                    }
                                }
                            }else{
                                for(Doc doc : docs) {
                                    if(StringUtils.isNotBlank(doc.name())){
                                        ParameterInfo param = new ParameterInfo();
                                        param.setChineseName(doc.value()).setRemark(doc.remark()).setRequire(doc.required()?"1":"0");
                                        param.setDefaultValue(doc.defaultValue()).setExample(doc.example()).setIgnore(doc.ignore());
                                        param.setName(doc.name()).setStatus("1").setType(doc.dataType());
                                        if(!doc.ignore()){
                                            paramterList.add(param);
                                        }
                                        if(!doc.ignore() && doc.required()) {
                                            requireParameters = DocArrayUtils.add(requireParameters, doc.name());
                                        }
                                    }
                                }
                            }

                            methodDoc.setDocs(paramterList);
                            //处理返回参数的文档注解
                            ReturnDoc returnDoc = (ReturnDoc) method.getAnnotation(ReturnDoc.class);
                            if(returnDoc != null) {
                                methodDoc.setReturnDoc(returnDoc.value());
                            }
                            methodDoc.setRequireParameters(requireParameters);
                            methodDoc.setIgnoreParameters(ignoreParameters);
                            methodDoc.setIncludeParameters(includeParameters);
                            mothodDocs.add(methodDoc);

                        }else {
                            logger.info(">>>>>>>>方法:" + method.getName() + "无@RequestMapping注解，不是对外接口,忽略！");
                        }
                    }
                    classDoc.setMethodDocList(mothodDocs);
                    SendApiDoc.createDoc(classDoc);
                }
            } catch (Exception e) {
                e.printStackTrace();
//                logger.debug("类:" + clazz.getName() + "不是@RequestMapping注解的类,忽略！");
            }
        }
    }

    public static RequestMappingBean parseMethod(Method method){
        RequestMappingBean methodRequestMappingBean = new RequestMappingBean();
        RequestMapping methodRequestMapping = method.getAnnotation(RequestMapping.class);
        if(methodRequestMapping!=null){
            methodRequestMappingBean.setConsumes(methodRequestMapping.consumes()).setHeaders(methodRequestMapping.headers())
                    .setMethod(methodRequestMapping.method()).setName(methodRequestMapping.name()).setParams(methodRequestMapping.params())
                    .setPath(methodRequestMapping.path()).setProduces(methodRequestMapping.produces()).setValue(methodRequestMapping.value());
            return methodRequestMappingBean;
        }
        GetMapping getMapping = method.getAnnotation(GetMapping.class);
        if(getMapping!=null){
            methodRequestMappingBean.setConsumes(getMapping.consumes()).setHeaders(getMapping.headers())
                    .setName(getMapping.name()).setParams(getMapping.params()).setPath(getMapping.path())
                    .setProduces(getMapping.produces()).setValue(getMapping.value()).setMethod(new RequestMethod[]{RequestMethod.GET});
            return methodRequestMappingBean;
        }

        PostMapping postMapping = method.getAnnotation(PostMapping.class);
        if(postMapping!=null){
            methodRequestMappingBean.setConsumes(postMapping.consumes()).setHeaders(postMapping.headers())
                    .setName(postMapping.name()).setParams(postMapping.params()).setPath(postMapping.path())
                    .setProduces(postMapping.produces()).setValue(postMapping.value()).setMethod(new RequestMethod[]{RequestMethod.POST});
            return methodRequestMappingBean;
        }
        PutMapping putMapping = method.getAnnotation(PutMapping.class);
        if(putMapping!=null){
            methodRequestMappingBean.setConsumes(putMapping.consumes()).setHeaders(putMapping.headers())
                    .setName(putMapping.name()).setParams(putMapping.params()).setPath(putMapping.path())
                    .setProduces(putMapping.produces()).setValue(putMapping.value()).setMethod(new RequestMethod[]{RequestMethod.PUT});
            return methodRequestMappingBean;
        }
        DeleteMapping deleteMapping = method.getAnnotation(DeleteMapping.class);
        if(deleteMapping!=null){
            methodRequestMappingBean.setConsumes(deleteMapping.consumes()).setHeaders(deleteMapping.headers())
                    .setName(deleteMapping.name()).setParams(deleteMapping.params()).setPath(deleteMapping.path())
                    .setProduces(deleteMapping.produces()).setValue(deleteMapping.value()).setMethod(new RequestMethod[]{RequestMethod.DELETE});
            return methodRequestMappingBean;
        }
        PatchMapping patchMapping = method.getAnnotation(PatchMapping.class);
        if(patchMapping!=null){
            methodRequestMappingBean.setConsumes(patchMapping.consumes()).setHeaders(patchMapping.headers())
                    .setName(patchMapping.name()).setParams(patchMapping.params()).setPath(patchMapping.path())
                    .setProduces(patchMapping.produces()).setValue(patchMapping.value()).setMethod(new RequestMethod[]{RequestMethod.PATCH});
            return methodRequestMappingBean;
        }
        return null;
    }

    /**
     * 处理必传的参数
     * @param requireParameter
     * @param requireParameters
     * @param parameterMaps
     * @param method
     */
    public static String[] requireParameter(RequireParameter requireParameter, String[] requireParameters, Map<String, ParameterInfo> parameterMaps, Method method){
        String requireArr = requireParameter.value();
        String requires[] = requireParameter.require();
        for(String rq : requireArr.split(",")) {
            requireParameters = DocArrayUtils.add(requireParameters, rq.trim());
            ParameterInfo param =parameterMaps.get(rq.trim());
            if(param!=null) {
                param.setRequire("1");
            }
        }
        for(String rq : requires) {
            requireParameters = DocArrayUtils.add(requireParameters, rq.trim());
            ParameterInfo param =parameterMaps.get(rq.trim());
            if(param!=null) {
                param.setRequire("1");
            }
        }
        ParameterFilter parameterFilter = method.getAnnotation(ParameterFilter.class);
        if(parameterFilter!=null){
            String[] requiresFilter = parameterFilter.requires();
            for(String rf : requiresFilter) {
                requireParameters = DocArrayUtils.add(requireParameters, rf.trim());
                ParameterInfo param =parameterMaps.get(rf.trim());
                if(param!=null) {
                    param.setRequire("1");
                }
            }
        }

        return requireParameters;
    }

    /**
     * 处理忽略的参数
     * @param ignoreParameter
     * @param ignoreParameters
     * @param parameterMaps
     * @param method
     */
    public static String[] ignoreParameter(IgnoreParameter ignoreParameter, String[] ignoreParameters, Map<String, ParameterInfo> parameterMaps, Method method){
        String ignoreArr = ignoreParameter.value();
        for(String ig : ignoreArr.split(",")){
            ignoreParameters = DocArrayUtils.add(ignoreParameters, ig.trim());
        }
        String[] ignores = ignoreParameter.ignore();
        for(String i : ignores){
            ignoreParameters = DocArrayUtils.add(ignoreParameters, i.trim());
        }
        ParameterFilter parameterFilter = method.getAnnotation(ParameterFilter.class);
        if(parameterFilter!=null){
            String[] excludes = parameterFilter.excludes();
            for(String exclude : excludes) {
                ignoreParameters = DocArrayUtils.add(ignoreParameters, exclude.trim());
            }

            String values = parameterFilter.value();
            for(String val : values.split(",")){
                ignoreParameters = DocArrayUtils.add(ignoreParameters, val.trim());
            }
        }
        return ignoreParameters;
    }

    /**
     * 处理仅包含的参数
     * @param parameterFilter
     * @param includeParameters
     * @param parameterMaps
     * @param method
     */
    public static String[] includeParameter(ParameterFilter parameterFilter, String[] includeParameters, Map<String, ParameterInfo> parameterMaps, Method method){
        String[] includes = parameterFilter.includes();
        for(String include : includes){
            includeParameters = DocArrayUtils.add(includeParameters, include.trim());
        }

        return includeParameters;
    }
}
