package com.iigo.library;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author SamLeung
 * @e-mail 729717222@qq.com
 * @github https://github.com/samlss
 * @csdn https://blog.csdn.net/Samlss
 * @description The dice view.
 */
public class DiceView extends View {
    public final static int DEFAULT_COLOR = Color.parseColor("#545453");
    public final static int DEFAULT_BG_COLOR = Color.parseColor("#FFFFCB");

    public final static int NUMBER_ONE = 1;
    public final static int NUMBER_TWO = 2;
    public final static int NUMBER_THREE = 3;
    public final static int NUMBER_FOUR = 4;
    public final static int NUMBER_FIVE = 5;
    public final static int NUMBER_SIX = 6;

    /**
     * The number of dice, one to six.
     * */
    private int mNumber = NUMBER_ONE;
    /**
     * The border color.
     * */
    private int mBorderColor = DEFAULT_COLOR;

    /**
     * The point color.
     * */
    private int mPointColor = DEFAULT_COLOR;

    /**
     * The bg color.
     * */
    private int mBgColor = DEFAULT_BG_COLOR;

    private Paint mPointPaint;
    private Paint mBorderPaint;
    private Paint mBgPaint;

    private Path mPointPath;
    private Path mBorderPath;
    private Path mBgPath;

    private int mWidth;
    private int mHeight;

    public DiceView(Context context) {
        this(context, null);
    }

    public DiceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DiceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        parseAttrs(attrs);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DiceView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        parseAttrs(attrs);
        init();
    }

    private void parseAttrs(AttributeSet attrs){
        if (attrs == null){
            return;
        }
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.DiceView);
        mPointColor = typedArray.getColor(R.styleable.DiceView_pointColor, DEFAULT_COLOR);
        mBorderColor = typedArray.getColor(R.styleable.DiceView_borderColor, DEFAULT_COLOR);
        mBgColor = typedArray.getColor(R.styleable.DiceView_bgColor, DEFAULT_BG_COLOR);
        mNumber = typedArray.getInteger(R.styleable.DiceView_number, NUMBER_ONE);

        if (mNumber < 1){
            mNumber = 1;
        }else if (mNumber > 6){
            mNumber = 6;
        }

        typedArray.recycle();
    }

    private void init(){
        mPointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPointPaint.setColor(mPointColor);
        mPointPaint.setStyle(Paint.Style.FILL);

        mBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBorderPaint.setColor(mBorderColor);
        mBorderPaint.setStyle(Paint.Style.STROKE);

        mBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBgPaint.setColor(mBgColor);
        mBgPaint.setStyle(Paint.Style.FILL);

        mPointPath  = new Path();
        mBorderPath = new Path();
        mBgPath     = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = w;
        mHeight = h;
        setupDiceNumber(mWidth, mHeight);
    }

    private void setupDiceNumber(int w, int h) {
        mPointPath.reset();
        mBorderPath.reset();
        mBgPath.reset();

        int centerX = w / 2;
        int centerY = h / 2;

        int minSize = Math.min(w, h);
        float borderStrokeWidth =  minSize / 15f;
        float borderRectRadius  = minSize / 2 - borderStrokeWidth / 2;

        mBorderPaint.setStrokeWidth(borderStrokeWidth);

        mBorderPath.addRect(new RectF(centerX - borderRectRadius, centerY - borderRectRadius,
                centerX + borderRectRadius, centerY + borderRectRadius), Path.Direction.CW);
        mBgPath.set(mBorderPath);

        float pointRadius = minSize / 10;
        switch (mNumber){
            case NUMBER_ONE:
                pointRadius = minSize / 8;
                mPointPath.addCircle(centerX, centerY, pointRadius, Path.Direction.CW);
                break;

            case NUMBER_TWO:
                double tArc1 = 45 / 180d * Math.PI;
                double tArc2 = -135 / 180d * Math.PI;

                float tX1 = (float) (Math.cos(tArc1) * borderRectRadius / 2) + centerX;
                float tY1 = (float) (Math.sin(tArc1) * borderRectRadius / 2) + centerY;

                float tX2 = (float) (Math.cos(tArc2) * borderRectRadius / 2) + centerX;
                float tY2 = (float) (Math.sin(tArc2) * borderRectRadius / 2) + centerY;

                mPointPath.addCircle(tX1, tY1, pointRadius, Path.Direction.CW);
                mPointPath.addCircle(tX2, tY2, pointRadius, Path.Direction.CW);
                break;

            case NUMBER_THREE:
                double thArc1 = 45 / 180d * Math.PI;
                double thArc2 = -135 / 180d * Math.PI;

                float thX1 = (float) (Math.cos(thArc1) * borderRectRadius * 2 / 3) + centerX;
                float thY1 = (float) (Math.sin(thArc1) * borderRectRadius * 2 / 3) + centerY;

                float thX2 = (float) (Math.cos(thArc2) * borderRectRadius * 2 / 3) + centerX;
                float thY2 = (float) (Math.sin(thArc2) * borderRectRadius * 2 / 3) + centerY;

                mPointPath.addCircle(thX1, thY1, pointRadius, Path.Direction.CW);
                mPointPath.addCircle(centerX, centerY, pointRadius, Path.Direction.CW);
                mPointPath.addCircle(thX2, thY2, pointRadius, Path.Direction.CW);
                break;

            case NUMBER_FOUR:
                mPointPath.addCircle(centerX - borderRectRadius/ 2, centerY - borderRectRadius / 2,
                        pointRadius, Path.Direction.CW);
                mPointPath.addCircle(centerX + borderRectRadius/ 2, centerY - borderRectRadius / 2,
                        pointRadius, Path.Direction.CW);
                mPointPath.addCircle(centerX - borderRectRadius/ 2, centerY + borderRectRadius / 2,
                        pointRadius, Path.Direction.CW);
                mPointPath.addCircle(centerX + borderRectRadius/ 2, centerY + borderRectRadius / 2,
                        pointRadius, Path.Direction.CW);
                break;

            case NUMBER_FIVE:
                mPointPath.addCircle(centerX, centerY, pointRadius, Path.Direction.CW);
                mPointPath.addCircle(centerX - borderRectRadius/ 2, centerY - borderRectRadius / 2,
                        pointRadius, Path.Direction.CW);
                mPointPath.addCircle(centerX + borderRectRadius/ 2, centerY - borderRectRadius / 2,
                        pointRadius, Path.Direction.CW);
                mPointPath.addCircle(centerX - borderRectRadius/ 2, centerY + borderRectRadius / 2,
                        pointRadius, Path.Direction.CW);
                mPointPath.addCircle(centerX + borderRectRadius/ 2, centerY + borderRectRadius / 2,
                        pointRadius, Path.Direction.CW);
                break;

            case NUMBER_SIX:
                mPointPath.addCircle(centerX - borderRectRadius/ 2, centerY,
                        pointRadius, Path.Direction.CW);
                mPointPath.addCircle(centerX + borderRectRadius/ 2, centerY,
                        pointRadius, Path.Direction.CW);
                mPointPath.addCircle(centerX - borderRectRadius/ 2, centerY - borderRectRadius / 2,
                        pointRadius, Path.Direction.CW);
                mPointPath.addCircle(centerX + borderRectRadius/ 2, centerY - borderRectRadius / 2,
                        pointRadius, Path.Direction.CW);
                mPointPath.addCircle(centerX - borderRectRadius/ 2, centerY + borderRectRadius / 2,
                        pointRadius, Path.Direction.CW);
                mPointPath.addCircle(centerX + borderRectRadius/ 2, centerY + borderRectRadius / 2,
                        pointRadius, Path.Direction.CW);
                break;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //draw the bg
        canvas.drawPath(mBgPath, mBgPaint);

        //draw the border
        canvas.drawPath(mBorderPath, mBorderPaint);
        canvas.drawPath(mPointPath, mPointPaint);
    }

    /**
     * Set number of this 'Dice', must be 1-6.
     * */
    public void setNumber(int number) {
        this.mNumber = number;
        if (mNumber < 1){
            mNumber = 1;
        }else if (number > 6){
            mNumber = 6;
        }

        setupDiceNumber(mWidth, mHeight);
        postInvalidate();
    }

    /**
     * Get current number of this 'Dice'.
     * */
    public int getNumber() {
        return mNumber;
    }

    /**
     * Set the bg color.
     * */
    public void setBgColor(int bgColor) {
        this.mBgColor = bgColor;
        mBgPaint.setColor(mBgColor);
        postInvalidate();
    }

    /**
     * Get the bg color.
     * */
    public int getBgColor() {
        return mBgColor;
    }

    /**
     * Set the point color.
     * */
    public void setPointColor(int pointColor) {
        this.mPointColor = pointColor;
        mPointPaint.setColor(mPointColor);
        postInvalidate();
    }

    /**
     * Get the point color
     * */
    public int getPointColor() {
        return mPointColor;
    }

    /**
     * Set the border color.
     * */
    public void setBorderColor(int borderColor) {
        this.mBorderColor = borderColor;
        mBorderPaint.setColor(mBorderColor);
        postInvalidate();
    }

    public int getBorderColor() {
        return mBorderColor;
    }
}
