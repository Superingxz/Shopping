package com.dl7.shopping.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dl7.shopping.R;
import com.dl7.shopping.bean.ProvinceBean;
import com.myz.customview.universaladapter.BaseViewHoldHelper;
import com.myz.customview.universaladapter.abslistview.CommonAblistViewAdapter;

import java.util.ArrayList;

/**
 * Created by MC.Zeng on 2017-07-10.
 */

public class ProvinceAdapter extends CommonAblistViewAdapter<ProvinceBean.DataBean> {
    private Context context;
    private ArrayList<ProvinceBean.DataBean> list=new ArrayList<>();
    private Holder holder;
    private int  selectItem=-1;


    public ProvinceAdapter(Context context, int layoutId,ArrayList<ProvinceBean.DataBean> list) {
        super(context,layoutId,list);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = View.inflate(context, R.layout.item_left_listview_city, null);
            holder = new Holder(view);
            view.setTag(holder);

        } else {
            holder = (Holder) view.getTag();

        }

        if (i == selectItem){
            view.setBackgroundColor(Color.parseColor("#35bb8a"));
            holder.tvProvince.setTextColor(Color.WHITE);
        }else {
            view.setBackgroundColor(Color.TRANSPARENT);
            holder.tvProvince.setTextColor(Color.BLACK);
        }
        holder.tvProvince.setText(list.get(i).getName());
        return view;
    }

    @Override
    public void convert(BaseViewHoldHelper holder, ProvinceBean.DataBean dataBean) {
        if (holder.getPosition() == selectItem){
            holder.getConvertView().setBackgroundColor(Color.parseColor("#35bb8a"));
            holder.setTextColor(R.id.tv_province, Color.WHITE);
        }else {
            holder.getConvertView().setBackgroundColor(Color.TRANSPARENT);
            holder.setTextColor(R.id.tv_province, Color.BLACK);
        }
        holder.setText(R.id.tv_province, dataBean.getName());
    }

    public  void setSelectItem(int selectItem) {
        this.selectItem = selectItem;

    }
        private static class Holder {
        TextView tvProvince;

        public Holder(View view) {
            tvProvince = (TextView) view.findViewById(R.id.tv_province);
        }
    }
}
