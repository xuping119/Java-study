package cn.migu.oop.demo1;

public class Student extends Person{

    private String name = "student";

    @Override
    public void show() {
        System.out.println(name);
    }

    public void say(){
        super.say();
        System.out.println("Student say");
    }
}
