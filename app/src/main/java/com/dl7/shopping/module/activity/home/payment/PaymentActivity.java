package com.dl7.shopping.module.activity.home.payment;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.dl7.shopping.R;
import com.dl7.shopping.module.base.BaseActivity;
import com.dl7.shopping.utils.FontManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 下单页面
 * Created by MC.Zeng on 2017-07-08.
 */

public class PaymentActivity extends BaseActivity<PaymentPresentter> implements IPaymentView {
    @BindView(R.id.tv_payment_back)
    TextView back;
    @BindView(R.id.elv_payment)
    ExpandableListView expandableListView;
    @BindView(R.id.tv_payment_time)
    TextView tvTime;
    @BindView(R.id.rl_payment_time)
    RelativeLayout rlPaymentTime;
    @BindView(R.id.tv_payment_all_circle)
    TextView allSelecticon;
    private Typeface iconFont;
    private List<String> groupArray = new ArrayList<String>();
    ;//组列表
    private List<List<String>> childArray = new ArrayList<List<String>>();//子列表

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_payment;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        //使用Font Awesome
        iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);

        groupArray.add("广大");
        groupArray.add("屈臣氏");
        groupArray.add("广大");
        groupArray.add("瓶装水");
        groupArray.add("广大");
        List<String> arrayList = new ArrayList<String>();
        arrayList.add("广大桶装水");
        arrayList.add("广大矿泉水");
        arrayList.add("广大饮料");

        //组循环
        for (int index = 0; index < groupArray.size(); ++index) {
            childArray.add(arrayList);
        }


        back = (TextView) findViewById(R.id.tv_payment_back);
        back.setTypeface(iconFont);
        expandableListView = (ExpandableListView) findViewById(R.id.elv_payment);

        allSelecticon = (TextView) findViewById(R.id.tv_payment_all_circle);
        allSelecticon.setTypeface(iconFont);

        rlPaymentTime = (RelativeLayout) findViewById(R.id.rl_payment_time);
        tvTime = (TextView) findViewById(R.id.tv_payment_time);


        //配送时间点击
        rlPaymentTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
                //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
                Calendar selectedDate = Calendar.getInstance();
                Calendar startDate = Calendar.getInstance();
                startDate.set(2017, 0, 1);//选择开始时间
                Calendar endDate = Calendar.getInstance();
                endDate.set(2019, 11, 28);//选择最大选择时间，，，月份是从0开始计算所以只有0-11月
                //时间选择器
                TimePickerView pvTime = new TimePickerView.Builder(PaymentActivity.this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        tvTime.setText(getTime(date));
                    }
                })//年月日时分秒 的显示与否，不设置则默认全部显示
                        .setType(new boolean[]{true, true, true, true, true, false})
                        .setLabel("年", "月", "日", "点", "分", "")
                        .isCenterLabel(false)
                        .setDividerColor(Color.DKGRAY)
                        .setContentSize(21)
                        .setDate(selectedDate)
                        .setRangDate(startDate, endDate)
                        .setBackgroundId(0x00FFFFFF) //设置外部遮罩颜色
                        .setDecorView(null)
                        .build();

//                pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                pvTime.show();

            }
        });

        //设置箭头图标
        expandableListView.setGroupIndicator(null);
        //设置ExpandableListView是否可以展开，返回false可以展开，true位不展开
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });

        expandableListView.setAdapter(new ExpandableListViewaAdapter(PaymentActivity.this));
        //设置默认expandableListView全部展开
        for (int i = 0; i < new ExpandableListViewaAdapter(PaymentActivity.this).getGroupCount(); i++) {
            expandableListView.expandGroup(i);
        }

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

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    @Override
    public void finishRefresh() {

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
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            // TODO Auto-generated method stub
            return true;
        }
    }
}
