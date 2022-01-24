package cn.migu.reflect.demo1;

//测试类的加载器有哪些
public class ClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException {
        //从下往上：系统加载器--扩展加载器--根加载器
        //获取系统加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);

        //获取扩展加载器
        ClassLoader extLoader = systemClassLoader.getParent();
        System.out.println(extLoader);

        //获取根加载器，由C/C++ 实现，java获取不到，null
        ClassLoader boot = extLoader.getParent();
        System.out.println(boot);

        //获取自定义类是由哪个加载器加载的
        ClassLoader classLoader = Class.forName("cn.migu.reflect.demo1.ClassLoaderTest").getClassLoader();
        System.out.println(classLoader);


    }
}
