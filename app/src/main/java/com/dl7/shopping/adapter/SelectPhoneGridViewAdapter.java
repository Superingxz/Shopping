package com.dl7.shopping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dl7.shopping.R;
import com.dl7.shopping.bean.SelectPhoneNumBean;

import java.util.List;

/**
 * Created by MC.Zeng on 2017-07-28.
 */

public class SelectPhoneGridViewAdapter extends BaseAdapter {
    private Context context;
    private List<SelectPhoneNumBean.DataBean.ListBean> mList;

    public SelectPhoneGridViewAdapter(List<SelectPhoneNumBean.DataBean.ListBean> mList,Context context) {
        this.mList=mList;
        this.context=context;
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
        convertView = LayoutInflater.from(context).inflate(R.layout.item_select_phone_gv,parent,false);
        TextView phoneNum = (TextView) convertView.findViewById(R.id.tv_select_phone_item);
        phoneNum.setText(mList.get(position).getPhone_number());
        return convertView;
    }
}
