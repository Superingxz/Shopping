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
import com.dl7.shopping.bean.WaterStoreBean;
import com.dl7.shopping.utils.FontManager;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by MC.Zeng on 2017-07-17.
 */

public class WaterStoreAdapter extends BaseAdapter {

    private String[] name={"天河区","天河区","天河区","asda","asdas","Asda","Asdasd","天河区","天河区","天河区","asda","asdas","Asda","Asdasd","天河区","天河区","天河区","asda","asdas","Asda","Asdasd","天河区","天河区","天河区","asda","asdas","Asda","Asdasd"};
    private Context context;
    private Typeface iconFont ;
    private int  selectItem=-1;
    private List<WaterStoreBean.DataBean.ListBean> mList;

    public WaterStoreAdapter(List<WaterStoreBean.DataBean.ListBean> mList,Context context) {
        this.mList=mList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder;
        iconFont= FontManager.getTypeface(context, FontManager.FONTAWESOME);
        if (convertView==null){
            holder=new viewHolder();
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
        holder.address.setText(mList.get(position).getAddress());
        holder.title.setText(mList.get(position).getName());
        DecimalFormat df = new DecimalFormat("0.0");

        String result = df.format(mList.get(position).getDistance());
        holder.distance.setText(result+"km");

        if (position+1==selectItem){
            Log.i("getView: ", position+"");
            holder.chack.setText(R.string.address);
        }else {
            holder.chack.setText(R.string.circle);
        }

        return convertView;
    }

//    private void setView(int position,viewHolder holder){
//
//    }


    public  void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }


    class viewHolder{
        TextView address,phone,distance,icon,chack,title;
    }
}