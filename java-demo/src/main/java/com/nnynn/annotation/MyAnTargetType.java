package com.nnynn.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义一个可以注解在Class上的注解
 *
 * @author bo.luo
 * @date 2020/11/27 16:15
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnTargetType {

    String value() default "类上注解默认值";
}
