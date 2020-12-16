package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目地址：https://leetcode-cn.com/problems/n-queens/
 * N皇后问题
 */

public class Question_Hard_51 {

    class Solution {

        private List<List<String>> result;

        public Solution(){
            result = new ArrayList<>();
        }

        public List<List<String>> solveNQueens(int n) {
            List<StringBuilder> sbList = new ArrayList<>();
            for (int i = 0; i < n; i++){
                sbList.add(getEmptyChars(n));
            }
            return solveNQueens(n, sbList, 0);
        }

        public List<List<String>> solveNQueens(int n, List<StringBuilder> stringList, int row){
            // 套回溯算法的解题框架
            if (row == n){
                List<String> list = new ArrayList<>();
                for (StringBuilder sb : stringList){
                    list.add(sb.toString());
                }
                result.add(list);
                return result;
            }
            StringBuilder sb = stringList.get(row);
            for (int i = 0; i < n; i++){
                if (!isValid(n, stringList, row, i)){
                    continue;
                }
                // 添加到路径
                sb.setCharAt(i, 'Q');
                // 遍历
                solveNQueens(n, stringList, row + 1);
                // 从路径删除
                sb.setCharAt(i, '.');
            }
            return result;
        }

        /**
         * 判断是否合法：
         * 前面的列有没有皇后
         * 上对角线有没有皇后
         * 下对角线有没有皇后
         * @param n
         * @param stringList
         * @param row
         * @param col
         * @return
         */
        private boolean isValid(int n , List<StringBuilder> stringList, int row, int col){
            // 列
//        for (int i = row - 1; i >= 0; i--){
            for(int i = 0; i < n; i++){
                if(stringList.get(i).charAt(col) == 'Q'){
                    return false;
                }
            }
            // 行，不用判断，因为每行只摆一个皇后
            /*StringBuilder sb = stringList.get(row);
            for (int i = col - 1;i >= 0; i--){
                if (sb.charAt(i) == 'Q'){
                    return false;
                }
            }*/
            // 上对角线
            for (int i = row - 1, j = col - 1;i >= 0 && j >= 0; i--,j--){
                if (stringList.get(i).charAt(j) == 'Q'){
                    return false;
                }
            }
            // 下对角线
            for (int i = row - 1, j = col + 1;i >= 0 && j < n; i--, j++){
                if (stringList.get(i).charAt(j) == 'Q'){
                    return false;
                }
            }
            return true;
        }

        // 生成待放置的char数组
        private StringBuilder getEmptyChars(int n){
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++){
                sb.append(".");
            }
            return sb;
        }
    }
}
