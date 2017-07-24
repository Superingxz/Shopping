package com.dl7.shopping.adapter;

/**
 * Created by MC.Zeng on 2017-07-10.
 */

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dl7.shopping.R;


public class LeftListAdapter extends BaseAdapter {

    private Context context;
    private String[] strs;
    private int index;

    public LeftListAdapter(Context context, String[] strs) {
        this.context = context;
        this.strs = strs;
    }

    public void setIndex(int index) {
        this.index = index;
        notifyDataSetChanged();
    }

    public int getIndex(){
        return index;
    }

    @Override
    public int getCount() {
        return strs.length;
    }

    @Override
    public Object getItem(int position) {
        return strs[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater mInflater = LayoutInflater.from(context);
            convertView = mInflater.inflate(R.layout.item_item_left_list, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.leftListText.setText(strs[position]);
//        holder.leftListText.setOnClickListener(new TextClick(position));
        if (position == index) {
            holder.leftListText.setBackgroundColor(Color.WHITE);
        } else {
            holder.leftListText.setBackgroundColor(Color.TRANSPARENT);
        }


        return convertView;
    }

    class ViewHolder {
        TextView leftListText;

        public ViewHolder(View view) {
            leftListText = (TextView) view.findViewById(R.id.leftListText);
        }
    }

    private class TextClick implements View.OnClickListener {
        private int index;

        public TextClick(int index) {
            this.index = index;
        }

        @Override
        public void onClick(View v) {
            setIndex(index);
        }
    }
}
