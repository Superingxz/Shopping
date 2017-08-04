package com.dl7.shopping.module.fragment;

/**
 * 商品详情下部webview部分
 * Created by MC.Zeng on 2017-07-21.
 */

import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;

import com.dl7.shopping.R;
import com.dl7.shopping.api.URL;
import com.dl7.shopping.bean.GoodsDetailBean;
import com.dl7.shopping.module.base.BaseFragment;
import com.dl7.shopping.utils.CommonMethod;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.widget.vertical.VerticalWebView;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;

public class DetailBottomWebViewFragment extends BaseFragment {
    @BindView(R.id.webView)
    VerticalWebView webview;
    @BindView(R.id.progressbar)
    View progressBar;
    private boolean hasInited = false;
    private String goods_id;
    private String introduction;
    private String addressId;
    private String uid;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_webview;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        uid = CommonMethod.getUid(getContext());
        SharedPreferences sp = getActivity().getSharedPreferences("flag", getActivity().MODE_PRIVATE);
        goods_id = sp.getString("goods_id", "");
        addressId = sp.getString("addressId1", "");
        Log.i("onCreateView: ", goods_id);
        initView();
        initData();
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    //获取数据
    private void initData() {
        OkGo.<String>post(URL.GOODSDETAIL_URL)
                .params("goods_id", goods_id)
                .params("member_id",uid)
                .params("address_id",addressId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body().toString();
                        Log.i("onSuccess: ", json);
                        try {
                            JSONObject j1=new JSONObject(json);
                            Gson gson=new Gson();
                            GoodsDetailBean goodsDetailBean = gson.fromJson(json, GoodsDetailBean.class);
                            JSONObject data = j1.getJSONObject("data");
                            introduction = data.getString("introduction");
                            Log.i( "onSuccess: ",introduction);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public void initView() {
        if (null != webview) {
            hasInited = true;
            progressBar.setVisibility(View.GONE);
//            webview.loadUrl("https://github.com/jeasonlzy0216");
            webview.loadUrl(introduction);
        }
    }

    @Override
    public void goTop() {
        webview.goTop();
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
