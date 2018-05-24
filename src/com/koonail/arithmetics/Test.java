package com.koonail.arithmetics;

import com.koonail.utils.CommonUtils;

/**
 * @Author: WEN KANG
 * @Description:
 * @Time : 2018/5/18 14:49
 */
public class Test {
    public static void main(String[] args) {
        /*System.out.println(getFrequency("aaaabcaaandeaandf","aa"));
        getCap("18*19");
        System.out.println(checkModel("66-C-3"));*/
        System.out.println(CommonUtils.isNumber("18+10"));
    }

    public static int getFrequency(String str,String aimStr){
        int num = 0;
        for (int i = 0;i < str.length();i++){
            if (aimStr.indexOf(0) == str.indexOf(i)){
                int endIndex = i + aimStr.length();
                if (endIndex > str.length()){
                    break;
                }
                String subStr = str.substring(i,endIndex);
                if (aimStr.equals(subStr)){
                    num ++;
                }
            }
        }
        return num;
    }

    public static double getCap(String str){
        if(str.contains("*")){
            String value1 = str.substring(0,str.indexOf("*"));
            System.out.println(value1);
            String value2 = str.substring(str.indexOf("*") + 1,str.length());
            System.out.println(value2);
        }
        return 0;
    }

    public static String checkModel(String value){
            String[] strs = value.split("-");
            if (value != null)
            {
                char[] charArray = strs[1].toCharArray();
                for (int i = 0; i < charArray.length; i++)
                {
                    int charAscii = (int) charArray[i];
                    if ((charAscii >= 65 && charAscii <= 90) || (charAscii >= 97 && charAscii <= 122))
                    {
                       continue;
                    } else {
                        return "否";
                    }
                }
                return "是";
            }
            return "否";
    }
}
