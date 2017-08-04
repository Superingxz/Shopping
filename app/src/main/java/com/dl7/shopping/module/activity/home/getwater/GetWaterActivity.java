package com.dl7.shopping.module.activity.home.getwater;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dl7.shopping.R;
import com.dl7.shopping.adapter.WaterDetailAdapter;
import com.dl7.shopping.api.URL;
import com.dl7.shopping.bean.WaterDetailBean;
import com.dl7.shopping.module.activity.home.payment.PaymentActivity;
import com.dl7.shopping.module.base.BaseActivity;
import com.dl7.shopping.rxbus.event.TwentyEvent;
import com.dl7.shopping.utils.CommonMethod;
import com.dl7.shopping.utils.FontManager;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
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
 * 取水
 * Created by MC.Zeng on 2017-07-04.
 */

public class GetWaterActivity extends BaseActivity<GetWaterPresenter>
        implements IGetWaterView {

    @BindView(R.id.tv_getwater_back)
    TextView back;
    @BindView(R.id.tv_water_total_num)
    TextView totalNum;
    @BindView(R.id.tv_water_month_num)
    TextView monthNum;
    @BindView(R.id.tv_water_barrel_num)
    TextView barrelNum;
    @BindView(R.id.tv_water_mortgage_barrel)
    TextView mortageBarrel;
    @BindView(R.id.lv_water_detail)
    PullToRefreshListView listView;
    @BindView(R.id.rl_payment)
    RelativeLayout payment;
    private Typeface iconFont;
    private List<String> groupArray;//组列表
    private List<List<String>> childArray;//子列表
    private boolean IsFlag;

    private String uid;
    private int pageNum=1;
    private WaterDetailBean waterDetailListBean;
    private List<WaterDetailBean.DataBean.ListBean> mlist=new ArrayList<>();
    private WaterDetailAdapter adapter;
    private String store_id;
    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_getwater;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        //使用Font Awesome
        iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
        back.setTypeface(iconFont);

        uid = CommonMethod.getUid(this);
        SharedPreferences sp = getSharedPreferences("flag", MODE_PRIVATE);
        store_id = sp.getString("store_id", "");
        initData();
        initListData();
        adapter = new WaterDetailAdapter(mlist,this);
        listView.setAdapter(adapter);
        listView.setMode(PullToRefreshBase.Mode.BOTH);

        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNum=1;
                mlist.clear();
                initListData();
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNum++;
                initListData();
                adapter.notifyDataSetChanged();

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(
                        new TwentyEvent(store_id));
                finish();
            }
        });

        //下单按钮跳转
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GetWaterActivity.this,PaymentActivity.class);
                startActivity(intent);
            }
        });
    }

    //获取用户取水信息
    private void initData() {
        OkGo.<String>post(URL.GETWATER_URL)
                .params("member_id",uid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body().toString();
                        Log.i("onSuccess: ", json);
                        try {
                            JSONObject j1=new JSONObject(json);
                            JSONObject data = j1.getJSONObject("data");
                            totalNum.setText(data.getInt("total_num")+"桶");
                            monthNum.setText(data.getInt("month_num")+"桶");
                            barrelNum.setText(data.getInt("barrel_num")+"个");
                            mortageBarrel.setText(data.getInt("mortgage_barrel")+"个");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    //获取用水明细数据
    private void initListData() {
        OkGo.<String>post(URL.WATERDETAIL_URL)
                .params("member_id",uid)
                .params("pageNum",pageNum)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body().toString();
                        Log.i("onSuccess: ", json);
                        List<WaterDetailBean.DataBean.ListBean> listBeen=new ArrayList<WaterDetailBean.DataBean.ListBean>();
                        Gson gson=new Gson();
                        waterDetailListBean = gson.fromJson(json, WaterDetailBean.class);

                        try {
                            JSONObject j1=new JSONObject(json);
                            JSONObject data = j1.getJSONObject("data");
                            JSONArray listArray = data.getJSONArray("list");
                            for (int i = 0; i < listArray.length(); i++) {
                                JSONObject listObject = listArray.getJSONObject(i);
                                waterDetailListBean.getData().getList().get(i).setNotes(listObject.getString("notes"));
                                waterDetailListBean.getData().getList().get(i).setType(listObject.getString("type"));
                                waterDetailListBean.getData().getList().get(i).setCreate_time(listObject.getString("create_time"));

                                listBeen.add(waterDetailListBean.getData().getList().get(i));
                            }
//                            mlist.addAll(listBeen);
                            mlist.addAll(waterDetailListBean.getData().getList());

                            adapter.notifyDataSetChanged();

                            //完成刷新
                            listView.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    listView.onRefreshComplete();
                                }
                            }, 1000);

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
