package com.dl7.shopping.module.activity.home.combo;

import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.dl7.shopping.R;
import com.dl7.shopping.adapter.ComboAdapter;
import com.dl7.shopping.api.URL;
import com.dl7.shopping.bean.ComboBean;
import com.dl7.shopping.module.base.BaseActivity;
import com.dl7.shopping.rxbus.event.TwentyEvent;
import com.dl7.shopping.utils.FontManager;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 套餐页面
 * Created by MC.Zeng on 2017-07-08.
 */

public class ComboActivity extends BaseActivity<ComboPresenter> implements IComboView {
    @BindView(R.id.tv_combo_back)
    TextView back;
    @BindView(R.id.lv_combo)
    ListView listView;
    private Typeface iconFont;
    private int[] img = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private String storeID;
    private List<ComboBean.DataBean> mList=new ArrayList<>();
    private ComboAdapter adapter;
    private String addressID;
    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_combo;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        //使用Font Awesome
        iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
        back.setTypeface(iconFont);

        Intent intent=getIntent();
        addressID = intent.getStringExtra("addressID");
        storeID = intent.getStringExtra("storeID");

        adapter = new ComboAdapter(mList,this);
        listView.setAdapter(adapter);

        initData();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(
                        new TwentyEvent(storeID));
                finish();
            }
        });
    }

    //获取数据
    private void initData() {
        OkGo.<String>post(URL.COMBO_URL)
                .params("store_id",storeID)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body().toString();
                        Log.i("onSuccess: ", json);

                        try {
                            Gson gson=new Gson();
                            ComboBean comboBean = gson.fromJson(json, ComboBean.class);
                            List<ComboBean.DataBean> dataBeen=new ArrayList<ComboBean.DataBean>();

                            JSONObject j1=new JSONObject(json);
                            JSONArray data = j1.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject dataObj = data.getJSONObject(i);
                                comboBean.getData().get(i).setGoods_name(dataObj.getString("goods_name"));
                                comboBean.getData().get(i).setGoods_id(dataObj.getString("goods_id"));
                                comboBean.getData().get(i).setCompany(dataObj.getString("company"));
                                comboBean.getData().get(i).setImage_url(dataObj.getString("image_url"));
                                comboBean.getData().get(i).setId(dataObj.getString("id"));
                                comboBean.getData().get(i).setSpecification(dataObj.getString("specification"));
                                comboBean.getData().get(i).setStoreID(storeID);
                                comboBean.getData().get(i).setAddressID(addressID);
                                dataBeen.add(comboBean.getData().get(i));
                            }
                            mList.addAll(dataBeen);
                            adapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public void finishRefresh() {

    }
}
