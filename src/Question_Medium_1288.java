import java.util.Arrays;
import java.util.Comparator;

public class Question_Medium_1288 {

    class Solution {
        public int removeCoveredIntervals(int[][] intervals) {
            if (intervals.length ==1){
                return intervals.length;
            }
            Arrays.sort(intervals, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    // 尾按降序排序，这样才能处理[2,6]、[2,4]这种
                    if (o1[0] == o2[0]){
                        return o2[1] - o1[1];
                    }

                    // 升序排列
                    return o1[0] - o2[0];
                }
            });
            int left = intervals[0][0];
            int right = intervals[0][1];
            int length = intervals.length;
            for (int i = 1; i < intervals.length;i++){
                // 覆盖的情况
                if (intervals[i][0] >= left && intervals[i][1] <= right){
                    length--;
                }
                // 合并
                if (intervals[i][0] <= left && intervals[i][1] > right){
                    right = intervals[i][1];
                }
                if (intervals[i][0] > right){
                    left = intervals[i][0];
                    right = intervals[i][1];
                }
            }
            return length;

        }
    }

}
