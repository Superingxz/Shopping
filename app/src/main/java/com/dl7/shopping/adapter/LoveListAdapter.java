package com.dl7.shopping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dl7.shopping.R;
import com.dl7.shopping.bean.LoveListBean;

import java.util.List;


/**
 * Created by MC.Zeng on 2017-07-01.
 */

public class LoveListAdapter extends BaseAdapter {
    private String[] address={"天河区","天河区","天河区","天河区","天河区","天河区","天河区","天河区","天河区","天河区","天河区","天河区"};

    private Context context;
    private List<LoveListBean.DataBean.ListBean> mlist;

    public LoveListAdapter(List<LoveListBean.DataBean.ListBean> mlist,Context context) {
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
        ViewHolder holder=new ViewHolder();
        if (convertView==null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_item_love_day, parent, false);
            holder.info= (TextView) convertView.findViewById(R.id.tv_love_item_info);
            holder.loveNum= (TextView) convertView.findViewById(R.id.tv_love_item_love_num);
            holder.time= (TextView) convertView.findViewById(R.id.tv_love_item_time);

            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }

//        holder.info.setText(address[position]);
        holder.info.setText(mlist.get(position).getNotes());
        holder.time.setText(mlist.get(position).getCreate_time());
        if (mlist.get(position).getType().equals("INCOME")){
            holder.loveNum.setText("+"+mlist.get(position).getSource());
        }

        return convertView;
    }

    class ViewHolder{
        TextView info,time,loveNum;
    }
}
