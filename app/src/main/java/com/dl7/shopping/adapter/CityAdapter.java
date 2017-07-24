package com.dl7.shopping.adapter;

import android.content.Context;
import android.graphics.Color;

import com.dl7.shopping.R;
import com.dl7.shopping.bean.CityBean;
import com.myz.customview.universaladapter.BaseViewHoldHelper;
import com.myz.customview.universaladapter.abslistview.CommonAblistViewAdapter;

import java.util.List;

/**
 * Created by MC.Zeng on 2017-07-10.
 */

public class CityAdapter extends CommonAblistViewAdapter<CityBean.DataBean> {
    private Context context;
    private List<CityBean.DataBean> citylist;
    public CityAdapter(Context context, int layoutId,List<CityBean.DataBean> citylist) {
        super(context,layoutId,citylist);
        this.context = context;
        this.citylist = citylist;
    }
    @Override
    public void convert(BaseViewHoldHelper holder, CityBean.DataBean dataBean) {
        if (holder.getPosition() == selectItem){
            holder.getConvertView().setBackgroundColor(Color.parseColor("#35bb8a"));
            holder.setTextColor(R.id.tv_city, Color.WHITE);
        }else {
            holder.getConvertView().setBackgroundColor(Color.TRANSPARENT);
            holder.setTextColor(R.id.tv_city, Color.BLACK);
        }
        holder.setText(R.id.tv_city, dataBean.getName());
    }

    public  void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }
    private int  selectItem=-1;
}
