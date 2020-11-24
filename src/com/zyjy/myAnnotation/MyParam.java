package com.zyjy.myAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//@Target(ElementType.TYPE)
@Target(ElementType.PARAMETER)//定义在参数的头上
@Retention(RetentionPolicy.RUNTIME)//保留（生命周期）：RetentionPolicy定义何时有效
public @interface MyParam {
    String value();
}
