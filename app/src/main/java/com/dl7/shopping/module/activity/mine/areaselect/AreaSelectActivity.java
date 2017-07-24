package com.dl7.shopping.module.activity.mine.areaselect;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dl7.shopping.R;
import com.dl7.shopping.adapter.LeftListAdapter;
import com.dl7.shopping.adapter.TestSectionedAdapter;
import com.dl7.shopping.module.base.BaseActivity;
import com.dl7.shopping.widget.PinnedHeaderListView;

import butterknife.BindView;


/**
 * Created by MC.Zeng on 2017-07-04.
 */

public class AreaSelectActivity extends BaseActivity<AreaSelectPresenter> implements IAreaSelectView {
    @BindView(R.id.left_listview)
    ListView left_listView;
    @BindView(R.id.pinnedListView)
    PinnedHeaderListView right_listview;

    private boolean isScroll = false;

    TestSectionedAdapter sectionedAdapter;
    LeftListAdapter adapter;

    private int leftFirstItem = 0;
    private int leftLastItem = 0;


//    private String[] leftStr = new String[]{"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
//    private String[][] rightStr = new String[][]{
//            {"星期一  早餐", "星期一  午餐", "星期一  晚餐"}, {"星期二  早餐", "星期二  午餐", "星期二  晚餐"},
//            {"星期三  早餐", "星期三  午餐", "星期三  晚餐"}, {"星期四  早餐", "星期四  午餐", "星期四  晚餐"},
//            {"星期五  早餐", "星期五  午餐", "星期五  晚餐"}, {"星期六  早餐", "星期六  午餐", "星期六  晚餐"},
//            {"星期日  早餐", "星期日  午餐", "星期日  晚餐"}};

    private String[] leftStr = new String[]{"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期七", "星期八", "星期九", "星期十", "星期十一"};
    private String[][] rightStr = new String[][]{
            {"星期一  早餐", "星期一  午餐", "星期一  晚餐"}, {"星期二  早餐", "星期二  午餐", "星期二  晚餐"},
            {"星期三  早餐", "星期三  午餐", "星期三  晚餐"}, {"星期四  早餐", "星期四  午餐", "星期四  晚餐"},
            {"星期五  早餐", "星期五  午餐", "星期五  晚餐"}, {"星期六  早餐", "星期六  午餐", "星期六  晚餐"},
            {"星期七  早餐", "星期七  午餐", "星期七  晚餐"}, {"星期八  早餐", "星期八  午餐", "星期八  晚餐"},
            {"星期九  早餐", "星期九  午餐", "星期九  晚餐"}, {"星期十  早餐", "星期十  午餐", "星期十  晚餐"},
            {"星期十一  早餐", "星期十一  午餐", "星期十一  晚餐"}};


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_area_select;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        sectionedAdapter = new TestSectionedAdapter(this, leftStr, rightStr);
        right_listview.setAdapter(sectionedAdapter);

//        left_listView.setAdapter(new ArrayAdapter<String>(this,
//                android.R.layout.simple_expandable_list_item_1, leftStr));

        adapter = new LeftListAdapter(AreaSelectActivity.this.getApplicationContext(), leftStr);
        left_listView.setAdapter(adapter);

        left_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position,
                                    long arg3) {
                isScroll = false;

//                for (int i = 0; i < left_listView.getChildCount(); i++) {
//                    if (i == position) {
//                        left_listView.getChildAt(i).setBackgroundColor(Color.rgb(255, 255, 255));
//                    } else {
//                        left_listView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
//                    }
//                }

                adapter.setIndex(position);

                int rightSection = 0;
                for (int i = 0; i < position; i++) {
                    rightSection += sectionedAdapter.getCountForSection(i) + 1;
                }
                right_listview.setSelection(rightSection);
            }
        });

        left_listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                leftFirstItem = firstVisibleItem;
                leftLastItem = firstVisibleItem + visibleItemCount;
            }
        });

        right_listview.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView arg0, int arg1) {
                switch (arg1) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                        isScroll = true;
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                        isScroll = true;
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        isScroll = false;
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                if (isScroll) {
                    int index = sectionedAdapter.getSectionForPosition(firstVisibleItem);
                    if (index != adapter.getIndex()) {
                        adapter.setIndex(index);
                    }
                    if (index < leftFirstItem + 2) {
                        left_listView.setSelection(adapter.getIndex());
                    } else if (index > leftLastItem - 2) {
                        left_listView.setSelection(adapter.getIndex());
                    }
                }
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
