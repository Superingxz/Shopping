package com.dl7.shopping.module.activity.mine.banlancedetail;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.dl7.shopping.R;
import com.dl7.shopping.api.URL;
import com.dl7.shopping.module.base.BaseActivity;
import com.dl7.shopping.utils.CommonMethod;
import com.dl7.shopping.utils.FontManager;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import butterknife.BindView;

/**
 * 余额详情
 * Created by MC.Zeng on 2017-07-15.
 */

public class BalanceDetailActivity extends BaseActivity<BalanceDetailPresenter>
        implements IBalanceDetailView {
    @BindView(R.id.tv_balance_detail_back)
    TextView back;
    @BindView(R.id.tv_balance_detail_title)
    TextView title;
    @BindView(R.id.tv_balance_detail_triangle)
    TextView triangle;
    @BindView(R.id.ll_balance_detail_title)
    LinearLayout llBalanceDetailTitle;
    @BindView(R.id.lv_balance_detail)
    PullToRefreshListView listView;
    private String uid;
    private Typeface iconFont;
    private int pageNum = 1;
    private PopupWindow popupWindow;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_balance_detail;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
        uid = CommonMethod.getUid(this);
        back.setTypeface(iconFont);
        triangle.setTypeface(iconFont);

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopupWindow();
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
        OkGo.<String>post(URL.BALANCRDETAIL_URL)
                .params("member_id", uid)
                .params("pageNum", pageNum)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body().toString();
                        Log.i("onSuccess: ", json);
                    }
                });
    }


    //弹出窗口
    private void initPopupWindow() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View popview = inflater.inflate(R.layout.popupwindow_balance_detail, null);

        popupWindow = new PopupWindow(popview, 250, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(popview);

        TextView popAll = (TextView) popview.findViewById(R.id.tv_pop_balance_detail_all);
        TextView popExpend = (TextView) popview.findViewById(R.id.tv_pop_balance_detail_expend);
        TextView popIncome = (TextView) popview.findViewById(R.id.tv_pop_balance_detail_income);

        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));
        //设置背景阴暗
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        //点击外部弹框消失
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(false);
        //弹框显示位置
        popupWindow.showAtLocation(popview, Gravity.TOP, Gravity.CENTER - 20, 100);

        //popupWindow消失事件
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //恢复背景颜色
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
    }

    @Override
    public void finishRefresh() {

    }
}
