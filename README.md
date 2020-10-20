# LeetCodeLearn
学习算法基础知识以及对应高频题目的解决

IDE刷题插件：(推荐IntelliJ,可以查看MD文件)
https://github.com/shuzijun/leetcode-editor/blob/master/README_ZH.md

目录：
1. Array数组
2. Linked List链表
3. Tree数
4. 遍历、排序
5. 分治
6. 动态规划

## 2.链表
递归的模板：
```
public ListNode reverseList(参数0) {
    if (终止条件)
        return;

    逻辑处理（可能有，也可能没有，具体问题具体分析）

    //递归调用
    ListNode reverse = reverseList(参数1);

    逻辑处理（可能有，也可能没有，具体问题具体分析）
}
```