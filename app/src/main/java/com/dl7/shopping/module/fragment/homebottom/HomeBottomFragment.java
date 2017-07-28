package com.dl7.shopping.module.fragment.homebottom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.dl7.shopping.R;
import com.dl7.shopping.module.base.BaseFragment;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by MC.Zeng on 2017-06-29.
 */

public class HomeBottomFragment extends BaseFragment<HomeBottomPresenter>
        implements IHomeBottomView {
    @BindView(R.id.fragment_home_listView)
    PullToRefreshListView listView;
    private String[] strs = new String[]{
            "first", "second", "third", "fourth", "fifth"
    };//定义一个String数组用来显示ListView的内容private ListView lv;/** Called when the activity is first created. */

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_hometop;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        listView.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, strs));
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
