package ingram.zuoBase.p11;

import java.util.HashMap;

/**
 * @Author qywu11
 * @Date 2023/2/15 10:18
 * @Version 1.0
 *
 * 设计RandomPool结构
 *
 * 设计一种结构，在该结构中有如下三个功能：
 * insert(key): 将某个key加入该结构，做到不重复加入
 * delete(key)：将原本在结构中的某个key移除
 * getRamdom()：等概率随机返回结构中任何一个key
 * 时间复杂度都是O(1)
 */
public class Code01_RandomPool {

    public  static class Pool<K>{
        private HashMap<K,Integer> keyIndexMap;
        private HashMap<Integer,K> IndexKeyMap;
        private int size;

        public Pool(){
            this.keyIndexMap = new HashMap<>();
            this.IndexKeyMap = new HashMap<>();
            this.size = 0;
        }

        public void insert(K key){
            if(!this.keyIndexMap.containsKey(key)){
                this.keyIndexMap.put(key,this.size);
                this.IndexKeyMap.put(this.size ++,key);
            }
        }

        public void delete(K key){
            if(this.keyIndexMap.containsKey(key)){
                int deleteIndex = this.keyIndexMap.get(key);
                int lastIndex = --this.size;
                K lastKey = this.IndexKeyMap.get(lastIndex);
                this.keyIndexMap.put(lastKey,deleteIndex);
                this.IndexKeyMap.put(deleteIndex,lastKey);
                keyIndexMap.remove(key);
                this.IndexKeyMap.remove(lastIndex);
            }
        }

        public K getRandom(){
            if(this.size == 0){
                return null;
            }
            int randomIndex = (int) (Math.random() * this.size); // 0 ~ size
            return this.IndexKeyMap.get(randomIndex);
        }
    }

    public static void main(String[] args) {
        Pool<String> pool = new Pool<>();
        pool.insert("Xtr");
        pool.insert("1");
        pool.insert("q");
        System.out.println(pool.getRandom());
    }
}
