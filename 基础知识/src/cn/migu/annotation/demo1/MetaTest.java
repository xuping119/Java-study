package cn.migu.annotation.demo1;

import java.lang.annotation.*;

//测试注解的四种元注解
//@MyAnnotation
public class MetaTest {

    //@MyAnnotation
    public static void test1() {

    }
}

/** 自定义注解：四种元注解
 * Target: 定义注解使用的范围，函数，类，字段，等等
 * Retention: 定义注解生效的范围，一般使用RUNTIME(runtime > class > source)
 * Documented: 说明注解是否出现在javadoc中
 * Inherited: 说明子类可以继承父类的注解
 */

@Target(value = {ElementType.METHOD,ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
@Inherited
@interface MyAnnotation{

}

