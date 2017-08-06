package com.dl7.shopping.module.fragment.detailtoplietview;

/**
 * 商品详情上部listview部分
 * Created by MC.Zeng on 2017-07-21.
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.dl7.shopping.R;
import com.dl7.shopping.api.URL;
import com.dl7.shopping.bean.GoodsDetailBean;
import com.dl7.shopping.module.activity.home.WaterOrderActivity;
import com.dl7.shopping.module.base.BaseFragment;
import com.dl7.shopping.utils.ColorUtil;
import com.dl7.shopping.utils.CommonMethod;
import com.dl7.shopping.utils.FontManager;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.widget.vertical.VerticalListView;
import com.stx.xhb.xbanner.XBanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DetailTopListViewFragment extends BaseFragment implements XBanner.XBannerAdapter{
    @BindView(R.id.listView)
    VerticalListView listview;
    private XBanner mBannerNet;
    private GoodsDetailBean goodsDetailBean=new GoodsDetailBean();
    private String goods_id;
    private TextView title;
    private TextView introduce;
    private TextView money;
    private TextView tvLove;
    private ImageView imgAdd;
    private ImageView imgSubtract;
    private int present_price;
    private String url;
    private RelativeLayout time;
    private TextView tvNum;
    private TextView tvTime;
    private TextView tvAddress;
    private List<String> dayArray=new ArrayList<>();
    private List<List<String>> timeArray=new ArrayList<>();
    private List<List<String>> sort=new ArrayList<>();
    private String reserve_sort;
    private Typeface iconFont;
    private String uid;
    private String addressId;
    private String id1;
    private String goods_id1;
    private RelativeLayout address;
    private ArrayList<GoodsDetailBean.DataBean.ImagesBean> imgBean;
    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_listview;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        //使用Font Awesome
        iconFont = FontManager.getTypeface(getContext(), FontManager.FONTAWESOME);

        uid = CommonMethod.getUid(getContext());

        LayoutInflater mflater = LayoutInflater.from(getContext());
        View headerView  = (RelativeLayout) mflater.inflate(R.layout.fragment_details_top, null);
        ImageView back = (ImageView) headerView.findViewById(R.id.img_details_back);
        mBannerNet = (XBanner) headerView.findViewById(R.id.details_banner_1);
        title = (TextView)headerView.findViewById(R.id.tv_details_title);
//        introduce = (TextView) headerView.findViewById(R.id.tv_details_introduce);
        money = (TextView) headerView.findViewById(R.id.tv_details_money);
        SharedPreferences sp = getActivity().getSharedPreferences("flag", getActivity().MODE_PRIVATE);
        goods_id = sp.getString("goods_id", "");
        addressId = sp.getString("addressId1", "");
        tvLove = (TextView)headerView.findViewById(R.id.tv_details_love);
        imgAdd = (ImageView) headerView.findViewById(R.id.img_details_add);
        imgSubtract = (ImageView)headerView.findViewById(R.id.img_details_subtract);
        tvAddress = (TextView) headerView.findViewById(R.id.tv_detail_address);
        tvTime = (TextView) headerView.findViewById(R.id.tv_detail_time);
        time = (RelativeLayout) headerView.findViewById(R.id.rl_detail_time);
        tvNum = (TextView) headerView.findViewById(R.id.tv_details_num);
        address = (RelativeLayout) headerView.findViewById(R.id.rl_detail_address);
        LinearLayout llBuyOrRent = (LinearLayout) headerView.findViewById(R.id.ll_detail_buy_or_rent);
        TextView buyBucket = (TextView) headerView.findViewById(R.id.tv_detail_buy);
        TextView rentBucket = (TextView) headerView.findViewById(R.id.tv_detail_rent);

        TextView icon = (TextView) headerView.findViewById(R.id.tv_detail_icon);
        TextView tvAddShoppingcart = (TextView) getActivity().findViewById(R.id.rgn_details_addshoppingcart);

        Intent intent=getActivity().getIntent();
        goods_id1 = intent.getStringExtra("goods_id");
        id1 = intent.getStringExtra("ID");
        String top = intent.getStringExtra("top");
        String bottom = intent.getStringExtra("bottom");
        final String business_type = intent.getStringExtra("business_type");
        final String id = intent.getStringExtra("goodsId");
        final String addressId = intent.getStringExtra("addressId");
        final String money = intent.getStringExtra("money");
        String playmethod = intent.getStringExtra("playmethod");
        final String bucket = intent.getStringExtra("bucket");

        initData();

        //暂时只有租桶
        rentBucket.setBackgroundResource(R.drawable.water_textview_background1);
        rentBucket.setTextColor(Color.parseColor("#ffffff"));
        buyBucket.setBackgroundResource(R.drawable.water_textview_background3);
        buyBucket.setTextColor(Color.parseColor("#1a1a1a"));

        if (business_type.equals("BUY_BARREL")){
//            address.setVisibility(View.GONE);
            llBuyOrRent.setVisibility(View.VISIBLE);
            time.setVisibility(View.GONE);
        }else{
            llBuyOrRent.setVisibility(View.GONE);
            time.setVisibility(View.VISIBLE);
//            address.setVisibility(View.VISIBLE);
        }

        icon.setTypeface(iconFont);

        tvAddShoppingcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (business_type.equals("BUY_BARREL")){
                    Intent intent1=new Intent(getContext(),WaterOrderActivity.class);
                    intent1.putExtra("business_type","ORDER_PAY_CONFIRM");
                    intent1.putExtra("business_type1",business_type);
                    intent1.putExtra("goodsId", id1);
                    intent1.putExtra("addressId",addressId);
                    intent1.putExtra("allNum",tvNum.getText().toString());
                    intent1.putExtra("store_id","");
                    intent1.putExtra("time","");
                    intent1.putExtra("reserve_sort","");
                    intent1.putExtra("money",money);
                    intent1.putExtra("playmethod","third");
                    intent1.putExtra("bucket",bucket);
                    startActivity(intent1);
                    getActivity().finish();
                }else{
                    if (tvTime.getText().toString().equals("时间>")) {
                        Toast.makeText(getContext(), "请选择配送时间", Toast.LENGTH_SHORT).show();
                    }else{
                        Intent intent1=new Intent(getContext(),WaterOrderActivity.class);
                        intent1.putExtra("business_type","ORDER_PAY_CONFIRM");
                        intent1.putExtra("business_type1",business_type);
                        intent1.putExtra("goodsId", id1);
                        intent1.putExtra("addressId",addressId);
                        intent1.putExtra("allNum",tvNum.getText().toString());
                        intent1.putExtra("store_id","");
                        intent1.putExtra("time",tvTime.getText().toString());
                        intent1.putExtra("reserve_sort",reserve_sort);
                        intent1.putExtra("money",money);
                        intent1.putExtra("playmethod","third");
                        intent1.putExtra("bucket",bucket);
                        startActivity(intent1);
                        getActivity().finish();
                    }
                }
            }
        });

        //减少数量
        imgSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num1= Integer.parseInt(tvNum.getText().toString());
                if (num1>1){
                    num1--;
                    tvNum.setText(num1+"");
//                    money.setText("¥    "+num1*present_price/100+"");
                }else{
                    Toast.makeText(getActivity(), "至少一桶才能下单", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //增加数量
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num1= Integer.parseInt(tvNum.getText().toString());
                num1++;
                tvNum.setText(num1+"");
//                money.setText("¥    "+num1*present_price/100+"");
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        listview.addHeaderView(headerView);
//        listview.addHeaderView(textView2);
//        listview.addFooterView(textView3);

        listview.setAdapter(new MyAdapter());
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    private void initData() {
        OkGo.<String>post(URL.GOODSDETAIL_URL)
                .params("goods_id", goods_id)
                .params("member_id",uid)
                .params("address_id",addressId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body().toString();
                        Log.i("onSuccess: ", json);
                        try {
                            JSONObject j1=new JSONObject(json);
                            Gson gson=new Gson();
                            final GoodsDetailBean goodsDetailBean = gson.fromJson(json, GoodsDetailBean.class);
                            initNetBanner(goodsDetailBean.getData().getImages());

                            //时间
                            time.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    url = "";
                                    if (goodsDetailBean.getData().getStore_type().equals("SCHOOL")){
                                        url =URL.SCHOOLTIME_URL;
                                    }else{
                                        url =URL.COMMONTIME_URL;
                                    }
                                    initSchoolData();
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void goTop() {
        listview.goTop();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void finishRefresh() {

    }

    private class MyAdapter extends BaseAdapter {

        private List<String> strings;

        public MyAdapter() {
            strings = new ArrayList<>();

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (null == convertView) {
                convertView = View.inflate(getActivity(), android.R.layout.simple_list_item_1, null);
            }
            TextView textView = (TextView) convertView;
            textView.setText(getItem(position));
            textView.setTextColor(Color.WHITE);
            textView.setBackgroundColor(ColorUtil.generateBeautifulColor());
            return convertView;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public String getItem(int position) {
            return strings.get(position);
        }

        @Override
        public int getCount() {
            return strings.size();
        }
    }

    private void initNetBanner(List<GoodsDetailBean.DataBean.ImagesBean> imagesBeen) {
        mBannerNet.setData(imagesBeen,null);

        mBannerNet.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, int position) {
                Toast.makeText(getContext(), "点击了第"+position+"图片", Toast.LENGTH_SHORT).show();
            }
        });
        mBannerNet.setmAdapter(this);
    }

    @Override
    public void loadBanner(XBanner banner, Object model, View view, int position) {
        Glide.with(this).load("http://shop-img.agymall.com/water/big/"+imgBean.get(position).getImage_url()).into((ImageView) view);
    }

    //获取学校的配送时间
    private void initSchoolData() {

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

                            initOptionPicker();//弹出滚动时间选择器
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    //时间选择
    private void initOptionPicker() {// 弹出选择器
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(getContext(), new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String str = dayArray.get(options1)+
                        timeArray.get(options1).get(options2);
                reserve_sort = sort.get(options1).get(options2);

                tvTime.setText(str);
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
}