package com.dl7.shopping.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dl7.shopping.R;
import com.dl7.shopping.bean.ComboItemBean;
import com.dl7.shopping.module.activity.home.ComboOrderActivity;
import com.dl7.shopping.utils.FontManager;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by MC.Zeng on 2017-07-29.
 */

public class ComboItemAdapter extends BaseAdapter {
    private Context context;
    private List<ComboItemBean.DataBean> mList;
    private Typeface iconFont;

    public ComboItemAdapter(List<ComboItemBean.DataBean> mList, Context context) {
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
        iconFont = FontManager.getTypeface(context, FontManager.FONTAWESOME);
        ViewHolder holder;
        if (convertView==null){
            holder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.item_combo_list_item,parent,false);
            holder.buy= (TextView) convertView.findViewById(R.id.tv_combo_item_buy);
            holder.enable= (TextView) convertView.findViewById(R.id.tv_item_combo_item_enable_water);
            holder.icon= (TextView) convertView.findViewById(R.id.tv_item_combo_item_icon);
            holder.love= (TextView) convertView.findViewById(R.id.tv_item_combo_item_love_num);
            holder.money= (TextView) convertView.findViewById(R.id.tv_item_combo_item_money);
            holder.title= (TextView) convertView.findViewById(R.id.tv_item_combo_item_name);
            holder.total= (TextView) convertView.findViewById(R.id.tv_item_combo_item_total_water);

            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        int money = mList.get(position).getMoney();
        int discount = mList.get(position).getDiscount();

        holder.title.setText(mList.get(position).getName());
        holder.icon.setTypeface(iconFont);
        holder.enable.setText("每月可用水量:"+mList.get(position).getAvailable_num()+"");
        holder.total.setText("该套餐总水量"+mList.get(position).getNumber()+"");
        DecimalFormat df1   =new   java.text.DecimalFormat("#.00");
        holder.money.setText("¥  "+df1.format(mList.get(position).getMoney()/100));
        holder.love.setText("+"+mList.get(position).getScore());

        holder.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ComboOrderActivity.class);
                intent.putExtra("group_id",mList.get(position).getId());
                intent.putExtra("addressID",mList.get(position).getAddressID());
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    class ViewHolder{
        TextView title,money,total,enable,buy,icon,love;
    }
}