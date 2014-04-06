package com.example.android_gridview_test;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Parcelable;
import android.app.Activity;
import android.content.res.Resources;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ViewPager viewPager;
	private ArrayList<View> listViews;
	private List<View> dots;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViewPager();
	}
	/**
	 * 初始化ViewPager
	 */
	private void initViewPager() {
		int[] mThumbIdsForOne = {R.drawable.icon1, R.drawable.icon2, R.drawable.icon3, R.drawable.icon4, R.drawable.icon5, R.drawable.icon6,
				R.drawable.icon1, R.drawable.icon2, R.drawable.icon3, };  
		int[] mThumbIdsForTwo = {R.drawable.icon1, R.drawable.icon2,};  
		
		Resources res = this.getResources();  
		String[] stringsForOne = res.getStringArray(R.array.sms_title1);
		String[] stringsFortwo = res.getStringArray(R.array.sms_title2);
		
		dots = new ArrayList<View>();
		dots.add(findViewById(R.id.v_dot0));
		dots.add(findViewById(R.id.v_dot1));
		dots.add(findViewById(R.id.v_dot2));
		dots.add(findViewById(R.id.v_dot3));
		dots.add(findViewById(R.id.v_dot4));

		listViews = new ArrayList<View>();
		LayoutInflater mInflater = getLayoutInflater();

		// for (int i = 0; i < 3; i++) {};
		
		

		View grid_one = mInflater.inflate(R.layout.activity_grid_grid_one, null);
		GridView gridviewone = (GridView) grid_one.findViewById(R.id.gridview);
		gridviewone.setAdapter(new ImageAdapter(this, mThumbIdsForOne, stringsForOne));   
		gridviewone.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
			}
		});

		listViews.add(grid_one);

		mInflater = getLayoutInflater();
		View grid_two = mInflater.inflate(R.layout.activity_grid_grid_one, null);
		GridView gridviewtwo = (GridView) grid_two.findViewById(R.id.gridview);
		gridviewtwo.setAdapter(new ImageAdapter(this, mThumbIdsForTwo, stringsFortwo));
		gridviewtwo.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
			}
		});
		listViews.add(grid_two);

		viewPager = (ViewPager) findViewById(R.id.vp);
		viewPager.setAdapter(new MyAdapter(listViews));// 设置填充ViewPager页面的适配器
		viewPager.setCurrentItem(0);
		// 设置一个监听器，当ViewPager中的页面改变时调用
		viewPager.setOnPageChangeListener(new MyPageChangeListener());

	}
	/**
	 * 当ViewPager中页面的状态发生改变时调用
	 */
	private class MyPageChangeListener implements OnPageChangeListener {
		private int oldPosition = 0;
		/**
		 * This method will be invoked when a new page becomes selected.
		 * position: Position index of the new selected page.
		 */
		public void onPageSelected(int position) {
			// currentItem = position;
			// tv_title.setText(titles[position]);
			dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
			dots.get(position).setBackgroundResource(R.drawable.dot_focused);
			oldPosition = position;
		}
		public void onPageScrollStateChanged(int arg0) {
		}
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}
	}
	/**
	 * 填充ViewPager页面的适配器
	 */
	private class MyAdapter extends PagerAdapter {
		public List<View> mListViews;

		public MyAdapter(List<View> mListViews) {
			this.mListViews = mListViews;
		}
		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(mListViews.get(arg1));
		}
		@Override
		public void finishUpdate(View arg0) {
		}
		@Override
		public int getCount() {
			return mListViews.size();
		}
		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(mListViews.get(arg1), 0);
			return mListViews.get(arg1);
		}
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == (arg1);
		}
		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
		}
		@Override
		public Parcelable saveState() {
			return null;
		}
		@Override
		public void startUpdate(View arg0) {
		}
	}
}
