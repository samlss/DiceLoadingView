package com.iigo.library;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

/**
 * @author SamLeung
 * @e-mail 729717222@qq.com
 * @github https://github.com/samlss
 * @csdn https://blog.csdn.net/Samlss
 * @description A dice 3D rotation loading view.
 */
public class DiceLoadingView extends ViewGroup {
    private final static int DEFAULT_DURATION = 2500;
    private final static int DEFAULT_SIZE = 200; //200 px
    private final static int FIXED_CHILD_COUNT = 7; //The fixed child count.

    /**
     * The real width of this view.
     * */
    private int mRealWidth;

    /**
     * The real height of this view.
     * */
    private int mRealHeight;

    /**
     * The width of child, need to subtract {@link #getPaddingLeft()} ()}, {@link #getPaddingRight()}.
     * */
    private int mChildWidth;
    /**
     * The height of child, need to subtract {@link #getPaddingTop()}, {@link #getPaddingBottom()}.
     * */
    private int mChildHeight;

    private int mFirstSideDiceNumber      = DiceView.NUMBER_ONE;
    private int mFirstSidePointColor      = DiceView.DEFAULT_COLOR;
    private int mFirstSideDiceBgColor     = DiceView.DEFAULT_BG_COLOR;
    private int mFirstSideDiceBorderColor = DiceView.DEFAULT_COLOR;

    private int mSecondSideDiceNumber      = DiceView.NUMBER_FIVE;
    private int mSecondSidePointColor      = DiceView.DEFAULT_COLOR;
    private int mSecondSideDiceBgColor     = DiceView.DEFAULT_BG_COLOR;
    private int mSecondSideDiceBorderColor = DiceView.DEFAULT_COLOR;

    private int mThirdSideDiceNumber      = DiceView.NUMBER_SIX;
    private int mThirdSidePointColor      = DiceView.DEFAULT_COLOR;
    private int mThirdSideDiceBgColor     = DiceView.DEFAULT_BG_COLOR;
    private int mThirdSideDiceBorderColor = DiceView.DEFAULT_COLOR;

    private int mFourthSideDiceNumber      = DiceView.NUMBER_TWO;
    private int mFourthSidePointColor      = DiceView.DEFAULT_COLOR;
    private int mFourthSideDiceBgColor     = DiceView.DEFAULT_BG_COLOR;
    private int mFourthSideDiceBorderColor = DiceView.DEFAULT_COLOR;

    private Camera mCamera;
    private Matrix mMatrix;

    private ValueAnimator mValueAnimator;
    private int mAnimatedValue;
    private long mAnimatorPlayTime;

    private DiceView[] mDiceViews = new DiceView[FIXED_CHILD_COUNT];
    private TimeInterpolator mInterpolator = new AccelerateDecelerateInterpolator();
    private long mDuration = DEFAULT_DURATION;

    public DiceLoadingView(Context context) {
        this(context, null);
    }

    public DiceLoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DiceLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        parseAttr(attrs);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DiceLoadingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        parseAttr(attrs);
        init();
    }

    private void parseAttr(AttributeSet attrs){
        if (attrs == null){
            return;
        }

        TypedArray typedArray     = getContext().obtainStyledAttributes(attrs, R.styleable.DiceLoadingView);
        mDuration = typedArray.getInteger(R.styleable.DiceLoadingView_animDuration, DEFAULT_DURATION);

        if (mDuration < 0){
            mDuration = DEFAULT_DURATION;
        }

        int interpolatorValue = typedArray.getInt(R.styleable.DiceLoadingView_animInterpolator, 0);

        switch (interpolatorValue){
            case 0:
                mInterpolator = new AccelerateDecelerateInterpolator();
                break;

            case 1:
                mInterpolator = new AccelerateInterpolator();
                break;

            case 2:
                mInterpolator = new DecelerateInterpolator();
                break;

            case 3:
                mInterpolator = new BounceInterpolator();
                break;

            case 4:
                mInterpolator = new CycleInterpolator(0.5f);
                break;

            case 5:
                mInterpolator = new LinearInterpolator();
                break;

            case 6:
                mInterpolator = new AnticipateOvershootInterpolator();
                break;

            case 7:
                mInterpolator = new AnticipateInterpolator();
                break;

            case 8:
                mInterpolator = new OvershootInterpolator();
                break;

            default:
                break;
        }

        mFirstSideDiceNumber      = typedArray.getInteger(R.styleable.DiceLoadingView_firstSideDiceNumber, DiceView.NUMBER_ONE);
        mFirstSidePointColor      = typedArray.getColor(R.styleable.DiceLoadingView_firstSideDicePointColor, DiceView.DEFAULT_COLOR);
        mFirstSideDiceBgColor     = typedArray.getColor(R.styleable.DiceLoadingView_firstSideDiceBgColor, DiceView.DEFAULT_BG_COLOR);
        mFirstSideDiceBorderColor = typedArray.getColor(R.styleable.DiceLoadingView_firstSideDiceBorderColor, DiceView.DEFAULT_COLOR);

        mSecondSideDiceNumber      = typedArray.getInteger(R.styleable.DiceLoadingView_secondSideDiceNumber, DiceView.NUMBER_FIVE);
        mSecondSidePointColor      = typedArray.getColor(R.styleable.DiceLoadingView_secondSideDicePointColor, DiceView.DEFAULT_COLOR);
        mSecondSideDiceBgColor     = typedArray.getColor(R.styleable.DiceLoadingView_secondSideDiceBgColor, DiceView.DEFAULT_BG_COLOR);
        mSecondSideDiceBorderColor = typedArray.getColor(R.styleable.DiceLoadingView_secondSideDiceBorderColor, DiceView.DEFAULT_COLOR);

        mThirdSideDiceNumber      = typedArray.getInteger(R.styleable.DiceLoadingView_thirdSideDiceNumber, DiceView.NUMBER_SIX);
        mThirdSidePointColor      = typedArray.getColor(R.styleable.DiceLoadingView_thirdSideDicePointColor, DiceView.DEFAULT_COLOR);
        mThirdSideDiceBgColor     = typedArray.getColor(R.styleable.DiceLoadingView_thirdSideDiceBgColor, DiceView.DEFAULT_BG_COLOR);
        mThirdSideDiceBorderColor = typedArray.getColor(R.styleable.DiceLoadingView_thirdSideDiceBorderColor, DiceView.DEFAULT_COLOR);

        mFourthSideDiceNumber      = typedArray.getInteger(R.styleable.DiceLoadingView_fourthSideDiceNumber, DiceView.NUMBER_TWO);
        mFourthSidePointColor      = typedArray.getColor(R.styleable.DiceLoadingView_fourthSideDicePointColor, DiceView.DEFAULT_COLOR);
        mFourthSideDiceBgColor     = typedArray.getColor(R.styleable.DiceLoadingView_fourthSideDiceBgColor, DiceView.DEFAULT_BG_COLOR);
        mFourthSideDiceBorderColor = typedArray.getColor(R.styleable.DiceLoadingView_fourthSideDiceBorderColor, DiceView.DEFAULT_COLOR);

        typedArray.recycle();
    }

    private void init(){
        mCamera = new Camera();
        mMatrix = new Matrix();

        addChildViews();
    }

    private void addChildViews(){
        mDiceViews[0] = new DiceView(getContext());
        mDiceViews[0].setNumber(mFourthSideDiceNumber);
        mDiceViews[0].setPointColor(mFourthSidePointColor);
        mDiceViews[0].setBgColor(mFourthSideDiceBgColor);
        mDiceViews[0].setBorderColor(mFourthSideDiceBorderColor);

        mDiceViews[1] = new DiceView(getContext());
        mDiceViews[1].setNumber(mFirstSideDiceNumber);
        mDiceViews[1].setPointColor(mFirstSidePointColor);
        mDiceViews[1].setBgColor(mFirstSideDiceBgColor);
        mDiceViews[1].setBorderColor(mFirstSideDiceBorderColor);

        mDiceViews[2] = new DiceView(getContext());
        mDiceViews[2].setNumber(mSecondSideDiceNumber);
        mDiceViews[2].setPointColor(mSecondSidePointColor);
        mDiceViews[2].setBgColor(mSecondSideDiceBgColor);
        mDiceViews[2].setBorderColor(mSecondSideDiceBorderColor);

        mDiceViews[3] = new DiceView(getContext());
        mDiceViews[3].setNumber(mThirdSideDiceNumber);
        mDiceViews[3].setPointColor(mThirdSidePointColor);
        mDiceViews[3].setBgColor(mThirdSideDiceBgColor);
        mDiceViews[3].setBorderColor(mThirdSideDiceBorderColor);

        mDiceViews[4] = new DiceView(getContext());
        mDiceViews[4].setNumber(mFourthSideDiceNumber);
        mDiceViews[4].setPointColor(mFourthSidePointColor);
        mDiceViews[4].setBgColor(mFourthSideDiceBgColor);
        mDiceViews[4].setBorderColor(mFourthSideDiceBorderColor);

        mDiceViews[5] = new DiceView(getContext());
        mDiceViews[5].setNumber(mFirstSideDiceNumber);
        mDiceViews[5].setPointColor(mFirstSidePointColor);
        mDiceViews[5].setBgColor(mFirstSideDiceBgColor);
        mDiceViews[5].setBorderColor(mFirstSideDiceBorderColor);

        mDiceViews[6] = new DiceView(getContext());
        mDiceViews[6].setNumber(mSecondSideDiceNumber);
        mDiceViews[6].setPointColor(mSecondSidePointColor);
        mDiceViews[6].setBgColor(mSecondSideDiceBgColor);
        mDiceViews[6].setBorderColor(mSecondSideDiceBorderColor);

        for (DiceView diceView : mDiceViews){
            addView(diceView, -1);
        }
    }

    /**
     * In order not to cut off the dice border, check and set padding here.
     * */
    private void checkPadding(int width, int height){
        int minSize = Math.min(width, height);

        int paddingLeft   = getPaddingLeft();
        int paddingRight  = getPaddingRight();
        int paddingTop    = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        int defPadding = minSize / 8;

        if (paddingLeft < defPadding){
            paddingLeft = defPadding;
        }

        if (paddingRight < defPadding){
            paddingRight = defPadding;
        }

        if (paddingTop < defPadding){
            paddingTop = defPadding;
        }

        if (paddingBottom < defPadding){
            paddingBottom = defPadding;
        }


        setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    @Override
    public void addView(View child, int index, LayoutParams params) {
        if (child instanceof DiceView == false){
            return;
        }

        super.addView(child, index, params);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);

        int w = widthSpecSize;
        int h = heightSpecSize;

        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            w = DEFAULT_SIZE;
            h = DEFAULT_SIZE;
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            w = DEFAULT_SIZE;
            h = heightSpecSize;
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            w = widthSpecSize;
            h = DEFAULT_SIZE;
        }

        mRealWidth  = w;
        mRealHeight = h;

        setMeasuredDimension(w, h);
        checkPadding(w, h);

        mChildWidth  = w - getPaddingLeft() - getPaddingRight();
        mChildHeight = h - getPaddingTop() - getPaddingBottom();

        measureChildren(MeasureSpec.makeMeasureSpec(mRealWidth, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(mRealHeight, MeasureSpec.EXACTLY));
    }

    @Override
    protected void onLayout(boolean b, int left, int top, int right, int bottom) {
        int childLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                child.layout(childLeft, paddingTop,
                        childLeft + child.getMeasuredWidth(), paddingTop + child.getMeasuredHeight());

                childLeft = childLeft + child.getMeasuredWidth();
            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        setupAnimator();
    }

    private void setupAnimator(){
        release();

        if (getChildCount() < FIXED_CHILD_COUNT){
            return;
        }

        mValueAnimator = ValueAnimator.ofInt(0, mChildWidth * 4);

        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mAnimatedValue = (int) valueAnimator.getAnimatedValue();
                scrollTo(mAnimatedValue+mChildWidth, 0);
                invalidate();
            }
        });

        mValueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationRepeat(Animator animation) {
                scrollTo(mChildWidth, 0);
            }
        });

        mValueAnimator.setInterpolator(mInterpolator);
        mValueAnimator.setDuration(mDuration);
        mValueAnimator.setRepeatMode(ValueAnimator.RESTART);
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.start();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        for (int i = 0; i < getChildCount(); i++) {
            drawChild(canvas, i, getChildAt(i));
        }
    }

    /**
     * Draw the child.
     *
     * @param canvas
     * @param index
     * @param child
     * */
    private void drawChild(Canvas canvas, int index, View child){
        int childLeft = mChildWidth * index + getPaddingLeft();
        int scrollX = getScrollX() + getPaddingLeft();

        if (scrollX + mChildWidth < childLeft) {
            return;
        }

        if (childLeft < scrollX - mChildWidth) {
            return;
        }

        float centerX = (scrollX > childLeft) ? childLeft + mChildWidth : childLeft ;
        float centerY = mChildHeight / 2;

        float degree = -90 * (scrollX - childLeft) / mChildWidth;

        if (degree > 90 || degree < -90) {
            return;
        }

        canvas.save();
        mCamera.save();
        mCamera.rotateY(degree);
        mCamera.getMatrix(mMatrix);
        mCamera.restore();

        mMatrix.preTranslate(-centerX, -centerY);
        mMatrix.postTranslate(centerX, centerY);

        canvas.concat(mMatrix);
        drawChild(canvas, child, getDrawingTime());
        canvas.restore();
    }

    /**
     * Pause the animation.
     * */
    public void pause(){
        if (mValueAnimator != null && mValueAnimator.isRunning()){
            mAnimatorPlayTime = mValueAnimator.getCurrentPlayTime();
            mValueAnimator.cancel();
        }
    }

    /**
     * Resume the animation.
     * */
    public void resume(){
        if (mValueAnimator != null && !mValueAnimator.isRunning()){
            mValueAnimator.setCurrentPlayTime(mAnimatorPlayTime);
            mValueAnimator.start();
        }
    }

    /**
     * Start the animation.
     * */
    public void start(){
        mAnimatorPlayTime = 0;
        if (mValueAnimator != null){
            mValueAnimator.start();
        }
    }

    /**
     * Cancel the animation.
     * */
    public void stop(){
        if (mValueAnimator != null){
            mValueAnimator.cancel();
        }
    }

    /**
     * Release this view when you do not need it.
     * */
    public void release(){
        stop();
        if (mValueAnimator != null){
            mValueAnimator.removeAllUpdateListeners();
            mValueAnimator.removeAllListeners();
        }
    }

    /**
     * Get the dice number of the first dice side.
     * */
    public int getFirstSideDiceNumber() {
        return mFirstSideDiceNumber;
    }

    /**
     * Set the dice number{1-6} of the first dice side.
     * */
    public void setFirstSideDiceNumber(int firstSideDiceNumber) {
        this.mFirstSideDiceNumber = firstSideDiceNumber;

        mDiceViews[1].setNumber(mFirstSideDiceNumber);
        mDiceViews[5].setNumber(mFirstSideDiceNumber);
    }

    /**
     * Get the point color of the first dice side.
     * */
    public int getFirstSidePointColor() {
        return mFirstSidePointColor;
    }

    /**
     * Set the point color of the first dice side.
     * */
    public void setFirstSidePointColor(int firstSidePointColor) {
        this.mFirstSidePointColor = firstSidePointColor;

        mDiceViews[1].setPointColor(mFirstSidePointColor);
        mDiceViews[5].setPointColor(mFirstSidePointColor);
    }

    /**
     * Get the bg color of the first dice side.
     * */
    public int getFirstSideDiceBgColor() {
        return mFirstSideDiceBgColor;
    }

    /**
     * Set the bg color of the first dice side.
     * */
    public void setFirstSideDiceBgColor(int firstSideDiceBgColor) {
        this.mFirstSideDiceBgColor = firstSideDiceBgColor;
        mDiceViews[1].setBgColor(mFirstSideDiceBgColor);
        mDiceViews[5].setBgColor(mFirstSideDiceBgColor);
    }

    /**
     * Get the border color of the first dice side.
     * */
    public int getFirstSideDiceBorderColor() {
        return mFirstSideDiceBorderColor;
    }

    /**
     * Set the border color of the first dice side.
     * */
    public void setFirstSideDiceBorderColor(int firstSideDiceBorderColor) {
        this.mFirstSideDiceBorderColor = firstSideDiceBorderColor;
        mDiceViews[1].setBorderColor(mFirstSideDiceBorderColor);
        mDiceViews[5].setBorderColor(mFirstSideDiceBorderColor);
    }

    /**
     * Set the dice number of the second dice side.
     * */
    public int getSecondSideDiceNumber() {
        return mSecondSideDiceNumber;
    }

    /**
     * Set the dice number{1-6} of the second dice side.
     * */
    public void setSecondSideDiceNumber(int secondSideDiceNumber) {
        this.mSecondSideDiceNumber = secondSideDiceNumber;
        mDiceViews[2].setNumber(mSecondSideDiceNumber);
        mDiceViews[6].setNumber(mSecondSideDiceNumber);
    }

    /**
     * Get the point color of the second dice side.
     * */
    public int getSecondSidePointColor() {
        return mSecondSidePointColor;
    }

    /**
     * Set the point color of the second dice side.
     * */
    public void setSecondSidePointColor(int secondSidePointColor) {
        this.mSecondSidePointColor = secondSidePointColor;

        mDiceViews[2].setPointColor(mSecondSidePointColor);
        mDiceViews[6].setPointColor(mSecondSidePointColor);
    }

    /**
     * Get the bg color of the second dice side.
     * */
    public int getSecondSideDiceBgColor() {
        return mSecondSideDiceBgColor;
    }

    /**
     * Set the bg color of the second dice side.
     * */
    public void setSecondSideDiceBgColor(int secondSideDiceBgColor) {
        this.mSecondSideDiceBgColor = secondSideDiceBgColor;
        mDiceViews[2].setBgColor(mSecondSideDiceBgColor);
        mDiceViews[6].setBgColor(mSecondSideDiceBgColor);
    }

    /**
     * Get the border color of the second dice side.
     * */
    public int getSecondSideDiceBorderColor() {
        return mSecondSideDiceBorderColor;
    }

    /**
     * Set the border color of the second dice side.
     * */
    public void setSecondSideDiceBorderColor(int secondSideDiceBorderColor) {
        this.mSecondSideDiceBorderColor = secondSideDiceBorderColor;

        mDiceViews[2].setBorderColor(mSecondSideDiceBorderColor);
        mDiceViews[6].setBorderColor(mSecondSideDiceBorderColor);
    }

    /**
     * Get the dice number of the third dice side.
     * */
    public int getThirdSideDiceNumber() {
        return mThirdSideDiceNumber;
    }

    /**
     * Set the dice number{1-6} of the third dice side.
     * */
    public void setThirdSideDiceNumber(int thirdSideDiceNumber) {
        this.mThirdSideDiceNumber = thirdSideDiceNumber;
        mDiceViews[3].setNumber(mThirdSideDiceNumber);
    }

    /**
     * Get the point color of the third dice side.
     * */
    public int getThirdSidePointColor() {
        return mThirdSidePointColor;
    }

    /**
     * Set the point color of the third dice side.
     * */
    public void setThirdSidePointColor(int thirdSidePointColor) {
        this.mThirdSidePointColor = thirdSidePointColor;
        mDiceViews[3].setPointColor(mThirdSidePointColor);
    }

    /**
     * Get the bg color of the third dice side.
     * */
    public int getThirdSideDiceBgColor() {
        return mThirdSideDiceBgColor;
    }

    /**
     * Set the bg color of the third dice side.
     * */
    public void setThirdSideDiceBgColor(int thirdSideDiceBgColor) {
        this.mThirdSideDiceBgColor = thirdSideDiceBgColor;
        mDiceViews[3].setBgColor(mThirdSideDiceBgColor);
    }

    /**
     * Get the border color of the third dice side.
     * */
    public int getThirdSideDiceBorderColor() {
        return mThirdSideDiceBorderColor;
    }

    /**
     * Set the border color of the third dice side.
     * */
    public void setThirdSideDiceBorderColor(int thirdSideDiceBorderColor) {
        this.mThirdSideDiceBorderColor = thirdSideDiceBorderColor;
        mDiceViews[3].setBorderColor(mThirdSideDiceBorderColor);
    }

    /**
     * Get the dice number of the fourth dice side.
     * */
    public int getFourthSideDiceNumber() {
        return mFourthSideDiceNumber;
    }

    /**
     * Set the dice number{1-6} of the fourth dice side.
     * */
    public void setFourthSideDiceNumber(int fourthSideDiceNumber) {
        this.mFourthSideDiceNumber = fourthSideDiceNumber;

        mDiceViews[0].setNumber(mFourthSideDiceNumber);
        mDiceViews[4].setNumber(mFourthSideDiceNumber);
    }

    /**
     * Get the point color of the fourth dice side.
     * */
    public int getFourthSidePointColor() {
        return mFourthSidePointColor;
    }

    /**
     * Set the point color of the fourth dice side.
     * */
    public void setFourthSidePointColor(int fourthSidePointColor) {
        this.mFourthSidePointColor = fourthSidePointColor;

        mDiceViews[0].setPointColor(mFourthSidePointColor);
        mDiceViews[4].setPointColor(mFourthSidePointColor);
    }

    /**
     * Get the bg color of the fourth dice side.
     * */
    public int getFourthSideDiceBgColor() {
        return mFourthSideDiceBgColor;
    }

    /**
     * Set the bg color of the fourth dice side.
     * */
    public void setFourthSideDiceBgColor(int fourthSideDiceBgColor) {
        this.mFourthSideDiceBgColor = fourthSideDiceBgColor;
        mDiceViews[0].setBgColor(mFourthSideDiceBgColor);
        mDiceViews[4].setBgColor(mFourthSideDiceBgColor);
    }

    /**
     * Get the border color of the fourth dice side.
     * */
    public int getFourthSideDiceBorderColor() {
        return mFourthSideDiceBorderColor;
    }

    /**
     * Set the border color of the fourth dice side.
     * */
    public void setFourthSideDiceBorderColor(int fourthSideDiceBorderColor) {
        this.mFourthSideDiceBorderColor = fourthSideDiceBorderColor;
        mDiceViews[0].setBorderColor(mFourthSideDiceBorderColor);
        mDiceViews[4].setBorderColor(mFourthSideDiceBorderColor);
    }

    /**
     * Get the animation's interpolator.
     * */
    public TimeInterpolator getInterpolator() {
        return mInterpolator;
    }

    /**
     * Set the animation's interpolator.
     * */
    public void setInterpolator(TimeInterpolator mInterpolator) {
        this.mInterpolator = mInterpolator;
        setupAnimator();
    }

    /**
     * Get the animation 's duration.
     * */
    public long getDuration() {
        return mDuration;
    }

    /**
     * Set the animation 's duration.
     * */
    public void setDuration(long duration) {
        this.mDuration = duration;
        setupAnimator();
    }
}

