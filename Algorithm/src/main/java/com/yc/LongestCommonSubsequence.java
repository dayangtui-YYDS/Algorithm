package com.yc;

/**
 * @ClassName: LongestCommonSubsequence
 * @Description:
 * @Author: yucongcong
 * @Date: 2022-06-18 14:17
 */
// 最长公共子序列问题
public class LongestCommonSubsequence {

    public static int LongestCommonSubsequence(String str1, String str2){
        // base case
        if (str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0) return 0;

        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m][n];
        dp[0][0] = s1[0] == s2[0] ? 1 : 0;
        for (int i = 1; i < s1.length; ++i)
        {
            dp[i][0] = s1[i] == s2[0] ? 1 : dp[i-1][0];
        }
        for (int j = 1; j < s2.length; ++j)
        {
            dp[0][j] = s1[0] == s2[j] ? 1 : dp[0][j-1];
        }

        for (int i=1; i< s1.length; ++i)
        {
            for (int j=1; j< s2.length; ++j)
            {
                // s1[i-1] != s2[j-1]证明s1[i] s2[j]至少有一个不在最长公共子序列中
                // dp[i-1][j] dp[i][j-1] dp[i-1][j-1] 而dp[i-1][j-1]的结果明显小于等于dp[i-1][j] 和dp[i][j-1] 可以直接忽略
                dp[i][j] = s1[i-1] == s2[j-1] ? dp[i-1][j-1] + 1 : Math.max(dp[i-1][j],dp[i][j-1]);
            }
        }
        return dp[m-1][n-1];
    }
}