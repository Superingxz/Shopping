package com.dl7.shopping.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dl7.shopping.R;
import com.dl7.shopping.bean.AddressMessageBean;
import com.dl7.shopping.utils.FontManager;
import com.myz.customview.universaladapter.BaseViewHoldHelper;
import com.myz.customview.universaladapter.abslistview.CommonAblistViewAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.dl7.shopping.R.id.tv_address_defult_circle;

/**
 * Created by MC.Zeng on 2017-07-07.
 */

public class AddressMessageAdapter extends CommonAblistViewAdapter<AddressMessageBean.DataBean.ListBean>
        implements View.OnClickListener{

    private Context context;
    private Typeface iconFont;
    private List<AddressMessageBean.DataBean.ListBean> mList=new ArrayList<>();


    private Callback mCallback;

    public interface Callback {
        void click(View v);
    }

    public AddressMessageAdapter(int layoutId,List<AddressMessageBean.DataBean.ListBean> mList,Context context,  Callback callback) {
        super(context,layoutId,mList);
        this.mList=mList;
        this.context = context;
        mCallback = callback;
    }

    @Override
    public void convert(BaseViewHoldHelper holder, AddressMessageBean.DataBean.ListBean listBean) {
        iconFont = FontManager.getTypeface(context, FontManager.FONTAWESOME);
        holder.setText(R.id.tv_address_name, listBean.getContact());
        holder.setText(R.id.tv_address_phone, listBean.getMobile());
        holder.setText(R.id.tv_address_detail, listBean.getAddress());
        ((TextView) holder.getView(R.id.tv_address_defult_circle)).setTypeface(iconFont);
        ((TextView) holder.getView(R.id.tv_edit_icon)).setTypeface(iconFont);
        ((TextView) holder.getView(R.id.tv_delete_icon)).setTypeface(iconFont);
        if (listBean.getIs_default()==0){
            holder.setTextColor(R.id.tv_address_detail, R.string.address);
            holder.setTextColor(R.id.tv_address_defult_circle,Color.parseColor("#35bb8a"));
            holder.setText(R.id.tv_address_defult_circle,"默认地址");
            holder.setTextColor(R.id.tv_address_default,Color.parseColor("#35bb8a"));
            holder.setTextColor(R.id.tv_address_default, Color.parseColor("#35bb8a"));
        }else if (listBean.getIs_default()==1){
            holder.setText(tv_address_defult_circle, R.string.circle);
            holder.setTextColor(R.id.tv_address_defult_circle, Color.parseColor("#1a1a1a"));
            holder.setText(R.id.tv_address_default, "设为默认地址");
            holder.setTextColor(R.id.tv_address_default,Color.parseColor("#1a1a1a"));
        }

        LinearLayout llDefault = holder.getView(R.id.ll_address_default);
        LinearLayout llDelete = holder.getView(R.id.ll_address_delete);
        LinearLayout llEdit = holder.getView(R.id.ll_address_edit);
        llDefault.setOnClickListener(this);
        llDefault.setTag(holder.getPosition());
        llDelete.setOnClickListener(this);
        llDelete.setTag(holder.getPosition());
        llEdit.setTag(holder.getPosition());
    }

    public void onClick(View v) {
        mCallback.click(v);
    }

}
