package com.dl7.shopping.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dl7.shopping.R;
import com.dl7.shopping.bean.ComboBean;
import com.dl7.shopping.module.activity.home.combo.ComboItemActivity;
import com.dl7.shopping.module.activity.home.DetailActivity;

import java.util.List;

/**
 * Created by MC.Zeng on 2017-07-08.
 */

public class ComboAdapter extends BaseAdapter {

    private Context context;
    private List<ComboBean.DataBean> mList;


    public ComboAdapter(List<ComboBean.DataBean> mList, Context context) {
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
        ViewHolder viewHolder;
        if (convertView==null){
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.item_combo_listview,parent,false);
            viewHolder.img= (ImageView) convertView.findViewById(R.id.img_combo_item);
            viewHolder.name= (TextView) convertView.findViewById(R.id.tv_combo_item_name);
            viewHolder.specification= (TextView) convertView.findViewById(R.id.tv_combo_item_specification);
            viewHolder.combo= (TextView) convertView.findViewById(R.id.tv_combo_item_combo);
            viewHolder.detail= (TextView) convertView.findViewById(R.id.tv_combo_item_detail);
//            viewHolder.img.setImageResource(img[position]);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(mList.get(position).getGoods_name());
        viewHolder.specification.setText(mList.get(position).getSpecification());
        Glide.with(context).load("http://shop-img.agymall.com/water/small/" + mList.get(position).getImage_url()).into(viewHolder.img);

        viewHolder.combo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ComboItemActivity.class);
                intent.putExtra("storeID",mList.get(position).getStoreID());
                intent.putExtra("addressID",mList.get(position).getAddressID());
                intent.putExtra("goodsID",mList.get(position).getGoods_id());
                context.startActivity(intent);
            }
        });

        viewHolder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,DetailActivity.class);
                intent.putExtra("top", "ListView");
                intent.putExtra("goods_id",mList.get(position).getId());
                intent.putExtra("bottom", "WebView");
                intent.putExtra("business_type","BUY_WATER");
                intent.putExtra("goodsId",mList.get(position).getGoods_id());
                intent.putExtra("addressId",mList.get(position).getAddressID());
                intent.putExtra("money","");
                intent.putExtra("playmethod","third");
                intent.putExtra("bucket","");
                SharedPreferences sp1 = context.getSharedPreferences("flag", context.MODE_PRIVATE);
                SharedPreferences.Editor editor1 = sp1.edit();
                editor1.putString("goods_id", mList.get(position).getId());
                editor1.commit();
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder{
        ImageView img;
        TextView name,specification,combo,detail;
    }
}
