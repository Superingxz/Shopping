package com.dl7.shopping.module.activity.mine.love;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.dl7.shopping.R;
import com.dl7.shopping.adapter.LoveListAdapter;
import com.dl7.shopping.api.URL;
import com.dl7.shopping.bean.LoveListBean;
import com.dl7.shopping.utils.CommonMethod;
import com.dl7.shopping.utils.FontManager;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 爱心
 * Created by MC.Zeng on 2017-07-01.
 */

public class LoveActivity extends AppCompatActivity {

    private Typeface iconFont;
    private TextView back;
    private String uid;
    private TextView tvScore;
    private TextView tvTotalScore;
    private TextView tvTotalProfit;
    private int pageNum=1;
    private PullToRefreshListView listView;
    private List<LoveListBean.DataBean.ListBean> mList=new ArrayList<>();
    private LoveListBean loveListBean;
    private LoveListAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_love);
        //使用Font Awesome
        iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);

        uid = CommonMethod.getUid(this);
        initView();
        adapter = new LoveListAdapter(mList,this);
        listView.setAdapter(adapter);
        initData();
        initListData();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNum=1;
                initData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNum++;
                initData();
            }
        });


    }

    //获取上部分数据
    private void initData() {
        OkGo.<String>post(URL.LOVEDATA_URL)
                .params("member_id",uid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body().toString();
                        Log.i("onSuccess1: ", json);
                        try {
                            JSONObject j1=new JSONObject(json);
                            JSONObject data = j1.getJSONObject("data");
                            String score = data.getString("score");
                            String total_score = data.getString("total_score");
                            String total_profit = data.getString("total_profit");

                            tvScore.setText(score+"个");
                            tvTotalScore.setText(total_score+"个");
                            tvTotalProfit.setText(total_profit);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                });
    }

    //获取下半list部分的数据
    private void initListData() {
        OkGo.<String>post(URL.LOVEDETAIL_URL)
                .params("member_id",uid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body().toString();
                        Log.i("onSuccess1: ", json);
                        List<LoveListBean.DataBean.ListBean> listBean=new ArrayList<LoveListBean.DataBean.ListBean>();
                        Gson gson=new Gson();
                        loveListBean = gson.fromJson(json, LoveListBean.class);
                        try {
                            JSONObject j1=new JSONObject(json);
                            JSONObject data = j1.getJSONObject("data");
                            JSONArray list = data.getJSONArray("list");
                            for (int i = 0; i <list.length(); i++) {
                                JSONObject listObject = list.getJSONObject(i);
                                String notes = listObject.getString("notes");
                                Double score = listObject.getDouble("score");
                                String create_time = listObject.getString("create_time");
                                loveListBean.getData().getList().get(i).setCreate_time(create_time);
                                loveListBean.getData().getList().get(i).setNotes(notes);
                                loveListBean.getData().getList().get(i).setScore(score);
                                loveListBean.getData().getList().get(i).setType(listObject.getString("type"));
                                listBean.add(loveListBean.getData().getList().get(i));
                            }
                            mList.addAll(loveListBean.getData().getList());
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void initView() {
        back = (TextView) findViewById(R.id.tv_love_back);
        back.setTypeface(iconFont);
        tvScore = (TextView) findViewById(R.id.tv_love_score);
        tvTotalScore = (TextView) findViewById(R.id.tv_love_total_score);
        tvTotalProfit = (TextView) findViewById(R.id.tv_love_total_profit);
        listView = (PullToRefreshListView) findViewById(R.id.lv_love);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
    }
}
