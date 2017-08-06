package com.dl7.shopping.module.activity.home.payment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.dl7.shopping.R;
import com.dl7.shopping.adapter.PaymentListAdapter;
import com.dl7.shopping.api.URL;
import com.dl7.shopping.bean.PaymentBean;
import com.dl7.shopping.bean.SchoolTimeBean;
import com.dl7.shopping.module.activity.home.WaterOrderActivity;
import com.dl7.shopping.module.activity.mysetting.address.AddressMessageActivity;
import com.dl7.shopping.module.base.BaseActivity;
import com.dl7.shopping.rxbus.event.EighteenEvent;
import com.dl7.shopping.rxbus.event.SeventhEvent;
import com.dl7.shopping.rxbus.event.SixEvent;
import com.dl7.shopping.utils.CommonMethod;
import com.dl7.shopping.utils.FontManager;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * 下单页面
 * Created by MC.Zeng on 2017-07-08.
 */

public class PaymentActivity extends BaseActivity<PaymentPresenter> implements IPaymentView {
    @BindView(R.id.tv_payment_back)
    TextView back;
    @BindView(R.id.lv_payment)
    ListView listView;
    @BindView(R.id.rl_payment_time)
    RelativeLayout rlPaymentTime;
    @BindView(R.id.tv_payment_all_circle)
    TextView allSelecticon;
    @BindView(R.id.tv_payment_order)
    TextView order;
    @BindView(R.id.rl_payment_address)
    RelativeLayout rl_paymentAddress;
    @BindView(R.id.tv_payment_address)
    TextView tvAddress;
    @BindView(R.id.tv_payment_total_num)
    TextView allNum;
    @BindView(R.id.tv_payment_time)
    TextView tvTime;

    private Typeface iconFont;
    private List<String> groupArray = new ArrayList<String>();
    ;//组列表
    private List<List<String>> childArray = new ArrayList<List<String>>();//子列表

    private String uid;
    private List<PaymentBean.DataBean.WaterGroupBean> list = new ArrayList<>();
    private List<PaymentBean.DataBean> mList = new ArrayList<>();
    private List<String> arrayList = new ArrayList<>();
    private PaymentListAdapter adapter;
    private View convertView;
    private String msg;
    private String addressId="";
    private String storeType="";
    private List<SchoolTimeBean.DataBean> timeList;
    private OptionsPickerView.Builder pvOptions;
    private OptionsPickerView pvNoLinkOptions;
    private List<String> dayArray=new ArrayList<>();
    private List<List<String>> timeArray=new ArrayList<>();
    private List<List<String>> sort=new ArrayList<>();
    private ArrayList<String> day = new ArrayList<>();
    private ArrayList<String> daytime = new ArrayList<>();
    private List<SchoolTimeBean.DataBean.TimeDataBean> timeDataList=new ArrayList<>();
    private String url;
    private String reserve_sort;
    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_payment;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        uid = CommonMethod.getUid(this);
        //使用Font Awesome
        iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
        back.setTypeface(iconFont);
        allSelecticon.setTypeface(iconFont);

        EventBus.getDefault().register(this);

        adapter = new PaymentListAdapter(mList, this, this);
        timeList=new ArrayList<>();
        //收货地址
        rl_paymentAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PaymentActivity.this, AddressMessageActivity.class);
                intent.putExtra("isSelect","true");
                startActivity(intent);
            }
        });

        //提交订单
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!addressId.equals("")) {
                    int k = 0;
                    int q = 0;
                    for (int i = 0; i < mList.size(); i++) {
                        for (int j = 0; j < mList.get(i).getWater_group().size(); j++) {
                            if (mList.get(i).getWater_group().get(j).isIs_check()) {//判断是否有商品被选择
                                k++;
                                q = k;
                            }
                        }
                    }

                    if (q > 1) {
                        Toast.makeText(PaymentActivity.this, "暂时不支持多种商品一起购买", Toast.LENGTH_SHORT).show();
                    } else if (q == 0) {
                        Toast.makeText(PaymentActivity.this, "请至少选择一种商品", Toast.LENGTH_SHORT).show();
                    } else if (q == 1) {
                        for (int i = 0; i < mList.size(); i++) {
                            for (int j = 0; j < mList.get(i).getWater_group().size(); j++) {
                                if (mList.get(i).getWater_group().get(j).isIs_check()) {
                                    String goods_id = mList.get(i).getWater_group().get(j).getGoods_id();

                                    int allnum = Integer.parseInt(allNum.getText().toString());//数量
                                    Intent intent = new Intent(PaymentActivity.this, WaterOrderActivity.class);
                                    intent.putExtra("business_type1","BUY_WATER");
                                    intent.putExtra("business_type","WATER_PAY_CONFIRM");
                                    intent.putExtra("goodsId", goods_id);
                                    intent.putExtra("allNum", allnum + "");
                                    intent.putExtra("addressId", addressId);
                                    intent.putExtra("time",tvTime.getText().toString());
                                    intent.putExtra("reserve_sort",reserve_sort);
                                    intent.putExtra("money","0");
                                    intent.putExtra("playmethod","water");
                                    intent.putExtra("bucket","");
                                    intent.putExtra("store_id",mList.get(i).getWater_group().get(j).getStore_id());
                                    startActivity(intent);
                                }
                            }
                        }
                    }
                } else {
                    Toast.makeText(PaymentActivity.this, "请选择收货地址", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //配送时间点击
        rlPaymentTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (storeType.equals("")){
                    Toast.makeText(PaymentActivity.this, "请先选择收货地址", Toast.LENGTH_SHORT).show();
                }else{
                    url = "";
                    if (storeType.equals("SCHOOL")){
                        url=URL.SCHOOLTIME_URL;
                    }else{
                        url=URL.COMMONTIME_URL;
                    }
                    initSchoolData();
                }
            }
        });

        //配送时间点击
        rlPaymentTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OkGo.<String>post(URL.SCHOOLTIME_URL)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {

                            }
                        });

//                //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
//                //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
//                Calendar selectedDate = Calendar.getInstance();
//                Calendar startDate = Calendar.getInstance();
//                startDate.set(2017, 0, 1);//选择开始时间
//                Calendar endDate = Calendar.getInstance();
//                endDate.set(2019, 11, 28);//选择最大选择时间，，，月份是从0开始计算所以只有0-11月
//                //时间选择器
//                TimePickerView pvTime = new TimePickerView.Builder(PaymentActivity.this, new TimePickerView.OnTimeSelectListener() {
//                    @RequiresApi(api = Build.VERSION_CODES.N)
//                    @Override
//                    public void onTimeSelect(Date date, View v) {//选中事件回调
//                        tvTime.setText(getTime(date));
//                    }
//                })//年月日时分秒 的显示与否，不设置则默认全部显示
//                        .setType(new boolean[]{true, true, true, true, true, false})
//                        .setLabel("年", "月", "日", "点", "分", "")
//                        .isCenterLabel(false)
//                        .setDividerColor(Color.DKGRAY)
//                        .setContentSize(21)
//                        .setDate(selectedDate)
//                        .setRangDate(startDate, endDate)
//                        .setBackgroundId(0x00FFFFFF) //设置外部遮罩颜色
//                        .setDecorView(null)
//                        .build();
//
////                pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
//                pvTime.show();

            }
        });
//        //设置箭头图标
//        expandableListView.setGroupIndicator(null);
//        //设置ExpandableListView是否可以展开，返回false可以展开，true位不展开
//        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//            @Override
//            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
//                return true;
//            }
//        });

        listView.setAdapter(adapter);
//        expandableListView.setAdapter(new ExpandableListViewaAdapter(PaymentActivity.this));
        initData();

//        //设置默认expandableListView全部展开
//        for(int i = 0; i < new ExpandableListViewaAdapter(PaymentActivity.this) .getGroupCount(); i++){
//            expandableListView.expandGroup(i);
//        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //获取数据
    private void initData() {
        OkGo.<String>post(URL.BRANDWATER_URL)
                .params("member_id", uid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body().toString();
                        Log.i("onSuccess: ", json);
                        Gson gson = new Gson();
                        PaymentBean paymentBean = gson.fromJson(json, PaymentBean.class);
                        mList.addAll(paymentBean.getData());
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    private void initOptionPicker() {// 弹出选择器

        OptionsPickerView  pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String str = dayArray.get(options1)+" "+
                        timeArray.get(options1).get(options2);
                tvTime.setText(str);
                reserve_sort = sort.get(options1).get(options2);
            }
        })

                .setTitleText("配送时间")
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.GREEN)//设置分割线的颜色
                .setSelectOptions(0, 0)//默认选中项
                .setTitleColor(Color.LTGRAY)
                .setCancelColor(Color.parseColor("#35bb8a"))//取消按钮颜色
                .setSubmitColor(Color.parseColor("#35bb8a"))//确定按钮颜色
                .setTextColorCenter(Color.parseColor("#35bb8a"))
                .isCenterLabel(true) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setBackgroundId(0x66000000) //设置外部遮罩颜色
                .build();


        pvOptions.setPicker(dayArray, timeArray);//二级选择器
        pvOptions.show();
    }
    //private void initOptionPicker() {// 不联动的多级选项
//    pvNoLinkOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
//        @Override
//        public void onOptionsSelect(int options1, int options2, int options3, View v) {
//
//            String str = timeList.get(options1).getTodayDay()+" "
//                    +timeList.get(options1).getTimeData().get(options2).getHour();//如果有第三级就后面继续添加
//            tvTime.setText(str);
//        }
//    })
//                .setTitleText("配送时间")
//                .setContentTextSize(20)//设置滚轮文字大小
//                .setDividerColor(Color.GREEN)//设置分割线的颜色
//                .setSelectOptions(0, 0)//默认选中项
////                .setBgColor(Color.BLACK)//背景颜色
////                .setTitleBgColor(Color.DKGRAY)//titlebar的颜色
//                .setTitleColor(Color.LTGRAY)
//                .setCancelColor(Color.parseColor("#35bb8a"))//取消按钮颜色
//                .setSubmitColor(Color.parseColor("#35bb8a"))//确定按钮颜色
//                .setTextColorCenter(Color.parseColor("#35bb8a"))
//                .isCenterLabel(true) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
//                .setBackgroundId(0x66000000) //设置外部遮罩颜色
//                .build();
//
//
//    //清楚原有数据
//    day.clear();
//    daytime.clear();
//    for (int i = 0; i < timeList.size(); i++) {
//        day.add(timeList.get(i).getTodayDay());
//        for (int j = 0; j < timeList.get(i).getTimeData().size(); j++) {
//            daytime.add(timeList.get(i).getTimeData().get(j).getHour());
//        }
//
//    }
//    pvNoLinkOptions.setNPicker(day, daytime, null);//没有第三级，所以为null
//    pvNoLinkOptions.show();
//}
    //获取学校的配送时间
    private void initSchoolData() {

        OkGo.<String>post(url)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body().toString();
                        Log.i("onSuccess: ", json);

                        try {
                            JSONObject j1=new JSONObject(json);
                            List<String> dayBean=new ArrayList<String>();
                            List<List<String>> timeBean=new ArrayList<List<String>>();
                            List<List<String>> sortBean=new ArrayList<List<String>>();

                            JSONArray data = j1.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject dataObj = data.getJSONObject(i);
                                dayBean.add(dataObj.getString("todayDay"));

                                List<String> timeArray=new ArrayList<String>();
                                List<String> sortArray=new ArrayList<String>();

                                JSONArray timeData = dataObj.getJSONArray("timeData");
                                for (int j = 0; j < timeData.length(); j++) {
                                    JSONObject timeObj = timeData.getJSONObject(j);
                                    timeArray.add(timeObj.getString("hour"));
                                    sortArray.add(timeObj.getInt("srot")+"");
                                }
                                timeBean.add(timeArray);
                                sortBean.add(sortArray);
                            }
                            dayArray.clear();
                            sort.addAll(sortBean);
                            timeArray.addAll(timeBean);
                            dayArray.addAll(dayBean);

                            initOptionPicker();//弹出滚动时间选择器
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
//    //获取学校的配送时间
//    private void initSchoolData() {
//        OkGo.<String>post(URL.SCHOOLTIME_URL)
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(Response<String> response) {
//                        String json = response.body().toString();
//                        Log.i("onSuccess: ", json);
//
//                        try {
//                            Gson gson=new Gson();
//                            SchoolTimeBean schoolTimeBean = gson.fromJson(json, SchoolTimeBean.class);
//                            List<SchoolTimeBean.DataBean> dataBeen=new ArrayList<SchoolTimeBean.DataBean>();
//                            List<SchoolTimeBean.DataBean.TimeDataBean> timeDataBeen=new ArrayList<SchoolTimeBean.DataBean.TimeDataBean>();
//                            JSONObject j1=new JSONObject(json);
//                            JSONArray data = j1.getJSONArray("data");
//                            for (int i = 0; i < data.length(); i++) {
//                                JSONObject dataObj = data.getJSONObject(i);
//                                schoolTimeBean.getData().get(i).setTodayDay(dataObj.getString("todayDay"));
//                                JSONArray timeData = dataObj.getJSONArray("timeData");
//                                for (int j = 0; j < timeData.length(); j++) {
//                                    JSONObject timeDataObj = timeData.getJSONObject(j);
//                                    schoolTimeBean.getData().get(i).getTimeData().get(j).setHour(timeDataObj.getString("hour"));
//                                    schoolTimeBean.getData().get(i).getTimeData().get(j).setSrot(timeDataObj.getInt("srot"));
//
//                                    timeDataBeen.add(schoolTimeBean.getData().get(i).getTimeData().get(j));
//                                }
//                                timeDataList.addAll(timeDataBeen);
//                                dataBeen.add(schoolTimeBean.getData().get(i));
//                            }
//                            timeList.clear();
//                            timeList.addAll(dataBeen);
//
//                            initOptionPicker();//弹出滚动时间选择器
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//    }

    @Subscribe
    public void onEventMainThread(SixEvent event) {

        msg = event.getMsg();
        Log.i("onEventMainThread: ", msg);
        tvAddress.setText(msg);
    }
    @Subscribe
    public void onEventMainThread(SeventhEvent event) {

        msg = event.getMsg();
        Log.i("onEventMainThread: ", msg);
        addressId=msg;
    }
    @Subscribe
    public void onEventMainThread(EighteenEvent event) {

        msg = event.getMsg();
        Log.i("onEventMainThread: ", msg);
        storeType=msg;
    }


    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    @Override
    public void finishRefresh() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消注册
        EventBus.getDefault().unregister(this);
    }

    class ExpandableListViewaAdapter extends BaseExpandableListAdapter {
        Activity activity;

        //        private int number=1;
        public ExpandableListViewaAdapter(Activity a) {
            activity = a;
        }

        /*第二级Child */
        @Override
        public Object getChild(int groupPosition, int childPosition) {
            // TODO Auto-generated method stub
            return childArray.get(groupPosition).get(childPosition);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            // TODO Auto-generated method stub
            return childPosition;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {

            convertView = LayoutInflater.from(PaymentActivity.this).inflate(R.layout.item_item_payment_product, parent, false);
            String name = childArray.get(groupPosition).get(childPosition);
            TextView itemName = (TextView) convertView.findViewById(R.id.tv_payment_name_item);
            itemName.setText(name);
            TextView checkBox = (TextView) convertView.findViewById(R.id.tv_check_box_item_item);
            checkBox.setTypeface(iconFont);
            final TextView num = (TextView) convertView.findViewById(R.id.tv_payment_num);
            ImageView subtract = (ImageView) convertView.findViewById(R.id.img_payment_subtract);
            ImageView add = (ImageView) convertView.findViewById(R.id.img_payment_add);
            //减少数量
            subtract.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //将得到的num的数据String转化为int
                    int number = Integer.parseInt(num.getText().toString());
                    if (number > 1) {
                        number--;
                        num.setText(number + "");

                    } else {
                        Toast.makeText(PaymentActivity.this, "至少一桶才能下单", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            //增加数量
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int number = Integer.parseInt(num.getText().toString());
                    number++;
                    num.setText(number + "");
                }
            });
            return convertView;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            // TODO Auto-generated method stub
            return childArray.get(groupPosition).size();
        }

        /*第一级Group */
        @Override
        public Object getGroup(int groupPosition) {
            // TODO Auto-generated method stub
            return getGroup(groupPosition);
        }

        @Override
        public int getGroupCount() {
            // TODO Auto-generated method stub
            return groupArray.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            // TODO Auto-generated method stub
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            String name = groupArray.get(groupPosition);
            convertView = LayoutInflater.from(PaymentActivity.this).inflate(R.layout.item_payment_product, parent, false);
            TextView productName = (TextView) convertView.findViewById(R.id.tv_payment_name);
            productName.setText(name);
            TextView checkBox = (TextView) convertView.findViewById(R.id.tv_check_box_item);
            checkBox.setTypeface(iconFont);
            return convertView;
        }

        @Override
        public boolean hasStableIds() {
       
            return false;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {

            return true;
        }
    }

}
