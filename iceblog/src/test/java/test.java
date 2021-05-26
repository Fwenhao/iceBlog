import java.util.*;

public class test extends ClassLoader{

    public static void main(String[] args) {
        System.out.println(solve(8,6));
    }

    /**
     * @description : 〈统计大小写数字字母〉
     * @param str 1
     * @return : void
     */
    public static void countNum(String str){
        String realStr = str.trim();

        int isUpperCaseCount=0,isLowerCaseCount=0,isDigitCount=0,isLetterCount=0;

        for (int i = 0; i < realStr.length(); i++) {
            if (Character.isDigit(realStr.charAt(i))){
                isDigitCount++;
            }
            if (Character.isLowerCase(realStr.charAt(i))){
                isLowerCaseCount++;
            }
            if (Character.isUpperCase(realStr.charAt(i))){
                isUpperCaseCount++;
            }
            if (Character.isLetter(realStr.charAt(i))){
                isLetterCount++;
            }
        }

        System.out.println("数字有："+isDigitCount+"个");
        System.out.println("小写字母有："+isLowerCaseCount+"个");
        System.out.println("大写字母有："+isUpperCaseCount+"个");
        System.out.println("一共出现了："+isLetterCount+"个字母");
    }

    /**
     * @description : 〈投票统计〉
     * @param candidateList 1
     * @param voteList 2
     * @return : void
     */
    public static void getm1(List<String> candidateList,List<String> voteList){

        Map<String,Integer> map = new HashMap<>();

        for (int i = 0; i < candidateList.size(); i++) {
            //初始化候选人投票
            map.put(candidateList.get(i),0);
            //循环遍历结果集，获取投票
            for (int j = voteList.size()-1; j >= 0; j--) {
                if (candidateList.get(i).equals(voteList.get(j))){
                    //票选数叠加
                    map.put(candidateList.get(i),map.get(candidateList.get(i))+1);
                    //投票统计过后，移除已经统计过的计票
                    voteList.remove(j);
                }
            }
        }
        System.out.println("无效选票"+voteList);
        System.out.println(map);

    }

    /**
     * 进制转换
     * @param M int整型 给定整数
     * @param N int整型 转换到的进制
     * @return string字符串
     */
    public static final String[] F = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
    public static String solve(int M,int N){
        //默认数字符号为正
        Boolean flag = false;
        if (M == 0){
            return "0";
        }else if (M < 0){
            flag = true;
            M *= -1;
        }

        StringBuffer sb = new StringBuffer();
        int temp ;
        while (M != 0){
            temp = M % N;
            sb.append(F[temp]);
            M = M / N;
        }

        sb.reverse();

        return (flag?"-":"") + sb.toString();


    }
}
