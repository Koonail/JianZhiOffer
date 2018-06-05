package com.koonail.arithmetics;

/**
 * @Author: WEN KANG
 * @Description: JVM测试
 * @Time : 2018/5/30 11:15
 */
public class JvmTest {
    private static final int _1MB = 1024 * 1024;
    /**
     * VM参数：-verbose:gc -Xms20M -Xmx20M(限制java堆内存为20M，且不支持扩展) -Xmn10M(新生代10M，剩下的给老年代)
     *         -XX:+PrintGCDetials(打印GC日志) -XX:SurivorRatio=8(新生代Eden和老年代Survivor的比值)
     */
    public static void testAllocation(){
        byte[] allcation1,allcation2,allcation3,allcation4;
        allcation1 = new byte[2 * _1MB];
        allcation2 = new byte[2 * _1MB];
        allcation3 = new byte[2 * _1MB];
        allcation4 = new byte[4 * _1MB];// 会出现一次Full GC
    }

    public static void main(String[] args) {
        testAllocation();
    }
}
