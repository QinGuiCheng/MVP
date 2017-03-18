package com.qin.zihu.module.auth;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.minzea.base.utils.RegularUtils;
import com.minzea.base.widget.CountdownButton;
import com.qin.zihu.R;
import com.qin.zihu.base.BaseActivity;
import com.qin.zihu.data.model.User;
import com.qin.zihu.module.banner.Banner;
import com.qin.zihu.utils.ZiHuToastUtils;

/**
 * Created by Qin on 2017/2/24.
 */

public class LoginActivity extends BaseActivity implements AuthContract.View, Banner.OnBannerClickListener {

    private EditText mPhone, mCode;
    private Button mLogin, mRippleBtn;
    private CountdownButton mGetCode;
    private TextView mNotRegisterNow;
    private AuthContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("登录");
       // mPresenter = new AuthPresenter(mContext, this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.unsubscribe();
        }
    }

    @Override
    public void initView() {
        mPhone = (EditText) findViewById(R.id.et_phone);
        mCode = (EditText) findViewById(R.id.et_code);
        mGetCode = (CountdownButton) findViewById(R.id.btn_get_code);
        mLogin = (Button) findViewById(R.id.btn_login);
        mNotRegisterNow = (TextView) findViewById(R.id.tv_not_register_now);
    }

    @Override
    public void bindEvent() {
        mLogin.setOnClickListener(this);
        mGetCode.setOnClickListener(this);
        mNotRegisterNow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get_code:
                if (RegularUtils.isMobileExact(mPhone.getText().toString())) {
                    mPresenter.getCode(mPhone.getText().toString());
                } else {
                    ZiHuToastUtils.showToast("请输入正确的手机号码！");
                }
                break;

            case R.id.btn_login:
                if (!RegularUtils.isMobileExact(mPhone.getText().toString())) {
                    ZiHuToastUtils.showToast("请输入正确的手机号码！");
                    break;
                }
                if (mCode.getText().length() != 4) {
                    ZiHuToastUtils.showToast("请填写正确的验证码！");
                    break;
                }
                mPresenter.login(mPhone.getText().toString(), mCode.getText().toString());
                break;

            case R.id.tv_not_register_now:

                break;

            default:
                break;
        }
    }

    @Override
    public void getCodeSuccess() {
        mGetCode.start();
        ZiHuToastUtils.showToast("验证码已发送，请注意查收");
    }

    @Override
    public void getCodeFailed(String msg) {
        if (msg != null && !msg.isEmpty()) {
            ZiHuToastUtils.showToast(msg);
        } else {
            ZiHuToastUtils.showToast("请求失败，请稍后重试");
        }
    }

    @Override
    public void loginSuccess(User user) {
        ZiHuToastUtils.showToast("登录成功！");
    }

    @Override
    public void loginFailed(String msg) {
        if (msg != null && !msg.isEmpty()) {
            ZiHuToastUtils.showToast(msg);
        } else {
            ZiHuToastUtils.showToast("登录失败，请稍后重试");
        }
    }

    @Override
    public void OnBannerClick(int position) {

    }
}
