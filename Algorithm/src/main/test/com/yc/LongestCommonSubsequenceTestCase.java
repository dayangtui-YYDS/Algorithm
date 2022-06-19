package com.yc; /**
 * @ClassName: LongestCommonSubsequenceTestCase
 * @Description:
 * @Author: yucongcong
 * @Date: 2022-06-18 16:04
 */
import org.junit.Test;

import java.util.Random;

/**
 * @ClassName: LongestCommonSubsequenceTestCase
 * @Description:
 * @Author: yucongcong
 * @Date: 2022-06-18 16:04
 */
public class LongestCommonSubsequenceTestCase {
    @Test
    public void Test(){
        char[] sample = CreateSample();
        int randomCount = 10;
        for (int i =0; i< randomCount; ++i){
            String str1 = CreateString(sample);
            String str2 = CreateString(sample);
            System.out.println(String.format("Str1: %s  Str2: %s LCS: %d",str1, str2,
                    LongestCommonSubsequence.LongestCommonSubsequence(str1,str2)));
            System.out.println(String.format("Str1: %s  Str2: %s LCS: %d",str1, str2,
                    LongestCommonSubsequence.LongestCommonSubsequenceRecur(str1,str2)));
        }
    }

    //创建一个字符串
    private String CreateString(char[] sample)
    {
        int count = random.nextInt(Bound);
        char[] ss = new char[count];
        for (int i = 0; i < count; i++) {
            ss[i] = sample[random.nextInt(sample.length)];
        }
        return String.valueOf(ss);
    }

    // 生成样本
    private char[] CreateSample()
    {
        // 英文字母数量
        int letterCount = 26;
        int numberCount = 10;
        char[] sample = new char[(letterCount<<1) + numberCount];
        // 生成小写字母
        char low = 'a';
        for (int i=0; i< letterCount; ++i){
            sample[i] = (char) (low + i);
        }
        // 生成大写字母
        char upper = 'A';
        for (int i=0; i< letterCount; ++i){
            sample[i + letterCount] = (char) (upper + i);
        }
        //生成数字
        char number = '0';
        for (int i=0; i< numberCount; ++i){
            sample[i +(letterCount<<1)] = (char) (number + i);
        }
        return sample;
    }

    private final int Bound = 20;
    // 随机数
    private final Random random = new Random();
}