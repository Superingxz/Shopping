package com.dl7.shopping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.dl7.shopping.R;


/**
 * Created by MC.Zeng on 2017-07-08.
 */

public class ComboAdapter extends BaseAdapter {

    private Context context;
    private int[] img={R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};

    public ComboAdapter(int[] img, Context context) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=new ViewHolder();
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_combo_listview,parent,false);
            viewHolder.img= (ImageView) convertView.findViewById(R.id.img_combo_item);
            viewHolder.img.setImageResource(img[position]);
        }

        return convertView;
    }

    class ViewHolder{
        ImageView img;
    }
}
