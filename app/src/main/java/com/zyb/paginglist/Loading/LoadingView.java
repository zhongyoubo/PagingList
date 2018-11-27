package com.zyb.paginglist.Loading;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.zyb.paginglist.R;

/**
 * Created by Sky000 on 2018/11/26.
 */

public class LoadingView extends View {

    private int mWidth;
    private int mHeight;
    private int mCenterX;//X坐标轴中心点
    private int mCenterY;//Y坐标轴中心点
    private final int mDefaultColor = 0xff999999;
    private final int mDefaultItemCount = 8;
    private final int mDefaultItemRadius = 1;
    private final int mDefaultItemWidth = 10;
    private final int mDefaultItemLength = 20;

    private int mItemColor = mDefaultColor;
    private int mItemWidth = mDefaultItemWidth;
    private int mItemLength = mDefaultItemLength;
    private int mItemCount = mDefaultItemCount;
    private int mItemRadius = mDefaultItemRadius;
    private boolean mIsItemRect = true;//菊花瓣为长方形还是圆形

    private int control = 1;

    private Paint mPaint;

    private String[] colors = {"#bbbbbb", "#aaaaaa", "#999999", "#888888", "#777777", "#666666",};

    // 自定义View有四个构造函数
    /**
     * 如果View是在Java代码里面new的，则调用第一个构造函数
      */
    public LoadingView(Context context) {
        super(context);
        init();
    }

    /**
     * 如果View是在.xml里声明的，则调用第二个构造函数
     * 自定义属性是从AttributeSet参数传进来的
     * @param context
     * @param attrs
     */
    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        //super(context, attrs);
        this(context,attrs,0);//原来是super(context, attrs);
        init();
    }

    /**
     *  不会自动调用
     * 一般是在第二个构造函数里主动调用
     * 如View有style属性时
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //自定义属性Custom attribute，Reading values from the XML layout，需要在构造方法中解析
        TypedArray typedArray = context.
                obtainStyledAttributes(attrs, R.styleable.LoadingView,0,0);
        try {
            mItemColor = typedArray.getColor(R.styleable.LoadingView_loading_pathColor,mDefaultColor);
            mItemWidth = typedArray.getDimensionPixelSize(R.styleable.LoadingView_loading_itemWidth,mDefaultItemWidth);
            mItemLength = typedArray.getDimensionPixelSize(R.styleable.LoadingView_loading_itemLength,mDefaultItemLength);
            mItemCount = typedArray.getInt(R.styleable.LoadingView_loading_itemCount,mDefaultItemCount);
            mItemRadius = typedArray.getDimensionPixelSize(R.styleable.LoadingView_loading_itemRadius,mDefaultItemRadius);
            mIsItemRect = typedArray.getBoolean(R.styleable.LoadingView_loading_isItemRect,true);
        }finally {
            if(typedArray != null){
                typedArray.recycle();
            }
        }
        init();
    }

    /**
     * API21之后才使用
     * 不会自动调用
     * 一般是在第二个构造函数里主动调用
     * 如View有style属性时
     * @param context
     * @param attrs
     * @param defStyleAttr
     * @param defStyleRes
     */
    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mItemColor);//设置画笔颜色
        mPaint.setStrokeWidth(mItemWidth);//设置线宽
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        mCenterX = mWidth/2;
        mCenterY = mHeight/2;
    }

    /**
     * @param canvas
     * 绘制的原理就是先画出一段line, 然后将canvas旋转,此例是旋转30度,
     * 我们都知道圆的一周是360度,这样正好需要绘制12个线段,然后每次画布旋转30度
     */

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.drawColor(Color.WHITE);//设置背景颜色
        if(mIsItemRect){//画线
            for(int i=0;i<mItemCount;i++){
                mPaint.setAlpha(((i + 1 + control) % mItemCount * 255) / mItemCount);
                canvas.drawLine(mCenterX,mCenterY-mItemLength,mCenterX,mCenterY-mItemLength*2,mPaint);
                canvas.rotate(360/mItemCount,mCenterX,mCenterY);//原点为中心，旋转360/mItemCount度（顺时针方向为正方向 ）
            }
        }else{//画圆
            for(int i=0;i<mItemCount;i++){
                mPaint.setAlpha(((i + 1 + control) % mItemCount * 255) / mItemCount);
                canvas.drawCircle(mCenterX,mCenterY+mItemLength,mItemRadius,mPaint);
                canvas.rotate(360/mItemCount,mCenterX,mCenterY);//原点为中心，画布旋转30度（顺时针方向为正方向 ）
            }
        }

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ValueAnimator valueAnimator = ValueAnimator.ofInt(mItemCount,1);
        valueAnimator.setDuration(1200);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                control = (int) animation.getAnimatedValue();//control:1~mItemCount
                invalidate();
            }
        });
        valueAnimator.start();
    }
}
