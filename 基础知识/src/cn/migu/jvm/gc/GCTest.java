package cn.migu.jvm.gc;

import java.lang.reflect.Array;
import java.util.ArrayList;

//打印GC数据
//-Xms8m -Xmx8m -XX:+PrintGCDetails
public class GCTest {
    public static void main(String[] args) {
        Byte[] bye = new Byte[1024*1024];
        ArrayList list = new ArrayList<GCTest>();
        while (true){
            list.add(new GCTest());
        }
        //GC (Allocation Failure) [PSYoungGen: 1535K->488K(2048K)] 1535K->683K(7680K)
        //Full GC (Ergonomics) [PSYoungGen: 504K->0K(2048K)] [ParOldGen: 5191K->5627K(5632K)] 5695K->5627K(7680K)
        //Full GC (Ergonomics) [PSYoungGen: 1536K->1535K(2048K)] [ParOldGen: 4427K->4427K(5632K)] 5963K->5963K(7680K)
        //Full GC (Ergonomics) [PSYoungGen: 1535K->1535K(2048K)] [ParOldGen: 5537K->5537K(5632K)] 7073K->7073K(7680K),
        //GC类型 [GC区域：回收前大小->回收后大小（该区域总大小）] 堆区域回收前大小->堆区域回收后大小（堆区总大小）
    }
}
