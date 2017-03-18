package com.qin.zihu.loader;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by Qin on 2017/3/3.
 */

public abstract class BaseImageLoader implements ImageLoaderInterface<ImageView>{

    @Override
    public ImageView creteImageView(Context context) {
        ImageView imageView = new ImageView(context);
        return imageView;
    }
}
