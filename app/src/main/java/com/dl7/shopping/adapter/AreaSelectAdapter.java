package com.dl7.shopping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dl7.shopping.R;


/**
 * Created by MC.Zeng on 2017-07-04.
 */

public class AreaSelectAdapter extends BaseAdapter {
    private String[] address={"天河区","天河区","天河区","天河区","天河区","天河区","天河区","天河区","天河区","天河区","天河区","天河区"};
    private Context context;

    public AreaSelectAdapter(String[] address, Context context) {
        this.address=address;
        this.context = context;
    }
    @Override
    public int getCount() {
        return address.length;
    }

    @Override
    public Object getItem(int position) {
        return address[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.item_address_select,parent,false);
        TextView addressTv = (TextView) convertView.findViewById(R.id.tv_item_address_select);
        addressTv.setText(address[position]);

        return convertView;
    }
}
