package com.dashu.lazyapidoc.process;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.dashu.lazyapidoc.annotation.ClassDoc;
import com.dashu.lazyapidoc.annotation.MethodDoc;
import com.dashu.lazyapidoc.annotation.ParameterInfo;
import com.dashu.lazyapidoc.annotation.ReturnFilter;
import com.dashu.lazyapidoc.util.DocArrayUtils;
import com.dashu.lazyapidoc.util.HttpRequest;
import com.dashu.lazyapidoc.util.LevelPropertyPreFilter;
import com.dashu.lazyapidoc.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SendApiDoc {

    public static Logger logger = LoggerFactory.getLogger(SendApiDoc.class);

    public static void createDoc(ClassDoc classDoc) {
//        String host = ProHolder.getValue("apiManager.host");
//        if(StringUtils.isBlank(host)) {
//            host = "http://192.168.10.252";
//        }
//        String packageNames[] = classDoc.getClassName().split("\\.");
//        String project = packageNames[2];
        String param = "project="+ApiConfig.project+"&treename="+classDoc.getTitle()+"&parentId=0";
        String json = HttpRequest.sendPost(ApiConfig.host+"/apiManager/testtree/", param);
        logger.info("添加接口分组结果数据:"+json);
        JSONObject obj = JSON.parseObject(json);
        Object data = obj.get("data");
        String treeId = "";
        if(data instanceof JSONObject) {
            JSONObject result = (JSONObject)data;
            treeId = result.getString("_id");
        }else if(data instanceof String){
            treeId = data.toString();
        }

        List<MethodDoc> methodDocs = classDoc.getMethodDocList();
        for(MethodDoc methodDoc : methodDocs) {
            logger.info("############正在处理类"+classDoc.getClassName()+"的方法:"+methodDoc.getMethodName());
            //处理类上面的泛型
            Map<String, Object> map = classDoc.getGenericParamterMapping();
            HashMap<String, String> gmap = new HashMap<>();
            for(Map.Entry<String, Object> entry : map.entrySet()) {
                gmap.put(entry.getKey(), entry.getValue().toString());
            }
            //处理方法上面的泛型
            if(StringUtils.isNotBlank(methodDoc.getReturnArgs())) {
                int ind = 0;
                for(String arg : methodDoc.getReturnArgs().split(",")) {
                    gmap.put(methodDoc.getReturnType()+"["+ind, arg);
                }
            }
            String methodFinalResult = BeanParser.parseRespone(methodDoc.getRawType(), methodDoc.getReturnType(), methodDoc.getReturnArgs(), methodDoc.getReturnDoc(), gmap);
//            logger.info("方法最终返回结果数据格式:"+methodFinalResult.toJSONString());
//            String respones = BeanParser.respone(methodFinalResult).toJSONString();
//            String respones = methodFinalResult.toJSONString();
            if(methodDoc.getReturnFilter()!=null){
                ReturnFilter returnFilter = methodDoc.getReturnFilter();
                methodFinalResult = returnFilter(methodFinalResult, returnFilter);
            }


            String respones = methodFinalResult;
            logger.info("方法最终返回结果数据格式[格式化后]:"+respones);

            String params = "";
            List<ParameterInfo> parameterInfos = methodDoc.getDocs();
            if(parameterInfos!=null&&!parameterInfos.isEmpty()) {
                JSONArray paramArray = new JSONArray();
                JSONArray paramsFilter = JSON.parseArray(ApiConfig.paramFilter);
                paramsFor:for(ParameterInfo parameterInfo : parameterInfos) {
                    String pt = TypeFormat.unifyType(parameterInfo.getType());
                    if(StringUtils.isNotBlank(pt)) {
                        //普通参数 基本类型参数
                        paramArray.add(TypeFormat.formatType(parameterInfo));
                    }else if(parameterInfo.getType().indexOf(".")==-1) {
                        //用户自定义的，没有小数点隔开的类型
                        paramArray.add(TypeFormat.formatType(parameterInfo));
                    }else {
                        //javabean
                        try {
                            for(int i=0; i<paramsFilter.size(); i++) {
                                JSONObject jb = paramsFilter.getJSONObject(i);
                                if(jb.getString("className").equals(parameterInfo.getType())) {
                                    JSONArray parr = jb.getJSONArray("params");
                                    for(int y=0; y<parr.size(); y++) {
                                        ParameterInfo p = JSON.parseObject(parr.getJSONObject(y).toJSONString(), ParameterInfo.class);
                                        paramArray.add(p);
                                    }
                                    continue paramsFor;
                                }
                            }
                        }catch (Exception e) {
                            logger.error("解释[paramFilter]参数配置时出错，请检查JSON是否写错");
                        }
                        JSON modelobj = BeanParser.parseClass(null, parameterInfo.getRawType(), null, 0, ApiConfig.maxlevel);
                        JSONObject beanobj = null;
                        if(modelobj instanceof JSONObject){
                            beanobj = (JSONObject) modelobj;
                        }
                        System.out.println("uuuu==========>"+beanobj.toJSONString());
                        JSONObject result = new JSONObject(true);
                        for(Map.Entry<String, Object> entry : beanobj.entrySet()) {
                            Object bobj = entry.getValue();
                            ParameterInfo pi = null;
                            if(bobj instanceof JSONArray) {
                                //javabean上的集合属性 属于复杂类型，直接跳过
                                continue;
                            }else if(bobj instanceof JSONObject) {
                                //System.out.println(entry.getKey()+"==========>"+((JSONObject) bobj).toJSONString());
                                pi = JSON.toJavaObject((JSONObject)bobj, ParameterInfo.class);
                                if(ApiConfig.paramsChildBean) {
                                    String piType = pi.getType();
                                    if(StringUtils.isBlank(piType)){
                                        continue;
                                    }else{
                                        String utype = TypeFormat.unifyType(pi.getType());
                                        if(StringUtils.isBlank(utype)) {
                                            //非基本类型
                                            continue;
                                        }
                                    }
                                }
                            }else if(bobj instanceof ParameterInfo) {
                                pi = (ParameterInfo)bobj;
                            }else {

                            }
                            //pi.setRequire("0");//方法上的javabean属性为非必须

                            if(DocArrayUtils.contains(methodDoc.getRequireParameters(), pi.getName())) {
                                pi.setRequire("1");
                            }

                            //如果参数不是排除掉的，加入参数列表
                            if(!pi.getIgnore()){
                                paramArray.add(TypeFormat.formatType(pi));
                            }

                        }
                    }
                }
//    			params = paramArray.toString();

                JSONArray paramArrayFinal = new JSONArray();
                for (Iterator iter = paramArray.iterator(); iter.hasNext();) {
                    ParameterInfo pi = (ParameterInfo) iter.next();
                    if(methodDoc.getIncludeParameters().length>0){
                        if(DocArrayUtils.contains(methodDoc.getIncludeParameters(), pi.getName())) {
                            if(methodDoc.getUnRequireParameters().length>0){
                                if(DocArrayUtils.contains(methodDoc.getUnRequireParameters(), pi.getName())){
                                    pi.setRequire("0");
                                }else{
                                    pi.setRequire("1");
                                }
                            }
                            paramArrayFinal.add(pi);
                        }
                    }else{
                        if(!DocArrayUtils.contains(methodDoc.getIgnoreParameters(), pi.getName())) {
                            if(methodDoc.getUnRequireParameters().length>0){
                                if(DocArrayUtils.contains(methodDoc.getUnRequireParameters(), pi.getName())){
                                    pi.setRequire("0");
                                }else{
                                    pi.setRequire("1");
                                }
                            }
                            paramArrayFinal.add(pi);
                        }
                    }

                }

                params = JSONArray.toJSONString(paramArrayFinal, SerializerFeature.SortField);
//                params = JSONArray.toJSONString(paramArray, SerializerFeature.SortField);
                logger.info("接口参数列表为:"+params);
            }

            String purl = "/"+ApiConfig.project+"/"+StringUtils.trimFirstOrLastChar(classDoc.getPath().trim(), '/', 0)+"/"+StringUtils.trimFirstOrLastChar(methodDoc.getPath().trim(), '/', 1);
//    		String purl = "/"+project+methodDoc.getPath();
            String title = methodDoc.getTitle();
            if(StringUtils.isBlank(methodDoc.getTitle())) {
                title = methodDoc.getMethodName();
            }
            String desc = methodDoc.getRemark();
            if(StringUtils.isBlank(methodDoc.getRemark())) {
                desc = "";
            }
            if(StringUtils.isBlank(params)) {
                params = new JSONArray().toJSONString();
            }

            Map<String, String> requestParams = new HashMap<>();
            requestParams.put("userId", "59883a21d8941f64336acde9");
            requestParams.put("title", title);
            requestParams.put("desc", desc);
            requestParams.put("httpMethod", methodDoc.getHttpMethod());
            requestParams.put("url", purl);
            requestParams.put("treeId", treeId);
            requestParams.put("project", ApiConfig.project);
            requestParams.put("params", params);
            requestParams.put("responses", respones);
            requestParams.put("returnDoc", StringUtils.isBlank(methodDoc.getReturnDoc())?"":methodDoc.getReturnDoc());
            requestParams.put("className", classDoc.getClassName());
            requestParams.put("methodName", methodDoc.getMethodName());
            requestParams.put("methodInfo", methodDoc.getMethodInfo());
            requestParams.put("privateStatus", methodDoc.isPrivateStatus()?"1":"0");

            String result = HttpRequest.sendPost(ApiConfig.host+"/apiManager/testapi/", requestParams);
            logger.info("添加到接口的结果是:"+result);
//            try{
//                title = URLEncoder.encode(title, "UTF-8");
//                desc = URLEncoder.encode(methodDoc.getRemark(), "UTF-8");
//                purl = URLEncoder.encode(purl, "UTF-8");
//                params = URLEncoder.encode(params, "UTF-8");
//                respones = URLEncoder.encode(respones, "UTF-8");
//
//                String apiParam = "userId=59883a21d8941f64336acde9&title="+title+"&desc="+desc+
//                        "&httpMethod="+methodDoc.getHttpMethod()+"&url="+purl+"&treeId="+treeId+
//                        "&project="+project+"&params="+params+"&responses="+respones+"&className="+classDoc.getClassName()+"&methodName="+methodDoc.getMethodName()+"&methodInfo="+methodDoc.getMethodInfo();
//                String result = HttpRequest.sendPost(host+"/apiManager/testapi/", apiParam, 10000, 20000);
//                logger.info("添加到接口的结果是:"+result);
//            }catch (Exception e){
//                e.printStackTrace();
//            }

        }

    }

    public static String returnFilter(String data, ReturnFilter returnFilter){
        try {
            Object obj = JSON.parse(data);
            SerializeFilter serializeFilter = null;
            if("simple".equalsIgnoreCase(returnFilter.type())) {
                SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
                String values = returnFilter.value();
                for (String v : values.split(",")){
                    filter.getExcludes().add(v.trim());
                }
                for (String exclude : returnFilter.excludes()) {
                    filter.getExcludes().add(exclude);
                }
                for (String include : returnFilter.includes()) {
                    filter.getIncludes().add(include);
                }
                filter.setMaxLevel(returnFilter.maxLevel());
                serializeFilter = filter;
            }else if("level".equalsIgnoreCase(returnFilter.type())){
                LevelPropertyPreFilter levelPropertyPreFilter = new LevelPropertyPreFilter();
                levelPropertyPreFilter.addExcludes(returnFilter.excludes());
                levelPropertyPreFilter.addIncludes(returnFilter.includes());
                levelPropertyPreFilter.setMaxLevel(returnFilter.maxLevel());
                String values = returnFilter.value();
                for (String v : values.split(",")){
                    levelPropertyPreFilter.getExcludes().add(v.trim());
                }
                serializeFilter = levelPropertyPreFilter;
            }
            if(returnFilter.sort()){
                return JSON.toJSONString(obj, serializeFilter, SerializerFeature.MapSortField);
            }else {
                return JSON.toJSONString(obj, serializeFilter);
            }
        }catch (Exception e){
            return data;
        }
    }
}
