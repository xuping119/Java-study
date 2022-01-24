package cn.migu.reflect.demo1;

//获取Class类的几种方式
public class ClassTest {
    public static void main(String[] args) throws ClassNotFoundException {
        Person person =  new Student();
        System.out.println("这个人是："+person.getName());

        //方式一  通过对象获得
        Class c1 = person.getClass();
        System.out.println(c1.getName()+", "+c1.hashCode());


        //方式二 通过包名获得
        Class c2 = Class.forName("cn.migu.reflect.demo1.Student");
        System.out.println(c2.getName()+", "+c2.hashCode());

        //方式三 通过类名获得
        Class c3 = Student.class;
        System.out.println(c3.getName()+", "+c3.hashCode());

        //获取父类的Class对象
        Class c4 = c3.getSuperclass();
        System.out.println(c4.getName()+", "+c4.hashCode());
    }
}

class Person{
    private String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Student extends Person{

    public Student() {
        super("学生");
    }
}

class Teacher extends Person{
    public Teacher( ) {
        super("老师");
    }
}