package com.qin.zihu.data.source.http;

import com.qin.zihu.data.model.User;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Qin on 2017/2/24.
 *
 */

public interface HttpService {

    // 登录
    @FormUrlEncoded
    @POST("api.php?mod=User&act=doLogin")
    Observable<HttpResult<User>> login(@FieldMap Map<String, String> params);

    // 获取验证码
    @FormUrlEncoded
    @POST("api.php?mod=User&act=sendLoginCode")
    Observable<HttpResult> getVerificationCode(@FieldMap Map<String, String> params);
}
