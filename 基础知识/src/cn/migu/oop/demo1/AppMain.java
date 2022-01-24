package cn.migu.oop.demo1;

public class AppMain {
    public static void main(String[] args) {

        Person o1 = new Student();
        o1.show();
        Person o2 = (Person)o1;
        o2.show();

        System.out.println("****************************");
        Person o3 = new Teacher();
        o3.show();
        Person o4 = (Person)o3;
        o4.show();

        System.out.println("****************************");
        Student s1 = new Student();
        s1.say();

        System.out.println("****************************");
        Teacher t1 = new Teacher();
        t1.eat();
    }
}
