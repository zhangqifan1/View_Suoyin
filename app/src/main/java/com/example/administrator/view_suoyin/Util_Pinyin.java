package com.example.administrator.view_suoyin;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * Created by 张祺钒
 * on2017/8/15.
 */

public class Util_Pinyin {
    public static String getPinyin(String string){
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        //不要音标
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        //设置转换出大写字母
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        char[] chars = string.toCharArray();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            //如果是空格,跳过当前循环
           if(Character.isWhitespace(c)){
                continue;
            }
            //如果不是汉字,直接拼写
            if(c>-128 && c<127){
                sb.append(c);
            }else{//是汉字 name我们就获取拼音
                try {
                    String s = PinyinHelper.toHanyuPinyinStringArray(c, format)[0];
                    sb.append(s);

                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    badHanyuPinyinOutputFormatCombination.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}
