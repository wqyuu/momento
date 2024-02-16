package ingram.zuoBase.p12;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @Author qywu11
 * @Date 2023/2/17 10:47
 * @Version 1.0
 * 并查集 1964-1989
 * 可用于解决岛问题并行计算，把整个二维数组分为多个，
 * 例如四块有abcd，每个cpu计算一块，得出的每块边角临近上的数据是否可相连，相连就合并union
 */
public class Code04_UnionSet {

    public static class Element<V>{
        private V value;
        public Element(V value){
            this.value = value;
        }
    }

    public static class UnionFindSet<V>{

        public HashMap<V,Element<V>> elementMap;
        // key 某个元素 value 该元素的父
        public HashMap<Element<V>,Element<V>> fatherMap;
        // key 某个集合代表元素 value 该集合大小
        public HashMap<Element<V>,Integer> sizeMap;

        public UnionFindSet(List<V> list){
            elementMap = new HashMap<>();
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V value : list){
                Element<V> element = new Element<>(value);
                elementMap.put(value,element);
                fatherMap.put(element,element);
                sizeMap.put(element,1);
            }
        }

        private Element<V> findHead(Element<V> element){

            Stack<Element<V>> stack = new Stack<>();
            if(!element.equals(fatherMap.get(element))){
                element = fatherMap.get(element);
                stack.push(element);
            }

            while (!stack.isEmpty()){
                fatherMap.put(stack.pop(),element);
            }

            return element;
        }

        public boolean isSameSet(V a,V b){
            if(elementMap.containsKey(a) && elementMap.containsKey(b)){
                return findHead(elementMap.get(a)) == findHead(elementMap.get(b));
            }
            return false;
        }

        public void union(Element<V> a,Element<V> b){

            Element<V> af = findHead(a);
            Element<V> bf = findHead(b);
            if(!af.equals(bf)){
                Element<V> max = sizeMap.get(af) >= sizeMap.get(bf) ?af:bf;
                Element<V> small = max == af?bf:af;
                fatherMap.put(small,max);
                sizeMap.put(max,sizeMap.get(af) + sizeMap.get(bf));
                sizeMap.remove(small);
            }

        }

    }

}
