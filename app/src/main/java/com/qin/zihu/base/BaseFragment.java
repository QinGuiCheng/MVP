package com.qin.zihu.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Qin on 2017/3/16.
 *  实现了懒加载模式，在fragment可见时才加载数据，以避免多个fragment的时候同时加载太多数据，尤其是在主界面
 * 若要使用懒加载，则获取数据的逻辑写在initData,若不需使用懒加载，则卸载initView，initData留空即可
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener{

    protected String TAG;
    protected BaseActivity mContext;
    protected View mContentView;

    private boolean isVisible;
    private boolean isPrepared;
    private boolean isFirstLoad = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getBaseActivity();
        TAG = this.getClass().getSimpleName();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        isFirstLoad = true;
        if (mContentView == null) {
            mContentView = inflater.inflate(onSetLayoutId(), container, false);
            initView();
            bindEvent();
        }
        isPrepared = true;
        lazyLoad();
        return mContentView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible() {
        lazyLoad();
    }

    protected void onInvisible() {
    }

    /**
     * 要实现延迟加载Fragment内容,需要在 onCreateView
     * isPrepared = true;
     */
    protected void lazyLoad() {
        if (!isPrepared || !isVisible || !isFirstLoad) {
            return;
        }
        isFirstLoad = false;
        initData();
    }

    protected abstract void initData();

    public BaseActivity getBaseActivity() {
        return (BaseActivity) this.getActivity();
    }

    /**
     * 设置布局文件
     *
     * @return 布局文件资源id
     */
    public abstract int onSetLayoutId();

    public abstract void initView();

    public abstract void bindEvent();

    public void showToastShort(String content) {
        mContext.showToastShort(content);
    }

    public void showToastLong(String content) {
        mContext.showToastShort(content);
    }

    public void showProgressDialog() {
        mContext.showProgressDialog();
    }

    public void showProgressDialog(String title, String message){
        mContext.showProgressDialog(title, message);
    }

    public void dismissProgressDialog() {
        mContext.dismissProgressDialog();
    }
}
