package com.minzea.base.widget.ImageBrowser;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.minzea.base.R;
import com.minzea.base.widget.photoview.PhotoViewAttacher;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class ImageDetailFragment extends Fragment {
	private String mImageUrl;
	private ImageView mImageView;
	private ImageView image_small;
	private View all_view;
	private ProgressBar progressBar;
	private PhotoViewAttacher mAttacher;

	public static ImageDetailFragment newInstance(String imageUrl) {
		final ImageDetailFragment f = new ImageDetailFragment();

		final Bundle args = new Bundle();
		args.putString("url", imageUrl);
		f.setArguments(args);
		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mImageUrl = getArguments() != null ? getArguments().getString("url") : null;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View v = inflater.inflate(R.layout.image_detail_fragment, container, false);
		mImageView = (ImageView) v.findViewById(R.id.image);
		all_view = v.findViewById(R.id.all_view);
		all_view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getActivity().finish();
			}
		});
		image_small = (ImageView) v.findViewById(R.id.image_small);
		mAttacher = new PhotoViewAttacher(mImageView);

//		mAttacher.setOnPhotoTapListener(new OnPhotoTapListener() {
//
//			@Override
//			public void onPhotoTap(View arg0, float arg1, float arg2) {
//				getActivity().finish();
//			}
//		});
		mAttacher.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
			
			@Override
			public void onViewTap(View view, float x, float y) {
				// TODO Auto-generated method stub
				getActivity().finish();
			}
		});
		progressBar = (ProgressBar) v.findViewById(R.id.loading);
		mImageView.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				return false;
			}
		});
		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
//		image_small.setImageResource(R.drawable.ic_launcher);
		DisplayImageOptions options = new DisplayImageOptions.Builder()//
		.cacheInMemory(true)//
		.cacheOnDisk(true)//
		.bitmapConfig(Config.RGB_565)//
		.build();
		String url = getArguments().getString("url");
		if(url==null)return;
        if(!url.startsWith("http")){
            url="file:/"+url;
        }
		ImageLoader imageLoader = ImageLoader.getInstance();
		imageLoader.init(ImageLoaderConfiguration.createDefault(getActivity()));
		imageLoader.displayImage(url, mImageView, options, new ImageLoadingListener() {

			@Override
			public void onLoadingStarted(String arg0, View arg1) {
				// TODO Auto-generated method stub
				progressBar.setVisibility(View.VISIBLE);
			}

			@Override
			public void onLoadingFailed(String arg0, View arg1, FailReason failReason) {
				// TODO Auto-generated method stub
				String message = "加载失败，请稍后重试";
				progressBar.setVisibility(View.GONE);
			}

			@Override
			public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
				// TODO Auto-generated method stub
				progressBar.setVisibility(View.GONE);
				image_small.setVisibility(View.GONE);
				all_view.setVisibility(View.GONE);
				mAttacher.update();
			}

			@Override
			public void onLoadingCancelled(String arg0, View arg1) {
				// TODO Auto-generated method stub

			}
		});
	}

}
