package ingram.lc.array;

/**
 * 56. 合并区间
 *
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 */
public class A10_mergeArray {


    public static int[][] merge(int[][] intervals) {

        int[][] res = new int[intervals.length][2];
        int j = 0;
        int pre = 0;
        for (int i = 0; i < intervals.length; i++) {
            int s = intervals[i][0];
            int e = intervals[i][1];

            if(pre >= s){
                int s1 = res[j-1][1];
                int e1= res[j-1][1];
                res[j-1][0] = Math.min(s,s1);
                res[j-1][1] = Math.max(e,e1);
            }else {
                res[j][0] = s;
                res[j][1] = e;
                j ++;
            }
            pre = e;
        }
        int[][] res1 = new int[j][2];
        for (int i = 0; i < j; i++) {
            res1[i] = res[i];
        }
        return res1;
    }

    public static void main(String[] args) {
        int[][] res = new int[][]{{1,3},{2,6},{8,10},{15,18}};
        merge(res);
    }


}
