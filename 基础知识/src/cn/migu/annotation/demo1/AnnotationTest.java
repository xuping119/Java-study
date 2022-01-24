package cn.migu.annotation.demo1;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//自定义注解
public class AnnotationTest {

    @MyAnnotation1(name = "小许")
    public void test1(){
    }

    @MyAnnotation2("小王")
    public void test2(){
    }
}

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation1{  //多个参数情况

    //参数定义格式：参数类型 + 参数名称 + (); + default 默认值
    String name();
    int age() default 18;
    String[] schools() default {"清华","北大","南大"};
}

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation2{  //单个参数情况

    //参数定义格式：参数类型 + 参数名称 + (); + default 默认值
    String value();  //参数名称为value，使用时候可以省略
}