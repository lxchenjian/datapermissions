package com.data.permissions.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(ElementType.METHOD)
@Retention(RUNTIME)
public @interface DataPermission {


    String value() default "";
    boolean openOrClose() default true;


}
