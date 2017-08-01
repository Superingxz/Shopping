package com.dl7.shopping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dl7.shopping.R;
import com.dl7.shopping.bean.PhoneCartFragmentListBean;

import java.util.List;

/**
 * Created by MC.Zeng on 2017-07-03.
 */

public class PhoneCUListViewAdapter extends BaseAdapter {

    private Context context;
    private List<PhoneCartFragmentListBean.DataBean> mList;


    public PhoneCUListViewAdapter(List<PhoneCartFragmentListBean.DataBean> mList, Context context) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            viewHolder=new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_phone_lv,parent,false);
            viewHolder.img= (ImageView) convertView.findViewById(R.id.img_phone_item);
            viewHolder.name= (TextView) convertView.findViewById(R.id.tv_phone_item_name);
            viewHolder.money= (TextView) convertView.findViewById(R.id.tv_phone_item_money);

            convertView.setTag(viewHolder);
        } else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.name.setText(mList.get(position).getGoodsName());
        viewHolder.money.setText(mList.get(position).getPresent_price()+"");


        return convertView;
    }

    static class ViewHolder
    {
        ImageView img;
        TextView name,money;
    }
}
