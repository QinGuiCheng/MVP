package com.qin.zihu.utils;

import android.widget.Toast;

import com.qin.zihu.app.ZiHuApp;

/**
 * Created by Qin on 2017/2/24.
 */

public class ZiHuToastUtils {

    public static final int LENGTH_LONG = 1;
    public static final int LENGTH_SHORT = 0;

    private static Toast mToast;

    private ZiHuToastUtils(){

    }

    // 整个app内toast单例，防止出现连续弹出toast导致toast显示时间加起来很长
    public static Toast getToast(){
        if(mToast == null){
            mToast = new Toast(ZiHuApp.getInstance());
        }
        return mToast;
    }

    public static void showToast(String text){
        showToast(text, Toast.LENGTH_SHORT);
    }

    public static void showToast(String text, int duration) {
        if(mToast == null) {
            mToast = Toast.makeText(ZiHuApp.getInstance(), text, duration);
        } else {
            mToast.setText(text);
            mToast.setDuration(duration);
        }
        mToast.show();
    }

    public static void showToast(int textId){
        showToast(textId, Toast.LENGTH_SHORT);
    }

    public static void showToast(int textId, int duration) {
        String text = ZiHuApp.getInstance().getString(textId);
        showToast(text, duration);
    }

    public static void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
}
