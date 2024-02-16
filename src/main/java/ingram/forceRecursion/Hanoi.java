package ingram.forceRecursion;

/**
 * @Author qywu11
 * @Date 2023/1/9 14:40
 * @Version 1.0
 */
public class Hanoi {

    public static void  hanoi(int n){
        if(n > 0){
            func(n,"左","右", "中");
        }
    }

    public static void func(int i,String s,String e,String other){
        if(i == 1){
            System.out.println("Move 1 from " + s + " to " + e);
        }else{
            func(i - 1,s,other,e);
            System.out.println("Move " + i +"  from " + s + " to " + e);
            func(i - 1,other,e,s);
        }
    }

    public static void main(String[] args){
        hanoi(3);
    }
}
