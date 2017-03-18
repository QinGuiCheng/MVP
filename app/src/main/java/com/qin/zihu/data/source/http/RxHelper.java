package com.qin.zihu.data.source.http;

import com.qin.zihu.constants.Constants;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Qin on 2017/2/24.
 * 对http请求结果预处理
 */

public class RxHelper {

    public static <T> Observable.Transformer<HttpResult<T>, T> handleResult() {

        return new Observable.Transformer<HttpResult<T>, T>() {

            @Override
            public Observable<T> call(Observable<HttpResult<T>> tObservable) {

                return tObservable.flatMap(new Func1<HttpResult<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(HttpResult<T> result) {
                        if (result.status == Constants.HTTP_STATUS_SUCCESS) {
                            return createData(result.data);
                        } else if (result.code != null && result.code.equals(Constants.HTTP_CODE_UNLOGIN)) {
                            return Observable.error(new UnLoginException(result.msg));
                        } else {
                            return Observable.error(new ServerException(result.msg));
                        }
                    }
                    //subscribeOn 子线程访问网络 == observeOn 回调到主线程
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };

    }

    private static <T> Observable<T> createData(final T data) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

}
