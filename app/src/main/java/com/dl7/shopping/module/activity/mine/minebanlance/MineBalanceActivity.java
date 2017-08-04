package com.dl7.shopping.module.activity.mine.minebanlance;

import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.dl7.shopping.R;
import com.dl7.shopping.adapter.MineBalanceAdapter;
import com.dl7.shopping.api.URL;
import com.dl7.shopping.module.activity.mine.banlancedetail.BalanceDetailActivity;
import com.dl7.shopping.module.base.BaseActivity;
import com.dl7.shopping.utils.CommonMethod;
import com.dl7.shopping.utils.FontManager;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;

/**
 * 我的-余额
 * Created by MC.Zeng on 2017-06-30.
 */

public class MineBalanceActivity extends BaseActivity<MineBalancePresenter>
        implements IMineBalanceView {
    @BindView(R.id.mine_balance_back)
    TextView back;
    @BindView(R.id.mine_balance_text)
    TextView balance;
    @BindView(R.id.mine_account_frozen_text)
    TextView frozen;
    @BindView(R.id.mine_balance_listview)
    ListView listView;
    private String[] name = {"提现", "充值", "账户明细", "大额转账"};
    private int[] img={R.mipmap.ti_xian,R.mipmap.chong_zhi,R.mipmap.ming_xi,R.mipmap.zhuan_zhang};
    private Typeface iconFont;
    private String uid;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_mine_balance;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        uid = CommonMethod.getUid(this);
        Log.i("onSuccess11: ", uid);

        //        //导入可以让Textview使用小图标的文件
//        font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        iconFont = FontManager.getTypeface(this, FontManager.FONTAWESOME);
        back.setTypeface(iconFont);

        MineBalanceAdapter adapter = new MineBalanceAdapter(img, name, this);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 2) {
                    Intent intent = new Intent(MineBalanceActivity.this, BalanceDetailActivity.class);
                    startActivity(intent);
                }
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
        OkGo.<String>post(URL.BALANCE_URL)
                .params("member_id", uid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body().toString();
                        Log.i("onSuccess: ", json);
                        try {
                            JSONObject j1 = new JSONObject(json);
                            JSONObject data = j1.getJSONObject(j1.getString("data"));
                            String cash = data.getString("cash");
                            String freeze_cash = data.getString("freeze_cash");
//                            Toast.makeText(getContext(), j1.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void finishRefresh() {

    }
}
