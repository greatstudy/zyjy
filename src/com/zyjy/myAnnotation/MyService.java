package com.zyjy.myAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)//类的注解
@Retention(RetentionPolicy.RUNTIME)
public @interface MyService {
    String value() ;
}
