package com.dashu.lazyapidoc.annotation;


import java.lang.annotation.*;

/**
 * 过滤返回的字段<br/>
 * 可排除某些字段，也可只包含某些字段
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ReturnFilter {
    /**
     * 排除某属性 与 excludes作用一样,多个用逗号隔开
     * @return
     */
    String value() default "";

    /**
     * 只包含某属性
     * @return
     */
    String[] includes() default {};

    /**
     * 排除某属性
     * @return
     */
    String[] excludes() default {};

    /**
     * 层级
     * @return
     */
    int maxLevel() default 0;

    /**
     * 模式 默认 simple:作用所有同名属性
     *          level:作用指定某层级下的属性
     * @return
     */
    String type() default "simple";

    /**
     * 排序
     * @return
     */
    boolean sort() default true;

    /**
     * 属性前缀
     * @return
     */
    String prefix() default "";
}
