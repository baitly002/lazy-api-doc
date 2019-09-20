package com.dashu.lazyapidoc.annotation;


import java.lang.annotation.*;

/**
 * 参数过滤<br/>
 * 可包含某些参数，也可排除某些参数，也可设置某些参数为必须
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParameterFilter {
    /**
     * 排除某参数 与 excludes作用一样,多个用逗号隔开
     * @return
     */
    String value() default "";

    /**
     * 只包含某参数
     * @return
     */
    String[] includes() default {};

    /**
     * 排除某参数
     * @return
     */
    String[] excludes() default {};

    /**
     * 必须的参数 与unRequires互斥
     * @return
     */
    String[] requires() default {};

    /**
     * 非必须的参数，除此之外的为必须 与requires互斥
     * @return
     */
    String[] unRequires() default {};

}
