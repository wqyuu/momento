package ingram.hw;

import java.util.*;
import java.util.stream.Collectors;

// 第三题 文件缓存系统  第二次修改
public class WenjianxitongA {

    static class Wenjianxitong {
        String wenjianming;
        int daxiao;
        int shijian;
        int jishuqi;
 
        public Wenjianxitong(String wenjianming, int daxiao, int jishuqi){
            this.wenjianming = wenjianming;
            this.daxiao = daxiao;
            this.jishuqi = jishuqi;
        }
 
        public int getJishuqi() {
            return jishuqi;
        }
        public int getShijian() {
            return shijian;
        }
    }
    public static Scanner shuru = null;

    public static void main(String[] args) {
        shuru = new Scanner(System.in);
       
        while (shuru.hasNext()) { 
            int one = shuru.nextInt();
            int two = shuru.nextInt();
            Map<String, Wenjianxitong> three = new HashMap<>();
            int four = 0;
            int five = 1;
            shuru.nextLine();
            for (int i = 0; i < two; i ++) {
                String cmd = shuru.nextLine();
                String[] arr = cmd.split(" ");
                if ("get".equals(arr[0])) {
                    Wenjianxitong file = three.get(arr[1]);
                    if (Objects.isNull(file)) {
                        continue;
                    }
                    file.shijian++;
                    file.jishuqi = five++;
                } else {
                    if (three.containsKey(arr[1])) {
                        continue;
                    }
                    int size = Integer.valueOf(arr[2]);
                    if (four + size > one) {
                        four = remove(three, one, four, size);
                    }
                    if (four + size <= one) {
                        four += size;
                        three.put(arr[1], new Wenjianxitong(arr[1], size, five++));
                    }
                }
            }
            if (three.isEmpty()) {
                System.out.println("NONE");
            } else {
                System.out.println(three.keySet().stream().sorted().collect(Collectors.joining(",")));   
            }
        }
    }
 
    public static int remove(Map<String, Wenjianxitong> fileMap, int total, int now, int size) {
        List<Wenjianxitong> list = fileMap.values().stream().sorted(Comparator.comparing(Wenjianxitong::getShijian).thenComparing(Wenjianxitong::getJishuqi))
                .collect(Collectors.toList());
        int result = now;
        for (Wenjianxitong fileTest : list) {
            if (result + size >= total) {
                fileMap.remove(fileTest.wenjianming);
                result = result - fileTest.daxiao;
            } else {
                break;
            }
        }
        return result;
    }
 

}