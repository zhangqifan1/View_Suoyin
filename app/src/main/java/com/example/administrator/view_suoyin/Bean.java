package com.example.administrator.view_suoyin;

/**
 * Created by 张祺钒
 * on2017/8/15.
 */

public class Bean implements Comparable<Bean>{
    public  String name,pinyin;

    public Bean(String name) {
        this.name = name;
        this.pinyin=Util_Pinyin.getPinyin(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    @Override
    public int compareTo(Bean bean) {

        return this.pinyin.compareTo(bean.pinyin);
    }
}
