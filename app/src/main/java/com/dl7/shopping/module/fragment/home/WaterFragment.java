package com.dl7.shopping.module.fragment.home;

/**
 * 订水
 * Created by MC.Zeng on 2017-06-27.
 */

import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;

import com.dl7.shopping.R;
import com.dl7.shopping.adapter.WaterListViewAdapter;
import com.dl7.shopping.api.URL;
import com.dl7.shopping.bean.WaterBean;
import com.dl7.shopping.module.activity.home.combo.ComboActivity;
import com.dl7.shopping.module.activity.home.getwater.GetWaterActivity;
import com.dl7.shopping.module.activity.home.help.HelpActivity;
import com.dl7.shopping.module.base.BaseFragment;
import com.dl7.shopping.module.fragment.home.baseview.IWaterView;
import com.dl7.shopping.module.fragment.home.presenter.WaterPresenter;
import com.dl7.shopping.rxbus.event.ThreeEvent;
import com.dl7.shopping.rxbus.event.TwelveEvent;
import com.dl7.shopping.rxbus.event.TwentyEvent;
import com.dl7.shopping.utils.FontManager;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class WaterFragment extends BaseFragment<WaterPresenter> implements WaterListViewAdapter.Callback ,IWaterView {
    @BindView(R.id.rbn_water)
    RadioButton rbnWater;
    @BindView(R.id.rbn_combo)
    RadioButton rbnCombo;
    @BindView(R.id.rbn_help)
    RadioButton rbnHelp;
    @BindView(R.id.lv_water)
    ListView listView;
    @BindView(R.id.water_layout)
    FrameLayout waterLayout;

    private View view;
    private int[] img = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private List<int[]> lists = new ArrayList<>();
    private Integer position;
    private PopupWindow popupWindow;
    private int number = 1;
    private List<WaterBean.DataBean> mList = new ArrayList<>();
    private Typeface iconFont;
    private String storeID = "";
    //    private List<WaterBean.DataBean> dataBean=new ArrayList<>();
    private WaterBean.DataBean dataBean;
    private WaterListViewAdapter adapter;

    private String msg;
    private String addressID;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_water;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        iconFont = FontManager.getTypeface(getContext(), FontManager.FONTAWESOME);
        EventBus.getDefault().register(this);



        rbnCombo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), ComboActivity.class);
                intent.putExtra("addressID",addressID);
                intent.putExtra("storeID",storeID);
                startActivity(intent);
                initData();
            }
        });

        rbnWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), GetWaterActivity.class);
                startActivity(intent);
            }
        });

        rbnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HelpActivity.class);
                startActivity(intent);
            }
        });

        adapter = new WaterListViewAdapter(mList,getContext(),this);
        listView.setAdapter(adapter);
        Log.i("onSuccess2: ",storeID);
        initData();
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public void goTop() {

    }

    //获取数据
    private void initData() {

        OkGo.<String>post(URL.WATERFRAGMENT_URL)
                .params("store_id",storeID)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dataBean=new WaterBean.DataBean();
                        String json = response.body().toString();
                        Log.i("onSuccess10: ", json);

                        Gson gson=new Gson();
                        WaterBean waterBean = gson.fromJson(json, WaterBean.class);
                        List<WaterBean.DataBean.WATERABean> ABean = waterBean.getData().getWATER_A();
                        List<WaterBean.DataBean.WATERBBean> BBean = waterBean.getData().getWATER_B();
                        List<WaterBean.DataBean.WATERCBean> CBean = waterBean.getData().getWATER_C();
                        List<WaterBean.DataBean.WATERDBean> DBean = waterBean.getData().getWATER_D();
                        mList.add(waterBean.getData());
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    @Subscribe
    public void onEventMainThread(ThreeEvent event) {

        msg = event.getMsg();
        Log.i("onEventMainThread: ", msg);
//        if (msg.equals("OK")){
//            initData();
//            Log.i("onEventMainThread: ", "1");
//        }
        mList.clear();
        adapter.notifyDataSetChanged();
        storeID = msg;
        initData();
    }

    @Subscribe
    public void onEventMainThread(TwelveEvent event) {

        msg = event.getMsg();
        Log.i("onEventMainThread: ", msg);
        addressID =msg;
    }

    @Subscribe
    public void onEventMainThread(TwentyEvent event) {

        msg = event.getMsg();
        Log.i("onEventMainThread: ", msg);
        addressID =msg;
        mList.clear();
        initData();
        adapter.notifyDataSetChanged();
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
    public void click(View v) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消注册
        EventBus.getDefault().unregister(this);
    }
}
