package com.example.animation.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.animation.R;

public class RoundProgressBar extends View {
    private int mRadius;
    private int mColor;
    private int mLineWidth;
    private int mTextSize;
    private int mProgress;

    private Paint mPaint;

    //一般用于布局文件中编写，系统建立view时会调用第二个构造方法，因此如果想让View能在布局文件中使用，一定要重写2个参数的构造方法
    //一般、レイアウトファイルで使用する際、システムはViewを構築する際に第2のコンストラクタを呼び出します。したがって、Viewをレイアウトファイルで使用できるようにしたい場合は、必ず2つのパラメータを持つコンストラクタをオーバーライドする必要があります。
    public RoundProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RoundProgressBar);
        mColor = ta.getColor(R.styleable.RoundProgressBar_color, 0xffff0000);
        mLineWidth = (int) ta.getDimension(R.styleable.RoundProgressBar_line_width, dp2px(3));
        mTextSize = (int) ta.getDimension(R.styleable.RoundProgressBar_android_textSize, dp2px(16));
        mProgress = ta.getInt(R.styleable.RoundProgressBar_android_progress, 30);
        mRadius = (int) ta.getDimension(R.styleable.RoundProgressBar_radius, dp2px(30));
        ta.recycle();//使用済み回収する

        initPaint();
    }

    private float dp2px(int dpVal) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, getResources().getDisplayMetrics());
    }

    private void initPaint() {
        // Paint オブジェクトの初期化
        mPaint = new Paint();

        // アンチエイリアス（描画の滑らかさ向上）を有効にする
        mPaint.setAntiAlias(true);

        mPaint.setColor(mColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int width = 0;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            int needWidth = measureWidth() + getPaddingLeft() + getPaddingRight();
            if (widthMode == MeasureSpec.AT_MOST) {
                width = Math.min(widthSize, needWidth);
            } else {
                width = needWidth;
            }
        }

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int height = 0;
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            int needHeight = measureHeight() + getPaddingTop() + getPaddingBottom();
            if (heightMode == MeasureSpec.AT_MOST) {
                height = Math.min(heightSize, needHeight);
            } else {
                height = needHeight;
            }
        }

        setMeasuredDimension(width, height);
    }

    private int measureHeight() {
        return mRadius*2;
    }

    private int measureWidth() {
        return mRadius*2;
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mLineWidth * 1.0f / 4);

        int width = getWidth();
        int height = getHeight();
        canvas.drawCircle(width / 2, height / 2, width / 2 - getPaddingLeft() - mPaint.getStrokeWidth() / 2, mPaint);

        mPaint.setStrokeWidth(mLineWidth);
        canvas.save();
        canvas.translate(getPaddingLeft(), getPaddingTop());
        float angle = mProgress * 1.0f / 100 * 360;
        canvas.drawArc(new RectF(0, 0, width - getPaddingLeft() * 2, height - getPaddingLeft() * 2), 0, angle, false, mPaint);
        canvas.restore();


        String text = mProgress + "%";
//        String text =  "慕课网";

        mPaint.setStrokeWidth(0);
        mPaint.setTextSize(mTextSize);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextAlign(Paint.Align.CENTER);
        int y=getHeight()/2;

        Rect bound=new Rect();
        mPaint.getTextBounds(text,0,text.length(),bound);
        int textHeight=bound.height();
        canvas.drawText(text,0,text.length(),getWidth()/2,y+textHeight/2,mPaint);
//        canvas.drawText(text,0,text.length(),getWidth()/2,y+textHeight/2-mPaint.descent()/2,mPaint);
//        canvas.drawText(text,0,text.length(),getWidth()/2,y-(mPaint.descent()/2+mPaint.ascent()/2),mPaint);

//        mPaint.setStrokeWidth(0);
//        canvas.drawLine(0,getHeight()/2,getWidth(),getWidth()/2,mPaint);
//        mPaint.setStrokeWidth(0);
//        canvas.drawLine(getWidth()/2,0,getWidth()/2,getWidth(),mPaint);

    }

    public void setProgress(int progress){
        mProgress=progress;
        invalidate();
    }

    public int getProgress(){
        return mProgress;
    }


    private static final String INSTANCE = "instance";
    private static final String KEY_PROGRESS = "key_progress";

    @Nullable
    //このメソッドは、ビューの状態を保存するために使用されます。KEY_TEXTで示されるキーで現在のテキストを保存し、INSTANCEで示されるキーで親クラスの状態を保存しています。これにより、ビューが再作成される際に状態を復元できます。
    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();                            // 新しいBundleを作成
        bundle.putInt(KEY_PROGRESS, mProgress);                      // 現在のテキストを保存
        bundle.putParcelable(INSTANCE, super.onSaveInstanceState());  // 親クラスのonSaveInstanceState()を呼び出し、その結果を保存
        return bundle;                                           // 作成したBundleを返す
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            Parcelable parcelable = bundle.getParcelable(INSTANCE);
            super.onRestoreInstanceState(parcelable);
            mProgress = bundle.getInt(KEY_PROGRESS);
            return;
        }
        super.onRestoreInstanceState(state);
    }
}
