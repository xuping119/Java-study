package cn.migu.function.demo1;

import jdk.nashorn.internal.objects.annotations.Getter;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class StreamTest {
    /*
    *题目：对五个用户进行筛选，
    * 1.ID必须是偶数
    * 2.年龄大于30岁
    * 3.用户名转换为大写
    * 4.用户名字母倒序排序
    * 5.只输出一个用户
     */
    public static void main(String[] args) {
        User u1 = new User(1,"a",29);
        User u2 = new User(2,"b",30);
        User u3 = new User(3,"c",31);
        User u4 = new User(4,"d",32);
        User u5 = new User(6,"e",33);

        List<User> list = Arrays.asList(u1,u2,u3,u4,u5);
        list.stream()
                .filter((u)->{return u.getId()%2 == 0;})//ID必须是偶数
                .filter((u)->{return u.getAge() > 30;}) //年龄大于30岁
                .map((u)->{//用户名转换为大写
                    User tmp = new User();
                    tmp.setName(u.getName().toUpperCase());
                    tmp.setId(u.getId());
                    tmp.setAge(u.getAge());
                    return tmp;
                })
                .sorted((uu1,uu2)->{return uu2.getName().compareTo(uu1.getName());})//用户名字母倒序排序
                .limit(1)
                .forEach(System.out::println);

    }
}

class User{
    private int id;
    private String name;
    private int age;

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}