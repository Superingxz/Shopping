package com.dl7.shopping.module.activity.home.details;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dl7.shopping.R;
import com.dl7.shopping.module.base.BaseActivity;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**000
 * 商品详情页面
 * Created by MC.Zeng on 2017-07-04.
 */

public class DetailsActivity extends BaseActivity<DetailsPresenter>
        implements XBanner.XBannerAdapter, IDetailsView {
    @BindView(R.id.details_banner_1)
    XBanner mBannerNet;
    @BindView(R.id.rbn_details_collect)
    RadioButton collect;
    @BindView(R.id.rbn_details_share)
    RadioButton share;
    @BindView(R.id.rbn_details_shoppingcart)
    RadioButton shoppingCart;
    @BindView(R.id.img_details_back)
    ImageView back;
    private List<String> imgesUrl;
    private boolean isChoose = false;//是否收藏

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_details;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        //      EventBus.getDefault().register(this);
        initNetBanner();
        collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isChoose = !isChoose;
                if (isChoose) {
                    collect.setTextColor(Color.rgb(53, 187, 137));
                } else {
                    collect.setTextColor(Color.BLACK);
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);


    }

    private void initNetBanner() {
        imgesUrl = new ArrayList<>();
        imgesUrl.add("http://img3.fengniao.com/forum/attachpics/913/114/36502745.jpg");
        imgesUrl.add("http://imageprocess.yitos.net/images/public/20160910/99381473502384338.jpg");
        imgesUrl.add("http://imageprocess.yitos.net/images/public/20160910/77991473496077677.jpg");
        imgesUrl.add("http://imageprocess.yitos.net/images/public/20160906/1291473163104906.jpg");
        mBannerNet.setData(imgesUrl, null);

        mBannerNet.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, int position) {
                Toast.makeText(DetailsActivity.this, "点击了第" + position + "图片", Toast.LENGTH_SHORT).show();
            }
        });
        mBannerNet.setmAdapter(this);
    }

    @Override
    public void loadBanner(XBanner banner, Object model, View view, int position) {
        Glide.with(this).load(imgesUrl.get(position)).into((ImageView) view);
    }

    @Override
    public void finishRefresh() {

    }
}
