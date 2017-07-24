package com.dl7.shopping.module.fragment.hometop;

/**
 * 顶部标签栏的首页
 * Created by MC.Zeng on 2017-06-27.
 */

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dl7.shopping.R;
import com.dl7.shopping.adapter.HomeListViewAdapter;
import com.dl7.shopping.module.base.BaseFragment;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class HomeTopFragment extends BaseFragment<HomeTopPresenter> implements XBanner.XBannerAdapter{
    @BindView(R.id.fragment_home_listView)
    PullToRefreshListView listView;

    private XBanner mBannerNet;
    private List<String> imgesUrl;
    private int[] rimg = {R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private int[] limg = {R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private Integer position;
    private View view;
    private int page = 1;


    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_hometop;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        View cycleView = View.inflate(getContext(), R.layout.layout_home_cycleimages, null);
        View middleView = View.inflate(getContext(), R.layout.layout_home_middle, null);
        mBannerNet = (XBanner) cycleView.findViewById(R.id.banner_1);

        //添加头布局
        listView.getRefreshableView().addHeaderView(cycleView);
        listView.getRefreshableView().addHeaderView(middleView);


        listView.setMode(PullToRefreshBase.Mode.BOTH);

        //刷新
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });

        initNetBanner();
        initListView();
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
                Toast.makeText(getContext(), "点击了第" + position + "图片", Toast.LENGTH_SHORT).show();


            }
        });
        mBannerNet.setmAdapter(this);
    }

    private void initListView() {
        HomeListViewAdapter adapter = new HomeListViewAdapter(limg, rimg, getContext());
        listView.setAdapter(adapter);
    }

    @Override
    public void loadBanner(XBanner banner, Object model, View view, int position) {
        Glide.with(this).load(imgesUrl.get(position)).into((ImageView) view);
    }

    @Override
    protected void updateViews(boolean isRefresh) {

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
}
