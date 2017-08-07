package com.dl7.shopping.module.activity.home.phonecart;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.dl7.shopping.R;
import com.dl7.shopping.api.URL;
import com.dl7.shopping.module.base.BaseFragment;
import com.dl7.shopping.module.fragment.home.phonecart.PhoneDetailBottomWebViewFragment;
import com.dl7.shopping.module.fragment.home.phonecart.PhoneDetailTopListViewFragment;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.widget.VerticalSlide;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by MC.Zeng on 2017-07-29.
 */

public class PhoneCartDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private VerticalSlide verticalSlide;
    private BaseFragment topFragment;
    private BaseFragment bottomFragment;
    private FloatingActionButton fab;
    private String goods_id;
    private String provinceName;
    private String cityName;
    private String goodsid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        verticalSlide = (VerticalSlide) findViewById(R.id.dragLayout);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        RadioButton shoppingCart = (RadioButton) findViewById(R.id.rbn_details_shoppingcart);
        TextView addShoppingCart = (TextView) findViewById(R.id.rgn_details_addshoppingcart);
        RadioButton share = (RadioButton) findViewById(R.id.rbn_details_share);

        share.setVisibility(View.GONE);
        addShoppingCart.setText("立即购买");
        shoppingCart.setVisibility(View.GONE);
        Intent intent = getIntent();
        goods_id = intent.getStringExtra("goodsId");
        String top = intent.getStringExtra("top");
        String bottom = intent.getStringExtra("bottom");
        final String province_id = intent.getStringExtra("province_id");
        final String city_id = intent.getStringExtra("city_id");
        final String store_id = intent.getStringExtra("store_id");
        final String card_type = intent.getStringExtra("card_type");

        initData();

        addShoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(PhoneCartDetailActivity.this,SelectPhoneNumActivity.class);
                intent1.putExtra("province_id",province_id);
                intent1.putExtra("city_id",city_id);
                intent1.putExtra("store_id",store_id);
                intent1.putExtra("card_type",card_type);
                intent1.putExtra("provinceName",provinceName);
                intent1.putExtra("cityName",cityName);
                startActivity(intent1);
            }
        });

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        if ("ListView".equals(top)) topFragment = new PhoneDetailTopListViewFragment();

        bundle.putString("goodsId",goods_id);

        topFragment.setArguments(bundle);

        transaction.replace(R.id.first, topFragment);

        if ("WebView".equals(bottom)) {
            bottomFragment = new PhoneDetailBottomWebViewFragment();
            verticalSlide.setOnShowNextPageListener(new VerticalSlide.OnShowNextPageListener() {
                @Override
                public void onShowNextPage() {
                    ((PhoneDetailBottomWebViewFragment) bottomFragment).initViews();
                }
            });
        }
        transaction.replace(R.id.drag_second, bottomFragment);

        transaction.commit();
    }

    private void initData() {
        OkGo.<String>post(URL.PHONECARTDETAIL_URL)
                .params("id", goods_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body().toString();
                        Log.i("onSuccess: ", json);

                        try {
                            JSONObject j1=new JSONObject(json);
                            JSONObject data = j1.getJSONObject("data");
                            provinceName = data.getString("provinceName");
                            cityName = data.getString("cityName");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }

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
