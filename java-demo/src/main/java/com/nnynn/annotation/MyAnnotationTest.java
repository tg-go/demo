package com.nnynn.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author bo.luo
 * @date 2020/11/27 16:20
 */
@MyAnTargetType
public class MyAnnotationTest {

    @MyAnTargetField
    private String field = "我是字段";

    @MyAnTargetMethod
    public void test(@MyAnTargetParameter String args) {
        System.out.println("参数=" + args);
    }


    public static void main(String[] args) {
        MyAnTargetType myAnTargetType = MyAnnotationTest.class.getAnnotation(MyAnTargetType.class);
        System.out.println("类上的注解=" + myAnTargetType.value());

        MyAnTargetMethod myAnTargetMethod = null;
        try {
            Method method = MyAnnotationTest.class.getDeclaredMethod("test", String.class);
            myAnTargetMethod = method.getAnnotation(MyAnTargetMethod.class);
            System.out.println("方法" + method.getName() + "上的注解=" + myAnTargetMethod.value());

            Annotation[][] annotations = method.getParameterAnnotations();
            for (Annotation[] tt : annotations) {
                for (Annotation t : tt) {
                    if (t instanceof MyAnTargetParameter) {
                        System.out.println("参数上的注解=" + ((MyAnTargetParameter) t).value());
                    }
                }
            }

            method.invoke(new MyAnnotationTest(), "哈哈哈哈");
            MyAnTargetField myAnTargetField = MyAnnotationTest.class.getDeclaredField("field").getAnnotation(MyAnTargetField.class);
            System.out.println("属性上的注解=" + myAnTargetField.value());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
