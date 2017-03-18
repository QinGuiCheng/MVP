package com.qin.zihu.data.source.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.minzea.base.utils.MD5Utils;
import com.minzea.base.utils.NetworkUtils;
import com.qin.zihu.app.ZiHuApp;
import com.qin.zihu.constants.Constants;
import com.qin.zihu.data.source.prefs.PreferencesKeys;
import com.qin.zihu.data.source.prefs.PreferencesUtils;
import com.qin.zihu.utils.ZiHuLogger;

import java.io.File;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Qin on 2017/2/24.
 */

public class HttpClient {
    private static final String HOST = Constants.HOST_URL;
    private static final String mSecret = "aaa8916a9dcb8e38e8c5a2d0b5d221f8";
    private static Retrofit mRetrofit;
    private static HttpService httpService;

    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetworkUtils.isNetworkAvailable(ZiHuApp.getInstance())) { //没网强制从缓存读取(必须得写，不然断网状态下，退出应用，或者等待一分钟后，就获取不到缓存）
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response originalResponse = chain.proceed(request);
            if (NetworkUtils.isNetworkAvailable(ZiHuApp.getInstance())) {
                int maxAge = 60; // 在线缓存在1分钟内可读取
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 3; // 离线时缓存保存3天
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }
    };

    private HttpClient() {

    }

    //抽取Retrofit 集成OkHttp3 的抽象基类
    private static Retrofit getRetrofit() {
        // log拦截器  打印所有的log
        HttpInterceptor interceptor = new HttpInterceptor();
        interceptor.setLevel(HttpInterceptor.Level.BODY);

        //cookie 缓存
        CookieHandler cookieHandler = new CookieManager(
                new PersistentCookieStore(ZiHuApp.getInstance()), CookiePolicy.ACCEPT_ALL);

        //设置 请求的缓存
        File cacheFile = new File(ZiHuApp.getInstance().getCacheDir(), "http");
        ZiHuLogger.i("http cache directory ==== " + cacheFile.getPath());
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50); //50Mb


        //获取OkHttpClient对象
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)//设置请求读写的超时时间
                .cookieJar(new JavaNetCookieJar(cookieHandler))//设置一个自动管理cookies的管理器
                .addInterceptor(interceptor)//添加拦截Log
                .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)//网络拦截器
                .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)//aplication 拦截器
                .cache(cache)
                .build();

        Gson gson = new GsonBuilder().setLenient().create();

        mRetrofit = new Retrofit.Builder()
                .client(client)//设置使用okHttp网络请求
                .baseUrl(HOST)//设置服务器路径
                .addConverterFactory(ScalarsConverterFactory.create())//可以转String类型
                .addConverterFactory(GsonConverterFactory.create(gson))//添加转化库，默认是Gson
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//添加回调库，采用RxJava
                .build();

        return mRetrofit;
    }

    public static HttpService getHttpService() {
        if (httpService == null) {
            httpService = getRetrofit().create(HttpService.class);
        }
        return httpService;
    }

    public static HashMap<String, String> createBaseParams() {
        ArrayList<String> list = new ArrayList<>();
        String uid = PreferencesUtils.getString(ZiHuApp.getInstance(), PreferencesKeys.UID, "");
        String time = String.valueOf(System.currentTimeMillis() / 1000);
        list.add(uid);
        list.add(mSecret);
        list.add(time);
        Collections.sort(list);
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append(list.get(0)).append("&").append(list.get(1)).append("&").append(list.get(2));
        String signature = MD5Utils.MD5Hash(sBuilder.toString());
        HashMap<String, String> params = new HashMap<>();
        params.put("app_time", time);
        params.put("app_uid", uid);
        params.put("app_signature", signature);
        return params;
    }
}
