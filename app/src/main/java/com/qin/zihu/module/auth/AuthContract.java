package com.qin.zihu.module.auth;

import com.qin.zihu.base.BasePresenter;
import com.qin.zihu.base.BaseView;
import com.qin.zihu.data.model.User;

/**
 * Created by Qin on 2017/2/24.
 */

public interface AuthContract {

    interface Presenter extends BasePresenter {
        void getCode(String phone);

        void login(String phone, String password);
    }

    interface View extends BaseView<Presenter> {
        void getCodeSuccess();

        void getCodeFailed(String msg);

        void loginSuccess(User user);

        void loginFailed(String msg);
    }
}
