package cn.migu.function.demo1;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 函数式编程，基本技能，需掌握
 */
public class FunctionTest {

    public static void main(String[] args) {

        //1.函数型接口，Function<String,String>.apply()
        Function<String,String> f1 = new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s;
            }
        };
        System.out.println("Function:"+f1.apply("欢迎来到函数式编程世界！"));

        //2.判断型接口：Predicate<String>.test()
        Predicate<String> f2 = (str)->{
            return str.isEmpty();
        };
        System.out.println("Predicate:输入是否为空:"+f2.test(""));

        //3.消费型接口：Consumer<String>.accept()
        Consumer<String> f3 = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("Consumer:消费了，"+s);
            }
        };
        f3.accept("业务消息1");

        //4.供给型接口：Supplier<String>.get()
        Supplier<String> f4 = new Supplier<String>() {
            @Override
            public String get() {
                return "生产了，商品1";
            }
        };
        System.out.println("Supplier:"+f4.get());
    }

}
