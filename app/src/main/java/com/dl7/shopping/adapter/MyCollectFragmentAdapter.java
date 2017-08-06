package com.dl7.shopping.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dl7.shopping.module.fragment.myCollect.MyCollectMerchantFragment;
import com.dl7.shopping.module.fragment.myCollect.MyCollectProductsFragment;


/**
 * Created by MC.Zeng on 2017-07-07.
 */

public class MyCollectFragmentAdapter extends FragmentStatePagerAdapter {
    private String[] tabTilte;

    public MyCollectFragmentAdapter(FragmentManager fm, String[] tabTitle) {
        super(fm);
        this.tabTilte = tabTitle;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MyCollectProductsFragment();
            case 1:
                return new MyCollectMerchantFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabTilte.length;
    }
}
