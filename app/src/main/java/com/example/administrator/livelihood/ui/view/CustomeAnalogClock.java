package com.example.administrator.livelihood.ui.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.text.format.Time;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.livelihood.R;

/**
 * 自定义闹钟控件
 */
public class CustomeAnalogClock extends View {
    private static final String TAG = "CustomeAnalogClockQQ";
    private Paint mDialPaint;
    private Paint mHourPaint;
    private Paint mMinutePaint;
    private Paint mSecondPaint;

    private Path mDialPath;
    private Path mHourPath;
    private Path mSecondPath;
    private Path mMinutePath;

    private Activity mActivity;
    private boolean mChange;
    private Time mTime;
    private float mSeconds;
    private float mMinute;
    private float mHour;

    private int mCircleX;
    private int mCircleY;
    private  float mRadius;


    public CustomeAnalogClock(Context context, AttributeSet attrs) {
        super(context, attrs);
        mActivity = (Activity) context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomeAnalogClock);
        mDialPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mHourPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mMinutePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSecondPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTime = new Time();
        initType(typedArray);
        //开启一个线程
        new TimeThread().start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mDialPath, mDialPaint);

        canvas.save();
        canvas.rotate(mHour / 12.0f * 360.0f, mCircleX, mCircleY);
        //  mHourPaint.setShadowLayer(mRadius/30f, (float)Math.sin(mHour/6f*Math.PI)*mRadius/25, (float)Math.cos(mHour/6f*Math.PI)*mRadius/25, shadowColor);
        canvas.drawPath(mHourPath, mHourPaint);
        canvas.restore();

        canvas.save();
        canvas.rotate(mMinute / 60.0f * 360.0f, mCircleX, mCircleY);
        //mMinutePaint.setShadowLayer(mRadius/30f, (float)Math.sin(mMinute/30f*Math.PI)*mRadius/20, (float)Math.cos(mMinute/30f*Math.PI)*mRadius/20, shadowColor);
        canvas.drawPath(mMinutePath, mMinutePaint);
        canvas.restore();

        canvas.save();
        canvas.rotate(mSeconds / 60.0f * 360.0f, mCircleX, mCircleY);
       // mSecondPaint.setShadowLayer(mRadius/60f, (float)Math.sin(mSeconds/30f*Math.PI)*mRadius/18, (float)Math.cos(mSeconds/30f*Math.PI)*mRadius/18, shadowColor);
        canvas.drawPath(mSecondPath, mSecondPaint);
        canvas.restore();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.w(TAG, "w:" + w + " h:" + h + " oldw:" + oldw + " oldh:" + oldh);
        mChange = true;
        updataClock(w, h);
    }


    private void initType(TypedArray typedArray) {
        mDialPaint.setColor(typedArray.getColor(R.styleable.CustomeAnalogClock_color_dial, Color.BLUE));
        mDialPaint.setStyle(Paint.Style.STROKE);
    }


    /**
     * View的sizi改变时调用
     *
     * @param w 改变后的 w
     * @param h
     */
    private void updataClock(int w, int h) {
        //直径
        float diameter = Math.min(w, h) * 0.85f;
        mRadius = diameter / 2;
        mCircleX = w / 2;
        mCircleY = h / 2;
        mDialPaint.setStrokeWidth(mRadius / 15);
        Log.w(TAG, "diameter:" + diameter);
        mDialPaint.setShadowLayer(mRadius, mCircleX, mCircleY, Color.RED);
        mDialPath = new Path();
        mDialPath.addCircle(mCircleX, mCircleY, mRadius * 1.1f, Path.Direction.CCW);
        int degree = 60;
        for (int i = 0; i < degree; i++) {
            if (i % 15 == 0) {
                mDialPath.addArc(new RectF(mCircleX - mRadius, mCircleY - mRadius, mCircleX + mRadius, mCircleY + mRadius), i * 6 - 1, 2);
                mDialPath.addArc(new RectF(mCircleX - mRadius * 0.96f, mCircleY - mRadius * 0.96f, mCircleX + mRadius * 0.96f, mCircleY + mRadius * 0.96f), i * 6 - 1, 2);
                mDialPath.addArc(new RectF(mCircleX - mRadius * 0.93f, mCircleY - mRadius * 0.93f, mCircleX + mRadius * 0.93f, mCircleY + mRadius * 0.93f), i * 6 - 1, 2);
                mDialPath.addArc(new RectF(mCircleX - mRadius * 0.9f, mCircleY - mRadius * 0.90f, mCircleX + mRadius * 0.9f, mCircleY + mRadius * 0.9f), i * 6 - 1, 2);
            } else if (i % 5 == 0) {
                mDialPath.addArc(new RectF(mCircleX - mRadius * 0.96f, mCircleY - mRadius * 0.96f, mCircleX + mRadius * 0.96f, mCircleY + mRadius * 0.96f), i * 6 - 0.5f, 1);
                mDialPath.addArc(new RectF(mCircleX - mRadius * 0.93f, mCircleY - mRadius * 0.93f, mCircleX + mRadius * 0.93f, mCircleY + mRadius * 0.93f), i * 6 - 0.5f, 1);
                mDialPath.addArc(new RectF(mCircleX - mRadius * 0.9f, mCircleY - mRadius * 0.90f, mCircleX + mRadius * 0.9f, mCircleY + mRadius * 0.9f), i * 6 - 0.5f, 1);
            }
            mDialPath.addArc(new RectF(mCircleX - mRadius, mCircleY - mRadius, mCircleX + mRadius, mCircleY + mRadius), i * 6 - 0.5f, 1);
        }
        mHourPath = new Path();
        mHourPath.addRoundRect(new RectF(100, 200, 500, 300), 20, 0, Path.Direction.CCW);
        mHourPath = new Path();
        mHourPath.addRoundRect(new RectF(mCircleX - mRadius / 30, mCircleY - mRadius / 2, mCircleX + mRadius / 30, mCircleY + mRadius / 6), mRadius / 50, mRadius / 50, Path.Direction.CW);

        mMinutePath = new Path();
        mMinutePath.addRoundRect(new RectF(mCircleX - mRadius / 50, mCircleY - mRadius * 0.95f, mCircleX + mRadius / 50, mCircleY + mRadius / 4), mRadius / 50, mRadius / 50, Path.Direction.CW);

        mSecondPath = new Path();
        mSecondPath.addRoundRect(new RectF(mCircleX - mRadius / 100, mCircleY - mRadius * 1.00f, mCircleX + mRadius / 100, mCircleY - mRadius * 0.50f), mRadius / 100f, mRadius / 100f, Path.Direction.CW);
        mSecondPath.addCircle(mCircleX, mCircleY - mRadius * 0.40f, mRadius / 25f, Path.Direction.CW);
        mSecondPath.addRoundRect(new RectF(mCircleX - mRadius / 50, mCircleY - mRadius * 0.30f, mCircleX + mRadius / 50, mCircleY - mRadius * 0.20f), mRadius / 100f, 2f, Path.Direction.CW);
        mSecondPath.addRoundRect(new RectF(mCircleX - mRadius / 100, mCircleY - mRadius * 0.15f, mCircleX + mRadius / 100, mCircleY - mRadius * 0.05f), mRadius / 100f, mRadius / 100f, Path.Direction.CW);
    }

    class TimeThread extends Thread {
        @Override
        public void run() {
            super.run();
            while (!Thread.currentThread().isInterrupted()) {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        onTimeChange();
                        invalidate();
                    }
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private void onTimeChange() {
        mTime.setToNow();
        int second = mTime.second;
        int minute = mTime.minute;
        int hour = mTime.hour;

        mSeconds = second;
        mMinute = minute + second / 60.0f;
        mHour = hour + mMinute / 60.0f;
        mChange = true;
    }
}
