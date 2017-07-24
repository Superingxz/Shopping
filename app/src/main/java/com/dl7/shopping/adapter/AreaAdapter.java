package com.dl7.shopping.adapter;

import android.content.Context;
import android.graphics.Color;

import com.dl7.shopping.bean.AreaBean;
import com.myz.customview.universaladapter.BaseViewHoldHelper;
import com.myz.customview.universaladapter.abslistview.CommonAblistViewAdapter;

import java.util.List;

import static com.dl7.shopping.R.id.tv_item_address_select;

/**
 * Created by MC.Zeng on 2017-07-13.
 */

public class AreaAdapter extends CommonAblistViewAdapter<AreaBean.DataBean> {
//    private String[] address={"天河区","天河区","天河区","天河区","天河区","天河区","天河区","天河区","天河区","天河区","天河区","天河区"};
    private List<AreaBean.DataBean> arealist;
    private Context context;
    private int  selectItem=-1;

    public AreaAdapter(int layoutId,List<AreaBean.DataBean> arealist, Context context) {
        super(context,layoutId,arealist);
        this.arealist=arealist;
        this.context = context;
    }

    @Override
    public void convert(BaseViewHoldHelper holder, AreaBean.DataBean dataBean) {
        holder.setText(tv_item_address_select, dataBean.getName());
        if (holder.getPosition() == selectItem){
            holder.getConvertView().setBackgroundColor(Color.parseColor("#35bb8a"));
            holder.setTextColorRes(tv_item_address_select,Color.WHITE);
        }else {
            holder.getConvertView().setBackgroundColor(Color.TRANSPARENT);
            holder.setTextColorRes(tv_item_address_select,Color.BLACK);
        }
    }

    public  void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }

}
