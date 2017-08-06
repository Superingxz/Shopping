package com.dl7.shopping.module.fragment.home.phonecart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dl7.shopping.R;


/**
 * Created by MC.Zeng on 2017-07-28.
 */

public class PhoneCMCCFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_phonecart_listview, container, false);

        return view;
    }

}
