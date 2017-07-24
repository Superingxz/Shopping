package com.dl7.shopping.adapter;

import android.content.Context;
import android.widget.TextView;

import com.dl7.shopping.R;
import com.dl7.shopping.bean.HelpBean;
import com.myz.customview.universaladapter.BaseViewHoldHelper;
import com.myz.customview.universaladapter.abslistview.CommonAblistViewAdapter;

import java.util.List;

/**
 * 帮助adapter
 * Created by MC.Zeng on 2017-07-19.
 */

public class HelpAdapter extends CommonAblistViewAdapter<HelpBean> {

    private Context context;
    private List<HelpBean> list;
    private HelpBean helpBean;
    public HelpAdapter(int layoutId,HelpBean helpBean,List<HelpBean> list,Context context){
        super(context,layoutId,list);
        this.list=list;
        this.helpBean=helpBean;
        this.context=context;
    }

    @Override
    public void convert(BaseViewHoldHelper holder, HelpBean helpBean) {
        int tag = helpBean.getTag();
        if (tag ==0){
            holder.setText(R.id.tv_help_item_right, helpBean.getRight());
            holder.setText(R.id.tv_help_item_left, helpBean.getLeft());
        }else if (tag==1){
            holder.setText(R.id.tv_help_item_right, helpBean.getRight());
            holder.setText(R.id.tv_help_item_left, helpBean.getLeft());
        }else if (tag==2){
            holder.setText(R.id.tv_help_item_right, helpBean.getRight());
            holder.setText(R.id.tv_help_item_left, helpBean.getLeft());
        }else if (tag==3){
            holder.setText(R.id.tv_help_item_right, helpBean.getRight());
            holder.setText(R.id.tv_help_item_left, helpBean.getLeft());
        }else if (tag==4){
            holder.setText(R.id.tv_help_item_right, helpBean.getRight());
            holder.setText(R.id.tv_help_item_left, helpBean.getLeft());
        }else if (tag==5){
            holder.setText(R.id.tv_help_item_right, helpBean.getRight());
            holder.setText(R.id.tv_help_item_left, helpBean.getLeft());
        }else if (tag==6){
            holder.setText(R.id.tv_help_item_right, helpBean.getRight());
            holder.setText(R.id.tv_help_item_left, helpBean.getLeft());
        }

    }

    class ViewHolder{
        TextView right,left;
    }
}
