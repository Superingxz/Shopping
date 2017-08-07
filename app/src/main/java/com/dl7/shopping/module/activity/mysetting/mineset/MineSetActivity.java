package com.dl7.shopping.module.activity.mysetting.mineset;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dl7.shopping.R;
import com.dl7.shopping.adapter.PhotoAdapter;
import com.dl7.shopping.api.URL;
import com.dl7.shopping.module.activity.mysetting.address.AddressMessageActivity;
import com.dl7.shopping.module.activity.mysetting.changename.ChangeNameActivity;
import com.dl7.shopping.module.base.BaseActivity;
import com.dl7.shopping.rxbus.event.FirstEvent;
import com.dl7.shopping.utils.CommonMethod;
import com.dl7.shopping.utils.FontManager;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.makeramen.roundedimageview.RoundedImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPreview;

/**
 * 我的-设置
 * Created by MC.Zeng on 2017-6-30.
 */

public class MineSetActivity extends BaseActivity<MineSetPresenter>
        implements IMineSetView {
    @BindView(R.id.mine_setting_back)
    TextView back;
    @BindView(R.id.iv_mine_set_head)
    RoundedImageView head;
    @BindView(R.id.rl_mine_set_head)
    RelativeLayout rlHead;
    @BindView(R.id.mine_set_account_tv)
    TextView mineSetAccountTv;
    @BindView(R.id.mine_set_accounts)
    RelativeLayout mineSetAccounts;
    @BindView(R.id.mine_set_name_tv)
    TextView nameTv;
    @BindView(R.id.mine_set_name)
    RelativeLayout name;
    @BindView(R.id.mine_set_gender_tv)
    TextView genderTv;
    @BindView(R.id.mine_set_gender_icon_tv)
    TextView genderIcon;
    @BindView(R.id.mine_set_gender)
    RelativeLayout gender;
    @BindView(R.id.mine_set_address)
    RelativeLayout address;
    private Typeface iconFont;
    private PhotoAdapter photoAdapter;
    private ArrayList<String> selectedPhotos = new ArrayList<>();
    //    private RelativeLayout exit;
    private SharedPreferences sp;
    private String uid;
    private String msg;
    private String spName;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_mine_setting;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        EventBus.getDefault().register(this);

        photoAdapter = new PhotoAdapter(this, selectedPhotos);
        uid = CommonMethod.getUid(this);

        iconFont = FontManager.getTypeface(this, FontManager.FONTAWESOME);
        back.setTypeface(iconFont);
//        exit = (RelativeLayout) findViewById(R.id.mine_set_exit);

       /* waterStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MineSetActivity.this, WaterStoreActivity.class);
                startActivity(intent);
            }
        });*/


//        //退出登录
//        exit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                SharedPreferences sp1 = getSharedPreferences("flag", MODE_PRIVATE);
//                SharedPreferences.Editor editor1 = sp1.edit();
////                editor1.putString("userphone", "");
//                editor1.putString("userid", "0");
//                editor1.putString("islogin","false");//登录状态
//                editor1.putString("image","");
//                editor1.putString("name","");
//                editor1.putString("mobile","");
//                editor1.commit();
//                Toast.makeText(MineSetActivity.this, "退出登录", Toast.LENGTH_SHORT).show();
//            }
//        });

        //收货地址管理
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MineSetActivity.this,AddressMessageActivity.class);
                intent.putExtra("isSelect","false");
                startActivity(intent);
            }
        });

        //更换性别
        gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建自定义dialog框
                AlertDialog.Builder builder = new AlertDialog.Builder(MineSetActivity.this);
                builder.setCancelable(false);
                View view = LayoutInflater.from(MineSetActivity.this).inflate(R.layout.layout_alertdialog, null);
                final AlertDialog dialog = builder.create();
                dialog.setView(view);
                dialog.show();
                //设置监听
                TextView man = (TextView) view.findViewById(R.id.alertdialog_photo);
                man.setText("男生");
                TextView woman = (TextView) view.findViewById(R.id.alertdialog_picture);
                woman.setText("女生");
                //设置dialog点击外部消失
                dialog.setCanceledOnTouchOutside(true);
                man.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initSex();
                        dialog.dismiss();
                        genderTv.setText("男生");
                        genderIcon.setText("♂");
                        genderIcon.setTextColor(Color.rgb(101, 186, 255));
                    }
                });
                woman.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initSex();
                        dialog.dismiss();
                        genderTv.setText("女生");
                        genderIcon.setText("♀");
                        genderIcon.setTextColor(Color.rgb(255, 85, 156));
                    }
                });
            }
        });


        //更换昵称
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MineSetActivity.this, ChangeNameActivity.class);
                intent.putExtra("name", nameTv.getText());
                startActivity(intent);
            }
        });

//        nameTv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });


        //改变头像的点击事件
        rlHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建自定义dialog框
                AlertDialog.Builder builder = new AlertDialog.Builder(MineSetActivity.this);
                builder.setCancelable(false);
                View view = LayoutInflater.from(MineSetActivity.this).inflate(R.layout.layout_alertdialog, null);
                final AlertDialog dialog = builder.create();
                dialog.setView(view);
                dialog.show();
                //设置监听
                TextView photo = (TextView) view.findViewById(R.id.alertdialog_photo);
                photo.setText("拍照");
                TextView picture = (TextView) view.findViewById(R.id.alertdialog_picture);
                picture.setText("选择照片");
                //设置dialog点击外部消失
                dialog.setCanceledOnTouchOutside(true);

                photo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        //拍照并裁剪
                        PhotoPicker.builder()
                                .setOpenCamera(true)
                                .setCrop(true)
                                .start(MineSetActivity.this);
                    }
                });

                picture.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        //选择图片并裁剪
                        PhotoPicker.builder()
                                .setPhotoCount(1)
                                .setPreviewEnabled(false)
                                .setCrop(true)
                                .setCropXY(1, 1)
                                .setCropColors(R.color.colorPrimary, R.color.colorPrimaryDark)
                                .start(MineSetActivity.this);
                    }
                });

            }
        });

        sp = getSharedPreferences("flag", MODE_PRIVATE);
        if (uid.equals(sp.getString("userid", "0"))) {//如果当前的用户id是存储的用户ID
            String islogin = sp.getString("islogin", "");
            spName = sp.getString("name", "");//得到本地的昵称


            nameTv.setText(spName);


        }

        //返回
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

    //修改性别获取数据
    private void initSex() {
        OkGo.<String>post(URL.UPDATE_URL)
                .params("id", uid)
                .params("sex", genderTv.getText().toString())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body().toString();
                        Log.i("onSuccess: ", json);
                        try {
                            JSONObject j1 = new JSONObject(json);
                            Toast.makeText(MineSetActivity.this, j1.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //选择返回
        if (resultCode == RESULT_OK &&
                (requestCode == PhotoPicker.REQUEST_CODE || requestCode == PhotoPreview.REQUEST_CODE)) {
//            iv_crop.setVisibility(View.GONE);
//            recyclerView.setVisibility(View.VISIBLE);

            List<String> photos = null;
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            }
            selectedPhotos.clear();
            if (photos != null) {
                selectedPhotos.addAll(photos);
            }
            photoAdapter.notifyDataSetChanged();
        }
        //拍照功能或者裁剪功能返回
        if (resultCode == RESULT_OK && requestCode == PhotoPicker.CROP_CODE) {
//            iv_crop.setVisibility(View.VISIBLE);
//            recyclerView.setVisibility(View.GONE);
            Glide.with(getApplicationContext()).load(Uri.fromFile(new File(data.getStringExtra(PhotoPicker.KEY_CAMEAR_PATH)))).into(head);
        }
    }

    @Subscribe
    public void onEventMainThread(FirstEvent event) {
        msg = event.getMsg();
        Log.i("onEventMainThread: ", msg);
        nameTv.setText(msg);
        SharedPreferences sp = getSharedPreferences("flag", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name", msg);
        editor.commit();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void finishRefresh() {

    }

}
