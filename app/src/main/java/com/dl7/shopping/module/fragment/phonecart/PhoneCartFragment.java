package com.dl7.shopping.module.fragment.phonecart;

/**
 * 电话卡
 * Created by MC.Zeng on 2017-06-27.
 */

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.dl7.shopping.R;
import com.dl7.shopping.adapter.PhoneListViewAdapter;
import com.dl7.shopping.module.base.BaseFragment;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import butterknife.BindView;

public class PhoneCartFragment extends BaseFragment<PhoneCartPresenter>
        implements RadioGroup.OnCheckedChangeListener, IPhoneCartView {
    @BindView(R.id.rbn_CU)
    RadioButton CUrbn;
    @BindView(R.id.rbn_CMCC)
    RadioButton CMCCrbn;
    @BindView(R.id.rbn_CT)
    RadioButton CTrbn;
    @BindView(R.id.navigation_btn_phone)
    RadioGroup navigationbar;
    @BindView(R.id.lv_fragment_phone)
    PullToRefreshListView listView;
    private View view;
    private Fragment mFragment;//当前显示的Fragment
    private int[] img = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};


    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_phonecart;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        navigationbar.setOnCheckedChangeListener(this);

        Resources res = this.getResources();
        Drawable myImage = res.getDrawable(R.mipmap.ic_launcher);
        myImage.setBounds(1, 1, 48, 45);
        CUrbn.setCompoundDrawables(null, myImage, null, null);
        CMCCrbn.setCompoundDrawables(null, myImage, null, null);
        CTrbn.setCompoundDrawables(null, myImage, null, null);

        //刷新
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });

        PhoneListViewAdapter adapter = new PhoneListViewAdapter(img, getContext());
        listView.setAdapter(adapter);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.rbn_CU:
                CUrbn.setChecked(true);
                CMCCrbn.setChecked(false);
                CTrbn.setChecked(false);
                break;
            case R.id.rbn_CMCC:
                CUrbn.setChecked(false);
                CMCCrbn.setChecked(true);
                CTrbn.setChecked(false);
                break;
            case R.id.rbn_CT:
                CUrbn.setChecked(false);
                CMCCrbn.setChecked(false);
                CTrbn.setChecked(true);
                break;
        }
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
