package com.minzea.base.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.minzea.base.R;


public class MultipleStatusView extends FrameLayout {

    private View mChildView;
    private View mCustomView;
    private OnActionListener mOnActionListener;

    public MultipleStatusView(Context context) {
        super(context);
    }

    public MultipleStatusView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MultipleStatusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MultipleStatusView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        int childCount = getChildCount();
        if (childCount > 1) {
            throw new IllegalStateException("MultipleStatusVIew only host 1 elements");
        } else if (childCount == 1) {
            mChildView = getChildAt(0);
        }
        super.onFinishInflate();
    }

    public void none() {
        custom(new View(getContext()));
    }

    public void loading() {
        custom(R.layout.layout_loading);
    }

    public void empty() {
        Resources res = getResources();
        custom(res.getDrawable(R.drawable.ic_empty),
                res.getString(R.string.multiple_status_view_tips_empty));
    }

    public void noNetwork(){
        Resources res = getResources();
        custom(res.getDrawable(R.drawable.ic_no_network),
                res.getString(R.string.multiple_status_view_tips_no_network));
    }

    public void error(){
        Resources res = getResources();
        custom(res.getDrawable(R.drawable.ic_error),
                res.getString(R.string.multiple_status_view_tips_error));
    }

    public void custom(Drawable drawable, String tips) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_action, this, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        if (imageView != null) {
            if (drawable == null) {
                imageView.setVisibility(GONE);
            } else {
                imageView.setVisibility(VISIBLE);
                imageView.setImageDrawable(drawable);
            }
        }

        TextView textView = (TextView) view.findViewById(R.id.txt_tips);
        if (textView != null) {
            if (TextUtils.isEmpty(tips)) {
                textView.setVisibility(GONE);
            } else {
                textView.setVisibility(VISIBLE);
                textView.setText(tips);
            }
        }

        View mainView = view.findViewById(R.id.ll_main_view);
        if (mainView != null) {

            mainView.setVisibility(VISIBLE);
            mainView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnActionListener != null) mOnActionListener.onAction(v);
                }
            });

        }
        custom(view);
    }

    public void custom(int resId) {
        custom(LayoutInflater.from(getContext()).inflate(resId, this, false));
    }

    public void custom(View customView) {
        removeView(mCustomView);
        mCustomView = customView;
        if (mChildView != null) mChildView.setVisibility(GONE);
        if (mCustomView != null) mCustomView.setVisibility(VISIBLE);
        addView(mCustomView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public void dismiss() {
        removeView(mCustomView);
        if (mChildView != null) mChildView.setVisibility(VISIBLE);
    }

    public interface OnActionListener {
        void onAction(View view);
    }

    public void setOnActionListener(OnActionListener onActionListener) {
        mOnActionListener = onActionListener;
    }
}
