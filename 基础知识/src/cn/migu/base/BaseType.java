package cn.migu.base;

/**
 * @author test
 * @version 1.8
 */
public class BaseType {

    /**
     * @author test
     * @param args
     */
    public static void main(String[] args) {
        //整数拓展   进制   二进制 0b  八进制 0   十进制  十六进制 0x

        int a1 = 10;
        int a2 = 010;
        int a3 = 0x10;
        int a4 =0b1;

        System.out.println(a1);
        System.out.println(a2);
        System.out.println(a3);
        System.out.println(a4);
        System.out.println("=========================");
        int a=0;
        System.out.println(a);
        int b=a++;
        System.out.println(a+","+b);
        int c = ++a;
        System.out.println(a+","+c);
        Math.pow(2,3);
    }
}
