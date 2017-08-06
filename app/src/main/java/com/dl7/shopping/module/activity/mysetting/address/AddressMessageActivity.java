package com.dl7.shopping.module.activity.mysetting.address;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.dl7.shopping.R;
import com.dl7.shopping.adapter.AddressMessageAdapter;
import com.dl7.shopping.api.URL;
import com.dl7.shopping.bean.AddressMessageBean;
import com.dl7.shopping.module.activity.home.waterstore.WaterStoreActivity;
import com.dl7.shopping.module.activity.mysetting.address.presenter.AddressMessagePresenter;
import com.dl7.shopping.module.activity.mysetting.address.baseview.IAddressMessageView;
import com.dl7.shopping.module.base.BaseActivity;
import com.dl7.shopping.rxbus.event.EighteenEvent;
import com.dl7.shopping.rxbus.event.ElevenEvent;
import com.dl7.shopping.rxbus.event.NightEvent;
import com.dl7.shopping.rxbus.event.SeventeenEvent;
import com.dl7.shopping.rxbus.event.SeventhEvent;
import com.dl7.shopping.rxbus.event.SixEvent;
import com.dl7.shopping.rxbus.event.SixteenEvent;
import com.dl7.shopping.rxbus.event.TenthEvent;
import com.dl7.shopping.utils.CommonMethod;
import com.dl7.shopping.utils.FontManager;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
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
    private String uid;
    private List<AddressMessageBean.DataBean.ListBean> mList=new ArrayList<>();
    private AddressMessageAdapter adapter;
    private String isSelect;
    private AddressMessageBean bean=new AddressMessageBean();
    private String msg;

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


        Intent intent=getIntent();
        isSelect = intent.getStringExtra("isSelect");

        EventBus.getDefault().register(this);

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

        if (isSelect.equals("true")){

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Log.i("onItemClick: ", mList.get(position).getStore_id());
                    OkGo.<String>post(URL.GETSTORETYPE_URL)
                            .params("store_id",mList.get(position).getStore_id())
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    String json = response.body().toString();
                                    Log.i( "onSuccess: ",json);
                                    try {
                                        JSONObject j1=new JSONObject(json);
                                        JSONObject data = j1.getJSONObject("data");
                                        String store_type = data.getString("store_type");
                                        EventBus.getDefault().post(
                                                new EighteenEvent(store_type));

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });

                    //刷新下单地址选择
                    EventBus.getDefault().post(
                            new SixEvent(mList.get(position).getAddress()));
                    EventBus.getDefault().post(
                            new SeventhEvent(mList.get(position).getId()));
                    finish();
                }
            });
        }else if (isSelect.equals("selectaddress")){
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, final long id) {

                    if (mList.get(position).getStore_id().equals("")){//判断改地址有无选择水店
                        AlertDialog.Builder builder = new AlertDialog.Builder(AddressMessageActivity.this);
                        builder.setCancelable(false);
                        builder.setTitle("提示");
                        builder.setMessage("您还没选择水店");
                        builder.setNegativeButton("现在去选择水店", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent=new Intent(AddressMessageActivity.this, WaterStoreActivity.class);
                                intent.putExtra("address",mList.get(position).getAddress());
                                intent.putExtra("longitude",mList.get(position).getLongitude()+"");
                                intent.putExtra("latitude",mList.get(position).getLatitude()+"");
                                intent.putExtra("id",mList.get(position).getId());
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
                    }else{//如果有水店，
                        String store_id = mList.get(position).getStore_id();
                        String address = mList.get(position).getAddress();
                        double longitude = mList.get(position).getLongitude();
                        double latitude = mList.get(position).getLatitude();
                        EventBus.getDefault().post(
                                new NightEvent(store_id));
                        EventBus.getDefault().post(
                                new TenthEvent(address));
                        finish();
                    }
                }
            });
        }else if(isSelect.equals("phoneSelect")){
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //刷新手机号选择下单地址选择
                    EventBus.getDefault().post(
                            new SixteenEvent(mList.get(position).getId()));
                    EventBus.getDefault().post(
                            new SeventeenEvent(mList.get(position).getAddress()));
                    finish();
                }
            });
        }

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
                        Gson gson = new Gson();
                        AddressMessageBean addressMessageBean = gson.fromJson(json, AddressMessageBean.class);
                        List<AddressMessageBean.DataBean.ListBean> listBean = addressMessageBean.getData().getList();
                        mList.addAll(listBean);
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void click(View v) {
        final Integer position = (Integer) v.getTag();
        switch (v.getId()){
            case R.id.ll_address_default://设为默认地址
                OkGo.<String>post(URL.ADDRESSDEFULT_URL)
                        .params("member_id",uid)
                        .params("id",mList.get(position).getId())
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                String json = response.body().toString();
                                Log.i("onSuccess: ",json );
                                if (isSelect.equals("selectdefult")){
//                                    EventBus.getDefault().post(
//                                            new EighthEvent("OK"));
                                    Intent intent=new Intent(AddressMessageActivity.this,WaterStoreActivity.class);
                                    intent.putExtra("address",mList.get(position).getAddress());
                                    startActivity(intent);
                                }

                                //重新刷新页面
                                initData();
                                mList.clear();
                                adapter.notifyDataSetChanged();
                            }
                        });
                break;

            case R.id.ll_address_delete://删除地址
                AlertDialog.Builder dialog=new AlertDialog.Builder(this);
                dialog.setTitle("删除");
                dialog.setMessage("确定删除该地址？");
                dialog.setCancelable(false);
                dialog.setPositiveButton("取消",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();//取消删除
                    }
                });
                dialog.setNegativeButton("确定",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        OkGo.<String>post(URL.ADDRESSDELETE_URL)
                                .params("member_id",uid)
                                .params("id",mList.get(position).getId())
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

    //通知刷新
    @Subscribe
    public void onEventMainThread(ElevenEvent event) {

        msg = event.getMsg();
        Log.i("onEventMainThread: ", msg);
        mList.clear();
        initData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消注册
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void finishRefresh() {

    }
}
