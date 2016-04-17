package com.lianjia.android.library;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by GanQuan on 16/4/11.
 */
public class LoadingHelper {
	private View mContentView;
	private Context mContext;
	public final String TAG = LoadingHelper.class.getSimpleName();

	private LoadingView mLoadingView;
	private RetryView mRetryView;

	public LoadingHelper setErrorListener(View.OnClickListener retryClick) {
		mRetryView.setOnRetryListener(retryClick);
		return this;
	}

	public void removeRetryListener() {
		mRetryView.setOnRetryListener(null);
	}

	/**
	 * 
	 * @param content
	 *            the view you want to replace,you should know the helper will
	 *            be added to the view parent
	 */
	public LoadingHelper(View content) {
		onCreateView(content);
	}

	/**
	 * @param content
	 */
	public LoadingHelper onCreateView(View content) {
		mContext = content.getContext();
		ViewGroup parent = (ViewGroup) content.getParent();
		addViews(parent, parent.indexOfChild(content));
		return this;
	}

	private void addViews(ViewGroup parent, int position) {
		mContentView = parent.getChildAt(position);

		ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);
		mLoadingView = new LoadingView(mContext);
		mRetryView = new RetryView(mContext);
		parent.addView(mLoadingView, position, params);
		mLoadingView.setVisibility(View.GONE);// added by gq
		parent.addView(mRetryView, position, params);
		mRetryView.setVisibility(View.GONE);

	}

	public boolean isRetryViewVisible() {
		return mRetryView.getVisibility() == View.VISIBLE;
	}

	public void showContentView() {
		Log.d(TAG, "showContentView: ");
		mContentView.setVisibility(View.VISIBLE);
		mLoadingView.setVisibility(View.GONE);
		mRetryView.setVisibility(View.GONE);
	}

	public void showLoadingView() {
		Log.d(TAG, "showLoadingView: ");
		mContentView.setVisibility(View.GONE);
		mLoadingView.setVisibility(View.VISIBLE);
		mRetryView.setVisibility(View.GONE);

	}

	public void showNetworkError() {
		Log.d(TAG, "showNetworkError: ");
		mRetryView.setVisibility(View.VISIBLE);
		mLoadingView.setVisibility(View.GONE);
		mContentView.setVisibility(View.GONE);
	}

	public void showDefaultEmptyView() {
		mRetryView.setVisibility(View.VISIBLE);
		mLoadingView.setVisibility(View.GONE);
		mContentView.setVisibility(View.GONE);
	}

	static class LoadingView extends BaseViewWrap {
		private ProgressBar mProgress;
		private TextView mMessageView;

		public LoadingView(Context context) {
			super(context);
		}

		@Override
		protected void onViewCreated(View mView) {
			mProgress = (ProgressBar) mView.findViewById(R.id.progress_bar);
			mMessageView = (TextView) mView.findViewById(R.id.message);
		}

		@Override
		protected int onBindLayoutId() {
			return R.layout.common_loading_layout;
		}
	}

	static class RetryView extends BaseViewWrap {
		private ImageView mRetryTipImg;
		private TextView mRetryTipTv;

		public RetryView(Context context) {
			super(context);
		}

		@Override
		protected void onViewCreated(View mView) {
			mRetryTipTv = (TextView) mView.findViewById(R.id.tv_tip);
			mRetryTipImg = (ImageView) mView.findViewById(R.id.img_retry_btn);
		}

		@Override
		protected int onBindLayoutId() {
			return R.layout.common_retry_layout;
		}

		public void setOnRetryListener(View.OnClickListener onClickListener) {
			this.mRetryTipImg.setOnClickListener(onClickListener);
		}

	}
}
