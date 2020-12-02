package string;

public class StringDemo {


    /**
     * BF算法：暴力朴素算法
     * 假设主串长为n，模式串长为m
     * 遍历n中从0，1...n-m开始的长度为m的子串是否与m匹配
     * 时间复杂度：O(n*m)
     * @param a
     * @param pattern
     * @return
     */
    public boolean bf(String a, String pattern){
        if (a.length() < pattern.length()){
            return false;
        }
        int length = a.length() - pattern.length();
        for (int i = 0; i < length; i++){
            for (int j = 0; j < pattern.length();j++){
                if (a.charAt(i + j) != pattern.charAt(j)){
                    break;
                }else if (j == (pattern.length() - 1)){
                    return true;
                }
            }
        }
        return false;
    }


}
