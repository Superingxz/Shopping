package com.dl7.shopping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dl7.shopping.R;
import com.dl7.shopping.bean.WaterDetailBean;

import java.util.List;

/**
 * Created by MC.Zeng on 2017-07-22.
 */



public class WaterDetailAdapter extends BaseAdapter {

    private Context context;
    private List<WaterDetailBean.DataBean.ListBean> mlist;

    public WaterDetailAdapter(List<WaterDetailBean.DataBean.ListBean> mlist,Context context) {
        this.mlist=mlist;
//    public LoveListAdapter(String[] address,Context context) {
//        this.address=address;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            holder=new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_item_getwater, parent, false);
            holder.title= (TextView) convertView.findViewById(R.id.tv_getwater_item_info);
//            holder.day= (TextView) convertView.findViewById(R.id.tv_getwater_item_day);
            holder.time= (TextView) convertView.findViewById(R.id.tv_getwater_item_time);

            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.title.setText(mlist.get(position).getNotes());
        holder.time.setText(mlist.get(position).getCreate_time());


        return convertView;
    }

    class ViewHolder{
        TextView title,time,day;
    }
}
