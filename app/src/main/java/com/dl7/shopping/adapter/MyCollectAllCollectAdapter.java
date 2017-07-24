package com.dl7.shopping.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dl7.shopping.R;
import com.dl7.shopping.utils.FontManager;


/**
 * Created by MC.Zeng on 2017-07-07.
 */

public class MyCollectAllCollectAdapter extends BaseAdapter {

    private Context context;
    private int[] img={R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
    private String[] title={"广大桶装水","广大桶装水","广大桶装水","广大桶装水","广大桶装水","广大桶装水"};
    private String[] money={"¥ 25","¥ 25","¥ 25","¥ 25","¥ 25","¥ 25"};
    private Typeface iconFont;

    public MyCollectAllCollectAdapter(int[] img,String[] title,String[] money,Context context) {
        this.img=img;
        this.title=title;
        this.money=money;
        this.context = context;
    }
    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public Object getItem(int position) {
        return title[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        iconFont = FontManager.getTypeface(context, FontManager.FONTAWESOME);
        ViewHolder holder=new ViewHolder();
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_collect,parent,false);
            holder.img= (ImageView) convertView.findViewById(R.id.img_collect_listview_item);
            holder.title= (TextView) convertView.findViewById(R.id.tv_collect_item_title);
            holder.money= (TextView) convertView.findViewById(R.id.tv_collect_item_money);
            holder.shoppingcart= (TextView) convertView.findViewById(R.id.tv_collect_item_shoppingcart);

            holder.shoppingcart.setTypeface(iconFont);
            holder.img.setImageResource(img[position]);
            holder.title.setText(title[position]);
            holder.money.setText(money[position]);

        }

        return convertView;
    }

    class ViewHolder{
        ImageView img;
        TextView title,money,shoppingcart;
    }
}
