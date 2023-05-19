package ingram.zuoBase.p26;

/**
 * @Author qywu11
 * @Date 2023/5/18 14:31
 * @Version 1.0
 */
public class Problem02_Light {

    //s中只有'.'或者'X'两种字符
    //路灯可以影响左中右三个位置
    //至少需要多少灯可以把.都点亮
    private static int minLight3(String test) {
        char[] chars = test.toCharArray();
        int light=0;
        int index=0;
        //当你来到i位置，一定保证之前的灯，彻底不会影响到i位置
        while(index<chars.length){
            if(chars[index]=='X'){
                index++;
            }else{//chars[index]=='.'
                if(index+1==chars.length){
                    light++;
                    break;
                }else{//如果下面有位置
                    if(chars[index+1]=='X'){
                        light++;
                        index=index+2;
                    }else{//下一个位置是.  ->   .  . (放灯)
                        light++;
                        index=index+3; // 贪心
                    }
                }
            }
        }
        return light;
    }

}
