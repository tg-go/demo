package com.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bo.luo
 * @date 2021/1/13 9:55
 */
public class GCTest {

    private static final int _10MB = 10 * 1024 * 1024;

    public static void main(String[] args) throws InterruptedException {
        test();
    }

    /**
     * -Xms100m:最大堆内存
     * -Xmx1000m:初始堆内存
     * -Xmn50m:新生代大小
     * -XX:+PrintGCDetails  打印GC日志详细信息
     * -XX:+UseConcMarkSweepGC   使用CMS GC
     * -XX:+UseParNewGC         使用ParNew GC
     * -XX:SurvivorRatio=8       新生代eden 和survivor的比例=8
     * -XX:MaxTenuringThreshold=1   经历多少次minor gc后晋升到 老年代
     * -XX:+PrintTenuringDistribution   输出survivor区对象的年龄分布
     * -XX:CMSInitiatingOccupancyFraction=68    CMS使用到达多少百分比时触发一次cms gc
     * -Xloggc:log/gc.log
     *
     * @throws InterruptedException
     */
    public static void test() throws InterruptedException {
        List<byte[]> list = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            byte[] alloc = new byte[_10MB];
            list.add(alloc);
        }
        Thread.sleep(Integer.MAX_VALUE);
    }
}
