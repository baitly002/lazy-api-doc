package com.dashu.lazyapidoc.process;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FieldParser {
    /**
     * 取得类成员变量 ，包括父类
     * @param clazz
     * @return
     */
    public static Field[] getAllFields(Class<?> clazz) {
//    	List<Field> fieldList = new ArrayList<>();
//    	while (clazz != null) {
//    		fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
//    		clazz = clazz.getSuperclass();
//    	}
        List<Field> fieldList = getAllFields(clazz, null);
        Field[] fields = new Field[fieldList.size()];
        fieldList.toArray(fields);
        return fields;
    }

    /**
     * 取得类成员变量 ，包括父类
     * @param clazz
     * @return
     */
    public static List<Field> getAllFields(Class<?> clazz, List<Field> fieldList) {
        if(fieldList == null) {
            fieldList = new ArrayList<>();
        }
        if (clazz != null) {
            Class<?> superClazz = clazz.getSuperclass();
            if(superClazz!=null) {
                getAllFields(superClazz, fieldList);
            }
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
        }
        return fieldList;
    }
}
