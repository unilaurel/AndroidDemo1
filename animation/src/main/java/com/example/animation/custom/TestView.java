package com.example.animation.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.animation.R;

public class TestView extends View {
    private static final String TAG = "TestView";
    private String mText = "Imooc";

    private Paint mPaint;

    //一般用于布局文件中编写，系统建立view时会调用第二个构造方法，因此如果想让View能在布局文件中使用，一定要重写2个参数的构造方法
    //一般、レイアウトファイルで使用する際、システムはViewを構築する際に第2のコンストラクタを呼び出します。したがって、Viewをレイアウトファイルで使用できるようにしたい場合は、必ず2つのパラメータを持つコンストラクタをオーバーライドする必要があります。
    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TestView);

//        int taInteger = ta.getInteger(R.styleable.TestView_test_interger, -1);
//        boolean aBoolean = ta.getBoolean(R.styleable.TestView_test_boolean, false);
//         str = ta.getString(R.styleable.TestView_test_string);
//        int anInt = ta.getInt(R.styleable.TestView_test_enum, 1);
//        float dimension = ta.getDimension(R.styleable.TestView_test_dimension, 0);
        int count = ta.getIndexCount();
        Log.e(TAG, "TestView: count " + count);
        for (int i = 0; i < count; i++) {
            int index = ta.getIndex(i);
            if (index == R.styleable.TestView_test_string) {
                mText = ta.getString(R.styleable.TestView_test_string);
            }
        }


        Log.e(TAG, "TestView: str " + mText);
//        Log.e(TAG, "TestView: taInteger "+taInteger);
//        Log.e(TAG, "TestView: aBoolean "+aBoolean);
//        Log.e(TAG, "TestView: anInt "+anInt);
//        Log.e(TAG, "TestView: dimension "+dimension);

        ta.recycle();//使用済み回収する
    }

    private void initPaint() {
        // Paint オブジェクトの初期化
        mPaint = new Paint();

        // 描画スタイルを設定（描込み）
        mPaint.setStyle(Paint.Style.STROKE);

        // 描込みの線の太さを設定
        mPaint.setStrokeWidth(10);

        // 描込みの線の色を設定（赤色）
        mPaint.setColor(0xFFFF0000);  // ARGB 表現での色設定（FF: 完全不透明, FF: 赤, 00: 緑, 00: 青）

        // アンチエイリアス（描画の滑らかさ向上）を有効にする
        mPaint.setAntiAlias(true);
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
        return 0;
    }

    private int measureWidth() {
        return 0;
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
//        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2 - mPaint.getStrokeWidth() / 2, mPaint);
//        mPaint.setStrokeWidth(1);
//        canvas.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2, mPaint);
//        canvas.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight(), mPaint);

        mPaint.setTextSize(100);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(0);
        canvas.drawText(mText, 0, mText.length(), 0, getHeight(), mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mText = "8888";  // テキストを"8888"に設定
        invalidate();    // ビューの再描画を要求
        return true;      // イベントが処理されたことを示す
    }

    private static final String INSTANCE = "instance";
    private static final String KEY_TEXT = "key_text";

    @Nullable
    //このメソッドは、ビューの状態を保存するために使用されます。KEY_TEXTで示されるキーで現在のテキストを保存し、INSTANCEで示されるキーで親クラスの状態を保存しています。これにより、ビューが再作成される際に状態を復元できます。
    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();                            // 新しいBundleを作成
        bundle.putString(KEY_TEXT, mText);                      // 現在のテキストを保存
        bundle.putParcelable(INSTANCE, super.onSaveInstanceState());  // 親クラスのonSaveInstanceState()を呼び出し、その結果を保存
        return bundle;                                           // 作成したBundleを返す
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            Parcelable parcelable = bundle.getParcelable(INSTANCE);
            super.onRestoreInstanceState(parcelable);
            mText = bundle.getString(KEY_TEXT);
            return;
        }
        super.onRestoreInstanceState(state);
    }
}
