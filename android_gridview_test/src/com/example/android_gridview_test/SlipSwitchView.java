package com.example.android_gridview_test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * 设置界面好友相互定位滑动按钮
 * 
 * @author dongjiejun
 * @since 1.0
 */
public class SlipSwitchView extends View implements OnTouchListener {

	private Bitmap switch_on_background, switch_off_background, slip_Btn;
	private Rect on_Rect, off_Rect;

	/** 是否正在滑动 */
	private boolean isSlipping = false;

	/** 当前开关状态，true为开启，false为关闭 */
	private boolean isSwitchOn = false;

	/** 手指按下时的水平坐标X，当前的水平坐标X */
	private float previousX, currentX;

	/** 开关监听器 */
	private OnSwitchListener onSwitchListener;

	/** 是否设置开关监听器 */
	private boolean isSwitchListenerOn = false;
	private int slip_Btn_Width;
	private int slip_Btn_Height;

	public SlipSwitchView(Context context) {
		super(context);
		init();
	}

	public SlipSwitchView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		setOnTouchListener(this);
	}

	public void setImageResource(int switchOnBackground, int switchOffBackground, int slipBar) {
		switch_on_background = BitmapFactory.decodeResource(getResources(), switchOnBackground);
		switch_off_background = BitmapFactory.decodeResource(getResources(), switchOffBackground);
		
		slip_Btn = BitmapFactory.decodeResource(getResources(), slipBar);
		slip_Btn_Width = slip_Btn.getWidth() ;
		slip_Btn_Height = slip_Btn.getHeight()   ;
		
		
		on_Rect = new Rect(switch_off_background.getWidth() - slip_Btn_Width, 0, switch_off_background.getWidth(), slip_Btn_Height);
		off_Rect = new Rect(0, 0, slip_Btn_Width, slip_Btn_Height);
	}

	public void setSwitchState(boolean switchState) {
		isSwitchOn = switchState;
	}

	protected boolean getSwitchState() {
		return isSwitchOn;
	}

	protected void updateSwitchState(boolean switchState) {
		isSwitchOn = switchState;
		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		Matrix matrix = new Matrix();
		Paint paint = new Paint();
		// 滑动按钮的左边坐标
		float slipButtonLeftX;

		// 手指滑动到左半边的时候表示开关为关闭状态，滑动到右半边的时候表示开关为开启状态
		if (currentX < (switch_on_background.getWidth() / 2)) {
			canvas.drawBitmap(switch_off_background, matrix, paint);
		} else {
			canvas.drawBitmap(switch_on_background, matrix, paint);
		}

		// 判断当前是否正在滑动
		if (isSlipping) {
			if (currentX > switch_on_background.getWidth()) {
				slipButtonLeftX = switch_on_background.getWidth() - slip_Btn_Width;
			} else {
				slipButtonLeftX = currentX - slip_Btn_Width / 2;
			}
		} else {
			// 根据当前的开关状态设置滑动按钮的位置
			if (isSwitchOn) {
				slipButtonLeftX = on_Rect.left - 3;
			} else {
				slipButtonLeftX = off_Rect.left + 3;
			}
		}

		// 校正滑动按钮的位置
		if (slipButtonLeftX < 0) {
			slipButtonLeftX = 0;
		} else if (slipButtonLeftX > switch_on_background.getWidth() - slip_Btn_Width) {
			slipButtonLeftX = switch_on_background.getWidth() - slip_Btn_Width;
		}

//		canvas.drawBitmap(slip_Btn, slipButtonLeftX, (float) -0.5, paint);
		canvas.drawBitmap(slip_Btn, slipButtonLeftX, (float) -0.5, paint);
	
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		setMeasuredDimension(switch_on_background.getWidth(), switch_on_background.getHeight()); 
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		// 滑动
			case MotionEvent.ACTION_MOVE :
				currentX = event.getX();
				break;

			// 按下
			case MotionEvent.ACTION_DOWN :
				if (event.getX() > switch_on_background.getWidth() || event.getY() > switch_on_background.getHeight()) {
					return false;
				}

				isSlipping = true;
				previousX = event.getX();
				currentX = previousX;
				break;

			// 松开
			case MotionEvent.ACTION_UP :
				isSlipping = false;
				// 松开前开关的状态
				boolean previousSwitchState = isSwitchOn;

				if (event.getX() >= (switch_on_background.getWidth() / 2)) {
					isSwitchOn = true;
				} else {
					isSwitchOn = false;
				}

				// 如果设置了监听器，则调用此方法
				if (isSwitchListenerOn && (previousSwitchState != isSwitchOn)) {
					onSwitchListener.onSwitched(isSwitchOn);
				}
				break;

			default :
				break;
		}
		invalidate();
		return true;
	}
	public void setOnSwitchListener(OnSwitchListener listener) {
		onSwitchListener = listener;
		isSwitchListenerOn = true;
	}
	public interface OnSwitchListener {
		abstract void onSwitched(boolean isSwitchOn);
	}
}
