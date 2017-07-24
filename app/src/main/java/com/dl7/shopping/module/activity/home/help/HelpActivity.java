package com.dl7.shopping.module.activity.home.help;

import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dl7.shopping.R;
import com.dl7.shopping.adapter.HelpAdapter;
import com.dl7.shopping.bean.HelpBean;
import com.dl7.shopping.module.base.BaseActivity;
import com.dl7.shopping.utils.FontManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 帮助页面
 * Created by MC.Zeng on 2017-07-18.
 */

public class HelpActivity extends BaseActivity<HelpPresenter> implements IHelpView {
    @BindView(R.id.tv_help_back)
    TextView back;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.tv_question1)
    TextView question1;
    @BindView(R.id.tv_question2)
    TextView question2;
    @BindView(R.id.tv_question3)
    TextView question3;
    @BindView(R.id.tv_question4)
    TextView question4;
    @BindView(R.id.tv_question5)
    TextView question5;
    @BindView(R.id.tv_question6)
    TextView question6;
    @BindView(R.id.tv_question7)
    TextView question7;
    @BindView(R.id.lv_help)
    ListView listView;
    @BindView(R.id.tv_help_confirm)
    TextView confirm;
    @BindView(R.id.rl_help_bottom)
    RelativeLayout rlHelpBottom;
    private Typeface iconFont;
    private HelpBean bean;
    private List<HelpBean> list;
    private HelpBean helpBean;
    private HelpAdapter adapter;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_help;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
        list = new ArrayList<>();
        helpBean = new HelpBean();
        back.setTypeface(iconFont);
        //问题的点击事件
        question1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                HelpBean helpBean = new HelpBean();
                helpBean.setRight("我的水还没收到1");
                helpBean.setLeft("正常情况下我们会在下单后的三个小时内配送");
                helpBean.setTag(0);
                list.add(helpBean);
                adapter.notifyDataSetChanged();

            }
        });
        question2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                HelpBean helpBean = new HelpBean();
                helpBean.setRight("我的水还");
                helpBean.setLeft("正常情况下我们会");
                helpBean.setTag(1);
                list.add(helpBean);
                Log.i("onClick: ", helpBean.getLeft() + helpBean.getRight());
                adapter.notifyDataSetChanged();

            }
        });
        question3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                HelpBean helpBean = new HelpBean();
                helpBean.setRight("我的水还没收到3");
                helpBean.setLeft("正常情况下我们会在下单后的三个小时内配送");
                helpBean.setTag(2);
                list.add(helpBean);
                Log.i("onClick: ", helpBean.getLeft() + helpBean.getRight());
                adapter.notifyDataSetChanged();

            }
        });
        question4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                HelpBean helpBean = new HelpBean();
                helpBean.setRight("我的水还没收到4");
                helpBean.setLeft("正常情况下我们会在下单后的三个小时内配送");
                helpBean.setTag(3);

                list.add(helpBean);
                adapter.notifyDataSetChanged();

            }
        });
        question5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                HelpBean helpBean = new HelpBean();
                helpBean.setRight("我的水还没收到5");
                helpBean.setLeft("正常情况下我们会在下单后的三个小时内配送");
                helpBean.setTag(4);
                list.add(helpBean);
                adapter.notifyDataSetChanged();

            }
        });
        question6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                HelpBean helpBean = new HelpBean();
                helpBean.setRight("我的水还没收到6");
                helpBean.setLeft("正常情况下我们会在下单后的三个小时内配送");
                helpBean.setTag(5);
                list.add(helpBean);
                adapter.notifyDataSetChanged();

            }
        });
        question7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                HelpBean helpBean = new HelpBean();
                helpBean.setRight("我的水还没收到7");
                helpBean.setLeft("正常情况下我们会在下单后的三个小时内配送");
                helpBean.setTag(6);
                list.add(helpBean);
                adapter.notifyDataSetChanged();

            }
        });

        adapter = new HelpAdapter(R.layout.item_help,helpBean, list, this);
        listView.setAdapter(adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
