package ingram.zuoBase.p26;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author qywu11
 * @Date 2023/5/18 14:33
 * @Version 1.0
 *
 * 给先序遍历和中序遍历数组，生成后组遍历数组
 * 大递归
 */
public class Problem03_TreeInAndPreToPos {


    public static int[] getPosArray1(int[] pre, int[] in) {
        if(pre==null&&in==null){
            return null;
        }
        int N=pre.length;
        int [] pos=new int[N];
        Map<Integer,Integer> map=new HashMap<>();//为了从in数组中找元素方便
        for(int i=0;i<N;i++){
            map.put(in[i],i);
        }
        set(pre,0,N-1,in,0,N-1,pos,0,N-1,map);//递归函数：pre数组从开始到结束的范围，in和pos类似
        return pos;
    }
    //利用pre[prei....prej]与in[ini...inj]
    //填好pos[posi...posj]
    public static void set(int[] pre, int prei, int prej, int[] in, int ini, int inj, int[] pos, int posi, int posj, Map<Integer, Integer> map){
        if(prej<prei){
            return;
        }
        if(prei==prej){//表明只有一个元素了，直接赋值
            pos[posi]=pre[prei];
            return ;
        }
        pos[posj]=pre[prei];//pre的第一个元素是pos的最后一个元素
        int find=map.get(pre[prei]);//找到元素在in中的位置
        set(pre,prei+1,prei+find-ini,in,ini,find-1,pos,posi,posi+find-ini-1,map);
        set(pre,prei+find-ini+1,prej ,in,find+1,inj ,pos,posi+find-ini,posj-1,map);
    }

}
