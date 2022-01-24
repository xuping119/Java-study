package cn.migu.annotation.demo2;

/**
 * 通过自定义注解，将实体类 Student 与 数据库表db_student 关联起来
 */
@ORMAnnotationTable("db_student")
public class Student {
    @ORMAnnotationField(value = "db_name",type = "varchar")
    private String name;
    @ORMAnnotationField(value = "db_id",type = "int")
    private int id;
    @ORMAnnotationField(value = "db_age",type = "int")
    private int age;

    public Student() {
    }

    public Student(String name, int id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", age=" + age +
                '}';
    }
}
