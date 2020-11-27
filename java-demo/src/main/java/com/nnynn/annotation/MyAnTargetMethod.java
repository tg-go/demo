package com.nnynn.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义一个注解在方法上的注解
 *
 * @author bo.luo
 * @date 2020/11/27 16:17
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnTargetMethod {

    String value() default "定义在方法中的注解默认值";
}
