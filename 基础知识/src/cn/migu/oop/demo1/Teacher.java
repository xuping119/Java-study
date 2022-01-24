package cn.migu.oop.demo1;

public class Teacher extends Person{
    private String name = "teacher";

    @Override
    public void show() {
        System.out.println(name);
    }

    public void say(){
        System.out.println("Teacher say");
    }
}
