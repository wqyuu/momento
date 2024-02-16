package ingram.lc.string;

/**
 * 1071. 字符串的最大公因子
 */
public class A02_gcdOfStrings {
    public static String gcdOfStrings(String str1, String str2) {
        if(!(str1 + str2).equals((str2 + str1))){
            return "";
        }
        return str1.substring(0,gcd(str1.length(),str2.length()));
    }

    public static String gcdOfStrings2(String str1, String str2) {
        if("".equals(str1)) return str2;
        if("".equals(str2)) return str1;

        if(str1.length()==str2.length()){
            return str1.equals(str2)? str1 : "";
        }else if(str1.length()<str2.length()){
            if(!str1.equals(str2.substring(0,str1.length()))) return "";
            return gcdOfStrings2(str1,str2.substring(str1.length()));
        }else{
            if(!str2.equals(str1.substring(0,str2.length()))) return "";
            return gcdOfStrings2(str2,str1.substring(str2.length()));
        }
    }
    public static void main(String[] args) {
        System.out.println(gcd(30,2));
        System.out.println(gcd(2,2));
        System.out.println(gcd(17,3));
        System.out.println(gcdOfStrings2("ABABAB","AB"));
    }

    private static int gcd(int a, int b) {
        return b == 0? a: gcd(b, a % b);
    }
}
