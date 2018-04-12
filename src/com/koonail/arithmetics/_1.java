package com.koonail.arithmetics;

public class _1 {

    /**
     * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     * [1] [2] [3]
     * [4] [5] [6]
     * [7] [8] [9]
     */

    /**
     * 从左上角开始遍历
     * @param target
     * @param array
     * @return
     */
    public static boolean find1(int target, int [][] array) {
        // 起始坐标
        int x = 0;
        int y = 0;
        // 边界值：x >= array.length() 或  y >= array[array.length() -1].length()
        while(x < array.length && y < array[array.length - 1].length)
        {
            if (target == array[x][y])
            {
                return true;
            } else
            {
                if (target > array[x][y] && y < array[x].length -1)
                {
                    // 逐行比较，如果大于当前数，且当前数不是本行最后一个时，y++
                    y ++;
                } else
                {
                    // 比较晚一行之后另起一行重置y
                    y = 0;
                    x ++;
                }
            }
        }
        return false;
    }

    /**
     * 从右上角开始遍历
     * @param target
     * @param array
     * @return
     */
    public static boolean find2(int target, int [][] array) {
        // 起始坐标
        int x = 0;
        int y = array[0].length -1;
        // 边界值：x >= array.length或y < 0
        while(x < array.length && y >= 0){
            // 如果target小于当前值，则左移，大于则下移
            if (target < array[x][y]){
                y --;
            } else if (target > array[x][y]){
                x ++;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int [][] array1 = {{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};
        System.out.println(find2(7, array1));
//		Random ra = new Random();
//		for (int i = 0;i < 10;i++)
//		{
//			int mm = ra.nextInt(20);
//			System.out.println("target="+ mm +  find1(mm, array));
//		}
    }
}
