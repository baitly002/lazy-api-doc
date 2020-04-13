package com.dashu.lazyapidoc.process;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.dashu.lazyapidoc.annotation.Doc;
import com.dashu.lazyapidoc.annotation.ParameterInfo;
import com.dashu.lazyapidoc.annotation.ReturnFilter;
import com.dashu.lazyapidoc.util.ParameterizedTypeImpl;
import com.dashu.lazyapidoc.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BeanParser {
    public static Logger logger = LoggerFactory.getLogger(BeanParser.class);

    public static JSON parseClass(JSONObject json, Type rawType, Map<String, Type> paramMap, int level, int maxLevel){
        if(json==null) {
            json = new JSONObject(true);
        }
        if(paramMap == null){
            paramMap = new HashMap<>();
        }
        if(rawType != null) {
            if (rawType instanceof ParameterizedType) {
                //ParameterizedType表示一个参数化类型，如Collection <String>
                //泛型Class
                ParameterizedType parameterizedType = (ParameterizedType) rawType;
                Class<?> clazz = (Class) parameterizedType.getRawType();
                //泛型类
                TypeVariable var[] = clazz.getTypeParameters();
                int index = 0;
                for(TypeVariable v :var){
                    System.out.println(v.getName() +"==>"+v.getGenericDeclaration());
                    paramMap.put(v.getName(), getGenericClassType(parameterizedType, index));
                    index++;
                }
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                boolean flag = false;
                for(Type actualTypeArgument : actualTypeArguments){
                    if(actualTypeArgument instanceof TypeVariable){
                        flag = true;
                    }
                }
                if(flag){
                    //是带参数的泛型类  需要转换获得最终类型
                    Type finalType = forParameterizedType(parameterizedType, paramMap);
                    parameterizedType = (ParameterizedType)finalType;
                }

//            System.out.println(parameterizedType.getRawType().getTypeName());
                Type t = parameterizedType.getRawType();
                if(Collection.class.isAssignableFrom((Class)t)){
                    JSONArray jsonArray = new JSONArray();
//                    Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                    for(Type tp : actualTypeArguments){//集合只有一个参数
                        JSON r = parseClass(null, tp, paramMap, level, maxLevel);
                        jsonArray.add(r);
                    }
                    return jsonArray;
                }else if(Map.class.isAssignableFrom((Class) t)){
                    JSONObject jobj = new JSONObject(true);
//                    Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                    for(Type tp : actualTypeArguments) {//Map有多个参数
                        JSON r = parseClass(null, tp, paramMap, level, maxLevel);
                        jobj.put("unknow_", r);
                    }
                    return jobj;
                }else{
                    return parseClass(null, parameterizedType.getRawType(), paramMap, level, maxLevel);
                }

            } else if (rawType instanceof TypeVariable) {
                //TypeVariable是类型变量的常用超级接口
                System.out.println("TypeVariable==>" + rawType.getTypeName());
            } else if (rawType instanceof GenericArrayType) {
                //GenericArrayType表示组件类型是参数化类型或类型变量的数组类型。
                System.out.println("GenericArrayType==>" + rawType.getTypeName());
            } else if (rawType instanceof WildcardType) {
                //WildcardType表示一个通配符型表达，如 ? ， ? extends Number ，或 ? super Integer
                System.out.println("WildcardType==>" + rawType.getTypeName());
            } else {
                //PrimitiveType表示原始类型。 这些措施包括boolean ， byte ， short ， int ， long ， char ， float和double
                if(level<maxLevel){
                    Class c = (Class) rawType;
                    if (c.isPrimitive()) {
                        System.out.println("基本类型==>" + rawType.getTypeName());
                        ParameterInfo parameterInfo = new ParameterInfo("", rawType);
                        return JSON.parseObject(JSON.toJSONString(parameterInfo));
                    } else {
                        System.out.println("引用类型==>" + rawType.getTypeName());
                    }
                    Field[] fields = FieldParser.getAllFields(c);
                    for (Field field : fields) {
                        //忽略serialVersionUID属性
                        if("serialVersionUID".equals(field.getName())){
                            continue;
                        }
                        //根据配置，是否忽略final修饰的属性
                        if(!ApiConfig.createFinalField){
                            if(Modifier.isFinal(field.getModifiers())){
                                continue;
                            }
                        }
                        //忽略transient修饰的属性
                        if(Modifier.isTransient(field.getModifiers())){
                            continue;
                        }
                        //忽略@JSONField(serialize = false)注解的属性
                        JSONField jsonField = field.getAnnotation(JSONField.class);
                        if(jsonField!=null){
                            if(!jsonField.serialize()){
                                continue;
                            }
                        }
                        //忽略@JsonIgnore注解的属性
                        if(byName("com.fasterxml.jackson.annotation.JsonIgnore")) {
                            JsonIgnore jsonIgnore = field.getAnnotation(JsonIgnore.class);
                            if(jsonIgnore!=null){
                                continue;
                            }
                        }
                        Type type = field.getGenericType();
                        String fieldType = type.getTypeName();
                        String ut = TypeFormat.unifyType(fieldType);
                        if (StringUtils.isNotBlank(ut)) {
                            fieldType = ut;
                        }
//                        ParameterInfo parameterInfo = new ParameterInfo(field.getName(), fieldType);
                        ParameterInfo parameterInfo = new ParameterInfo(field.getName(), type);
                        Doc doc = field.getAnnotation(Doc.class);
                        if (doc != null) {
                            if (StringUtils.isNotBlank(doc.value())) {
                                parameterInfo.setChineseName(doc.value());
                            }
                            if (StringUtils.isNotBlank(doc.remark())) {
                                parameterInfo.setRemark(doc.remark());
                            }
                            if (StringUtils.isNotBlank(doc.dataType())) {
                                parameterInfo.setType(doc.dataType());
                            }
                            if (StringUtils.isNotBlank(doc.example())) {
                                parameterInfo.setExample(doc.example());
                            }
                            if (StringUtils.isNotBlank(doc.defaultValue())) {
                                parameterInfo.setDefaultValue(doc.defaultValue());
                            }
                            parameterInfo.setRequire(doc.required() ? "1" : "0");
                            parameterInfo.setIgnore(doc.ignore());
//                    logger.info("成员变量:" + field.getName() + "；含义:" + doc.value() + "；备注:" + doc.remark());
                        } else {
//                    logger.info("成员变量:" + field.getName());
                        }
                        if (type instanceof ParameterizedType) {
                            if (doc != null) {
//                            json.put(field.getName() + "_", doc.value() + ";备注：" + doc.remark());
                                if(ApiConfig.createFieldRemark){
                                    json.put(field.getName() + "_", parameterInfo);
                                }
                            }
                            //最终泛型
                            type = forParameterizedType((ParameterizedType)type, paramMap);
                            json.put(field.getName(), parseClass(null, type, paramMap, level+1, maxLevel));
                        }else if(type instanceof TypeVariable){
                            Type vt = paramMap.get(type.getTypeName());
                            if (doc != null) {
//                            json.put(field.getName() + "_", doc.value() + ";备注：" + doc.remark());
                                if(ApiConfig.createFieldRemark){
                                    json.put(field.getName() + "_", parameterInfo);
                                }
                            }
                            if(isPrimitive(vt.getTypeName()) || StringUtils.isNotBlank(TypeFormat.unifyType(vt.getTypeName()))){
//                                parameterInfo.setType(vt.getTypeName());
//                                parameterInfo.setRawType(vt);
                                ParameterInfo parameterInfoPrimitive = new ParameterInfo(field.getName(), vt).setRequire("0");
                                json.put(field.getName(), parameterInfoPrimitive);
                            }else{
                                json.put(field.getName(), parseClass(null, vt, paramMap, level+1, maxLevel));
                            }

                        } else {
                            Class fieldClass = (Class) field.getGenericType();
                            if (fieldClass.isPrimitive()) {
                                json.put(field.getName(), parameterInfo);
                            } else {
                                if(StringUtils.isNotBlank(TypeFormat.unifyType(fieldClass.getTypeName()))){
                                    json.put(field.getName(), parameterInfo);
                                }else{

                                    json.put(field.getName(), parseClass(null, field.getGenericType(), paramMap, level+1, maxLevel));
                                }
                            }

                        }
                    }
                }
            }
        }
        return json;
    }

    public static Type getGenericClassType(Type type, int index) {
        if (!(type instanceof ParameterizedType)) {
            return Object.class;
        }
        Type[] params = ((ParameterizedType) type).getActualTypeArguments();
        if(type instanceof ParameterizedType ){
            if (index >= params.length || index < 0) {
                return Object.class;

            }
        }

        return params[index];
    }

    public static Type forParameterizedType(ParameterizedType rawType, Map<String, Type> map){
        Type[] args = rawType.getActualTypeArguments();
        Type[] argsNew = new Type[args.length];
        int index = 0;
        for(Type tp :args){
            if(tp instanceof ParameterizedType){
                argsNew[index] = forParameterizedType((ParameterizedType)tp, map);
                System.out.println("arg==>ParameterizedType==>"+tp.getTypeName());
            }else if(tp instanceof TypeVariable){
                Type vt = map.get(tp.getTypeName());
                argsNew[index] = vt;
                System.out.println("arg==>TypeVariable==>"+tp.getTypeName());
            }else if(tp instanceof GenericArrayType){
                System.out.println("arg==>GenericArrayType==>"+tp.getTypeName());
            }else if(tp instanceof WildcardType ){
                System.out.println("arg==>WildcardType==>"+tp.getTypeName());
            }else{
                Class c = (Class)tp;
                if(c.isPrimitive()){
                    System.out.println("arg==>基本类型==>"+tp.getTypeName());
                }else{
                    System.out.println("arg==>引用类型==>"+tp.getTypeName());
                }
                argsNew[index] = tp;
            }
            index++;
        }
        if(argsNew.length>0){
            ParameterizedType newType = new ParameterizedTypeImpl((Class)rawType.getRawType(), argsNew, null);
            return newType;
        }
        return rawType;
    }

    public static JSON respone(JSON json, String formatStr) {
        if(StringUtils.isBlank(formatStr)){
            formatStr = "[{type}]{chineseName}【{remark}】";
        }
        if(json instanceof JSONObject){
            JSONObject jsonObject = (JSONObject) json;
            JSONObject result = new JSONObject(true);
            for(Map.Entry<String, Object> entry : jsonObject.entrySet()) {
                logger.info("JSON KEY==="+entry.getKey());
                Object obj = entry.getValue();
                if(obj instanceof JSONObject) {
                    result.put(entry.getKey(), respone((JSONObject)obj, formatStr));
//                    for(Map.Entry<String, Object> ent : ((JSONObject) obj).entrySet()){
//                        result.put(ent.getKey(), respone(ent.getValue(), formatStr));
//                    }

//                    ParameterInfo pi = JSON.toJavaObject((JSONObject)obj, ParameterInfo.class);
//                    if(StringUtils.isNotBlank(pi.getName()) && StringUtils.isNotBlank(pi.getRequire())) {
//                        String valueStr = formatStr;
//                        String simpleTypeName = TypeFormat.unifyType(pi.getType());
//                        if(StringUtils.isNotBlank(simpleTypeName)){
//                            valueStr = valueStr.replaceAll("\\{type\\}", simpleTypeName);
//                        }
//                        if(StringUtils.isNotBlank(pi.getChineseName())){
//                            valueStr = valueStr.replaceAll("\\{chineseName\\}", pi.getChineseName());
//                        }
//                        if(StringUtils.isNotBlank(pi.getRemark())){
//                            valueStr = valueStr.replaceAll("\\{remark\\}", pi.getRemark());
//                        }
//                        valueStr = valueStr.replaceAll("\\{type\\}", "").replaceAll("\\{chineseName\\}", "").replaceAll("\\{remark\\}", "");
//                        result.put(entry.getKey(), valueStr);
////                        if(StringUtils.isBlank(pi.getRemark())) {
////                            result.put(entry.getKey(), "["+simpleTypeName+"]"+pi.getChineseName());
////                        }else {
////                            result.put(entry.getKey(), "["+simpleTypeName+"]"+pi.getChineseName()+"  备注:"+pi.getRemark());
////                        }
//                    }else {
//                        result.put(entry.getKey(), respone((JSONObject)obj, formatStr));
//                    }
                }else {
                    if(obj instanceof ParameterInfo) {
                        ParameterInfo pi = (ParameterInfo)obj;
                        String valueStr = formatStr;
                        String simpleTypeName = TypeFormat.unifyType(pi.getType());
                        if(StringUtils.isNotBlank(simpleTypeName)){
                            valueStr = valueStr.replaceAll("\\{type\\}", simpleTypeName);
                        }
                        if(StringUtils.isNotBlank(pi.getChineseName())){
                            valueStr = valueStr.replaceAll("\\{chineseName\\}", pi.getChineseName());
                        }
                        if(StringUtils.isNotBlank(pi.getRemark())){
                            valueStr = valueStr.replaceAll("\\{remark\\}", pi.getRemark());
                        }
                        if(ApiConfig.replaceToNullConfig.length>0){
                            for(String r : ApiConfig.replaceToNullConfig){
                                valueStr = valueStr.replaceAll(r, "");
                            }
                        }
//                        valueStr = valueStr.replaceAll("\\{type\\}", "").replaceAll("\\{chineseName\\}", "").replaceAll("\\{remark\\}", "");
                        result.put(entry.getKey(), valueStr);
//                        if(StringUtils.isBlank(pi.getRemark())) {
//                            result.put(entry.getKey(), "["+simpleTypeName+"]"+pi.getChineseName());
//                        }else {
//                            result.put(entry.getKey(), "["+simpleTypeName+"]"+pi.getChineseName()+"  备注:"+pi.getRemark());
//                        }
                    }else {
                        if(obj instanceof JSONArray) {
                            JSONArray array = (JSONArray)obj;
//                            logger.info(array.toJSONString());
                            JSONArray jsonArray = new JSONArray();
                            for(int i=0; i<array.size(); i++) {
                                Object o = array.get(i);
//                                JSONObject o = array.getJSONObject(i);
                                if(o instanceof JSONObject){
                                    jsonArray.add(respone((JSONObject)o, formatStr));
                                }else if(o instanceof JSONArray){
                                    jsonArray.add(respone((JSONArray)o, formatStr));
                                }else{

                                }
                                result.put(entry.getKey(), jsonArray);
                            }
                        }
                    }
                }
            }
            return result;
        }else if(json instanceof JSONArray){
            JSONArray array = (JSONArray)json;
//            logger.info(array.toJSONString());
            JSONArray jsonArray = new JSONArray();
            for(int i=0; i<array.size(); i++) {
                Object o = array.get(i);
                if(o instanceof JSONObject){
                    jsonArray.add(respone((JSONObject)o, formatStr));
                }else if(o instanceof JSONArray){
                    jsonArray.add(respone((JSONArray)o, formatStr));
                }else{

                }
//                result.put(entry.getKey(), jsonArray);
            }
            return jsonArray;
        }else{
            return new JSONObject();
        }


    }

    public static String parseRespone(ReturnFilter returnFilter, Type rawType, String returnType, String returnArgs, String returnDoc, HashMap<String, String> genericMap) {
        String dataRemark = "";
        String rawTypeName = rawType.getTypeName();
        JSON result = null;
        if(StringUtils.isNotBlank(ApiConfig.rootClass) && (returnFilter == null || !returnFilter.ignoreCriterion())){
            try {
                Class<?> rootClazz = Class.forName(ApiConfig.rootClass);
//                ParameterizedType parameterizedType = new ParameterizedTypeImpl(rootClazz, new Type[]{rawType}, null);
                ParameterizedType parameterizedType = new ParameterizedTypeImpl(rootClazz, new Type[]{rawType}, null);
                rawType = parameterizedType;

            }catch (Exception e){
                logger.error("无法初始rootClass:"+ApiConfig.rootClass);
                e.printStackTrace();
            }
        }
//        result = forActualTypeArguments(rawType, returnType, maxLevel);
        result = parseClass(null, rawType, null, 0, ApiConfig.maxlevel);
        if(StringUtils.isNotBlank(ApiConfig.rootClass)){
            if(StringUtils.isNotBlank(ApiConfig.returnDocParameter)){
                if(result instanceof JSONObject){
                    JSONObject jsonObject = (JSONObject) result;
                    Object obj = jsonObject.get(ApiConfig.returnDocParameter+"_");
                    if(obj instanceof ParameterInfo){
                        ParameterInfo parameterInfo = (ParameterInfo) obj;
                        parameterInfo.setRemark(returnDoc);
                    }

                    //将@ReturnDoc注解的内容追加到data节点上
                    Object data = jsonObject.get(ApiConfig.returnDocParameter);
                    if(data instanceof ParameterInfo){
                        ParameterInfo parameterInfo = (ParameterInfo) data;
                        parameterInfo.setRemark(returnDoc);
                    }
                }
            }
        }
        logger.info("返回的顶层数据格式为:"+result.toJSONString());

//    	logger.info("最终返回的数据格式:"+respone(jsonObject).toJSONString());
        String resultFinal = respone(result, ApiConfig.responseFormatString).toJSONString();
        if("void".equals(rawTypeName)){
            resultFinal = "[void]请求无需返回数据";
            return resultFinal;
        }else{
            if("{}".equals(resultFinal) || "[]".equals(resultFinal)){
                //boolean ， byte ， short ， int ， long ， char ， float和double
                if(isPrimitive(rawType.getTypeName())){
                    resultFinal = "["+rawType.getTypeName()+"]请求返回基本类型数据";
                    if(StringUtils.isNotBlank(returnDoc)){
                        resultFinal = resultFinal+",备注："+returnDoc;
                    }
                    return resultFinal;
                }else{
                    resultFinal = "["+rawType.getTypeName()+"]请求返回的数据暂时无法解释";
                    if(StringUtils.isNotBlank(returnDoc)){
                        resultFinal = resultFinal+",备注："+returnDoc;
                    }
                    return resultFinal;
                }
            }else{
                return resultFinal;
            }
        }


    }

    public static boolean isPrimitive(String returnType){
        //boolean ， byte ， short ， int ， long ， char ， float和double
        if("boolean".equals(returnType)){
            return true;
        }else if("byte".equals(returnType)){
            return true;
        }else if("short".equals(returnType)){
            return true;
        }else if("int".equals(returnType)){
            return true;
        }else if("long".equals(returnType)){
            return true;
        }else if("char".equals(returnType)){
            return true;
        }else if("float".equals(returnType)){
            return true;
        }else if("double".equals(returnType)){
            return true;
        }else{
            return false;
        }
    }

    public static boolean byName(String className) {
        boolean flag = true;
        try {
            flag = null != Class.forName(className);
        } catch (Exception e) {
//            if(logger.isWarnEnabled()) {
//                logger.warn("系统中找不到{}类，请检查是否要用到相应的功能，如果没用到请忽略此警告！", className, e);
//            }
            flag = false;
        }
        return flag;
    }

}
