package com.dl7.shopping.module.activity.home.phonecart;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.dl7.shopping.R;
import com.dl7.shopping.adapter.SelectPhoneGridViewAdapter;
import com.dl7.shopping.api.URL;
import com.dl7.shopping.bean.SelectPhoneNumBean;
import com.dl7.shopping.utils.FontManager;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择号码页面
 * Created by MC.Zeng on 2017-07-29.
 */

public class SelectPhoneNumActivity extends AppCompatActivity {

    private TextView back;
    private Typeface iconFont;
    private String province_id;
    private String city_id;
    private String store_id;
    private String card_type;
    private EditText search;
    private String phone_number;
    private int pageNum=1;
    private List<SelectPhoneNumBean.DataBean.ListBean> mList=new ArrayList<>();
    private SelectPhoneGridViewAdapter adapter;
    private PullToRefreshGridView gridView;
    private TextView tvAddress;
    private String cityName;
    private String provinceName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_phone);

        Intent intent=getIntent();
        province_id = intent.getStringExtra("province_id");
        city_id = intent.getStringExtra("city_id");
        store_id = intent.getStringExtra("store_id");
        card_type = intent.getStringExtra("card_type");
        provinceName = intent.getStringExtra("provinceName");
        Log.i("onCreate: ", provinceName);
        cityName = intent.getStringExtra("cityName");

        //使用Font Awesome
        iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
        phone_number ="";
        initView();

        adapter = new SelectPhoneGridViewAdapter(mList,this);
        gridView.setAdapter(adapter);
        initData();

        gridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                pageNum=1;
                initData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                pageNum++;
                initData();
            }
        });

        tvAddress.setText(provinceName+"-"+cityName);


        //根据输入的内容去查找
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                phone_number=search.getText().toString();
                mList.clear();
                initData();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //设置搜索的点击，软键盘搜索按钮的点击事件
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // 先隐藏键盘
                    ((InputMethodManager) search.getContext()
                            .getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(SelectPhoneNumActivity.this
                                            .getCurrentFocus().getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    //得到输入的号码，然后加载数据
                    phone_number=search.getText().toString();
                    initData();
                    return true;
                }
                return false;
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1=new Intent(SelectPhoneNumActivity.this,PhoneCartOrderActivity.class);
                intent1.putExtra("goods_id",mList.get(position).getId());
                startActivity(intent1);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    //获取数据
    private void initData() {
        OkGo.<String>post(URL.PHONENUMLIST_URL)
                .params("province_id",province_id)
                .params("city_id",city_id)
                .params("phone_number",phone_number)
                .params("store_id",store_id)
                .params("card_type",card_type)
                .params("pageNum",pageNum)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body().toString();
                        Log.i( "onSuccess: ",json);
                        Gson gson=new Gson();
                        SelectPhoneNumBean selectPhoneNumBean = gson.fromJson(json, SelectPhoneNumBean.class);
                        mList.addAll(selectPhoneNumBean.getData().getList());
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    private void initView() {
        back = (TextView) findViewById(R.id.tv_select_phone_back);
        back.setTypeface(iconFont);
        search = (EditText) findViewById(R.id.et_select_phone_search);
        tvAddress = (TextView) findViewById(R.id.tv_select_phone_address);

        gridView = (PullToRefreshGridView) findViewById(R.id.gv_select_phone);
    }
}
