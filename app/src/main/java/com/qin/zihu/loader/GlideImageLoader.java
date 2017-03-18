package com.qin.zihu.loader;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Qin on 2017/3/3.
 */

public class GlideImageLoader extends BaseImageLoader {

    private static GlideImageLoader instance;

    public static GlideImageLoader getInstance() {
        if (instance == null) {
            instance = new GlideImageLoader();
        }
        return instance;
    }

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .crossFade()
                .into(imageView);
    }

    @Override
    public void displayCircleImage(Context context, Object path, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .crossFade()
                .bitmapTransform(new CropCircleTransformation(context))
                .into(imageView);
    }
}
