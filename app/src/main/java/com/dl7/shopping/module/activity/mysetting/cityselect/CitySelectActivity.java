package com.dl7.shopping.module.activity.mysetting.cityselect;

import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.dl7.shopping.R;
import com.dl7.shopping.adapter.AreaAdapter;
import com.dl7.shopping.adapter.CityAdapter;
import com.dl7.shopping.adapter.ProvinceAdapter;
import com.dl7.shopping.api.URL;
import com.dl7.shopping.bean.AreaBean;
import com.dl7.shopping.bean.CityBean;
import com.dl7.shopping.bean.ProvinceBean;
import com.dl7.shopping.module.base.BaseActivity;
import com.dl7.shopping.rxbus.event.SecondEvent;
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
 * 城市选择(三级联动)
 * Created by MC.Zeng on 2017-07-04.
 */

public class CitySelectActivity extends BaseActivity<CitySelectPresenter>
        implements ICitySelectView {

    @BindView(R.id.tv_address_select_back)
    TextView back;
    @BindView(R.id.tv_city_select_confirm)
    TextView confirm;
    @BindView(R.id.tv_city_select_province)
    TextView tvProvince;
    @BindView(R.id.tv_city_select_city)
    TextView tvCity;
    @BindView(R.id.tv_city_select_area)
    TextView tvArea;
    @BindView(R.id.lv_address_1)
    ListView provinceListView;
    @BindView(R.id.lv_address_2)
    ListView cityListView;
    @BindView(R.id.lv_address_3)
    ListView areaListView;
    private ArrayList<ProvinceBean.DataBean> proList = new ArrayList<>();
    private ProvinceAdapter provinceAdapter;
    private Typeface iconFont;
    private TextView province;
    private TextView tvCityRight;
    private boolean isFrist = true;
    private int p = 0;
    private TextView tvPlot;
    private ProvinceBean provinceBean;
    private List<CityBean.DataBean> cityList = new ArrayList<>();
    private CityAdapter cityAdapter;
    private List<AreaBean.DataBean> areaList = new ArrayList<>();
    private boolean isClick = true;//是否直接点击确定
    private String location1;
    private String location2;
    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_city_select;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);

        provinceListView = (ListView) findViewById(R.id.lv_address_1);
        cityListView = (ListView) findViewById(R.id.lv_address_2);
        areaListView = (ListView) findViewById(R.id.lv_address_3);
        back = (TextView) findViewById(R.id.tv_address_select_back);
        back.setTypeface(iconFont);
        tvProvince = (TextView) findViewById(R.id.tv_city_select_province);
        tvCity = (TextView) findViewById(R.id.tv_city_select_city);
        tvArea = (TextView) findViewById(R.id.tv_city_select_area);
//        tvPlot = (TextView) findViewById(R.id.tv_city_select_plot);
        confirm = (TextView) findViewById(R.id.tv_city_select_confirm);


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClick) {
                    EventBus.getDefault().post(
                            new SecondEvent(location2));

                    finish();//结束本页面
                } else {
                    EventBus.getDefault().post(
                            new SecondEvent(location1));

                    finish();//结束本页面
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvProvince.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvCity.setVisibility(View.VISIBLE);
                tvArea.setVisibility(View.GONE);
//                tvPlot.setVisibility(View.GONE);
                confirm.setVisibility(View.GONE);
                areaListView.setVisibility(View.GONE);
                provinceListView.setVisibility(View.VISIBLE);
                cityListView.setVisibility(View.VISIBLE);
            }
        });


        provinceAdapter = new ProvinceAdapter(this,R.layout.item_left_listview_city, proList);
//        provinceAdapter.setSelectItem(0);
        provinceListView.setAdapter(provinceAdapter);

        cityAdapter = new CityAdapter(this, R.layout.item_right_listview_city,cityList);
        cityListView.setAdapter(cityAdapter);

        final AreaAdapter areaAdapter = new AreaAdapter(R.layout.item_address_select,areaList, this);
        areaListView.setAdapter(areaAdapter);


        //省列表点击
        provinceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                provinceAdapter.setSelectItem(i);
                provinceAdapter.notifyDataSetChanged();
                province = (TextView) view.findViewById(R.id.tv_province);
                tvProvince.setText(proList.get(i).getName());

                //获取城市数据
                OkGo.<String>post(URL.PROCITYCOUNTRY_URL)
                        .params("province_id", proList.get(i).getId())
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                String json = response.body().toString();
                                Log.i("onSuccess: ", json);

                                Gson gson = new Gson();
                                CityBean cityBean = gson.fromJson(json, CityBean.class);
                                List<CityBean.DataBean> citylist = new ArrayList<CityBean.DataBean>();

                                try {
                                    JSONObject j1 = new JSONObject(json);
                                    JSONArray data = j1.getJSONArray("data");
                                    for (int j = 0; j < data.length(); j++) {
                                        JSONObject dataObject = data.getJSONObject(j);
                                        cityBean.getData().get(j).setName(dataObject.getString("name"));
                                        cityBean.getData().get(j).setId(dataObject.getString("id"));
                                        cityBean.getData().get(j).setProvince_name(dataObject.getString("province_name"));

                                        citylist.add(cityBean.getData().get(j));
                                    }
                                    cityList.clear();
                                    cityList.addAll(citylist);
                                    cityAdapter.setSelectItem(0);
                                    cityAdapter.notifyDataSetChanged();
                                    tvCity.setVisibility(View.VISIBLE);
                                    tvCity.setText("/" + cityList.get(0).getName());

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
            }
        });

        //市列表点击
        cityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cityAdapter.setSelectItem(position);
                cityAdapter.notifyDataSetChanged();
                tvCity.setVisibility(View.VISIBLE);
                confirm.setVisibility(View.VISIBLE);
                tvCity.setText("/" + cityList.get(position).getName());
                provinceListView.setVisibility(View.GONE);
                areaListView.setVisibility(View.VISIBLE);

                //获取区域数据
                OkGo.<String>post(URL.PROCITYCOUNTRY_URL)
                        .params("city_id", cityList.get(position).getId())
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                String json = response.body().toString();
                                Log.i("onSuccess: ", json);

                                Gson gson = new Gson();
                                AreaBean areaBean = gson.fromJson(json, AreaBean.class);
                                List<AreaBean.DataBean> dataBean = new ArrayList<AreaBean.DataBean>();

                                try {
                                    JSONObject j1 = new JSONObject(json);
                                    JSONArray data = j1.getJSONArray("data");
                                    for (int i = 0; i < data.length(); i++) {
                                        JSONObject dataObject = data.getJSONObject(i);
                                        areaBean.getData().get(i).setName(dataObject.getString("name"));

                                        dataBean.add(areaBean.getData().get(i));
                                    }
                                    areaList.clear();
                                    areaList.addAll(dataBean);
                                    areaAdapter.setSelectItem(0);
                                    areaAdapter.notifyDataSetChanged();
                                    tvArea.setVisibility(View.VISIBLE);
                                    tvArea.setText("/" + areaList.get(0).getName());
                                    isClick = true;
                                    location2 = areaList.get(0).getProvince_name() + areaList.get(0).getCity_name() + areaList.get(0).getName();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
            }
        });

        areaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                areaAdapter.setSelectItem(position);
                areaAdapter.notifyDataSetChanged();
                tvArea.setText("/" + areaList.get(position).getName());
                tvArea.setVisibility(View.VISIBLE);
                isClick = false;
                location1 = areaList.get(position).getProvince_name() + areaList.get(position).getCity_name() + areaList.get(position).getName();
            }
        });

        //加载数据
        initData();
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    //获取省份数据
    private void initData() {
        OkGo.<String>post(URL.PROCITYCOUNTRY_URL)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body().toString();
                        Log.i("onSuccess: ", json);
                        Gson gson = new Gson();
                        provinceBean = gson.fromJson(json, ProvinceBean.class);
                        proList.addAll(provinceBean.getData());
                        provinceAdapter.setSelectItem(0);
                        tvProvince.setText(proList.get(0).getName());
                        provinceAdapter.notifyDataSetChanged();

                        //获取城市数据
                        OkGo.<String>post(URL.PROCITYCOUNTRY_URL)
                                .params("province_id", proList.get(0).getId())
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(Response<String> response) {
                                        String json = response.body().toString();
                                        Log.i("onSuccess: ", json);

                                        Gson gson = new Gson();
                                        CityBean cityBean = gson.fromJson(json, CityBean.class);
                                        List<CityBean.DataBean> citylist = cityBean.getData();
                                        cityList.clear();
                                        cityList.addAll(citylist);
                                        cityAdapter.setSelectItem(0);
                                        cityAdapter.notifyDataSetChanged();
                                        tvCity.setVisibility(View.VISIBLE);
                                        tvCity.setText("/" + cityList.get(0).getName());

                                    }
                                });
                    }
                });
    }

    @Override
    public void finishRefresh() {

    }
}
