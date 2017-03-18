package com.qin.zihu.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.aitangba.swipeback.SwipeBackActivity;
import com.qin.zihu.R;
import com.qin.zihu.app.ActivityManager;
import com.qin.zihu.utils.ZiHuToastUtils;

/**
 * Created by Qin on 2017/2/24.
 */

public abstract class BaseActivity extends SwipeBackActivity implements View.OnClickListener {


    protected String TAG;
    protected Context mContext;
    protected ProgressDialog mProgressDialog;
    protected Toolbar mToolbar;
    protected TextView mTvTitle, mAction1, mAction2, mIvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TAG = this.getClass().getSimpleName();
        mContext = this;

        // 设置不能横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        //Activity管理
        ActivityManager.getInstance().addActivity(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        View view = LayoutInflater.from(this).inflate(layoutResID, null);
        setContentView(view);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        init();
    }

    public void init() {
        initView();
        bindEvent();
    }

    /**
     * 初始化页面
     */
    public abstract void initView();

    /**
     * 绑定事件
     */
    public abstract void bindEvent();

    @Override
    protected void onDestroy() {
        super.onDestroy();

        dismissProgressDialog();
        ActivityManager.getInstance().removeActivity(this);
    }

    protected void showProgressDialog() {
        showProgressDialog(null, getString(R.string.loading));
    }

    protected void showProgressDialog(String title, String message) {
        mProgressDialog = new ProgressDialog(this);
        if (title != null && !title.isEmpty()) {
            mProgressDialog.setTitle(title);
        }
        if (message != null && !message.isEmpty()) {
            mProgressDialog.setMessage(message);
        }
        mProgressDialog.show();
    }

    protected void dismissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    protected void showToastShort(String s) {
        ZiHuToastUtils.showToast(s, ZiHuToastUtils.LENGTH_SHORT);
    }

    protected void showToastLong(String s) {
        ZiHuToastUtils.showToast(s, ZiHuToastUtils.LENGTH_LONG);
    }

    protected void setTitle(String title) {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTvTitle = (TextView) mToolbar.findViewById(R.id.tv_title);
        mAction1 = (TextView) mToolbar.findViewById(R.id.toolbar_right_1);
        mAction2 = (TextView) mToolbar.findViewById(R.id.toolbar_right_2);
        mIvBack = (TextView) mToolbar.findViewById(R.id.iv_toolbar_back);

        if (mTvTitle != null) {
            mTvTitle.setText(title);
        }
        View back = findViewById(R.id.iv_toolbar_back);
        if (back != null) {
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    protected void hideToolbar(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if(mToolbar != null){
            mToolbar.setVisibility(View.GONE);
        }
    }

    protected void showToolbar(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if(mToolbar != null){
            mToolbar.setVisibility(View.VISIBLE);
        }
    }

    protected void hideBackIcon() {
        if (mIvBack != null) {
            mIvBack.setVisibility(View.GONE);
        }
    }

    protected void setAction1(String action) {
        if (mAction1 != null) {
            mAction1.setText(action);
            mAction1.setVisibility(View.VISIBLE);
            mAction1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onAction1Click();
                }
            });
        }
    }

    protected void setAction1Icon(int resId) {
        if (mAction1 != null) {
            mAction1.setVisibility(View.VISIBLE);
            Drawable drawable = mContext.getResources().getDrawable(resId);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            mAction1.setCompoundDrawables(drawable, null, null, null);
            mAction1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onAction1Click();
                }
            });
        }
    }

    protected void hideAction1() {
        if (mAction1 != null) {
            mAction1.setVisibility(View.GONE);
        }
    }

    protected void showAction1() {
        if (mAction1 != null) {
            mAction1.setVisibility(View.VISIBLE);
            mAction1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onAction1Click();
                }
            });
        }
    }

    protected void onAction1Click() {

    }

    protected void setAction2(String action) {
        if (mAction2 != null) {
            mAction2.setText(action);
            mAction2.setVisibility(View.VISIBLE);
            mAction2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onAction2Click();
                }
            });
        }
    }

    protected void setAction2Icon(int resId) {
        if (mAction2 != null) {
            mAction2.setVisibility(View.VISIBLE);
            Drawable drawable = mContext.getResources().getDrawable(resId);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            mAction2.setCompoundDrawables(drawable, null, null, null);
            mAction2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onAction2Click();
                }
            });
        }
    }

    protected void hideAction2() {
        if (mAction2 != null) {
            mAction2.setVisibility(View.GONE);
        }
    }

    protected void showAction2() {
        if (mAction2 != null) {
            mAction2.setVisibility(View.VISIBLE);
            mAction2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onAction2Click();
                }
            });
        }
    }

    protected void onAction2Click() {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
