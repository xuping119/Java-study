package cn.migu.jvm.oom;

import cn.migu.jvm.gc.GCTest;

import java.util.ArrayList;

//制造OOM，使用MAT JPofiler 分析
//-Xms4m -Xmx4m -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
public class OOMTest {
    public static void main(String[] args) {
        Byte[] bye = new Byte[1024*1024];
        ArrayList list = new ArrayList<OOMTest>();
        try{
            while (true){
                /**
                 * Dumping heap to java_pid17420.hprof ...
                 *
                 * java.lang.OutOfMemoryError: GC overhead limit exceeded
                 * 	at cn.migu.jvm.oom.OOMTest.main(OOMTest.java:15)
                 */
                list.add(new OOMTest());
            }
        }catch (Error e){
            System.out.println("捕获到错误了。");
            e.printStackTrace();
        }

    }
}
