package com.minzea.base.widget.ImageBrowser;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class ImageBrowserViewPager extends ViewPager {

	private static final String TAG = "ImageViewPager";

	public ImageBrowserViewPager(Context context) {
		super(context);
	}

	public ImageBrowserViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		try {
			return super.onInterceptTouchEvent(ev);
		} catch (IllegalArgumentException e) {
			Log.e(TAG, "image viewpager error1");
			return false;
		} catch (ArrayIndexOutOfBoundsException e) {
			Log.e(TAG, "image viewpager error2");
			return false;
		}
	}

}
