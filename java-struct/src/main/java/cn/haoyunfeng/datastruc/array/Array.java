package cn.haoyunfeng.datastruc.array;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * @Description:
 * @author: haoyunfeng2
 * @Date: 2019/10/12 10:54 上午
 */
public class Array {
    private AtomicIntegerArray data[];
    private AtomicInteger capacity;
    private AtomicInteger count;
//    private AtomicReference

    public Array(AtomicInteger capacity){
        this.data = new AtomicIntegerArray[5];
        this.capacity = capacity;
        this.count = new AtomicInteger(0);//一开始一个数都没有存所以为0
    }

    public boolean insert(Integer index,Integer data){
        // 数组空间已满
        if (count.equals(capacity)) {
            System.out.println("没有可插入的位置");
            return false;
        }
        return true;
    }
}
