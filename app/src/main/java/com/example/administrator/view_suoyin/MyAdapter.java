package com.example.administrator.view_suoyin;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 张祺钒
 * on2017/8/15.
 */

public class MyAdapter extends BaseAdapter {
    private List<Bean> list;
    private Context context;

    public MyAdapter(List<Bean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            holder=new ViewHolder();
            view=view.inflate(context,R.layout.item,null);
            holder.tvIndex=view.findViewById(R.id.tv_index);
            holder.tvName=view.findViewById(R.id.tv_name);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        Bean bean = list.get(i);
        //当前首字母
        String currentStr = bean.getPinyin().charAt(0) + "";
        String indexStr=null;
        //如果是第一个名字直接显示
        if(i==0){
            indexStr=currentStr;
        }else{
            //判断当前首字母和上一个条目的首字母是否一致,不一致时显示完整item界面
            String lastStr = list.get(i - 1).getPinyin().charAt(0) + "";
            //判断俩个参数是否一致,不一致时就执行赋值的逻辑
            if(!TextUtils.equals(lastStr,currentStr)){
                //不一致时直接赋值indexStr
                indexStr=currentStr;
            }
        }
        holder.tvIndex.setText(indexStr);
        holder.tvName.setText(bean.getName());
        //做一个判断  如果不一致显示出来 如果一致则隐藏   不等于空说明走了赋值逻辑 说明与当前不一样  是第一次出现
        holder.tvIndex.setVisibility(indexStr != null ? View.VISIBLE:View.GONE);

        return view;
    }
    static class ViewHolder{
        TextView tvIndex,tvName;
    }
}
