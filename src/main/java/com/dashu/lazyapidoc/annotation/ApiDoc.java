package com.dashu.lazyapidoc.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiDoc {
	Doc[] value();
}
