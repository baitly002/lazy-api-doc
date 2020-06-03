package com.dashu.lazyapidoc.annotation;

import com.dashu.lazyapidoc.util.StringUtils;

import java.io.Serializable;

public class ReturnFilterConfig implements Serializable {

    public ReturnFilterConfig(){

    }

    public ReturnFilterConfig(ReturnFilter returnFilter){
        this.value = returnFilter.value();
        this.includes = returnFilter.includes();
        this.excludes = returnFilter.excludes();
        this.maxLevel = returnFilter.maxLevel();
        this.type = returnFilter.type();
        this.sort = returnFilter.sort();
        this.prefix = returnFilter.prefix();
        this.ignoreCriterion = returnFilter.ignoreCriterion();
    }

    public void merge(ReturnFilter returnFilter){
        //下面是合并
        if(StringUtils.isNotBlank(this.value) && StringUtils.isNotBlank(returnFilter.value())){
            this.value = this.value+","+returnFilter.value();
        }
        if(StringUtils.isBlank(this.value) && StringUtils.isNotBlank(returnFilter.value())){
            this.value = returnFilter.value();
        }


        // 合并两个数组
        String[] includeArr = new String[this.includes.length + returnFilter.includes().length];
        System.arraycopy(this.includes, 0, includeArr, 0, this.includes.length);
        System.arraycopy(returnFilter.includes(), 0, includeArr, this.includes.length, returnFilter.includes().length);
        this.includes = includeArr;

        String[] excludeArr = new String[this.excludes.length + returnFilter.excludes().length];
        System.arraycopy(this.excludes, 0, excludeArr, 0, this.excludes.length);
        System.arraycopy(returnFilter.excludes(), 0, includeArr, this.excludes.length, returnFilter.excludes().length);
        this.excludes = excludeArr;

        //下面是覆盖
        this.maxLevel = returnFilter.maxLevel();
        this.type = returnFilter.type();
        this.sort = returnFilter.sort();
        this.prefix = returnFilter.prefix();
        this.ignoreCriterion = returnFilter.ignoreCriterion();
    }

    /**
     * 排除某属性 与 excludes作用一样,多个用逗号隔开
     * @return
     */
    String value;

    /**
     * 只包含某属性
     * @return
     */
    String[] includes;

    /**
     * 排除某属性
     * @return
     */
    String[] excludes;

    /**
     * 层级
     * @return
     */
    int maxLevel;

    /**
     * 模式 默认 simple:作用所有同名属性
     *          level:作用指定某层级下的属性
     * @return
     */
    String type;

    /**
     * 排序
     * @return
     */
    boolean sort;

    /**
     * 属性前缀
     * @return
     */
    String prefix;

    boolean ignoreCriterion;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String[] getIncludes() {
        return includes;
    }

    public void setIncludes(String[] includes) {
        this.includes = includes;
    }

    public String[] getExcludes() {
        return excludes;
    }

    public void setExcludes(String[] excludes) {
        this.excludes = excludes;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSort() {
        return sort;
    }

    public void setSort(boolean sort) {
        this.sort = sort;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public boolean isIgnoreCriterion() {
        return ignoreCriterion;
    }

    public void setIgnoreCriterion(boolean ignoreCriterion) {
        this.ignoreCriterion = ignoreCriterion;
    }
}
