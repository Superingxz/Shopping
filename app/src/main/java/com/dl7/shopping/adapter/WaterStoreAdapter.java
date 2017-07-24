package com.dl7.shopping.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dl7.shopping.R;
import com.dl7.shopping.utils.FontManager;

/**
 * Created by MC.Zeng on 2017-07-17.
 */

public class WaterStoreAdapter extends BaseAdapter {

    private String[] name={"天河区","天河区","天河区","asda","asdas","Asda","Asdasd","天河区","天河区","天河区","asda","asdas","Asda","Asdasd","天河区","天河区","天河区","asda","asdas","Asda","Asdasd","天河区","天河区","天河区","asda","asdas","Asda","Asdasd"};
    private Context context;
    private Typeface iconFont ;
    private int  selectItem=-1;
    public WaterStoreAdapter(String[] name,Context context) {
        this.name=name;
        this.context = context;

    }

    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public Object getItem(int position) {
        return name[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder=new viewHolder();
        iconFont= FontManager.getTypeface(context, FontManager.FONTAWESOME);
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_water_store,parent,false);
            holder.chack= (TextView) convertView.findViewById(R.id.tv_water_store_check_icon);
            holder.title= (TextView) convertView.findViewById(R.id.tv_water_store_title);
            holder.address= (TextView) convertView.findViewById(R.id.tv_water_store_address);
            holder.distance= (TextView) convertView.findViewById(R.id.tv_water_store_distance);
            holder.phone= (TextView) convertView.findViewById(R.id.tv_water_store_phone);
            holder.icon= (TextView) convertView.findViewById(R.id.tv_water_store_icon);

            convertView.setTag(holder);

        }else{
            holder= (viewHolder) convertView.getTag();
        }

        holder.icon.setTypeface(iconFont);
        holder.chack.setTypeface(iconFont);

        if (selectItem==position){
            Log.i("getView: ", position+"");
            holder.chack.setText(R.string.address);
        }else {
            holder.chack.setText(R.string.circle);
        }

        holder.address.setText(name[position]);



        return convertView;
    }


    public  void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }

    class viewHolder{
        TextView address,phone,distance,icon,chack,title;
    }
}
