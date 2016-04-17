package com.lianjia.android.library;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by GanQuan on 16/4/12.
 */
public abstract class BaseViewWrap extends FrameLayout {

	public BaseViewWrap(Context context) {
		super(context);
		onCreate(context);
	}

	protected void onCreate(Context context) {
		View view = LayoutInflater.from(context).inflate(onBindLayoutId(), this);
		onViewCreated(view);
	}

	/**
	 * pass the view params which you can find by it
	 * 
	 * @param mView
	 */
	protected abstract void onViewCreated(View mView);

	/**
	 * set the view layout Id as the content view
	 * 
	 * @return
	 */
	protected abstract int onBindLayoutId();

}
