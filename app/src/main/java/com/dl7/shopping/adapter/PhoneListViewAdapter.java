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
 * Created by MC.Zeng on 2017-07-03.
 */

public class PhoneListViewAdapter extends BaseAdapter {

    private Context context;
    private int[] img={R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};


    public PhoneListViewAdapter(int[] img, Context context) {
        this.img=img;

        this.context = context;
    }

    @Override
    public int getCount() {
        return img.length;
    }

    @Override
    public Object getItem(int position) {
        return img[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=new ViewHolder();
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_fragment_phone_lv,parent,false);
            viewHolder.img= (ImageView) convertView.findViewById(R.id.img_item_fragment_phone_lv);

            viewHolder.img.setBackgroundResource(img[position]);

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "你点击了弟"+position+"项", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    static class ViewHolder
    {
        ImageView img;
    }
}
