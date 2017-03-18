package com.qin.zihu.app;

import android.app.Application;

import com.antfortune.freeline.FreelineCore;
import com.qin.zihu.constants.Config;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Qin on 2017/2/24.
 */

public class ZiHuApp extends Application {

    private final String TAG = this.getClass().getSimpleName();
    private static ZiHuApp instance;

    private static final String APK_ID = "apk_id";
    private String apk_id = null;

    public static ZiHuApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        init();
    }

    private void init() {
        initLeakCanary();
        initBugly();
        initUmeng();
        initPush();
        initFreeline();
    }

    //初始化Freeline
    private void initFreeline() {
        FreelineCore.init(this);
    }

    // bugly
    private void initBugly() {
    }

    // 内存泄露检测
    private void initLeakCanary() {
        if (Config.DEBUG) {
            LeakCanary.install(this);
        }
    }

    // 友盟
    private void initUmeng() {

    }


    // 推送
    private void initPush() {

    }


}
