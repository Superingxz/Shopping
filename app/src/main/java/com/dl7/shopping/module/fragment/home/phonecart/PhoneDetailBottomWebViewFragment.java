package com.dl7.shopping.module.fragment.home.phonecart;

import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;

import com.dl7.shopping.R;
import com.dl7.shopping.module.base.BaseFragment;
import com.dl7.shopping.module.fragment.home.phonecart.presenter.PhoneDetailBottomWebViewPresenter;
import com.dl7.shopping.module.fragment.home.phonecart.baseview.IPhoneDetailBottomWebViewView;
import com.lzy.widget.vertical.VerticalWebView;

import butterknife.BindView;

/**
 * Created by MC.Zeng on 2017-07-29.
 */

public class PhoneDetailBottomWebViewFragment extends BaseFragment<PhoneDetailBottomWebViewPresenter>
        implements IPhoneDetailBottomWebViewView {
    @BindView(R.id.webView)
    VerticalWebView webview;
    @BindView(R.id.progressbar)
    View progressBar;
    private boolean hasInited = false;
    private String goods_id;
    private String introduction;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_webview;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    public void initViews() {
        SharedPreferences sp = getActivity().getSharedPreferences("flag", getActivity().MODE_PRIVATE);
        goods_id = sp.getString("goodsId", "");
        Log.i("onCreateView: ", goods_id);
        if (null != webview) {
            hasInited = true;
            progressBar.setVisibility(View.GONE);
//            webview.loadUrl("https://github.com/jeasonlzy0216");
            webview.loadUrl(introduction);
        }
//        initData();
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

//    //获取数据
//    private void initData() {
//        OkGo.<String>post(URL.GOODSDETAIL_URL)
//                .params("goods_id", goods_id)
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(Response<String> response) {
//                        String json = response.body().toString();
//                        Log.i("onSuccess: ", json);
//                        try {
//                            JSONObject j1=new JSONObject(json);
//                            Gson gson=new Gson();
//                            GoodsDetailBean goodsDetailBean = gson.fromJson(json, GoodsDetailBean.class);
//                            JSONObject data = j1.getJSONObject("data");
//                            introduction = data.getString("introduction");
//                            Log.i( "onSuccess: ",introduction);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//    }


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
