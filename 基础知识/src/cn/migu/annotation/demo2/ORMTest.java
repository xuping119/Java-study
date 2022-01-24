package cn.migu.annotation.demo2;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class ORMTest {
    public static void main(String[] args) throws ClassNotFoundException {
        //获取实体类 Student 的注解，并转换为SQL

        Class clzz = Class.forName("cn.migu.annotation.demo2.Student");
        Annotation[] annotations = clzz.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }

        //获取指定注解的值
        ORMAnnotationTable annotation = (ORMAnnotationTable) clzz.getAnnotation(ORMAnnotationTable.class);
        System.out.println(annotation.value());

        ORMAnnotationField ormAnnotationField;
        Field[] fields = clzz.getDeclaredFields();
        for (Field field : fields) {
            ormAnnotationField = field.getAnnotation(ORMAnnotationField.class);
            System.out.println(ormAnnotationField.value());
        }
    }
}
