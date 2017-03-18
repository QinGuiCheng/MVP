package com.qin.zihu.loader;

import android.content.Context;
import android.view.View;

import java.io.Serializable;

/**
 * Created by Qin on 2017/3/3.
 */

public interface ImageLoaderInterface <T extends View> extends Serializable {

    void displayImage(Context context, Object path, T imageView);

    void displayCircleImage(Context context, Object path, T imageView);

    T creteImageView(Context context);
}
