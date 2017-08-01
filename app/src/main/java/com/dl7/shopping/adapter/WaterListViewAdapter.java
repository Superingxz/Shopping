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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.dl7.shopping.R;
import com.dl7.shopping.api.URL;
import com.dl7.shopping.bean.WaterBean;
import com.dl7.shopping.module.activity.home.DetailActivity;
import com.dl7.shopping.module.activity.home.WaterOrderActivity;
import com.dl7.shopping.utils.FontManager;
import com.example.zhouwei.library.CustomPopWindow;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    private WaterBean waterBean=new WaterBean();
    private List<WaterBean.DataBean> dataBean;
    private List<WaterBean.DataBean.WATERABean> wateraBean;
    private List<WaterBean.DataBean.WATERBBean> waterbBean;
    private List<WaterBean.DataBean.WATERCBean> watercBean;
    private List<WaterBean.DataBean.WATERDBean> waterdBean;
    private int number=1;
    private CustomPopWindow popWindow;
    //    private List<SchoolTimeBean.DataBean.TimeDataBean> timeDataList=new ArrayList<>();
    private List<String> dayArray=new ArrayList<>();
    private List<List<String>> timeArray=new ArrayList<>();
    private List<List<String>> sort=new ArrayList<>();
    private OptionsPickerView pvNoLinkOptions;
    private ArrayList<String> day = new ArrayList<>();
    private ArrayList<String> daytime = new ArrayList<>();
    private TextView tvTime;
    private RelativeLayout rlTime;
    private TextView tvNum;
    private TextView tvMoney;
    private String reserve_sort="";
    private String url;
    private int present_price;
    private TextView tvName;
    private ImageView popHead;
    private String image_url;
    private String goods_name;
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
            final int position1=position;

            convertView = LayoutInflater.from(context).inflate(R.layout.item_water_liseview, parent, false);

            TextView category_name = (TextView) convertView.findViewById(R.id.tv_item_water_category_name);

            //得到scroview里面的布局，然后替换掉里面的布局
            LinearLayout itemScroViewll = (LinearLayout) convertView.findViewById(R.id.ll_item_water_scroview);
            LayoutInflater mflater = LayoutInflater.from(context);

            //先移除之前在scroview里面LinearLayout的布局
            itemScroViewll.removeAllViews();

            //itemScroViewll.addView(infoView1);
            //itemScroViewll.addView(infoView1);
            if (position1==0){
                for(int i =0 ;i<dataBean.get(position).getWATER_A().size();i++){
                    final int finalI = i;
                    //得到需要添加进去的布局
                    View infoView  = (LinearLayout) mflater.inflate(R.layout.item_scrollview_item_water, null);
                    final TextView icon = (TextView) infoView.findViewById(R.id.shopping_img);
                    icon.setTypeface(iconFont);



                    icon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (dataBean.get(position1).getWATER_A().get(finalI).getCategory_name().equals("空桶")){
                                initPopupWindow(icon, true,position1,finalI);
                            }else{
                                initPopupWindow(icon, false,position1,finalI);
                            }

                        }
                    });
                    LinearLayout llgoods = (LinearLayout) infoView.findViewById(R.id.ll_item_item);
                    final String id = dataBean.get(position).getWATER_A().get(i).getId();
                    llgoods.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.i("getView: ", id);
//                            Intent intent=new Intent(context,DetailsActivity.class);
//                            intent.putExtra("goods_id",goods_id);
//                            context.startActivity(intent);
                            Intent intent=new Intent(context,DetailActivity.class);
                            intent.putExtra("top", "ListView");
                            intent.putExtra("goods_id",id);
                            intent.putExtra("bottom", "WebView");
                            SharedPreferences sp1 = context.getSharedPreferences("flag", context.MODE_PRIVATE);
                            SharedPreferences.Editor editor1 = sp1.edit();
                            editor1.putString("goods_id", id);
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
                    final int finalI = i;
                    icon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (dataBean.get(position1).getWATER_B().get(finalI).getCategory_name().equals("空桶")){
                                initPopupWindow(icon, true,position1,finalI);
                            }else{
                                initPopupWindow(icon, false,position1,finalI);
                            }

                        }
                    });
                    LinearLayout llgoods = (LinearLayout) infoView.findViewById(R.id.ll_item_item);
                    final String id = dataBean.get(position).getWATER_B().get(i).getId();
                    llgoods.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(context,DetailActivity.class);
                            intent.putExtra("top", "ListView");
                            intent.putExtra("goods_id",id);
                            intent.putExtra("bottom", "WebView");
                            SharedPreferences sp1 = context.getSharedPreferences("flag", context.MODE_PRIVATE);
                            SharedPreferences.Editor editor1 = sp1.edit();
                            editor1.putString("goods_id", id);
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
                    final int finalI = i;
                    icon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (dataBean.get(position1).getWATER_C().get(finalI).getCategory_name().equals("空桶")){
                                initPopupWindow(icon, true,position1,finalI);
                            }else{
                                initPopupWindow(icon, false,position1,finalI);
                            }

                        }
                    });
                    LinearLayout llgoods = (LinearLayout) infoView.findViewById(R.id.ll_item_item);
                    final String id = dataBean.get(position).getWATER_C().get(i).getId();
                    llgoods.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.i("getView: ", id);
                            Intent intent=new Intent(context,DetailActivity.class);
                            intent.putExtra("top", "ListView");
                            intent.putExtra("goods_id",id);
                            intent.putExtra("bottom", "WebView");
                            SharedPreferences sp1 = context.getSharedPreferences("flag", context.MODE_PRIVATE);
                            SharedPreferences.Editor editor1 = sp1.edit();
                            editor1.putString("goods_id", id);
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
                    final int finalI = i;
                    Log.i("onClick: ",dataBean.get(position).getWATER_A().get(finalI).getCategory_name() );
                    icon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (dataBean.get(position1).getWATER_D().get(finalI).getCategory_name().equals("空桶")){
                                initPopupWindow(icon, true,position1,finalI);
                            }else{
                                initPopupWindow(icon, false,position1,finalI);
                            }
                        }
                    });
                    LinearLayout llgoods = (LinearLayout) infoView.findViewById(R.id.ll_item_item);
                    final String id = dataBean.get(position).getWATER_D().get(i).getId();
                    llgoods.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(context,DetailActivity.class);
                            intent.putExtra("top", "ListView");
                            intent.putExtra("goods_id",id);
                            intent.putExtra("bottom", "WebView");
                            SharedPreferences sp1 = context.getSharedPreferences("flag", context.MODE_PRIVATE);
                            SharedPreferences.Editor editor1 = sp1.edit();
                            editor1.putString("goods_id", id);
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
    private void initPopupWindow(View view, final boolean isBucket, final int position, final int i) {
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

        popHead = (ImageView) popview.findViewById(R.id.img_pop_head);
        tvName = (TextView) popview.findViewById(R.id.tv_pop_name);
        TextView tipIcon = (TextView) popview.findViewById(R.id.tv_pop_tip_icon);
        LinearLayout rlBucket = (LinearLayout) popview.findViewById(R.id.ll_pop_buy_or_rent);
        tipIcon.setTypeface(iconFont);
        TextView tvDismiss = (TextView) popview.findViewById(R.id.tv_pop_dismiss);
        tvMoney = (TextView) popview.findViewById(R.id.tv_pop_money);
        final TextView buyBucket = (TextView) popview.findViewById(R.id.tv_pop_buy);
        final TextView rentBuckt = (TextView) popview.findViewById(R.id.tv_pop_rent);
//        TextView address = (TextView) popview.findViewById(R.id.tv_pop_address);
//        RelativeLayout rlAddress = (RelativeLayout) popview.findViewById(R.id.rl_pop_address);
        rlTime = (RelativeLayout) popview.findViewById(R.id.rl_pop_time);
        tvTime = (TextView) popview.findViewById(R.id.tv_pop_time);
        ImageView subtract = (ImageView) popview.findViewById(R.id.img_pop_subtract);
        ImageView add = (ImageView) popview.findViewById(R.id.img_pop_add);
        tvNum = (TextView) popview.findViewById(R.id.tv_pop_num);
        TextView payment = (TextView) popview.findViewById(R.id.tv_pop_payment);


        if (position==0){
            number=dataBean.get(position).getWATER_A().get(i).getNum();
            tvNum.setText(number+"");
            goods_name = dataBean.get(position).getWATER_A().get(i).getGoods_name();
            present_price = dataBean.get(position).getWATER_A().get(i).getPresent_price();
            tvName.setText(dataBean.get(position).getWATER_A().get(i).getGoods_name());
            image_url = dataBean.get(position).getWATER_A().get(i).getImage_url();
            Glide.with(context).load("http://shop-img.agymall.com/water/small/" + dataBean.get(position).getWATER_A().get(i).getImage_url()).into(popHead);
            SharedPreferences sp1 = context.getSharedPreferences("flag", context.MODE_PRIVATE);
            SharedPreferences.Editor editor1 = sp1.edit();
            editor1.putString("popnume",goods_name);
            editor1.putInt("popprice",present_price);
            editor1.putString("popurl",image_url);
            editor1.commit();
        }else if (position==1){
            number=dataBean.get(position).getWATER_B().get(i).getNum();
            tvNum.setText(number+"");
            goods_name = dataBean.get(position).getWATER_B().get(i).getGoods_name();
            present_price = dataBean.get(position).getWATER_B().get(i).getPresent_price();
            tvName.setText(dataBean.get(position).getWATER_B().get(i).getGoods_name());
            image_url = dataBean.get(position).getWATER_B().get(i).getImage_url();
            Glide.with(context).load("http://shop-img.agymall.com/water/small/" + dataBean.get(position).getWATER_B().get(i).getImage_url()).into(popHead);
            SharedPreferences sp1 = context.getSharedPreferences("flag", context.MODE_PRIVATE);
            SharedPreferences.Editor editor1 = sp1.edit();
            editor1.putString("popnume",goods_name);
            editor1.putInt("popprice",present_price);
            editor1.putString("popurl",image_url);
            editor1.commit();
        }else if (position==2){
            number=dataBean.get(position).getWATER_C().get(i).getNum();
            tvNum.setText(number+"");
            goods_name = dataBean.get(position).getWATER_C().get(i).getGoods_name();
            image_url = dataBean.get(position).getWATER_C().get(i).getImage_url();
            present_price = dataBean.get(position).getWATER_C().get(i).getPresent_price();
            tvName.setText(dataBean.get(position).getWATER_C().get(i).getGoods_name());
            Glide.with(context).load("http://shop-img.agymall.com/water/small/" + dataBean.get(position).getWATER_C().get(i).getImage_url()).into(popHead);
            SharedPreferences sp1 = context.getSharedPreferences("flag", context.MODE_PRIVATE);
            SharedPreferences.Editor editor1 = sp1.edit();
            editor1.putString("popnume",goods_name);
            editor1.putInt("popprice",present_price);
            editor1.putString("popurl",image_url);
            editor1.commit();
        }else if (position==3){
            number=dataBean.get(position).getWATER_D().get(i).getNum();
            tvNum.setText(number+"");
            goods_name = dataBean.get(position).getWATER_D().get(i).getGoods_name();
            present_price = dataBean.get(position).getWATER_D().get(i).getPresent_price();
            tvName.setText(dataBean.get(position).getWATER_D().get(i).getGoods_name());
            image_url = dataBean.get(position).getWATER_D().get(i).getImage_url();
            Glide.with(context).load("http://shop-img.agymall.com/water/small/" + dataBean.get(position).getWATER_D().get(i).getImage_url()).into(popHead);
            SharedPreferences sp1 = context.getSharedPreferences("flag", context.MODE_PRIVATE);
            SharedPreferences.Editor editor1 = sp1.edit();
            editor1.putString("popnume",goods_name);
            editor1.putInt("popprice",present_price);
            editor1.putString("popurl",image_url);
            editor1.commit();
        }
        tvMoney.setText(present_price+"");
        //根据是否为空桶来显示
        if (isBucket){
            rlTime.setVisibility(View.GONE);
            rlBucket.setVisibility(View.VISIBLE);
        }else{
            rlTime.setVisibility(View.VISIBLE);
            rlBucket.setVisibility(View.GONE);
        }

        tvDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindow.dissmiss();
            }
        });

        //暂时只有租桶
        rentBuckt.setBackgroundResource(R.drawable.water_textview_background1);
        rentBuckt.setTextColor(Color.parseColor("#ffffff"));
        buyBucket.setBackgroundResource(R.drawable.water_textview_background3);
        buyBucket.setTextColor(Color.parseColor("#1a1a1a"));

//        //买桶
//        buyBucket.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                buyBucket.setBackgroundResource(R.drawable.water_textview_background1);
//                buyBucket.setTextColor(Color.parseColor("#ffffff"));
//                rentBuckt.setBackgroundResource(R.drawable.water_textview_background2);
//                rentBuckt.setTextColor(Color.parseColor("#1a1a1a"));
//            }
//        });
//        //租桶
//        rentBuckt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                rentBuckt.setBackgroundResource(R.drawable.water_textview_background1);
//                rentBuckt.setTextColor(Color.parseColor("#ffffff"));
//                buyBucket.setBackgroundResource(R.drawable.water_textview_background2);
//                buyBucket.setTextColor(Color.parseColor("#1a1a1a"));
//            }
//        });
//        //地址
//        rlAddress.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getContext(), CitySelectActivity.class);
//                startActivity(intent);
//            }
//        });
        //时间
        rlTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num1= Integer.parseInt(tvNum.getText().toString());
                url = "";
                SharedPreferences sp = context.getSharedPreferences("flag", context.MODE_PRIVATE);
                String popgoods_name = sp.getString("popnume", "");
                int popprice = sp.getInt("popprice", 0);
                String popurl = sp.getString("popurl", "");

                if (dataBean.get(position).getStore_type().equals("SCHOOL")){
                    url= URL.SCHOOLTIME_URL;
                }else{
                    url=URL.COMMONTIME_URL;
                }

                Log.i("onClick: ", popgoods_name);
                initSchoolData(popgoods_name,popprice,popurl,isBucket,num1);
            }
        });

        //减少数量
        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num1= Integer.parseInt(tvNum.getText().toString());
                if (num1>1){
                    num1--;
                    tvNum.setText(num1+"");
                    tvMoney.setText(num1*present_price+"");
                }else{
                    Toast.makeText(context, "至少一桶才能下单", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //增加数量
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num1= Integer.parseInt(tvNum.getText().toString());
                num1++;
                tvNum.setText(num1+"");
                tvMoney.setText(num1*present_price+"");
            }
        });

        //提交订单
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String goods_id = null;

                if (position==0){
                    goods_id = dataBean.get(position).getWATER_A().get(i).getGoods_id();
                }else if (position==1){
                    goods_id = dataBean.get(position).getWATER_B().get(i).getGoods_id();
                }else if (position==2){
                    goods_id = dataBean.get(position).getWATER_C().get(i).getGoods_id();
                }else if (position==3){
                    goods_id = dataBean.get(position).getWATER_D().get(i).getGoods_id();
                }

                String addressID = dataBean.get(position).getAddressID();

                String num = tvNum.getText().toString();
                String money = tvMoney.getText().toString();
                String time = tvTime.getText().toString();
                if (isBucket) {
                    Intent intent=new Intent(context, WaterOrderActivity.class);
                    intent.putExtra("business_type","BUY_BARREL");
                    intent.putExtra("goodsId",goods_id);
                    intent.putExtra("addressId",addressID);
                    intent.putExtra("allNum",num);
                    intent.putExtra("time","");
                    intent.putExtra("reserve_sort","");
                    intent.putExtra("money",money);
                    intent.putExtra("playmethod","third");
                    intent.putExtra("bucket","RENT");
                    context.startActivity(intent);
                    popWindow.dissmiss();
                }else{
                    if (tvTime.getText().toString().equals("时间>")){
                        Toast.makeText(context, "请选择配送时间", Toast.LENGTH_SHORT).show();
                    }else{
                        Intent intent=new Intent(context, WaterOrderActivity.class);
                        intent.putExtra("business_type","BUY_WATER");
                        intent.putExtra("goodsId",goods_id);
                        intent.putExtra("addressId",addressID);
                        intent.putExtra("allNum",num);
                        intent.putExtra("time",time);
                        intent.putExtra("reserve_sort",reserve_sort);
                        intent.putExtra("money",money);
                        intent.putExtra("playmethod","third");
                        intent.putExtra("bucket","");
                        context.startActivity(intent);
                        popWindow.dissmiss();
                    }
                }
            }
        });

    }

    private void initOptionPicker(final String popname, final int popprice, final String popurl, final boolean isBucket, final int num) {// 弹出选择器
        OptionsPickerView  pvOptions = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String str = dayArray.get(options1)+
                        timeArray.get(options1).get(options2);
                reserve_sort = sort.get(options1).get(options2);

                Log.i( "onClick: ",popname);
                //再次弹框
                tvName.setText(popname);
                Glide.with(context).load("http://shop-img.agymall.com/water/small/" +popurl).into(popHead);
                tvTime.setText(str);
                tvNum.setText(num+"");
                tvMoney.setText(num*popprice+"");
            }
        })

                .setTitleText("配送时间")
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.GREEN)//设置分割线的颜色
                .setSelectOptions(0, 0)//默认选中项
                .setTitleColor(Color.LTGRAY)
                .setCancelColor(Color.parseColor("#35bb8a"))//取消按钮颜色
                .setSubmitColor(Color.parseColor("#35bb8a"))//确定按钮颜色
                .setTextColorCenter(Color.parseColor("#35bb8a"))
                .isCenterLabel(true) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setBackgroundId(0x66000000) //设置外部遮罩颜色
                .isDialog(true)
                .build();


        pvOptions.setPicker(dayArray, timeArray);//二级选择器
        pvOptions.show();
    }


    //获取学校的配送时间
    private void initSchoolData(final String popname, final int popprice, final String popurl, final boolean isBucket, final int num) {

        OkGo.<String>post(url)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body().toString();
                        Log.i("onSuccess: ", json);

                        try {
                            JSONObject j1=new JSONObject(json);
                            List<String> dayBean=new ArrayList<String>();
                            List<List<String>> timeBean=new ArrayList<List<String>>();
                            List<List<String>> sortBean=new ArrayList<List<String>>();

                            JSONArray data = j1.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject dataObj = data.getJSONObject(i);
                                dayBean.add(dataObj.getString("todayDay"));

                                List<String> timeArray=new ArrayList<String>();
                                List<String> sortArray=new ArrayList<String>();

                                JSONArray timeData = dataObj.getJSONArray("timeData");
                                for (int j = 0; j < timeData.length(); j++) {
                                    JSONObject timeObj = timeData.getJSONObject(j);
                                    timeArray.add(timeObj.getString("hour"));
                                    sortArray.add(timeObj.getInt("srot")+"");
                                }
                                timeBean.add(timeArray);
                                sortBean.add(sortArray);
                            }
                            dayArray.clear();
                            sort.addAll(sortBean);
                            timeArray.addAll(timeBean);
                            dayArray.addAll(dayBean);

                            initOptionPicker(popname,popprice,popurl,isBucket,num);//弹出滚动时间选择器
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
    public void onClick(View v) {
        mCallback.click(v);
    }
}
