package com.dl7.shopping.adapter;

/**
 * Created by MC.Zeng on 2017-06-27.
 */

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.dl7.shopping.api.URL;
import com.dl7.shopping.module.fragment.bottomshopping.BottomShoppingFragment;
import com.dl7.shopping.module.fragment.hometop.HomeTopFragment;
import com.dl7.shopping.module.fragment.phonecart.PhoneCartFragment;
import com.dl7.shopping.module.fragment.topshopping.TopShoppingFragment;
import com.dl7.shopping.module.fragment.water.WaterFragment;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.List;

public class MyFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
    private List<String> tabTitle;
    private Context context;
    private String uid;

    public MyFragmentStatePagerAdapter(FragmentManager fm, List<String> tabTitle, Context context) {
        super(fm);
        this.tabTitle = tabTitle;
        this.context=context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeTopFragment();
            case 1:
                return new WaterFragment();
            case 2:
                return new PhoneCartFragment();
            case 3:
                return new TopShoppingFragment();
            case 4:
                return new BottomShoppingFragment();
        }
        return null;
    }

    //读取默认地址
    private void initDefultAdress() {
        OkGo.<String>post(URL.DEFAULTADDRESS_URL)
                .params("member_id",uid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body().toString();
                        Log.i("onSuccess3: ",json );
                    }
                });
    }

    @Override
    public int getCount() {
        return tabTitle.size();
    }
}