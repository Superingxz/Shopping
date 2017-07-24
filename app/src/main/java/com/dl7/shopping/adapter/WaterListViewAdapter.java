package com.dl7.shopping.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dl7.shopping.R;
import com.dl7.shopping.bean.WaterBean;
import com.dl7.shopping.utils.FontManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MC.Zeng on 2017-07-03.
 */

public class WaterListViewAdapter extends BaseAdapter implements View.OnClickListener{

    private Context context;
    private int[] img = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private List<int[]> lists = new ArrayList<>();
    private Typeface iconFont;
    private TextView shoppingimg1;
    private TextView shoppingimg2;
    private TextView shoppingimg3;
    private ImageView product1;
    private ImageView product2;
    private ImageView product3;

    private Callback mCallback;
    private HorizontalScrollView scrollView;
    private LinearLayout scrollViewItem;
    private List<WaterBean.DataBean> dataBean;
    private List<WaterBean.DataBean.WATERABean> wateraBean;
    private List<WaterBean.DataBean.WATERBBean> waterbBean;
    private List<WaterBean.DataBean.WATERCBean> watercBean;
    private List<WaterBean.DataBean.WATERDBean> waterdBean;

    public interface Callback {
        void click(View v);
    }

    public WaterListViewAdapter(List<WaterBean.DataBean> dataBean, Context context, Callback callback) {
        this.dataBean=dataBean;
        this.context = context;
        mCallback = callback;
    }


    @Override
    public int getCount() {
        Log.i("getCount: ", dataBean.size()+"");
        return dataBean.size();
    }

    @Override
    public Object getItem(int position) {
        return dataBean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        iconFont = FontManager.getTypeface(context, FontManager.FONTAWESOME);
//        ViewHolder viewHolder=new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_water_liseview, parent, false);

            TextView category_name = (TextView) convertView.findViewById(R.id.tv_item_water_category_name);

            //得到scroview里面的布局，然后替换掉里面的布局
            LinearLayout itemScroViewll = (LinearLayout) convertView.findViewById(R.id.ll_item_water_scroview);
            LayoutInflater mflater = LayoutInflater.from(context);

            //先移除之前在scroview里面LinearLayout的布局
            itemScroViewll.removeAllViews();

//            itemScroViewll.addView(infoView1);

            //            itemScroViewll.addView(infoView1);
            Log.i("getView: ","4111");
            if (position==0){
                for(int i =0 ;i<dataBean.get(position).getWATER_A().size();i++){
                    Log.i("getView: ",dataBean.get(position).getWATER_A().size()+"");
                    //得到需要添加进去的布局
                    View infoView  = (LinearLayout) mflater.inflate(R.layout.item_scrollview_item_water, null);
                    TextView icon = (TextView) infoView.findViewById(R.id.shopping_img);
                    icon.setTypeface(iconFont);
                    ImageView img = (ImageView) infoView.findViewById(R.id.img_water_product);
                    Glide.with(context).load("http://shop-img.agymall.com/water/small/"+dataBean.get(position).getWATER_A().get(i).getImage_url()).into(img);
                    TextView goodsName = (TextView) infoView.findViewById(R.id.tv_item_water_goods_name);
                    goodsName.setText(dataBean.get(position).getWATER_A().get(i).getGoods_name());
                    TextView company = (TextView) infoView.findViewById(R.id.tv_item_water_company);
                    company.setText(dataBean.get(position).getWATER_A().get(i).getSpecification()+"/"+dataBean.get(position).getWATER_A().get(i).getCompany());
                    TextView money = (TextView) infoView.findViewById(R.id.tv_item_water_money);
                    money.setText("¥  "+dataBean.get(position).getWATER_A().get(i).getPresent_price());
                    category_name.setText(dataBean.get(position).getWATER_A().get(i).getCategory_name());
                    //添加进去
                    itemScroViewll.addView(infoView);
                }
            }else if (position==1) {
                for (int i = 0; i < dataBean.get(position).getWATER_B().size(); i++) {
                    //得到需要添加进去的布局
                    View infoView = (LinearLayout) mflater.inflate(R.layout.item_scrollview_item_water, null);
                    TextView icon = (TextView) infoView.findViewById(R.id.shopping_img);
                    icon.setTypeface(iconFont);
                    ImageView img = (ImageView) infoView.findViewById(R.id.img_water_product);
                    Glide.with(context).load("http://shop-img.agymall.com/water/small/" + dataBean.get(position).getWATER_B().get(i).getImage_url()).into(img);
                    TextView goodsName = (TextView) infoView.findViewById(R.id.tv_item_water_goods_name);
                    goodsName.setText(dataBean.get(position).getWATER_B().get(i).getGoods_name());
                    TextView company = (TextView) infoView.findViewById(R.id.tv_item_water_company);
                    company.setText(dataBean.get(position).getWATER_B().get(i).getSpecification() + "/" + dataBean.get(position).getWATER_B().get(i).getCompany());
                    TextView money = (TextView) infoView.findViewById(R.id.tv_item_water_money);
                    money.setText("¥  " + dataBean.get(position).getWATER_B().get(i).getPresent_price());
                    category_name.setText(dataBean.get(position).getWATER_B().get(i).getCategory_name());

                    //添加进去
                    itemScroViewll.addView(infoView);
                }
            }else if (position==2) {
                for (int i = 0; i < dataBean.get(position).getWATER_C().size(); i++) {
                    //得到需要添加进去的布局
                    View infoView = (LinearLayout) mflater.inflate(R.layout.item_scrollview_item_water, null);
                    TextView icon = (TextView) infoView.findViewById(R.id.shopping_img);
                    icon.setTypeface(iconFont);
                    ImageView img = (ImageView) infoView.findViewById(R.id.img_water_product);
                    Glide.with(context).load("http://shop-img.agymall.com/water/small/" + dataBean.get(position).getWATER_C().get(i).getImage_url()).into(img);
                    TextView goodsName = (TextView) infoView.findViewById(R.id.tv_item_water_goods_name);
                    goodsName.setText(dataBean.get(position).getWATER_C().get(i).getGoods_name());
                    TextView company = (TextView) infoView.findViewById(R.id.tv_item_water_company);
                    company.setText(dataBean.get(position).getWATER_C().get(i).getSpecification() + "/" + dataBean.get(position).getWATER_C().get(i).getCompany());
                    TextView money = (TextView) infoView.findViewById(R.id.tv_item_water_money);
                    money.setText("¥  " + dataBean.get(position).getWATER_C().get(i).getPresent_price());
                    category_name.setText(dataBean.get(position).getWATER_C().get(i).getCategory_name());

                    //添加进去
                    itemScroViewll.addView(infoView);
                }
            }else if (position==3) {
                for (int i = 0; i < dataBean.get(position).getWATER_D().size(); i++) {
                    //得到需要添加进去的布局
                    View infoView = (LinearLayout) mflater.inflate(R.layout.item_scrollview_item_water, null);
                    TextView icon = (TextView) infoView.findViewById(R.id.shopping_img);
                    icon.setTypeface(iconFont);
                    ImageView img = (ImageView) infoView.findViewById(R.id.img_water_product);
                    Glide.with(context).load("http://shop-img.agymall.com/water/small/" + dataBean.get(position).getWATER_D().get(i).getImage_url()).into(img);
                    TextView goodsName = (TextView) infoView.findViewById(R.id.tv_item_water_goods_name);
                    goodsName.setText(dataBean.get(position).getWATER_D().get(i).getGoods_name());
                    TextView company = (TextView) infoView.findViewById(R.id.tv_item_water_company);
                    company.setText(dataBean.get(position).getWATER_D().get(i).getSpecification() + "/" + dataBean.get(position).getWATER_D().get(i).getCompany());
                    TextView money = (TextView) infoView.findViewById(R.id.tv_item_water_money);
                    money.setText("¥  " + dataBean.get(position).getWATER_D().get(i).getPresent_price());
                    category_name.setText(dataBean.get(position).getWATER_D().get(i).getCategory_name());

                    //添加进去
                    itemScroViewll.addView(infoView);
                }
            }




        }
        return convertView;
    }


    public void onClick(View v) {
        mCallback.click(v);
    }
}
