package com.dl7.shopping.module.activity.mysetting.previewimg;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dl7.shopping.R;
import com.dl7.shopping.adapter.PreViewGridAdapter;
import com.dl7.shopping.module.base.BaseActivity;
import com.dl7.shopping.widget.LineGridView;

import java.util.ArrayList;

import butterknife.BindView;
import me.iwf.photopicker.PhotoPreview;
import me.iwf.photopicker.event.PhotoOnLongClick;
import me.iwf.photopicker.event.PhotoOnLongClickManager;

/**
 * 图库选择图片
 * Created by MC.Zeng on 2017-07-06.
 */

public class PreViewImgActivity extends BaseActivity<PreViewImgPresenter>
        implements IPreViewImgView {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.nametv)
    TextView nametv;
    @BindView(R.id.img_grid)
    LineGridView img_grid;
    ArrayList<String> imgData = new ArrayList<>();
    private ArrayList<String> onLongClickListData = new ArrayList<>();

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_preview_img;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        onLongClickListData.add("分享");
        onLongClickListData.add("保存");
        onLongClickListData.add("取消");
        //图片长按后的item点击事件回调
        PhotoOnLongClickManager photoOnLongClickManager = PhotoOnLongClickManager.getInstance();
        photoOnLongClickManager.setOnLongClickListener(new PhotoOnLongClick() {
            @Override
            public void sendOnLongClick(int position, String path) {
                Toast.makeText(PreViewImgActivity.this, "你点击了：" + onLongClickListData.get(position) + "，图片路径：" + path, Toast.LENGTH_LONG).show();
            }
        });
        imgData.add("https://images-cn.ssl-images-amazon.com/images/I/61PI88GEqTL.jpg");
        imgData.add("https://images-cn.ssl-images-amazon.com/images/I/61m2kJWam5L.jpg");
        imgData.add("https://images-cn.ssl-images-amazon.com/images/I/61dQGK0xeuL.jpg");
        imgData.add("https://images-cn.ssl-images-amazon.com/images/I/71YuPpF6jKL.jpg");
        imgData.add("https://images-cn.ssl-images-amazon.com/images/I/615YYN4q7gL.jpg");
        imgData.add("https://images-cn.ssl-images-amazon.com/images/I/61Nriqwg2LL.jpg");
        imgData.add("https://images-cn.ssl-images-amazon.com/images/I/615b9gM9GgL.jpg");
        imgData.add("https://images-cn.ssl-images-amazon.com/images/I/41fi2pEYkuL.jpg");
        PreViewGridAdapter gridAdapter = new PreViewGridAdapter(PreViewImgActivity.this, imgData);
        img_grid.setAdapter(gridAdapter);
        img_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                PhotoPreview.builder()
                        .setPhotos(imgData)
                        .setCurrentItem(position)
                        .setShowToolbar(false)
                        .setOnLongClickListData(onLongClickListData)
                        .start(PreViewImgActivity.this);
            }
        });
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public void finishRefresh() {

    }

//    @Override
//    public void sendOnLongClick(int position, String path) {
//        Toast.makeText(PreViewImgActivity.this, "你点击了：" + onLongClickListData.get(position) + "，图片路径：" + path, Toast.LENGTH_LONG).show();
//
//    }
}
