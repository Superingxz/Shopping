package com.dl7.shopping.module.fragment.myCollect;

import android.widget.ListView;

import com.dl7.shopping.R;
import com.dl7.shopping.adapter.MyCollectClassifyAdapter;
import com.dl7.shopping.module.base.BaseFragment;
import com.dl7.shopping.module.fragment.myCollect.baseview.ICollectClassifyView;
import com.dl7.shopping.module.fragment.myCollect.presenter.CollectClassifyPresenter;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import butterknife.BindView;

/**
 * Created by MC.Zeng on 2017-07-07.
 */

public class MyCollectClassifyFragment extends BaseFragment<CollectClassifyPresenter>
        implements ICollectClassifyView {
    @BindView(R.id.lv_collect)
    PullToRefreshListView listView;
    private int[] img = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private String[] title = {"广大桶装水", "广大桶装水", "广大桶装水", "广大桶装水", "广大桶装水", "广大桶装水"};
    private String[] money = {"¥ 25", "¥ 25", "¥ 25", "¥ 25", "¥ 25", "¥ 25"};
    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_collect_listview;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
        MyCollectClassifyAdapter adapter = new MyCollectClassifyAdapter(img, title, money, getContext());
        listView.setAdapter(adapter);
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public void goTop() {

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
