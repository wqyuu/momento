package ingram.greedy;

import org.apache.logging.log4j.util.PropertySource;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author qywu11
 * @Date 2023/1/3 17:06
 * @Version 1.0
 * 贪心策略 时间段安排最多会议
 */
public class BestArrange {

    class Program{
        int start;
        int end;
        int length;
    }

    public static class ProgramComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1,Program o2) {
            return o1.end - o2.end;
        }
    }


    public static int bestArrange(Program[] programs,int timePoint){
        Arrays.sort(programs,new ProgramComparator());
        int result = 0;
        // 从左往右依次遍历所有会议
        for (int i = 0; i < programs.length; i++) {
            if(timePoint <= programs[i].start){
                result ++;
                timePoint = programs[i].end;
            }
        }
        return result;
    }



}
