package com.koonail.arithmetics;

public class _2 {
    /**
     * 请实现一个函数，将一个字符串中的空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
     */

    /**
     * 估计不是出题者的本意
     * @param str
     * @return
     */
    public static String replaceSpace1(StringBuffer str) {
        String strs = str.toString().replaceAll(" ", "%20");
        return strs;
    }

    /**
     * 在数组层面进行操作
     * 思路：
     * 把字符串看成一个数组
     * 无非就是从前往后遍历替换，或者从后往前替换
     * 1、从前往后替换，没替换一次，后面的字符就都需要往后位移
     * 2、从后往前替换，可以先将数组扩容至替换完之后的长度，然后每次替换之后影响到的元素只需位移一次
     * 综上述采取第二种方法
     * @param str
     * @return
     */
    public static String replaceSpace2(StringBuffer str) {
        // 原下标
        int indexOld = str.length() -1;
        // 字符串原长度
        int lengthOld = str.length();
        // 空格数
        int spaceNum = 0;
        for (int i = 0; i < lengthOld;i++){
            if (' ' == str.charAt(i)){
                spaceNum ++;
            }
        }
        // 替换后长度
        int lengthNew = lengthOld + spaceNum * 2;
        str.setLength(lengthNew);
        // 新下标
        int indexNew = str.length() - 1;
        // 边界值：indexOld < 0 或 indexOld >= indexNew
        while (indexOld >= 0 && indexOld < indexNew){
            if (' ' == str.charAt(indexOld)){
                // 先赋值，再向前移动下标
                str.setCharAt(indexNew --, '0');
                str.setCharAt(indexNew --, '2');
                str.setCharAt(indexNew --, '%');
            }else {
                str.setCharAt(indexNew --, str.charAt(indexOld));
            }
            indexOld --;
        }
        return str.toString();
    }

    public static void main(String[] args) {
        StringBuffer str = new StringBuffer("hello world");
        System.out.println(replaceSpace2(str));
    }
}
