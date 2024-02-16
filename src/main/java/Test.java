import lombok.Data;
import lombok.Synchronized;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.ReentrantLock;

// 更新姓名对应的年龄，并记录这个姓名被更新多少次

@Data
public class Test {

    private Map<String,Integer> map = new ConcurrentHashMap<>();
    public LongAdder total;


    public  int updateAgeByName(String name, int age){
        ReentrantLock lock = new ReentrantLock();
        try {
            lock.lock();
            map.put(name,age);
            total.increment();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return total.intValue();
    }




}
