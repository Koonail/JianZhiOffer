package com.koonail.utils;

public class TestCases {
    /**
     * 创建一个二维数组
     * @param x
     * @param y
     * @return
     */
    public static int[][] getTwoDimArray(int x,int y)
    {
        int[][] rs = new int[x][y];
        for (int i = 0;i < x;i++)
        {
            for (int k = 0;k < y;k++)
            {
                rs[i][k] = i + k + 1;
            }
        }
        return rs;
    }

}
