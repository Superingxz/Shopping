package com.dl7.shopping.module.activity.home.getwater;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.dl7.shopping.R;
import com.dl7.shopping.module.base.BaseActivity;
import com.dl7.shopping.utils.FontManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 取水
 * Created by MC.Zeng on 2017-07-04.
 */

public class GetWaterActivity extends BaseActivity<GetWaterPresenter>
        implements IGetWaterView {

    @BindView(R.id.tv_getwater_back)
    TextView back;
    @BindView(R.id.elv_water)
    ExpandableListView expandableListView;
    private Typeface iconFont;
    private List<String> groupArray;//组列表
    private List<List<String>> childArray;//子列表
    private boolean IsFlag;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_getwater;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        //使用Font Awesome
        iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);

        back.setTypeface(iconFont);
        expandableListView.setGroupIndicator(null);
        groupArray = new ArrayList<String>();
        childArray = new ArrayList<List<String>>();

        groupArray.add("移动开发");
        groupArray.add("语言");
        groupArray.add("移动开发");
        groupArray.add("移动开发");
        groupArray.add("移动开发");
        List<String> arrayList = new ArrayList<String>();
        arrayList.add("Android");
        arrayList.add("IOS");
        arrayList.add("Windows Phone");

        //组循环
        for (int index = 0; index < groupArray.size(); ++index) {
            childArray.add(arrayList);
        }

        expandableListView.setAdapter(new ExpandableListViewaAdapter(GetWaterActivity.this));
        //设置默认expandableListView全部展开
        for (int i = 0; i < new ExpandableListViewaAdapter(GetWaterActivity.this).getGroupCount(); i++) {
            expandableListView.expandGroup(i);
        }


        //设置ExpandableListView是否可以展开，返回false可以展开，true位不展开
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {


                return true;
            }
        });

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getwater);
        ButterKnife.bind(this);

    }

    private void addInfo(String group, String[] child) {

        groupArray.add(group);

        List<String> childItem = new ArrayList<String>();

        for (int index = 0; index < child.length; index++) {
            childItem.add(child[index]);
        }
        childArray.add(childItem);
    }

    @Override
    public void finishRefresh() {

    }

    class ExpandableListViewaAdapter extends BaseExpandableListAdapter {
        Activity activity;

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
            convertView = LayoutInflater.from(GetWaterActivity.this).inflate(R.layout.item_item_getwater, parent, false);
            String day = childArray.get(groupPosition).get(childPosition);
            TextView itemInfo = (TextView) convertView.findViewById(R.id.tv_getwater_item_info);
            itemInfo.setText(day);
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
            String month = groupArray.get(groupPosition);
            convertView = LayoutInflater.from(GetWaterActivity.this).inflate(R.layout.item_activity_love_month, parent, false);
            TextView loveMonth = (TextView) convertView.findViewById(R.id.tv_love_month);
            loveMonth.setText(month);
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
