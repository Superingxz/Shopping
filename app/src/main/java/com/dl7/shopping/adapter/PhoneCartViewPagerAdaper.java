package com.dl7.shopping.adapter;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dl7.shopping.module.fragment.home.phonecart.PhoneCMCCFragment;
import com.dl7.shopping.module.fragment.home.phonecart.PhoneCTFragment;
import com.dl7.shopping.module.fragment.home.phonecart.PhoneCUFragment;
import com.dl7.shopping.module.fragment.home.phonecart.PhoneCartRenewFragment;

import java.util.List;

/**
 * Created by MC.Zeng on 2017-07-28.
 */

public class PhoneCartViewPagerAdaper extends FragmentPagerAdapter{
    private Context context;
    private List<Fragment> fragments;
    private String uid;

    public PhoneCartViewPagerAdaper(FragmentManager fm,List<Fragment> fragments, Context context) {
        super(fm);
        this.fragments=fragments;
        this.context=context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PhoneCUFragment();
            case 1:
                return new PhoneCMCCFragment();
            case 2:
                return new PhoneCTFragment();
            case 3:
                return new PhoneCartRenewFragment();
        }
        return null;
    }
//
//    //读取默认地址
//    private void initDefultAdress() {
//        OkGo.<String>post(URL.DEFAULTADDRESS_URL)
//                .params("member_id",uid)
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(Response<String> response) {
//                        String json = response.body().toString();
//                        Log.i("onSuccess3: ",json );
//                    }
//                });
//    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
