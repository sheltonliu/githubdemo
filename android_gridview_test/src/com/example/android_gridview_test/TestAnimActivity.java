package com.example.android_gridview_test;

import com.example.android_gridview_test.SlipSwitchView.OnSwitchListener;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
/**
 * 测试动画
 * @author liushuo
 */
public class TestAnimActivity extends Activity {
	protected static final String TAG = "TestAnimActivity";  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_anim); 
		findView();
	}
	private void findView() {
		ProgressBar progress_cloud_bar = (ProgressBar) findViewById(R.id.progress_cloud_bar); 
		SlipSwitchView slipSwitchView = (SlipSwitchView) findViewById(R.id.view_switchview); 
		
		ImageView iv_slip_off_on = (ImageView) findViewById(R.id.iv_slip_off_on);
		ImageView iv_slip_bar = (ImageView) findViewById(R.id.iv_slip_bar);
		
		TextView tv = (TextView) findViewById(R.id.tv);
		tv.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Log.i(TAG, "setOnTouchListener-------");
				return false;
			}
		});
		
		
		slipSwitchView.setImageResource(R.drawable.slip_off_on, R.drawable.slip_off_on, R.drawable.slip_bar);
//		slipSwitchView.setImageResource(R.id.iv_slip_off_on, R.id.iv_slip_off_on, R.id.iv_slip_bar);
		slipSwitchView.setSwitchState(true);
		slipSwitchView.setOnSwitchListener(new OnSwitchListener() {
			@Override
			public void onSwitched(boolean isSwitchOn) {
				Log.i(TAG, "isSwitchOn----"+isSwitchOn);  
			}
		});
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.i(TAG, "onTouchEvent-------");
		return super.onTouchEvent(event);
	}
}
