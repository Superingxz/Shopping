package com.dl7.shopping.module.fragment.Mine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dl7.shopping.R;
import com.dl7.shopping.api.URL;
import com.dl7.shopping.module.activity.mine.login.LoginOrRegisterActivity;
import com.dl7.shopping.module.activity.mine.love.LoveActivity;
import com.dl7.shopping.module.activity.mine.minebanlance.MineBalanceActivity;
import com.dl7.shopping.module.activity.mine.mycollect.MyCollectActivity;
import com.dl7.shopping.module.activity.mysetting.mineset.MineSetActivity;
import com.dl7.shopping.module.base.BaseFragment;
import com.dl7.shopping.rxbus.event.FirstEvent;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.makeramen.roundedimageview.RoundedImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;


/**
 * 我的
 * Created by MC.Zeng on 2017-06-27.
 */
public class MineFragment extends BaseFragment<MinePresenter>
        implements View.OnClickListener, IMineView {
    @BindView(R.id.mine_setting)
    TextView mineSetting;
    @BindView(R.id.iv_mine_head)
    RoundedImageView ivMineHead;
    @BindView(R.id.tv_login_or_name)
    TextView loginORname;
    @BindView(R.id.mine_login_ll)
    LinearLayout login;
    @BindView(R.id.tv_allocation_love_num)
    TextView tvAllocationLoveNum;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.tv_add_love_num)
    TextView tvAddLoveNum;
    @BindView(R.id.tv_all_love_num)
    TextView tvAllLoveNum;
    @BindView(R.id.mine_background_linearlayout)
    LinearLayout mineBackgroundLinearlayout;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.ll_mine_allorder)
    LinearLayout llMineAllorder;
    @BindView(R.id.ll_mine_obligation)
    LinearLayout llMineObligation;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.ll_mine_shipments)
    LinearLayout llMineShipments;
    @BindView(R.id.ll_mine_receiving)
    LinearLayout llMineReceiving;
    @BindView(R.id.ll_mine_appraise)
    LinearLayout llMineAppraise;
    @BindView(R.id.mine_balance)
    LinearLayout mineBalance;
    @BindView(R.id.mine_love)
    LinearLayout mineLove;
    @BindView(R.id.mine_collect)
    LinearLayout mineCollect;
    @BindView(R.id.mine_recharge)
    LinearLayout mineRecharge;
    @BindView(R.id.mine_help)
    LinearLayout mineHelp;
    @BindView(R.id.imageView3)
    ImageView imageView3;
    @BindView(R.id.mine_exit)
    LinearLayout exit;
    private Typeface iconFont;

    //    private String uid;
    private SharedPreferences sp;
    private String msg;
    @Subscribe
    public void onEventMainThread(FirstEvent event) {
        msg = event.getMsg();
        Log.i("onEventMainThread: ", msg);
//        if (msg.equals("OK")){
//            initData();
//            Log.i("onEventMainThread: ", "1");
//        }
        SharedPreferences sp = getActivity().getSharedPreferences("flag", getActivity().MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("data", msg);
        initData();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        EventBus.getDefault().register(this);
        checkLogin();
        //退出登录
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sp1 = getActivity().getSharedPreferences("flag", getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor1 = sp1.edit();
//                editor1.putString("userphone", "");
                editor1.putString("userid", "0");
                editor1.putString("islogin", "false");//登录状态
                editor1.putString("image", "");
                editor1.putString("name", "");
                editor1.putString("mobile", "");
                editor1.commit();
                initData();
                Toast.makeText(getContext(), "退出登录", Toast.LENGTH_SHORT).show();
            }
        });

        //跳转到我的收藏页面
        mineCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sp.getString("islogin", "").equals("true")) {
                    Intent intent = new Intent(getContext(), MyCollectActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getContext(), LoginOrRegisterActivity.class);
                    startActivity(intent);
                }
            }
        });

        //跳转到设置页面
        mineSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sp.getString("islogin", "").equals("true")) {
                    Intent intent = new Intent(getContext(), MineSetActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getContext(), LoginOrRegisterActivity.class);
                    startActivity(intent);
                }
            }
        });

        //跳转到余额页面
        mineBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sp.getString("islogin", "").equals("true")) {
                    Intent intent = new Intent(getContext(), MineBalanceActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getContext(), LoginOrRegisterActivity.class);
                    intent.putExtra("type", "2");
                    startActivity(intent);
                }

            }
        });

        //爱心页面
        mineLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sp.getString("islogin", "").equals("true")) {
                    Intent intent = new Intent(getContext(), LoveActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getContext(), LoginOrRegisterActivity.class);
                    startActivity(intent);
                }
            }
        });

        initData();
    }

    private void checkLogin() {
        //查看是否有保存的账号
        sp = getActivity().getSharedPreferences("flag", getContext().MODE_PRIVATE);
//        uid = CommonMethod.getUid(getContext());

//        uid=sp.getString("data","0");
//        Log.i("onCreateView: ", sp.getString("data","0"));

        if (sp.getString("data", "0").equals(sp.getString("userid", "0"))) {//如果当前的用户id是存储的用户ID
            String islogin = sp.getString("islogin", "");
            if (islogin.equals("true")) {
                loginORname.setText(sp.getString("name", ""));//从本地拿到昵称
                String image = sp.getString("image", "");//从本地拿到头像
                if (TextUtils.isEmpty(sp.getString("image", ""))) {//判断本地是否有头像
                    ivMineHead.setImageResource(R.mipmap.user_head);//如果本地没有头像就使用默认头像
                } else {
                    Glide.with(this).load(sp.getString("image", "")).into(ivMineHead);//加载头像
                }

            } else if (islogin.equals("false")) {
                loginORname.setText("登录/注册");
                //跳转到登录/注册页面
                login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), LoginOrRegisterActivity.class);
                        startActivity(intent);
                    }
                });
            }
        } else {
            loginORname.setText("登录/注册");

            //跳转到登录/注册页面
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), LoginOrRegisterActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    //获取数据
    private void initData() {
        OkGo.<String>post(URL.MINE_URL)
                .params("member_id", sp.getString("data", "0"))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body().toString();
                        Log.i("onSuccess: ", json);
                        SharedPreferences sp = getActivity().getSharedPreferences("flag", getActivity().MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("islogin", "true");
                        try {
                            JSONObject j1 = new JSONObject(json);

//                            JSONObject data = j1.getJSONObject(j1.getString("data"));
//                            String score = data.getString("score");//可分配
//                            String score_new = data.getString("score_new");//新增
//                            String score_index = data.getString("score_index");

//                            Toast.makeText(getContext(), j1.getString("message"), Toast.LENGTH_SHORT).show();
                            if (sp.getString("data", "0").equals(sp.getString("userid", "0"))) {

                                //如果当前的用户id是存储的用户ID
                                String islogin = sp.getString("islogin", "");
                                if (islogin.equals("true")) {
                                    loginORname.setText(sp.getString("name", ""));//从本地拿到昵称
                                    login.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                        }
                                    });
                                    String image = sp.getString("image", "");//从本地拿到头像
                                    if (TextUtils.isEmpty(sp.getString("image", ""))) {//判断本地是否有头像
                                        ivMineHead.setImageResource(R.mipmap.user_head);//如果本地没有头像就使用默认头像
                                    } else {
                                        Glide.with(getContext()).load(sp.getString("image", "")).into(ivMineHead);//加载头像
                                    }

                                } else if (islogin.equals("false")) {
                                    loginORname.setText("登录/注册");
                                    //跳转到登录/注册页面
                                    login.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent(getContext(), LoginOrRegisterActivity.class);
                                            startActivity(intent);
                                        }
                                    });
                                }
                            } else {
                                loginORname.setText("登录/注册");
                                //跳转到登录/注册页面
                                login.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(getContext(), LoginOrRegisterActivity.class);
                                        startActivity(intent);
                                    }
                                });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消注册
        EventBus.getDefault().unregister(this);
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
