package com.dl7.shopping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.dl7.shopping.R;

/**
 * Created by MC.Zeng on 2017-06-29.
 */

public class HomeListViewAdapter extends BaseAdapter {

    private Context context;
    private int[] rimg={R.mipmap.ic_launcher,R.mipmap.ic_launcher};
    private int[] limg={R.mipmap.ic_launcher,R.mipmap.ic_launcher};

    public HomeListViewAdapter(int[] limg,int[] rimg, Context context) {
        this.rimg=rimg;
        this.limg=limg;
        this.context = context;
    }
    @Override
    public int getCount() {
        return rimg.length;
    }

    @Override
    public Object getItem(int position) {
        return rimg[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_home_lisview,parent,false);
            holder.leftimg= (ImageView) convertView.findViewById(R.id.left_img_listview);
            holder.rightimg= (ImageView) convertView.findViewById(R.id.right_img_listview);

            holder.rightimg.setBackgroundResource(rimg[position]);
            holder.leftimg.setBackgroundResource(limg[position]);




            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        holder.leftimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"你点击了左边"+position,Toast.LENGTH_SHORT).show();
            }
        });
        holder.rightimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"你点击了右边"+position,Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    static class ViewHolder
    {
        ImageView rightimg,leftimg;
    }
}
