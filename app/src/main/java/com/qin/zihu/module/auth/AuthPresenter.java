package com.qin.zihu.module.auth;

import android.content.Context;

import com.qin.zihu.constants.Constants;
import com.qin.zihu.data.model.User;
import com.qin.zihu.data.source.http.BaseSubscribe;
import com.qin.zihu.data.source.http.HttpClient;
import com.qin.zihu.data.source.http.HttpResult;
import com.qin.zihu.data.source.http.HttpService;
import com.qin.zihu.data.source.http.RxHelper;
import com.qin.zihu.utils.ZiHuToastUtils;

import java.util.HashMap;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Qin on 2017/2/24.
 */

public class AuthPresenter implements AuthContract.Presenter {

    private CompositeSubscription mAllSubscription = new CompositeSubscription();
    private Context mContext;
    private AuthContract.View mView;
    private HttpService mHttpService;


    public AuthPresenter(Context context, AuthContract.View view) {
        this.mContext = context;
        this.mView = view;
        mHttpService = HttpClient.getHttpService();
    }


    @Override
    public void getCode(String phone) {

        HashMap<String, String> params = HttpClient.createBaseParams();
        params.put("phone", phone);
        mAllSubscription.add(mHttpService.getVerificationCode(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new BaseSubscribe<HttpResult>(mContext) {
                    @Override
                    protected void _onNext(HttpResult httpResult) {
                        if (httpResult.status == Constants.HTTP_STATUS_SUCCESS) {
                            mView.getCodeSuccess();
                        } else {
                            mView.getCodeFailed(httpResult.msg);
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.getCodeFailed(message);
                    }

                    @Override
                    protected void _onCompleted() {
                    }

                    @Override
                    protected void _onNoNetwork() {
                    }
                }));

    }

    @Override
    public void login(String phone, String code) {

        HashMap<String, String> params = HttpClient.createBaseParams();
        params.put("phone", phone);
        params.put("regCode", code);
        mAllSubscription.add(mHttpService.login(params)
                .compose(RxHelper.<User>handleResult())
                .subscribe(new BaseSubscribe<User>(mContext) {
                    @Override
                    protected void _onNext(User user) {
                        mView.loginSuccess(user);
                    }

                    @Override
                    protected void _onError(String message) {
                        ZiHuToastUtils.showToast(message);
                    }

                    @Override
                    protected void _onCompleted() {

                    }

                    @Override
                    protected void _onNoNetwork() {

                    }
                }));

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        if (mAllSubscription != null && !mAllSubscription.isUnsubscribed()) {
            mAllSubscription.unsubscribe();
        }
    }
}
