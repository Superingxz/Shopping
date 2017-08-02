package com.dl7.shopping.module.fragment;

/**
 * 商品详情上部listview部分
 * Created by MC.Zeng on 2017-07-21.
 */

import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dl7.shopping.R;
import com.dl7.shopping.api.URL;
import com.dl7.shopping.bean.GoodsDetailBean;
import com.dl7.shopping.module.base.BaseFragment;
import com.dl7.shopping.utils.ColorUtil;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.widget.vertical.VerticalListView;
import com.stx.xhb.xbanner.XBanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DetailTopListViewFragment extends BaseFragment implements XBanner.XBannerAdapter{
    @BindView(R.id.listView)
    VerticalListView listview;
    private XBanner mBannerNet;
    private GoodsDetailBean goodsDetailBean=new GoodsDetailBean();
    private String goods_id;
    private TextView title;
    private TextView introduce;
    private TextView money;
    private ArrayList<GoodsDetailBean.DataBean.ImagesBean> imgBean;
    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_listview;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        LayoutInflater mflater = LayoutInflater.from(getContext());
        View headerView  = (RelativeLayout) mflater.inflate(R.layout.fragment_details_top, null);
        ImageView back = (ImageView) headerView.findViewById(R.id.img_details_back);
        mBannerNet = (XBanner) headerView.findViewById(R.id.details_banner_1);
        title = (TextView)headerView.findViewById(R.id.tv_details_title);
        introduce = (TextView) headerView.findViewById(R.id.tv_details_introduce);
        money = (TextView) headerView.findViewById(R.id.tv_details_money);
        SharedPreferences sp = getActivity().getSharedPreferences("flag", getActivity().MODE_PRIVATE);
        goods_id = sp.getString("goods_id", "");
        initData();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        listview.addHeaderView(headerView);
//        listview.addHeaderView(textView2);
//        listview.addFooterView(textView3);

        listview.setAdapter(new MyAdapter());
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    private void initData() {
        OkGo.<String>post(URL.GOODSDETAIL_URL)
                .params("goods_id", goods_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body().toString();
                        Log.i("onSuccess: ", json);
                        try {
                            JSONObject j1=new JSONObject(json);
                            Gson gson=new Gson();
                            GoodsDetailBean goodsDetailBean = gson.fromJson(json, GoodsDetailBean.class);
                            JSONObject data = j1.getJSONObject("data");
                            imgBean = new ArrayList<GoodsDetailBean.DataBean.ImagesBean>();
                            title.setText(data.getString("goods_name")+data.getString("specification"));
                            money.setText("¥    "+data.getInt("present_price"));
                            goodsDetailBean.getData().setGoods_name(data.getString("goods_name"));
                            goodsDetailBean.getData().setSpecification(data.getString("specification"));
                            goodsDetailBean.getData().setPresent_price(data.getInt("present_price"));
//                            title.setText(data.getString("goods_name")+data.getString("specification"));
//                            money.setText("¥   "+data.getString("present_price"));

                            JSONArray images = data.getJSONArray("images");
                            for (int i = 0; i < images.length(); i++) {
                                JSONObject imgObject = images.getJSONObject(i);
                                goodsDetailBean.getData().getImages().get(i).setImage_url(imgObject.getString("image_url"));

                                imgBean.add(goodsDetailBean.getData().getImages().get(i));
                            }
                            Log.i("onSuccess: ", imgBean.size()+"" );
                            initNetBanner(imgBean);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void goTop() {
        listview.goTop();
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

    private class MyAdapter extends BaseAdapter {

        private List<String> strings;

        public MyAdapter() {
            strings = new ArrayList<>();

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (null == convertView) {
                convertView = View.inflate(getActivity(), android.R.layout.simple_list_item_1, null);
            }
            TextView textView = (TextView) convertView;
            textView.setText(getItem(position));
            textView.setTextColor(Color.WHITE);
            textView.setBackgroundColor(ColorUtil.generateBeautifulColor());
            return convertView;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public String getItem(int position) {
            return strings.get(position);
        }

        @Override
        public int getCount() {
            return strings.size();
        }
    }

    private void initNetBanner(List<GoodsDetailBean.DataBean.ImagesBean> imagesBeen) {
        mBannerNet.setData(imagesBeen,null);

        mBannerNet.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, int position) {
                Toast.makeText(getContext(), "点击了第"+position+"图片", Toast.LENGTH_SHORT).show();
            }
        });
        mBannerNet.setmAdapter(this);
    }

    @Override
    public void loadBanner(XBanner banner, Object model, View view, int position) {
        Glide.with(this).load("http://shop-img.agymall.com/water/big/"+imgBean.get(position).getImage_url()).into((ImageView) view);
    }
}