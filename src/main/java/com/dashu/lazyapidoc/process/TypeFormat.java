package com.dashu.lazyapidoc.process;

import com.dashu.lazyapidoc.annotation.ParameterInfo;
import com.dashu.lazyapidoc.util.StringUtils;

import java.lang.reflect.Type;

public class                                                                                                                                        TypeFormat {

    public static ParameterInfo formatType(ParameterInfo pi) {
        if(StringUtils.isNotBlank(pi.getType())) {
            if(pi.getType().indexOf("<")>=0) {
                //泛型类型数据
                pi.setRequire("0").setStatus("0");//无效 非必须
            }else {
                //格式化数据类型 小写 .最后一位单词
                String type = StringUtils.lowerCase(pi.getType());
                String utype = unifyType(type);
                if(StringUtils.isNotBlank(utype)) {
                    //基本数据类型
                }else {
                    //复杂类型，请求参数一般为基本类型，复杂类型难以处理，故设置为无效
                    pi.setRequire("0").setStatus("0");//无效 非必须
                }
                String types[] = type.split("\\.");
                type = types[(types.length-1)];
                pi.setType(type);
            }
        }
        return pi;
    }

    /**
     * CLASS类型格式化保留最后一个字段
     * @param className
     * @return
     */
    public static String unifyType(String className){
        String[] cPaths = className.split("\\.");
        String rawType = cPaths[cPaths.length - 1];
        if ("byte".equalsIgnoreCase(rawType)) {
            return "byte";
        } else if ("short".equalsIgnoreCase(rawType)) {
            return "short";
        } else if ("int".equals(rawType) || "Integer".equalsIgnoreCase(rawType)) {
            return "int";
        } else if ("long".equalsIgnoreCase(rawType)) {
            return "long";
        } else if ("float".equalsIgnoreCase(rawType)) {
            return "float";
        } else if ("double".equalsIgnoreCase(rawType)) {
            return "double";
        } else if ("boolean".equalsIgnoreCase(rawType)) {
            return "boolean";
        } else if ("char".equalsIgnoreCase(rawType) || "Character".equalsIgnoreCase(rawType)) {
            return "char";
        } else if ("String".equalsIgnoreCase(rawType)) {
            return "string";
        } else if ("date".equalsIgnoreCase(rawType) || "ZonedDateTime".equalsIgnoreCase(rawType)) {
            return "date";
        } else if ("file".equalsIgnoreCase(rawType)) {
            return "file";
        } else if ("byte[]".equalsIgnoreCase(rawType)) {
            return "byte";
        } else if ("short[]".equalsIgnoreCase(rawType)) {
            return "short";
        } else if ("int[]".equals(rawType) || "Integer[]".equals(rawType)) {
            return "int";
        } else if ("long[]".equalsIgnoreCase(rawType)) {
            return "long";
        } else if ("float[]".equalsIgnoreCase(rawType)) {
            return "float";
        } else if ("double[]".equalsIgnoreCase(rawType)) {
            return "double";
        } else if ("boolean[]".equalsIgnoreCase(rawType)) {
            return "boolean";
        } else if ("char[]".equalsIgnoreCase(rawType) || "Character[]".equalsIgnoreCase(rawType)) {
            return "char";
        } else if ("String[]".equalsIgnoreCase(rawType)) {
            return "string";
        } else if ("date[]".equalsIgnoreCase(rawType) || "ZonedDateTime[]".equalsIgnoreCase(rawType)) {
            return "date";
        } else if ("file[]".equalsIgnoreCase(rawType)) {
            return "file";
        } else {
            return null;
        }
    }

    public static boolean ignoreParameter(Type type){
        if(type.getTypeName().startsWith("javax.servlet.")){
            return true;
        }
//        if("javax.servlet.http.HttpServletResponse".equals(type.getTypeName())){
//            return true;
//        }
//        if("javax.servlet.http.HttpServletRequest;".equals(type.getTypeName())){
//            return true;
//        }
        return false;
    }
}
