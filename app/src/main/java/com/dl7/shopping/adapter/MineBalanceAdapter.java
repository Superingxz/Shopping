package com.dl7.shopping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dl7.shopping.R;


/**
 * Created by MC.Zeng on 2017-06-30.
 */

public class MineBalanceAdapter extends BaseAdapter {

    private int[] img={R.mipmap.ic_launcher, R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};

    private String[] name={"提现","充值","账户明细","大额转账"};
    private Context context;

    public MineBalanceAdapter(int[] img,String[] name, Context context) {
        this.img = img;
        this.name=name;
        this.context = context;
    }


    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public Object getItem(int position) {
        return name[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = new ViewHolder();
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_mine_balance_listview,parent,false);
            viewHolder.balanceImg= (ImageView) convertView.findViewById(R.id.balance_item_img);
            viewHolder.balanceText= (TextView) convertView.findViewById(R.id.balance_item_text);

            convertView.setTag(viewHolder);
        }
        viewHolder.balanceText.setText(name[position]);
        viewHolder.balanceImg.setBackgroundResource(img[position]);


        return convertView;
    }

    class ViewHolder{
        private TextView balanceText;
        private ImageView balanceImg;
    }
}
