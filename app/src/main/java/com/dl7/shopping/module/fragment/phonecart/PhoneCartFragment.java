package com.dl7.shopping.module.fragment.phonecart;

/**
 * 电话卡
 * Created by MC.Zeng on 2017-06-27.
 */

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.dl7.shopping.R;
import com.dl7.shopping.adapter.PhoneCartViewPagerAdaper;
import com.dl7.shopping.bean.PhoneCartFragmentListBean;
import com.dl7.shopping.module.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PhoneCartFragment extends BaseFragment<PhoneCartPresenter>
        implements  IPhoneCartView {
    @BindView(R.id.rbn_CU)
    RadioButton CUrbn;
    @BindView(R.id.rbn_CMCC)
    RadioButton CMCCrbn;
    @BindView(R.id.rbn_CT)
    RadioButton CTrbn;
    @BindView(R.id.rbn_renew)
    RadioButton renewbn;
    @BindView(R.id.navigation_btn_phone)
    RadioGroup navigationbar;
    @BindView(R.id.vp_fragment_phonecart)
    ViewPager viewPager;
    private View view;
    private Fragment mFragment;//当前显示的Fragment
    private int[] img = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private String card_type;
    private List<PhoneCartFragmentListBean.DataBean> mList=new ArrayList<>();
    private List<Fragment> fragments;
    private PhoneCartViewPagerAdaper adaper;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_phonecart;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        Resources res = this.getResources();
        Drawable myImage = res.getDrawable(R.mipmap.ic_launcher);
        myImage.setBounds(1, 1, 48, 45);
        CUrbn.setCompoundDrawables(null, myImage, null, null);
        CMCCrbn.setCompoundDrawables(null, myImage, null, null);
        CTrbn.setCompoundDrawables(null, myImage, null, null);
        renewbn.setCompoundDrawables(null, myImage, null, null);

        fragments = new ArrayList<>();
        fragments.add(new PhoneCUFragment());//联通
        fragments.add(new PhoneCMCCFragment());//移动
        fragments.add(new PhoneCTFragment());//电信
        fragments.add(new PhoneCartRenewFragment());//续费
        adaper = new PhoneCartViewPagerAdaper(getChildFragmentManager(),fragments,getContext());
        viewPager.setAdapter(adaper);
        viewPager.setCurrentItem(0);

        navigationbar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                changeFragment(checkedId);
            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //改变模块的选项
                RadioButton rb = (RadioButton) navigationbar.getChildAt(position);
                rb.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 切换fragment
     * @param checkedId
     */
    private void changeFragment(int checkedId){
        switch (checkedId) {
            case R.id.rbn_CU:
                card_type="UNICOM";
                CUrbn.setChecked(true);
                CMCCrbn.setChecked(false);
                CTrbn.setChecked(false);
                renewbn.setChecked(false);
                viewPager.setCurrentItem(0);

                break;
            case R.id.rbn_CMCC:
                card_type="MOVE";
                CUrbn.setChecked(false);
                CMCCrbn.setChecked(true);
                CTrbn.setChecked(false);
                renewbn.setChecked(false);
                viewPager.setCurrentItem(1);

                break;
            case R.id.rbn_CT:
                card_type="TELECOM";
                CUrbn.setChecked(false);
                CMCCrbn.setChecked(false);
                CTrbn.setChecked(true);
                renewbn.setChecked(false);
                viewPager.setCurrentItem(2);

                break;
            case R.id.rbn_renew:
                viewPager.setCurrentItem(3);
                CUrbn.setChecked(false);
                CMCCrbn.setChecked(false);
                CTrbn.setChecked(false);
                renewbn.setChecked(true);

                break;
        }
    }


//    //通知刷新--可以连续定位
//    @Subscribe
//    public void onEventMainThread(ThirteenEvent event) {
//        String msg = event.getMsg();
//        Log.i("onEventMainThread: ",msg);
//        if (msg.equals("OK")){
////            initListData();
//        }
//    }
//

    @Override
    public void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
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
