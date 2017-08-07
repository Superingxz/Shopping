package com.dl7.shopping.module.activity;

import android.Manifest;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.dl7.shopping.R;
import com.dl7.shopping.module.fragment.HomeTabFragment;
import com.dl7.shopping.module.fragment.Mine.MineFragment;
import com.dl7.shopping.module.fragment.merchant.MerchantFragment;
import com.dl7.shopping.module.fragment.shoppingcart.ShoppingCartFragment;
import com.dl7.shopping.utils.CommonMethod;


public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private long exitTime = 0;
    private RadioGroup navigationBar;
    private RadioButton btn1, btn2, btn3, btn4;
    private Fragment fragment2, fragment3, fragment4;
    private Fragment fragment1 = new HomeTabFragment();
    private Fragment mFragment;//当前显示的Fragment

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    public static Activity instance;

    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {

            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    Log.i("onLocationChanged: ", amapLocation.getProvince()+ amapLocation.getCity()+ amapLocation.getDistrict()+ amapLocation.getStreet()+ amapLocation.getStreetNum());
                    Log.i("onLocationChanged: ", amapLocation.getLocationType()+"");
//                    //保存经纬度
//                    SharedPreferences sp1 = getSharedPreferences("flag",MODE_PRIVATE);
//                    SharedPreferences.Editor editor1 = sp1.edit();
//                    Log.i("onCreate: ", mLocationClient.getLastKnownLocation().getLongitude()+"");
//                    editor1.putString("longitude",mLocationClient.getLastKnownLocation().getLongitude()+"");
//                    editor1.putString("latitude",mLocationClient.getLastKnownLocation().getLatitude()+"");
//                    editor1.commit();

//                    //刷新电话卡页面
//                    EventBus.getDefault().post(
//                            new ThirteenEvent("OK"));

                }else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError","location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                }
            }

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolOpenCarmer();


//        //同时请求多个权限
//        RxPermissions.getInstance(MainActivity.this)
//                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                        Manifest.permission.READ_EXTERNAL_STORAGE,
//                        Manifest.permission.ACCESS_FINE_LOCATION ,
//                        Manifest.permission.ACCESS_COARSE_LOCATION
//                )//多个权限用","隔开
//                .subscribe(new Action1<Boolean>() {
//                    @Override
//                    public void call(Boolean aBoolean) {
//                        if (aBoolean) {
//                            //当所有权限都允许之后，返回true
//                            Log.i("permissions", "btn_more_sametime：" + aBoolean);
//                        } else {
//                            //只要有一个权限禁止，返回false，
//                            //下一次申请只申请没通过申请的权限
//                            Log.i("permissions", "btn_more_sametime：" + aBoolean);
//                        }
//                    }
//                });
//        //注册EventBus
//        EventBus.getDefault().register(this);
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //保存经纬度
        SharedPreferences sp1 = getSharedPreferences("flag",MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sp1.edit();
        Log.i("onCreate: ", mLocationClient.getLastKnownLocation().getLongitude()+"");
        editor1.putString("longitude",mLocationClient.getLastKnownLocation().getLongitude()+"");
        editor1.putString("latitude",mLocationClient.getLastKnownLocation().getLatitude()+"");
        editor1.commit();

//        //传当前位置经度和纬度
//        EventBus.getDefault().post(
//                new ThirteenEvent(mLocationClient.getLastKnownLocation().getLongitude()+""));
//        EventBus.getDefault().post(
//                new FourteenEvent(mLocationClient.getLastKnownLocation().getLatitude()+""));


        Bundle bundle = new Bundle();
        //mLocationClient.getLastKnownLocation().getCity()可以得到当前定位的城市
        bundle.putString("city",mLocationClient.getLastKnownLocation().getCity());

//        bundle.putString("longitude",mLocationClient.getLastKnownLocation().getLongitude()+"");
//        bundle.putString("latitude",mLocationClient.getLastKnownLocation().getLatitude()+"");
        fragment1.setArguments(bundle);
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.main_fragment, fragment1).commit();
        mFragment = fragment1;

        initViews();
        mFragment = fragment1;


        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();

        //三种模式
        //仅用设备定位模式：不需要连接网络，只使用GPS进行定位，这种模式下不支持室内环境的定位，自 v2.9.0 版本支持返回地址描述信息。
//        //设置定位模式为AMapLocationMode.Device_Sensors，仅设备模式。
//        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Device_Sensors);

        //低功耗定位模式：不会使用GPS和其他传感器，只会使用网络定位（Wi-Fi和基站定位）；
//        //设置定位模式为AMapLocationMode.Battery_Saving，低功耗模式。
//        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);

        //高精度定位模式：会同时使用网络定位和GPS定位，优先返回最高精度的定位结果，以及对应的地址描述信息。
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);


        //设置定位请求超时时间，默认为30秒。
        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(8000);

        //设置是否开启定位缓存机制：当开启定位缓存功能，在高精度模式和低功耗模式下进行的网络定位结果均会生成本地缓存，不区分单次定位还是连续定位。GPS定位结果不会被缓存。
//        //关闭缓存机制
//        mLocationOption.setLocationCacheEnable(false);

        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();



    }

    private void boolOpenCarmer(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED ||
//                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)   //可读
//                        != PackageManager.PERMISSION_GRANTED ||
//                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)  //可写
//                        != PackageManager.PERMISSION_GRANTED||
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    1);}
    }

    private void initViews() {
        String uid = CommonMethod.getUid(this);
        instance=this;
        navigationBar = (RadioGroup) findViewById(R.id.navigation_btn);
        btn1 = (RadioButton) findViewById(R.id.btn1);
        btn2 = (RadioButton) findViewById(R.id.btn2);
        btn3 = (RadioButton) findViewById(R.id.btn3);
        btn4 = (RadioButton) findViewById(R.id.btn4);
        navigationBar.setOnCheckedChangeListener(this);

        Resources res = this.getResources();
        Drawable myImage = res.getDrawable(R.mipmap.ic_launcher);
        myImage.setBounds(1, 1, 48, 45);
        btn1.setCompoundDrawables(null, myImage, null, null);
        btn2.setCompoundDrawables(null, myImage, null, null);
        btn3.setCompoundDrawables(null, myImage, null, null);
        btn4.setCompoundDrawables(null, myImage, null, null);

//        fragment1 = new HomeTabFragment();
        fragment2 = new MerchantFragment();
        fragment3 = new ShoppingCartFragment();
        fragment4 = new MineFragment();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.btn1:
                btn1.setChecked(true);
                btn2.setChecked(false);
                btn3.setChecked(false);
                btn4.setChecked(false);
                switchFragment(fragment1);
                break;
            case R.id.btn2:
                btn1.setChecked(false);
                btn2.setChecked(true);
                btn3.setChecked(false);
                btn4.setChecked(false);
                switchFragment(fragment2);

                break;
            case R.id.btn3:
                btn1.setChecked(false);
                btn2.setChecked(false);
                btn3.setChecked(true);
                btn4.setChecked(false);
                switchFragment(fragment3);

                break;
            case R.id.btn4:
                btn1.setChecked(false);
                btn2.setChecked(false);
                btn3.setChecked(false);
                btn4.setChecked(true);
                switchFragment(fragment4);

                break;
        }
    }

    private void switchFragment(Fragment fragment) {
        //判断当前显示的Fragment是不是切换的Fragment
        if (mFragment != fragment) {
            //判断切换的Fragment是否已经添加过
            if (!fragment.isAdded()) {
                //如果没有，则先把当前的Fragment隐藏，把切换的Fragment添加上
                getSupportFragmentManager().beginTransaction().hide(mFragment)
                        .add(R.id.main_fragment, fragment).commit();
            } else {
                //如果已经添加过，则先把当前的Fragment隐藏，把切换的Fragment显示出来
                getSupportFragmentManager().beginTransaction().hide(mFragment).show(fragment).commit();
            }
            mFragment = fragment;
        }
    }

//    private void CheckPermission() {
//        if (!Settings.System.canWrite(this)) {
//            Toast.makeText(instance, "请在该设置页面勾选，才可以使用路况提醒功能", Toast.LENGTH_SHORT).show();
//
//            Uri selfPackageUri = Uri.parse("package:"
//                    +getPackageName());
//            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS,
//                    selfPackageUri);
//            startActivity(intent);
//        }
//    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment1 == null && fragment instanceof HomeTabFragment){
            fragment1 = fragment;
        } else if (fragment2 == null && fragment instanceof MerchantFragment){
            fragment2 = fragment;
        }else if (fragment3 == null && fragment instanceof ShoppingCartFragment){
            fragment3 = fragment;
        }else if (fragment4 == null && fragment instanceof MineFragment){
            fragment4 = fragment;
        }
    }

    @Override
    protected void onStop() {
        mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
        super.onStop();
    }

    //实现再按一次退出
    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            // ToastUtil.makeToastInBottom("再按一次退出应用", MainMyselfActivity);
            Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
            return;
        }
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
//        EventBus.getDefault().unregister(this);//反注册EventBus
    }
}
