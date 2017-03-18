package com.qin.zihu.utils;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.qin.zihu.constants.Config;

/**
 * Created by Qin on 2017/2/24.
 */

public class ZiHuLogger {


    public static String mTag = "Logger";
    private static boolean debug = true;

    static {
        com.orhanobut.logger.Logger
                .init(mTag)// default PRETTYLOGGER or use just init()
                .hideThreadInfo();                 // default shown
        debug = Config.DEBUG;
    }

    public static void v(@NonNull String tag, String msg) {
        if (!TextUtils.isEmpty(msg) && debug) {
            try{
                tag = checkTag(tag);
                com.orhanobut.logger.Logger.t(tag).v(msg);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void d(@NonNull String tag, String msg) {
        if (!TextUtils.isEmpty(msg) && debug) {
            try{
                tag = checkTag(tag);
                com.orhanobut.logger.Logger.t(tag).d(msg);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void i(@NonNull String tag, String msg) {
        if (!TextUtils.isEmpty(msg) && debug) {
            try{
                tag = checkTag(tag);
                com.orhanobut.logger.Logger.t(tag).i(msg);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void w(@NonNull String tag, String msg) {
        if (!TextUtils.isEmpty(msg) && debug) {
            try{
                tag = checkTag(tag);
                com.orhanobut.logger.Logger.t(tag).w(msg);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void e(@NonNull String tag, String msg) {
        if (!TextUtils.isEmpty(msg) && debug) {
            try{
                tag = checkTag(tag);
                com.orhanobut.logger.Logger.t(tag).e(msg);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void e(@NonNull String tag, Exception e) {
        tag = checkTag(tag);
        if (debug) {
            try{
                com.orhanobut.logger.Logger.t(tag).e(e == null ? "未知错误" : e.getMessage());
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }
    }

    public static void v(String msg) {
        if (!TextUtils.isEmpty(msg) && debug) {
            try{
                com.orhanobut.logger.Logger.v(msg);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void d(String msg) {
        if (!TextUtils.isEmpty(msg) && debug) {
            try{
                com.orhanobut.logger.Logger.d(msg);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void i(String msg) {
        if (!TextUtils.isEmpty(msg) && debug) {
            try{
                com.orhanobut.logger.Logger.i(msg);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void w(String msg) {
        if (!TextUtils.isEmpty(msg) && debug) {
            try{
                com.orhanobut.logger.Logger.v(msg);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void e(String msg) {
        if (!TextUtils.isEmpty(msg) && debug) {
            try{
                com.orhanobut.logger.Logger.e(msg);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void e(Exception e) {
        if (debug) {
            try{
                com.orhanobut.logger.Logger.e(e == null ? "未知错误" : e.getMessage());
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }
    }

    public static void wtf(@NonNull String tag, String msg) {
        if (!TextUtils.isEmpty(msg) && debug) {
            try{
                tag = checkTag(tag);
                com.orhanobut.logger.Logger.t(tag).wtf(msg);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void json(@NonNull String tag, String msg) {
        if (!TextUtils.isEmpty(msg) && debug) {
            try{
                tag = checkTag(tag);
                com.orhanobut.logger.Logger.t(tag).json(msg);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void xml(@NonNull String tag, String msg) {
        if (!TextUtils.isEmpty(msg) && debug) {
            try{
                tag = checkTag(tag);
                com.orhanobut.logger.Logger.t(tag).xml(msg);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void wtf(String msg) {
        if (!TextUtils.isEmpty(msg) && debug) {
            try{
                com.orhanobut.logger.Logger.wtf(msg);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void json(String msg) {
        if (!TextUtils.isEmpty(msg) && debug) {
            try{
                com.orhanobut.logger.Logger.json(msg);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void xml(String msg) {
        if (!TextUtils.isEmpty(msg) && debug) {
            try{
                com.orhanobut.logger.Logger.xml(msg);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private static String checkTag(String tag) {
        if (TextUtils.isEmpty(tag)) {
            tag = mTag;
        }
        return tag;
    }
}
