package com.dashu.lazyapidoc.process;

public class ApiConfig {
    public static String host = "http://10.201.78.119";
    public static String project = "mydemo";
    public static String responseFormatString = "[{type}] {chineseName}【{remark}】";
    public static String[] replaceToNullConfig = {"\\[\\{type\\}\\]","\\{chineseName\\}", "【\\{remark\\}】"};
    public static boolean paramsChildBean = true;//是否跳过请求时复杂参数的处理 springmvc 对请求参数的类型支持有限
//    public static String rootClass = "com.dashu.base.common.model.ResultMsg";
    public static String rootClass = "";
    public static String returnDocParameter = "data";
    public static String paramFilter = "[{\"className\":\"com.dashu.base.common.model.Token\", \"params\":[{\"chineseName\":\"身份令牌\",\"format\":\"\",\"name\":\"token\",\"remark\":\"\",\"require\":\"1\",\"type\":\"string\",\"status\":\"1\"}]},"
            + "{\"className\":\"org.springframework.web.multipart.MultipartFile\", \"params\":[{\"chineseName\":\"文件\",\"format\":\"\",\"name\":\"file\",\"remark\":\"\",\"require\":\"1\",\"type\":\"file\",\"status\":\"1\"}]}]";
    public static int maxlevel = 3;
    public static boolean createFinalField = false;
    public static boolean createFieldRemark = false;//是否对含有子属性的字段生成系统相关的说明
}
