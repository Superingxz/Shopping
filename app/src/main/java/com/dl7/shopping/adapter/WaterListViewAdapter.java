package com.dl7.shopping.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dl7.shopping.R;
import com.dl7.shopping.bean.WaterBean;
import com.dl7.shopping.module.activity.home.DetailActivity;
import com.dl7.shopping.module.activity.home.payment.PaymentActivity;
import com.dl7.shopping.utils.FontManager;
import com.example.zhouwei.library.CustomPopWindow;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by MC.Zeng on 2017-07-03.
 */

public class WaterListViewAdapter extends BaseAdapter implements View.OnClickListener{

    private Context context;
    private List<int[]> lists = new ArrayList<>();
    private Typeface iconFont;
    private TextView shoppingimg1;
    private TextView shoppingimg2;
    private TextView shoppingimg3;
    private ImageView product1;
    private ImageView product2;
    private ImageView product3;

    private Callback mCallback;
    private HorizontalScrollView scrollView;
    private LinearLayout scrollViewItem;
    private List<WaterBean.DataBean> dataBean;
    private List<WaterBean.DataBean.WATERABean> wateraBean;
    private List<WaterBean.DataBean.WATERBBean> waterbBean;
    private List<WaterBean.DataBean.WATERCBean> watercBean;
    private List<WaterBean.DataBean.WATERDBean> waterdBean;
    private PopupWindow popupWindow;
    private int number=1;
    private CustomPopWindow popWindow;
    //    private Activity activity;

    public interface Callback {
        void click(View v);
    }

    public WaterListViewAdapter(List<WaterBean.DataBean> dataBean, Context context,  Callback callback) {
        this.dataBean=dataBean;
        this.context = context;
//        this.activity=activity;
        mCallback = callback;
    }


    @Override
    public int getCount() {
        Log.i("getCount: ", dataBean.size()+"");
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
    public View getView(int position, View convertView, final ViewGroup parent) {
        iconFont = FontManager.getTypeface(context, FontManager.FONTAWESOME);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_water_liseview, parent, false);

            TextView category_name = (TextView) convertView.findViewById(R.id.tv_item_water_category_name);

            //得到scroview里面的布局，然后替换掉里面的布局
            LinearLayout itemScroViewll = (LinearLayout) convertView.findViewById(R.id.ll_item_water_scroview);
            LayoutInflater mflater = LayoutInflater.from(context);

            //先移除之前在scroview里面LinearLayout的布局
            itemScroViewll.removeAllViews();

            //itemScroViewll.addView(infoView1);
            //itemScroViewll.addView(infoView1);

            if (position==0){
                for(int i =0 ;i<dataBean.get(position).getWATER_A().size();i++){
                    Log.i("getView: ",dataBean.get(position).getWATER_A().size()+"");
                    //得到需要添加进去的布局
                    View infoView  = (LinearLayout) mflater.inflate(R.layout.item_scrollview_item_water, null);
                    final TextView icon = (TextView) infoView.findViewById(R.id.shopping_img);
                    icon.setTypeface(iconFont);

                    icon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            initPopupWindow(icon);
                        }
                    });
                    LinearLayout llgoods = (LinearLayout) infoView.findViewById(R.id.ll_item_item);
                    final String goods_id = dataBean.get(position).getWATER_A().get(i).getId();
                    llgoods.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.i("getView: ", goods_id);
//                            Intent intent=new Intent(context,DetailsActivity.class);
//                            intent.putExtra("goods_id",goods_id);
//                            context.startActivity(intent);
                            Intent intent=new Intent(context,DetailActivity.class);
                            intent.putExtra("top", "ListView");
                            intent.putExtra("goods_id",goods_id);
                            intent.putExtra("bottom", "WebView");
                            SharedPreferences sp1 = context.getSharedPreferences("flag", context.MODE_PRIVATE);
                            SharedPreferences.Editor editor1 = sp1.edit();
                            editor1.putString("goods_id", goods_id);
                            editor1.commit();
                            context.startActivity(intent);
                        }
                    });
                    ImageView img = (ImageView) infoView.findViewById(R.id.img_water_product);
                    Glide.with(context).load("http://shop-img.agymall.com/water/small/"+dataBean.get(position).getWATER_A().get(i).getImage_url()).into(img);
                    TextView goodsName = (TextView) infoView.findViewById(R.id.tv_item_water_goods_name);
                    goodsName.setText(dataBean.get(position).getWATER_A().get(i).getGoods_name());
                    TextView company = (TextView) infoView.findViewById(R.id.tv_item_water_company);
                    company.setText(dataBean.get(position).getWATER_A().get(i).getSpecification()+"/"+dataBean.get(position).getWATER_A().get(i).getCompany());
                    TextView money = (TextView) infoView.findViewById(R.id.tv_item_water_money);
                    money.setText("¥  "+dataBean.get(position).getWATER_A().get(i).getPresent_price());
                    category_name.setText(dataBean.get(position).getWATER_A().get(i).getCategory_name());

                    //添加进去
                    itemScroViewll.addView(infoView);
                }
            }else if (position==1) {
                for (int i = 0; i < dataBean.get(position).getWATER_B().size(); i++) {
                    //得到需要添加进去的布局
                    View infoView = (LinearLayout) mflater.inflate(R.layout.item_scrollview_item_water, null);
                    final TextView icon = (TextView) infoView.findViewById(R.id.shopping_img);
                    icon.setTypeface(iconFont);
                    icon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            initPopupWindow(icon);
                        }
                    });
                    LinearLayout llgoods = (LinearLayout) infoView.findViewById(R.id.ll_item_item);
                    final String goods_id = dataBean.get(position).getWATER_B().get(i).getId();
                    llgoods.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.i("getView: ", goods_id);
                            Intent intent=new Intent(context,DetailActivity.class);
                            intent.putExtra("top", "ListView");
                            intent.putExtra("goods_id",goods_id);
                            intent.putExtra("bottom", "WebView");
                            SharedPreferences sp1 = context.getSharedPreferences("flag", context.MODE_PRIVATE);
                            SharedPreferences.Editor editor1 = sp1.edit();
                            editor1.putString("goods_id", goods_id);
                            editor1.commit();
                            context.startActivity(intent);
                        }
                    });
                    ImageView img = (ImageView) infoView.findViewById(R.id.img_water_product);
                    Glide.with(context).load("http://shop-img.agymall.com/water/small/" + dataBean.get(position).getWATER_B().get(i).getImage_url()).into(img);
                    TextView goodsName = (TextView) infoView.findViewById(R.id.tv_item_water_goods_name);
                    goodsName.setText(dataBean.get(position).getWATER_B().get(i).getGoods_name());
                    TextView company = (TextView) infoView.findViewById(R.id.tv_item_water_company);
                    company.setText(dataBean.get(position).getWATER_B().get(i).getSpecification() + "/" + dataBean.get(position).getWATER_B().get(i).getCompany());
                    TextView money = (TextView) infoView.findViewById(R.id.tv_item_water_money);
                    money.setText("¥  " + dataBean.get(position).getWATER_B().get(i).getPresent_price());
                    category_name.setText(dataBean.get(position).getWATER_B().get(i).getCategory_name());

                    //添加进去
                    itemScroViewll.addView(infoView);
                }
            }else if (position==2) {
                for (int i = 0; i < dataBean.get(position).getWATER_C().size(); i++) {
                    //得到需要添加进去的布局
                    View infoView = (LinearLayout) mflater.inflate(R.layout.item_scrollview_item_water, null);
                    final TextView icon = (TextView) infoView.findViewById(R.id.shopping_img);
                    icon.setTypeface(iconFont);
                    icon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            initPopupWindow(icon);
                        }
                    });
                    LinearLayout llgoods = (LinearLayout) infoView.findViewById(R.id.ll_item_item);
                    final String goods_id = dataBean.get(position).getWATER_C().get(i).getId();
                    llgoods.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.i("getView: ", goods_id);
                            Intent intent=new Intent(context,DetailActivity.class);
                            intent.putExtra("top", "ListView");
                            intent.putExtra("goods_id",goods_id);
                            intent.putExtra("bottom", "WebView");
                            SharedPreferences sp1 = context.getSharedPreferences("flag", context.MODE_PRIVATE);
                            SharedPreferences.Editor editor1 = sp1.edit();
                            editor1.putString("goods_id", goods_id);
                            editor1.commit();
                            context.startActivity(intent);
                        }
                    });
                    ImageView img = (ImageView) infoView.findViewById(R.id.img_water_product);
                    Glide.with(context).load("http://shop-img.agymall.com/water/small/" + dataBean.get(position).getWATER_C().get(i).getImage_url()).into(img);
                    TextView goodsName = (TextView) infoView.findViewById(R.id.tv_item_water_goods_name);
                    goodsName.setText(dataBean.get(position).getWATER_C().get(i).getGoods_name());
                    TextView company = (TextView) infoView.findViewById(R.id.tv_item_water_company);
                    company.setText(dataBean.get(position).getWATER_C().get(i).getSpecification() + "/" + dataBean.get(position).getWATER_C().get(i).getCompany());
                    TextView money = (TextView) infoView.findViewById(R.id.tv_item_water_money);
                    money.setText("¥  " + dataBean.get(position).getWATER_C().get(i).getPresent_price());
                    category_name.setText(dataBean.get(position).getWATER_C().get(i).getCategory_name());

                    //添加进去
                    itemScroViewll.addView(infoView);
                }
            }else if (position==3) {
                for (int i = 0; i < dataBean.get(position).getWATER_D().size(); i++) {
                    //得到需要添加进去的布局
                    View infoView = (LinearLayout) mflater.inflate(R.layout.item_scrollview_item_water, null);
                    final TextView icon = (TextView) infoView.findViewById(R.id.shopping_img);
                    icon.setTypeface(iconFont);
                    icon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            initPopupWindow(icon);
                        }
                    });
                    LinearLayout llgoods = (LinearLayout) infoView.findViewById(R.id.ll_item_item);
                    final String goods_id = dataBean.get(position).getWATER_D().get(i).getId();
                    llgoods.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(context,DetailActivity.class);
                            intent.putExtra("top", "ListView");
                            intent.putExtra("goods_id",goods_id);
                            intent.putExtra("bottom", "WebView");
                            SharedPreferences sp1 = context.getSharedPreferences("flag", context.MODE_PRIVATE);
                            SharedPreferences.Editor editor1 = sp1.edit();
                            editor1.putString("goods_id", goods_id);
                            editor1.commit();
                            context.startActivity(intent);
                        }
                    });
                    ImageView img = (ImageView) infoView.findViewById(R.id.img_water_product);
                    Glide.with(context).load("http://shop-img.agymall.com/water/small/" + dataBean.get(position).getWATER_D().get(i).getImage_url()).into(img);
                    TextView goodsName = (TextView) infoView.findViewById(R.id.tv_item_water_goods_name);
                    goodsName.setText(dataBean.get(position).getWATER_D().get(i).getGoods_name());
                    TextView company = (TextView) infoView.findViewById(R.id.tv_item_water_company);
                    company.setText(dataBean.get(position).getWATER_D().get(i).getSpecification() + "/" + dataBean.get(position).getWATER_D().get(i).getCompany());
                    TextView money = (TextView) infoView.findViewById(R.id.tv_item_water_money);
                    money.setText("¥  " + dataBean.get(position).getWATER_D().get(i).getPresent_price());
                    category_name.setText(dataBean.get(position).getWATER_D().get(i).getCategory_name());

                    //添加进去
                    itemScroViewll.addView(infoView);
                }
            }
        }
        return convertView;
    }

    //弹框
    private void initPopupWindow(View view) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View popview = inflater.inflate(R.layout.popupwindow_water, null);

        popWindow = new CustomPopWindow.PopupWindowBuilder(context)
                .setView(popview)//显示的布局，还可以通过设置一个View
                //     .size(600,400) //设置显示的大小，不设置就默认包裹内容
                .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                .size(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
                .setBgDarkAlpha(0.5f) // 控制亮度
                .setFocusable(true)//是否获取焦点，默认为ture
                .setOutsideTouchable(true)//是否PopupWindow 以外触摸dissmiss
                .create()//创建PopupWindow
                .showAtLocation(view, Gravity.BOTTOM,0,0);//设置底部显示

        ImageView head = (ImageView) popview.findViewById(R.id.img_pop_head);
        TextView name = (TextView) popview.findViewById(R.id.tv_pop_name);
        TextView tipIcon = (TextView) popview.findViewById(R.id.tv_pop_tip_icon);
        tipIcon.setTypeface(iconFont);
        TextView tvDismiss = (TextView) popview.findViewById(R.id.tv_pop_dismiss);
        final TextView money = (TextView) popview.findViewById(R.id.tv_pop_money);
        final TextView buyBucket = (TextView) popview.findViewById(R.id.tv_pop_buy);
        final TextView rentBuckt = (TextView) popview.findViewById(R.id.tv_pop_rent);
//        TextView address = (TextView) popview.findViewById(R.id.tv_pop_address);
//        RelativeLayout rlAddress = (RelativeLayout) popview.findViewById(R.id.rl_pop_address);
//        RelativeLayout rlTime = (RelativeLayout) popview.findViewById(R.id.rl_pop_time);
//        TextView time = (TextView) popview.findViewById(R.id.tv_pop_time);
        ImageView subtract = (ImageView) popview.findViewById(R.id.img_pop_subtract);
        ImageView add = (ImageView) popview.findViewById(R.id.img_pop_add);
        final TextView num = (TextView) popview.findViewById(R.id.tv_pop_num);
        TextView payment = (TextView) popview.findViewById(R.id.tv_pop_payment);

        tvDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindow.dissmiss();
            }
        });

        //买桶
        buyBucket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buyBucket.setBackgroundResource(R.drawable.water_textview_background1);
                buyBucket.setTextColor(Color.parseColor("#ffffff"));
                rentBuckt.setBackgroundResource(R.drawable.water_textview_background2);
                rentBuckt.setTextColor(Color.parseColor("#1a1a1a"));
            }
        });
        //租桶
        rentBuckt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rentBuckt.setBackgroundResource(R.drawable.water_textview_background1);
                rentBuckt.setTextColor(Color.parseColor("#ffffff"));
                buyBucket.setBackgroundResource(R.drawable.water_textview_background2);
                buyBucket.setTextColor(Color.parseColor("#1a1a1a"));
            }
        });
//        //地址
//        rlAddress.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getContext(), CitySelectActivity.class);
//                startActivity(intent);
//            }
//        });
//        //时间
//        rlTime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        //减少数量
        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (number>1){
                    number--;
                    num.setText(number+"");
                    money.setText("¥ "+number*25+"");
                }else{
                    Toast.makeText(context, "至少一桶才能下单", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //增加数量
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number++;
                num.setText(number+"");
                money.setText("¥ "+number*25+"");
            }
        });

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, PaymentActivity.class);
                context.startActivity(intent);
                popWindow.dissmiss();
            }
        });

    }


    public void onClick(View v) {
        mCallback.click(v);
    }
}
