package com.lianjia.android.loadinghelper;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lianjia.android.library.LoadingHelper;

/**
 * Created by GanQuan on 16/4/17. a simple test
 */
public class MainActivity extends AppCompatActivity {
	TextView contentView;
	LoadingHelper mLoadingHelper;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		contentView = (TextView) findViewById(R.id.content_view);
		initLoadingHelper();
		loadData();
		initViewActions();
	}

	private void initViewActions() {
		contentView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				loadData();
			}
		});
	}

	private void initLoadingHelper() {
		mLoadingHelper = new LoadingHelper(contentView);
		mLoadingHelper.setErrorListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				loadData();
			}
		});
	}

	private LoadingHelper getLoadingHelper() {
		if (mLoadingHelper == null) {
			initLoadingHelper();
		}
		return mLoadingHelper;
	}

	int count = 0;

	private void loadData() {
		getLoadingHelper().showLoadingView();
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				switch (count % 2) {
				case 0:
					getLoadingHelper().showContentView();
					break;
				case 1:
					getLoadingHelper().showNetworkError();
					break;
				}
				count++;

			}
		}, 1500);
	}
}
