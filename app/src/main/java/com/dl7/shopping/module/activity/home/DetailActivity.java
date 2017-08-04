package com.dl7.shopping.module.activity.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.dl7.shopping.R;
import com.dl7.shopping.bean.GoodsDetailBean;
import com.dl7.shopping.module.base.BaseFragment;
import com.dl7.shopping.module.fragment.DetailBottomWebViewFragment;
import com.dl7.shopping.module.fragment.DetailTopListViewFragment;
import com.lzy.widget.VerticalSlide;

import java.util.ArrayList;

/**
 * 商品详情
 * Created by MC.Zeng on 2017-07-21.
 */

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    private VerticalSlide verticalSlide;
    private BaseFragment topFragment;
    private BaseFragment bottomFragment;
    private FloatingActionButton fab;
    private String goods_id;
    private ArrayList<GoodsDetailBean.DataBean.ImagesBean> imgBean;
    private String goods_name="";
    private String specification="";
    private int present_price=0;
    private TextView tvAddShoppingcart;
    private String id1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tvAddShoppingcart = (TextView) findViewById(R.id.rgn_details_addshoppingcart);
        verticalSlide = (VerticalSlide) findViewById(R.id.dragLayout);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        final Intent intent = getIntent();
        goods_id = intent.getStringExtra("goods_id");
        id1 = intent.getStringExtra("ID");
        String top = intent.getStringExtra("top");
        String bottom = intent.getStringExtra("bottom");
        final String business_type = intent.getStringExtra("business_type");
        final String id = intent.getStringExtra("goodsId");
        final String addressId = intent.getStringExtra("addressId");
        final String money = intent.getStringExtra("money");
        String playmethod = intent.getStringExtra("playmethod");
        final String bucket = intent.getStringExtra("bucket");
//        initData();

        tvAddShoppingcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(DetailActivity.this,WaterOrderActivity.class);
                intent1.putExtra("business_type","ORDER_PAY_CONFIRM");
                intent1.putExtra("business_type1",business_type);
                intent1.putExtra("goodsId",id1);
                intent1.putExtra("addressId",addressId);
                intent1.putExtra("allNum","1");
                intent1.putExtra("store_id","");
                intent1.putExtra("time","");
                intent1.putExtra("reserve_sort","");
                intent1.putExtra("money",money);
                intent1.putExtra("playmethod","third");
                intent1.putExtra("bucket",bucket);
                startActivity(intent1);
                finish();
            }
        });

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        if ("ListView".equals(top)) topFragment = new DetailTopListViewFragment();

        bundle.putString("goods_id",goods_id);
//        bundle.putString("goods_name", goods_name);
//        Log.i("onCreate: ","11" );
//        Log.i("onCreate: ",goods_name.toString() );
//        bundle.putString("specification", specification);
//        bundle.putString("present_price", present_price+"");

        topFragment.setArguments(bundle);

        transaction.replace(R.id.first, topFragment);

        if ("WebView".equals(bottom)) {
            bottomFragment = new DetailBottomWebViewFragment();
            verticalSlide.setOnShowNextPageListener(new VerticalSlide.OnShowNextPageListener() {
                @Override
                public void onShowNextPage() {
                    ((DetailBottomWebViewFragment) bottomFragment).initView();
                }
            });
        }
        transaction.replace(R.id.drag_second, bottomFragment);

        transaction.commit();
    }

//    //获取数据
//    private void initData() {
//        OkGo.<String>post(URL.GOODSDETAIL_URL)
//                .params("goods_id", goods_id)
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(Response<String> response) {
//                        String json = response.body().toString();
//                        Log.i("onSuccess: ", json);
//                        try {
//                            JSONObject j1=new JSONObject(json);
//                            Gson gson=new Gson();
//                            GoodsDetailBean goodsDetailBean = gson.fromJson(json, GoodsDetailBean.class);
//                            imgBean = new ArrayList<GoodsDetailBean.DataBean.ImagesBean>();
//                            JSONObject data = j1.getJSONObject("data");
////                            imgBean = new ArrayList<GoodsDetailBean.DataBean.ImagesBean>();
//                            goods_name = data.getString("goods_name");
//                            Log.i("onSuccess: ",goods_name );
//                            specification = data.getString("specification");
//                            present_price = data.getInt("present_price");
//                            goodsDetailBean.getData().setGoods_name(data.getString("goods_name"));
//                            goodsDetailBean.getData().setSpecification(data.getString("specification"));
//                            goodsDetailBean.getData().setPresent_price(data.getInt("present_price"));
////                            title.setText(data.getString("goods_name")+data.getString("specification"));
////                            money.setText("¥   "+data.getString("present_price"));
//
//                            JSONArray images = data.getJSONArray("images");
//                            for (int i = 0; i < images.length(); i++) {
//                                JSONObject imgObject = images.getJSONObject(i);
//                                goodsDetailBean.getData().getImages().get(i).setImage_url(imgObject.getString("image_url"));
//
//                                imgBean.add(goodsDetailBean.getData().getImages().get(i));
//                            }
////                            Log.i("onSuccess: ", imgBean.size()+"" );
////                            initNetBanner(imgBean);
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//    }

    @Override
    public void onClick(View v) {
        /**
         * 返回顶部分三步
         * 1.第二页滚动到第二页的顶部
         * 2.VerticalSlide从第二页返回第一页
         * 3.第一页滚动到第一页的顶部
         * OnGoTopListener 表示第一页滚动到顶部 的方法,这个由于采用什么布局,库内部并不知道,所以一般是自己实现
         * 也可以不实现,直接传null
         */
        bottomFragment.goTop();
        verticalSlide.goTop(new VerticalSlide.OnGoTopListener() {
            @Override
            public void goTop() {
                topFragment.goTop();
            }
        });
    }
}