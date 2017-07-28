package com.dl7.shopping.module.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dl7.shopping.R;
import com.dl7.shopping.adapter.MyFragmentStatePagerAdapter;
import com.dl7.shopping.api.URL;
import com.dl7.shopping.module.activity.mine.login.LoginOrRegisterActivity;
import com.dl7.shopping.module.activity.mysetting.address.addressmessage.AddressMessageActivity;
import com.dl7.shopping.module.activity.home.waterstore.WaterStoreActivity;
import com.dl7.shopping.rxbus.event.EighthEvent;
import com.dl7.shopping.rxbus.event.FifthEvent;
import com.dl7.shopping.rxbus.event.FourEvent;
import com.dl7.shopping.rxbus.event.TenthEvent;
import com.dl7.shopping.rxbus.event.ThreeEvent;
import com.dl7.shopping.utils.CommonMethod;
import com.dl7.shopping.utils.FontManager;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * 底部首页
 * Created by MC.Zeng on 2017-06-27.
 */



public class HomeTabFragment extends Fragment {

    private ViewPager mViewPager1;
    private TabLayout mTabLayout;
    private List<String> tabTitle=new ArrayList<>();
    //    private String[] tabTitle = {"首页","订水","电话卡","线上商城","线下商城"};
    private Typeface iconFont;
    private TextView location;

    private TextView citytv;
    private ImageView search;
    private Bundle bundle;
    private JSONObject dataObject;
    private String msg;
    private String address;
    private boolean isDefult=true;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_homebottom, container, false);

        EventBus.getDefault().register(this);

        tabTitle.add("首页");
        tabTitle.add("订水");
        tabTitle.add("电话卡");
        tabTitle.add("线上商城");
        tabTitle.add("线下商城");
        initViews(rootView);
        for (int i=0; i<tabTitle.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle.get(i)));
        }
        initData();

        return rootView;
    }

    private void initViews(View rootView) {
        bundle = getArguments();


        //轮播图
//        View cycleImages = LayoutInflater.from(getContext()).inflate(R.layout.layout_home_cycleimages,null);
        mViewPager1 = (ViewPager) rootView.findViewById(R.id.mViewPager1);
        mTabLayout = (TabLayout) rootView.findViewById(R.id.mTabLayout);
        citytv = (TextView) rootView.findViewById(R.id.tv_location);
        citytv.setText(bundle.getString("city"));
        search = (ImageView) rootView.findViewById(R.id.img_search);
        iconFont = FontManager.getTypeface(getContext(), FontManager.FONTAWESOME);
        location = (TextView) rootView.findViewById(R.id.iv_location);
        location.setTypeface(iconFont);

    }

    private void initData() {

        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setSelectedTabIndicatorColor(Color.parseColor("#35bb8a"));
        mTabLayout.setTabTextColors(Color.BLACK,Color.parseColor("#35bb8a"));

        mViewPager1.setAdapter(new MyFragmentStatePagerAdapter(getChildFragmentManager(),tabTitle,getActivity()));
        mViewPager1.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mViewPager1.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            //滑动事件
            @Override
            public void onPageSelected(int position) {
                if(position==1){
                    final String uid = CommonMethod.getUid(getActivity());
                    if (isDefult){
                        OkGo.<String>post(URL.DEFAULTADDRESS_URL)
                                .params("member_id",uid)
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(Response<String> response) {
                                        String json = response.body().toString();
                                        Log.i("onSuccess3: ",json );
                                        try {
                                            JSONObject j1=new JSONObject(json);

                                            if (uid.equals("0")){
//                                            Toast.makeText(getActivity(), "您还没登录", Toast.LENGTH_SHORT).show();
                                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                                builder.setCancelable(false);
                                                builder.setTitle("提示");
                                                builder.setMessage("您还没登录");
                                                builder.setNegativeButton("现在去登录", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        Intent intent=new Intent(getContext(), LoginOrRegisterActivity.class);
                                                        startActivity(intent);
                                                        mViewPager1.setCurrentItem(0);//如果没登陆，就弹出登录或注册页面，然后viewpager返回第一个item
                                                    }
                                                });
                                                builder.setPositiveButton("暂不登录", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.dismiss();
                                                    }
                                                });
                                                builder.show();

                                            }else{
                                                if (j1.getString("message").equals("您还没有地址哦")){//判断帐号有没有默认地址
                                                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                                    builder.setCancelable(false);
                                                    builder.setTitle("提示");
                                                    builder.setMessage("您还没选择默认地址");
                                                    builder.setNegativeButton("现在去选择默认地址", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            Intent intent=new Intent(getContext(), AddressMessageActivity.class);
                                                            intent.putExtra("isSelect","selectdefult");
                                                            startActivity(intent);

                                                        }
                                                    });
                                                    builder.setPositiveButton("暂不选择默认地址", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            dialog.dismiss();
                                                        }
                                                    });
                                                    builder.show();
//                                                Toast.makeText(getActivity(), "您还没选择默认地址", Toast.LENGTH_SHORT).show();
                                                }else{
                                                    address = j1.getJSONObject("data").getString("address");
                                                    if (j1.getJSONObject("data").isNull("store_id")){//判断该地址有没有绑定水店
                                                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                                        builder.setCancelable(false);
                                                        builder.setTitle("提示");
                                                        builder.setMessage("您还没选择水店");
                                                        builder.setNegativeButton("现在去选择水店", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                Intent intent=new Intent(getContext(), WaterStoreActivity.class);
                                                                intent.putExtra("address",address);
                                                                startActivity(intent);

                                                            }
                                                        });
                                                        builder.setPositiveButton("暂不选择水店", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                dialog.dismiss();
                                                            }
                                                        });
                                                        builder.show();
//                                                    Toast.makeText(getActivity(), "您还没选择水店", Toast.LENGTH_SHORT).show();
//                                                    Intent intent=new Intent(getContext(), WaterStoreActivity.class);
//                                                    startActivity(intent);
                                                    }else{
                                                        EventBus.getDefault().post(
                                                                new ThreeEvent(j1.getJSONObject("data").getString("store_id")));

                                                        search.setVisibility(View.GONE);
                                                        citytv.setText(j1.getJSONObject("data").getString("address"));
                                                        citytv.setOnClickListener(new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View v) {
                                                                Intent intent=new Intent(getContext(),AddressMessageActivity.class);
                                                                intent.putExtra("isSelect","selectaddress");
                                                                startActivity(intent);
                                                            }
                                                        });
                                                    }
                                                }
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                    }else{//如果不是默认地址
                        search.setVisibility(View.GONE);
                        citytv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent=new Intent(getContext(),AddressMessageActivity.class);
                                intent.putExtra("isSelect","selectaddress");
                                startActivity(intent);
                            }
                        });
                    }
                }else{
                    search.setVisibility(View.VISIBLE);
                    citytv.setText(bundle.getString("city"));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //标签栏的点击事件
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(final TabLayout.Tab tab) {

                mViewPager1.setCurrentItem(tab.getPosition());//点击tablayout显示相应的页面
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    //通知刷新
    @Subscribe
    public void onEventMainThread(FourEvent event) {

        msg = event.getMsg();
        Log.i("onEventMainThread: ", msg);
        initData();
    }
    //通知刷新
    @Subscribe
    public void onEventMainThread(EighthEvent event) {

        msg = event.getMsg();
        Log.i("onEventMainThread: ", msg);
        initData();
    }

    //通知刷新
    @Subscribe
    public void onEventMainThread(TenthEvent event) {

        msg = event.getMsg();
        Log.i("onEventMainThread: ", msg);
        citytv.setText(msg);
        mViewPager1.setCurrentItem(0);
        initData();

    }

    //通知刷新
    @Subscribe
    public void onEventMainThread(FifthEvent event) {
        msg = event.getMsg();
        mViewPager1.setCurrentItem(0);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消注册
        EventBus.getDefault().unregister(this);
    }
}
