package com.dl7.shopping.module.activity.mysetting.address.addressmessage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.dl7.shopping.R;
import com.dl7.shopping.adapter.AddressMessageAdapter;
import com.dl7.shopping.api.URL;
import com.dl7.shopping.bean.AddressMessageBean;
import com.dl7.shopping.module.activity.mysetting.address.AddAddress.AddAddressActivity;
import com.dl7.shopping.module.base.BaseActivity;
import com.dl7.shopping.utils.CommonMethod;
import com.dl7.shopping.utils.FontManager;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 收货地址管理
 * Created by MC.Zeng on 2017-07-07.
 */

public class AddressMessageActivity extends BaseActivity<AddressMessagePresenter>
        implements AddressMessageAdapter.Callback, IAddressMessageView {

    @BindView(R.id.tv_address_message_back)
    TextView back;
    @BindView(R.id.lv_address_manage)
    ListView listView;
    @BindView(R.id.btn_add_address)
    Button btnAdd;
    private Typeface iconFont;
    private String[] name = {"aas", "adsdasd", "sadd", "ad", "d", "d", "dddd"};
    private String[] phone = {"123456", "125874", "1587551", "sadasd", "asda", "Asdas", "asdasd"};
    private String[] address = {"天河区", "天河区", "天河区", "asda", "asdas", "Asda", "Asdasd"};
    private String uid;
    private List<AddressMessageBean.DataBean.ListBean> mList = new ArrayList<>();
    private AddressMessageAdapter adapter;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_address;
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

        adapter = new AddressMessageAdapter(R.layout.item_address_message,mList, this, this);
        listView.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddressMessageActivity.this, AddAddressActivity.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initData();

    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    //获取数据
    private void initData() {
        OkGo.<String>post(URL.ADDRESSMESSAGE_URL)
                .params("member_id", uid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body().toString();
                        Log.i("onSuccess: ", json);

                        Gson gson = new Gson();
                        AddressMessageBean addressMessageBean = gson.fromJson(json, AddressMessageBean.class);
                        List<AddressMessageBean.DataBean.ListBean> listBean = new ArrayList<AddressMessageBean.DataBean.ListBean>();
                        try {
                            JSONObject j1 = new JSONObject(json);
                            JSONObject data = j1.getJSONObject("data");
                            JSONArray list = data.getJSONArray("list");
                            for (int i = 0; i < list.length(); i++) {
                                JSONObject listObject = list.getJSONObject(i);
                                addressMessageBean.getData().getList().get(i).setContact(listObject.getString("contact"));
                                addressMessageBean.getData().getList().get(i).setMobile(listObject.getString("mobile"));
                                addressMessageBean.getData().getList().get(i).setAddress(listObject.getString("address"));
                                addressMessageBean.getData().getList().get(i).setIs_default(listObject.getInt("is_default"));
                                addressMessageBean.getData().getList().get(i).setId(listObject.getString("id"));

                                listBean.add(addressMessageBean.getData().getList().get(i));
                            }
                            mList.addAll(listBean);
                            adapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });
    }

    @Override
    public void click(View v) {
        final Integer position = (Integer) v.getTag();
        switch (v.getId()) {
            case R.id.ll_address_default://设为默认地址
                OkGo.<String>post(URL.ADDRESSDEFULT_URL)
                        .params("member_id", uid)
                        .params("id", mList.get(position).getId())
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                String json = response.body().toString();
                                Log.i("onSuccess: ", json);
                                //重新刷新页面
                                initData();
                                mList.clear();
                                adapter.notifyDataSetChanged();
                            }
                        });
                break;

            case R.id.ll_address_delete://删除地址
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("删除");
                dialog.setMessage("确定删除该地址？");
                dialog.setCancelable(false);
                dialog.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();//取消删除
                    }
                });
                dialog.setNegativeButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        OkGo.<String>post(URL.ADDRESSDELETE_URL)
                                .params("member_id", uid)
                                .params("id", mList.get(position).getId())
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(Response<String> response) {
                                        String json = response.body().toString();
                                        Log.i("onSuccess: ", json);
                                        //刷新页面
                                        initData();
                                        mList.clear();
                                        adapter.notifyDataSetChanged();
                                    }
                                });
                    }
                });

                dialog.create().show();//显示dialog
                break;
        }
    }

    @Override
    public void finishRefresh() {

    }
}
