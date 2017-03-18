package com.qin.zihu.data.source.http;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.minzea.base.utils.AppUtils;
import com.qin.zihu.app.ZiHuApp;
import com.qin.zihu.constants.Constants;
import com.qin.zihu.module.auth.LoginActivity;

import java.net.UnknownHostException;

import rx.Subscriber;

/**
 * Created by Qin on 2017/2/24.
 */

public abstract class BaseSubscribe <T> extends Subscriber<T> {

    private Context mContext;
    private ProgressDialog dialog;
    private String msg;
    private boolean showDialog = false;

    protected boolean showDialog() {
        return showDialog;
    }

    public BaseSubscribe(){
        this.showDialog = false;
    }

    public BaseSubscribe(Context context, String msg) {
        this.mContext = context;
        this.msg = msg;
        this.showDialog = true;
    }

    public BaseSubscribe(Context context) {
        this(context, "请稍后...");
        this.showDialog = true;
    }

    @Override
    public void onCompleted() {
        if (showDialog()) {
            dialog.dismiss();
        }
        _onCompleted();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (showDialog()) {
            dialog = new ProgressDialog(mContext);
            dialog.setMessage(msg);
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(true);
            //点击取消的时候取消订阅
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    if (!isUnsubscribed()) {
                        unsubscribe();
                    }
                }
            });
            dialog.show();
        }
    }

    @Override
    public void onNext(T t) {
        if(t instanceof HttpResult){
            if(((HttpResult) t).code != null && ((HttpResult) t).code.equals(Constants.HTTP_CODE_UNLOGIN)){
                _onCompleted();
                onUnLogin();
            }else if(((HttpResult) t).status != Constants.HTTP_STATUS_SUCCESS){
                String errorMsg = ((HttpResult) t).msg;
                if(errorMsg == null){
                    errorMsg = "请求失败，请稍后重试";
                }
                _onError(errorMsg);
            }else{
                _onNext(t);
            }
        }else{
            _onNext(t);
        }
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof UnknownHostException) {
            _onNoNetwork();
        }else if(e instanceof UnLoginException){
            onUnLogin();
        } else if (e instanceof ServerException) {
            _onError(e.getMessage());
        } else {
            _onError("请求失败，请稍后再试...");
        }
        if (showDialog()){
            dialog.dismiss();
        }
    }

    private void onUnLogin(){
        if(AppUtils.getTopActivityName(ZiHuApp.getInstance()) != null
                && !AppUtils.getTopActivityName(ZiHuApp.getInstance()).equals(LoginActivity.class.getName())){
            Intent startActivity = new Intent();
            startActivity.setClass(ZiHuApp.getInstance(), LoginActivity.class);
            startActivity.setAction(LoginActivity.class.getName());
            startActivity.setFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK
                            | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            ZiHuApp.getInstance().startActivity(startActivity);
        }
    }

    protected abstract void _onNext(T t);

    protected abstract void _onError(String message);

    protected abstract void _onCompleted();

    protected abstract void _onNoNetwork();
}
