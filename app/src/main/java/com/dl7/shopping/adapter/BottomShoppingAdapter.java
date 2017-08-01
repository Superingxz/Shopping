package com.dl7.shopping.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dl7.shopping.R;
import com.dl7.shopping.utils.FontManager;


/**
 * Created by MC.Zeng on 2017-07-30.
 */

public class BottomShoppingAdapter extends BaseAdapter {

    private Context context;
    private Typeface iconFont;

    public BottomShoppingAdapter(Context context) {

        this.context = context;
    }
    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        iconFont = FontManager.getTypeface(context, FontManager.FONTAWESOME);
        ViewHolder holder;
        if (convertView==null){
            holder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.item_bottom_shopping_lv,parent,false);
            holder.icon= (TextView) convertView.findViewById(R.id.tv_bottom_shopping_award_icon);


            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }

        holder.icon.setTypeface(iconFont);


        return convertView;
    }

    class ViewHolder{
        TextView name,phone,address,icon,defult,edit,delete;
    }
}
