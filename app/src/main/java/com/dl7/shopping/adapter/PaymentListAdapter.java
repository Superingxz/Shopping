package com.dl7.shopping.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dl7.shopping.R;
import com.dl7.shopping.bean.PaymentBean;
import com.dl7.shopping.utils.FontManager;

import java.util.List;

/**
 * Created by MC.Zeng on 2017-07-24.
 */

public class PaymentListAdapter extends BaseAdapter {

    private List<PaymentBean.DataBean> dataBean;
    private Context context;
    private Typeface iconFont;
    private LinearLayout itemPayment;
    private Activity activity;

//    private Callback mCallback;
//
//    public interface Callback {
//        void click(View v);
//    }

    public PaymentListAdapter(List<PaymentBean.DataBean> dataBean, Context context,Activity activity) {
        this.dataBean=dataBean;
        this.context = context;
        this.activity=activity;
//        mCallback=callback;
    }
    @Override
    public int getCount() {
        return dataBean.size();
    }

    @Override
    public Object getItem(int position) {
        return dataBean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        iconFont = FontManager.getTypeface(context, FontManager.FONTAWESOME);
        if (convertView==null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_payment, parent, false);
//            final TextView checkBox = (TextView) convertView.findViewById(R.id.tv_check_box_item);
            LinearLayout allCheck = (LinearLayout) convertView.findViewById(R.id.ll_payment_layout);
//            checkBox.setTypeface(iconFont);
            dataBean.get(position).setIs_all_check(false);//默认不全选
            TextView title = (TextView) convertView.findViewById(R.id.tv_payment_name);
            title.setText(dataBean.get(position).getBrand_name());

            //得到scroview里面的布局，然后替换掉里面的布局
            LinearLayout itemScroViewll = (LinearLayout) convertView.findViewById(R.id.ll_item_payment_scroview);
            LayoutInflater mflater = LayoutInflater.from(context);

            itemScroViewll.removeAllViews();

            for (int i = 0; i < dataBean.get(position).getWater_group().size(); i++) {
                //先移除之前在scroview里面LinearLayout的布局
                dataBean.get(position).getWater_group().get(i).setIs_check(false);//默认不选中
                View infoView  = (LinearLayout) mflater.inflate(R.layout.item_item_payment_product, null);
                final TextView itemCheckBox = (TextView) infoView.findViewById(R.id.tv_check_box_item_item);
                itemPayment = (LinearLayout) infoView.findViewById(R.id.ll_item_payment);
                ImageView img = (ImageView) infoView.findViewById(R.id.img_payment_head);
                final TextView itemTitle = (TextView) infoView.findViewById(R.id.tv_payment_name_item);
                itemCheckBox.setTypeface(iconFont);
                TextView useAble = (TextView) infoView.findViewById(R.id.tv_payment_useable);
                TextView totalNum = (TextView) infoView.findViewById(R.id.tv_payment_residue);
                ImageView head = (ImageView) infoView.findViewById(R.id.img_payment_head);

                itemTitle.setText(dataBean.get(position).getWater_group().get(i).getGoods_name());
                useAble.setText("可用"+dataBean.get(position).getWater_group().get(i).getNumber()+"桶");
                totalNum.setText("剩余"+dataBean.get(position).getWater_group().get(i).getTotal_num()+"桶");
                Glide.with(context).load("http://shop-img.agymall.com/water/big/" + dataBean.get(position).getWater_group().get(i).getImage_url()).into(img);
                final TextView num = (TextView) infoView.findViewById(R.id.tv_payment_num);
                ImageView subtract = (ImageView) infoView.findViewById(R.id.img_payment_subtract);
                ImageView add = (ImageView) infoView.findViewById(R.id.img_payment_add);
                Log.i("getView: ",dataBean.get(position).getWater_group().get(i).getNum()+"" );
//                num.setText(dataBean.get(position).getWater_group().get(i).getNum()+"");

                final int finalI = i;

//                if (dataBean.get(position).getWater_group().get(i).isIs_check()==false){
//                    dataBean.get(position).setIs_all_check(false);
//                }

//                allCheck.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dataBean.get(position).setIs_all_check(!dataBean.get(position).isIs_all_check());
//                        if (dataBean.get(position).isIs_all_check()){
//                            checkBox.setText(R.string.address);
//
//                            for (int j = 0; j < dataBean.get(position).getWater_group().size(); j++) {
//                                dataBean.get(position).getWater_group().get(j).setIs_check(true);
//                                itemCheckBox.setText(R.string.address);
//                            }
//                        }else{
//                            checkBox.setText(R.string.circle);
//
//                            dataBean.get(position).getWater_group().get(finalI).setIs_check(false);
//                            for (int j = 0; j < dataBean.get(position).getWater_group().size(); j++) {
//                                dataBean.get(position).getWater_group().get(j).setIs_check(false);
//                                itemCheckBox.setText(R.string.circle);
//                            }
//                        }
//                    }
//                });

//                if (dataBean.get(position).isIs_all_check()){
//                    checkBox.setText(R.string.address);
//                }else{
//                    checkBox.setText(R.string.circle);
//                }

//                if (dataBean.get(position).isIs_all_check()){
//                    for (int j = 0; j < dataBean.get(position).getWater_group().size(); j++) {
//                        dataBean.get(position).getWater_group().get(j).setIs_check(true);
//                    }
//                }else{
//                    for (int j = 0; j < dataBean.get(position).getWater_group().size(); j++) {
//                        dataBean.get(position).getWater_group().get(j).setIs_check(false);
//                    }
//                }

                itemPayment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dataBean.get(position).getWater_group().get(finalI).setIs_check(!dataBean.get(position).getWater_group().get(finalI).isIs_check());
                        if (dataBean.get(position).getWater_group().get(finalI).isIs_check()){
                            itemCheckBox.setText(R.string.address);
                        }else{
                            itemCheckBox.setText(R.string.circle);
                        }
                    }
                });
                if (dataBean.get(position).getWater_group().get(finalI).isIs_check()){
                    itemCheckBox.setText(R.string.address);
                }else{
                    itemCheckBox.setText(R.string.circle);
                }
//
//                subtract.setTag(position+","+finalI);
//                add.setTag(position+","+finalI);
//                subtract.setOnClickListener(this);
//                add.setOnClickListener(this);


                //减少数量
                subtract.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (dataBean.get(position).getWater_group().get(finalI).isIs_check()){
                            //将得到的num的数据String转化为int
                            int number = Integer.parseInt(num.getText().toString());
                            if (number>1){
                                number--;
                                num.setText(number+"");
                                TextView allNum = (TextView) activity.findViewById(R.id.tv_payment_total_num);
                                allNum.setText(itemTitle.getText().toString()+number+"");
                                notifyDataSetChanged();
                            }else {
                                Toast.makeText(context, "至少一桶才能下单", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });


                //增加数量
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (dataBean.get(position).getWater_group().get(finalI).isIs_check()) {
                            int number = Integer.parseInt(num.getText().toString());
                            number++;
                            num.setText(number + "");
                            TextView allNum = (TextView) activity.findViewById(R.id.tv_payment_total_num);
                            TextView orderName = (TextView) activity.findViewById(R.id.tv_payment_order_name);
                            allNum.setText(number + "");
                            orderName.setText(itemTitle.getText().toString());
                            notifyDataSetChanged();
                        }
                    }
                });
                itemScroViewll.addView(infoView);
            }
        }
        return convertView;
    }

//    @Override
//    public void onClick(View v) {
//        mCallback.click(v);
//    }
}
