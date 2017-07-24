package com.dl7.shopping.module.fragment.water;

/**
 * 订水
 * Created by MC.Zeng on 2017-06-27.
 */

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.dl7.shopping.R;
import com.dl7.shopping.adapter.WaterListViewAdapter;
import com.dl7.shopping.api.URL;
import com.dl7.shopping.bean.WaterBean;
import com.dl7.shopping.module.activity.home.combo.ComboActivity;
import com.dl7.shopping.module.activity.home.getwater.GetWaterActivity;
import com.dl7.shopping.module.activity.home.help.HelpActivity;
import com.dl7.shopping.module.activity.home.payment.PaymentActivity;
import com.dl7.shopping.module.base.BaseFragment;
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

public class WaterFragment extends BaseFragment<WaterPresenter> implements WaterListViewAdapter.Callback ,IWaterView{
    @BindView(R.id.rbn_water)
    RadioButton rbnWater;
    @BindView(R.id.rbn_combo)
    RadioButton rbnCombo;
    @BindView(R.id.rbn_help)
    RadioButton rbnHelp;
    @BindView(R.id.lv_water)
    ListView lvWater;
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
    private String storeID = "c48f4240-3e9d-11e7-9d6e-b8975a6f72af";
    //    private List<WaterBean.DataBean> dataBean=new ArrayList<>();
    private WaterBean.DataBean dataBean;
    private WaterListViewAdapter adapter;


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
        initView();
        adapter = new WaterListViewAdapter(mList, getContext(), this);
        lvWater.setAdapter(adapter);

        initData();
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    //获取数据
    private void initData() {
        OkGo.<String>post(URL.WATERFRAGMENT_URL)
                .params("store_id", storeID)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dataBean = new WaterBean.DataBean();
                        String json = response.body().toString();
                        Log.i("onSuccess2: ", json);
                        Gson gson = new Gson();
                        WaterBean waterBean = gson.fromJson(json, WaterBean.class);
                        List<WaterBean.DataBean.WATERABean> ABean = new ArrayList<WaterBean.DataBean.WATERABean>();
                        List<WaterBean.DataBean.WATERBBean> BBean = new ArrayList<WaterBean.DataBean.WATERBBean>();
                        List<WaterBean.DataBean.WATERCBean> CBean = new ArrayList<WaterBean.DataBean.WATERCBean>();
                        List<WaterBean.DataBean.WATERDBean> DBean = new ArrayList<WaterBean.DataBean.WATERDBean>();
                        try {
                            JSONObject j1 = new JSONObject(json);
                            JSONObject data = j1.getJSONObject("data");
                            JSONArray water_a = data.getJSONArray("WATER_A");
                            for (int i = 0; i < water_a.length(); i++) {
                                Log.i("onSuccess: ", water_a.length() + "");
                                JSONObject water_aJSONObject = water_a.getJSONObject(i);

                                waterBean.getData().getWATER_A().get(i).setCategory_name(water_aJSONObject.getString("category_name"));
                                waterBean.getData().getWATER_A().get(i).setGoods_name(water_aJSONObject.getString("goods_name"));
                                waterBean.getData().getWATER_A().get(i).setPresent_price(water_aJSONObject.getInt("present_price"));
                                waterBean.getData().getWATER_A().get(i).setImage_url(water_aJSONObject.getString("image_url"));
                                waterBean.getData().getWATER_A().get(i).setCompany(water_aJSONObject.getString("company"));
                                waterBean.getData().getWATER_A().get(i).setDiscount(water_aJSONObject.getInt("discount"));
                                waterBean.getData().getWATER_A().get(i).setSpecification(water_aJSONObject.getString("specification"));
                                waterBean.getData().getWATER_A().get(i).setCategory_name(water_aJSONObject.getString("category_name"));

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

    private void initView() {
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
    }

    @Override
    public void click(View v) {
        final Integer position = (Integer) v.getTag();

//        switch (v.getId()){
//            case R.id.shopping_img:
//                initPopupWindow();
//                break;
//            case R.id.shopping_img2:
//                initPopupWindow();
//                break;
//            case R.id.shopping_img3:
//                initPopupWindow();
//                break;
//        }
    }

    private void initPopupWindow() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View popview = inflater.inflate(R.layout.popupwindow_water, null);

        popupWindow = new PopupWindow(popview, 750, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(popview);
        ImageView head = (ImageView) popview.findViewById(R.id.img_pop_head);
        TextView name = (TextView) popview.findViewById(R.id.tv_pop_name);
        TextView tipIcon = (TextView) popview.findViewById(R.id.tv_pop_tip_icon);
        tipIcon.setTypeface(iconFont);
        TextView tvDismiss = (TextView) popview.findViewById(R.id.tv_pop_dismiss);
        final TextView money = (TextView) popview.findViewById(R.id.tv_pop_money);
        final TextView buyBucket = (TextView) popview.findViewById(R.id.tv_pop_buy);
        final TextView rentBuckt = (TextView) popview.findViewById(R.id.tv_pop_rent);
//        TextView address = (TextView) popview.findViewById(R.id.tv_pop_address);
//        RelativeLayout rlAddress = (RelativeLayout) popview.findViewById(R.id.rl_pop_address);
//        RelativeLayout rlTime = (RelativeLayout) popview.findViewById(R.id.rl_pop_time);
//        TextView time = (TextView) popview.findViewById(R.id.tv_pop_time);
        ImageView subtract = (ImageView) popview.findViewById(R.id.img_pop_subtract);
        ImageView add = (ImageView) popview.findViewById(R.id.img_pop_add);
        final TextView num = (TextView) popview.findViewById(R.id.tv_pop_num);
        TextView payment = (TextView) popview.findViewById(R.id.tv_pop_payment);

        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));
        //设置背景阴暗
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.5f;
        getActivity().getWindow().setAttributes(lp);
        //点击外部弹框消失
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(false);
        //弹框显示位置
        popupWindow.showAtLocation(popview, Gravity.BOTTOM, 0, 0);
        //popupWindow消失事件
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //恢复背景颜色
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 1f;
                getActivity().getWindow().setAttributes(lp);
            }
        });


        tvDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        //买桶
        buyBucket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buyBucket.setBackgroundResource(R.drawable.water_textview_background1);
                buyBucket.setTextColor(Color.parseColor("#ffffff"));
                rentBuckt.setBackgroundResource(R.drawable.water_textview_background2);
                rentBuckt.setTextColor(Color.parseColor("#1a1a1a"));
            }
        });
        //租桶
        rentBuckt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rentBuckt.setBackgroundResource(R.drawable.water_textview_background1);
                rentBuckt.setTextColor(Color.parseColor("#ffffff"));
                buyBucket.setBackgroundResource(R.drawable.water_textview_background2);
                buyBucket.setTextColor(Color.parseColor("#1a1a1a"));
            }
        });
//        //地址
//        rlAddress.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getContext(), CitySelectActivity.class);
//                startActivity(intent);
//            }
//        });
//        //时间
//        rlTime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        //减少数量
        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (number > 1) {
                    number--;
                    num.setText(number + "");
                    money.setText("¥ " + number * 25 + "");
                } else {
                    Toast.makeText(getContext(), "至少一桶才能下单", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //增加数量
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number++;
                num.setText(number + "");
                money.setText("¥ " + number * 25 + "");
            }
        });

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PaymentActivity.class);
                startActivity(intent);
                popupWindow.dismiss();
            }
        });

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
}
