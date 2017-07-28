package com.dl7.shopping.module.fragment.water;

/**
 * 订水
 * Created by MC.Zeng on 2017-06-27.
 */

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
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
import com.dl7.shopping.rxbus.event.ThreeEvent;
import com.dl7.shopping.utils.FontManager;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class WaterFragment extends BaseFragment<WaterPresenter> implements WaterListViewAdapter.Callback ,IWaterView{
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

        Resources res = this.getResources();
        Drawable myImage = res.getDrawable(R.mipmap.ic_launcher);
        myImage.setBounds(1, 1, 48, 45);
        rbnWater.setCompoundDrawables(null, myImage, null, null);
        rbnCombo.setCompoundDrawables(null, myImage, null, null);
        rbnHelp.setCompoundDrawables(null, myImage, null, null);

        rbnCombo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ComboActivity.class);
                startActivity(intent);
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
                        List<WaterBean.DataBean.WATERABean> ABean=new ArrayList<WaterBean.DataBean.WATERABean>();
                        List<WaterBean.DataBean.WATERBBean> BBean=new ArrayList<WaterBean.DataBean.WATERBBean>();
                        List<WaterBean.DataBean.WATERCBean> CBean=new ArrayList<WaterBean.DataBean.WATERCBean>();
                        List<WaterBean.DataBean.WATERDBean> DBean=new ArrayList<WaterBean.DataBean.WATERDBean>();
                        try {
                            JSONObject j1=new JSONObject(json);
                            JSONObject data = j1.getJSONObject("data");
                            JSONArray water_a = data.getJSONArray("WATER_A");
                            for (int i = 0; i < water_a.length(); i++) {
                                Log.i("onSuccess: ", water_a.length()+"");
                                JSONObject water_aJSONObject = water_a.getJSONObject(i);

                                waterBean.getData().getWATER_A().get(i).setCategory_name(water_aJSONObject.getString("category_name"));
                                waterBean.getData().getWATER_A().get(i).setGoods_name(water_aJSONObject.getString("goods_name"));
                                waterBean.getData().getWATER_A().get(i).setPresent_price(water_aJSONObject.getInt("present_price"));
                                waterBean.getData().getWATER_A().get(i).setImage_url(water_aJSONObject.getString("image_url"));
                                waterBean.getData().getWATER_A().get(i).setCompany(water_aJSONObject.getString("company"));
                                waterBean.getData().getWATER_A().get(i).setDiscount(water_aJSONObject.getInt("discount"));
                                waterBean.getData().getWATER_A().get(i).setSpecification(water_aJSONObject.getString("specification"));
                                waterBean.getData().getWATER_A().get(i).setCategory_name(water_aJSONObject.getString("category_name"));
                                waterBean.getData().getWATER_A().get(i).setId(water_aJSONObject.getString("id"));

                                ABean.add(waterBean.getData().getWATER_A().get(i));
                            }
                            mList.add(waterBean.getData());

                            JSONArray water_b = data.getJSONArray("WATER_B");
                            for (int i = 0; i < water_b.length(); i++) {
                                JSONObject water_bJSONObject = water_b.getJSONObject(i);
                                waterBean.getData().getWATER_B().get(i).setCategory_name(water_bJSONObject.getString("category_name"));
                                waterBean.getData().getWATER_B().get(i).setGoods_name(water_bJSONObject.getString("goods_name"));
                                waterBean.getData().getWATER_B().get(i).setPresent_price(water_bJSONObject.getInt("present_price"));
                                waterBean.getData().getWATER_B().get(i).setImage_url(water_bJSONObject.getString("image_url"));
                                waterBean.getData().getWATER_B().get(i).setCompany(water_bJSONObject.getString("company"));
                                waterBean.getData().getWATER_B().get(i).setDiscount(water_bJSONObject.getInt("discount"));
                                waterBean.getData().getWATER_B().get(i).setSpecification(water_bJSONObject.getString("specification"));
                                waterBean.getData().getWATER_B().get(i).setCategory_name(water_bJSONObject.getString("category_name"));
                                waterBean.getData().getWATER_B().get(i).setId(water_bJSONObject.getString("id"));

                                BBean.add(waterBean.getData().getWATER_B().get(i));
                            }
                            mList.add(waterBean.getData());

                            JSONArray water_c = data.getJSONArray("WATER_C");
                            for (int i = 0; i < water_c.length(); i++) {
                                JSONObject water_cJSONObject = water_c.getJSONObject(i);
                                waterBean.getData().getWATER_C().get(i).setCategory_name(water_cJSONObject.getString("category_name"));
                                waterBean.getData().getWATER_C().get(i).setGoods_name(water_cJSONObject.getString("goods_name"));
                                waterBean.getData().getWATER_C().get(i).setPresent_price(water_cJSONObject.getInt("present_price"));
                                waterBean.getData().getWATER_C().get(i).setImage_url(water_cJSONObject.getString("image_url"));
                                waterBean.getData().getWATER_C().get(i).setCompany(water_cJSONObject.getString("company"));
                                waterBean.getData().getWATER_C().get(i).setDiscount(water_cJSONObject.getInt("discount"));
                                waterBean.getData().getWATER_C().get(i).setSpecification(water_cJSONObject.getString("specification"));
                                waterBean.getData().getWATER_C().get(i).setCategory_name(water_cJSONObject.getString("category_name"));
                                waterBean.getData().getWATER_C().get(i).setId(water_cJSONObject.getString("id"));

                                CBean.add(waterBean.getData().getWATER_C().get(i));
                            }
                            mList.add(waterBean.getData());

                            JSONArray water_d = data.getJSONArray("WATER_D");
                            for (int i = 0; i < water_d.length(); i++) {
                                JSONObject water_dJSONObject = water_d.getJSONObject(i);
                                waterBean.getData().getWATER_D().get(i).setCategory_name(water_dJSONObject.getString("category_name"));
                                waterBean.getData().getWATER_D().get(i).setGoods_name(water_dJSONObject.getString("goods_name"));
                                waterBean.getData().getWATER_D().get(i).setPresent_price(water_dJSONObject.getInt("present_price"));
                                waterBean.getData().getWATER_D().get(i).setImage_url(water_dJSONObject.getString("image_url"));
                                waterBean.getData().getWATER_D().get(i).setCompany(water_dJSONObject.getString("company"));
                                waterBean.getData().getWATER_D().get(i).setDiscount(water_dJSONObject.getInt("discount"));
                                waterBean.getData().getWATER_D().get(i).setSpecification(water_dJSONObject.getString("specification"));
                                waterBean.getData().getWATER_D().get(i).setCategory_name(water_dJSONObject.getString("category_name"));
                                waterBean.getData().getWATER_D().get(i).setId(water_dJSONObject.getString("id"));

                                DBean.add(waterBean.getData().getWATER_D().get(i));
                            }
                            mList.add(waterBean.getData());
                            adapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


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
