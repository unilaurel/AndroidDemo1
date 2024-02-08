package com.example.jinjie;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScrollViewActivity3 extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "ScrollViewActivity3";
    private ScrollView scrollView;

    private TextView textView;

    private Button up_btn;
    private Button down_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view3);

        textView = findViewById(R.id.text);
        textView.setText(R.string.lorem_ipsum);

        up_btn = findViewById(R.id.up);
        down_btn = findViewById(R.id.down);
        up_btn.setOnClickListener(this);
        down_btn.setOnClickListener(this);

        scrollView = findViewById(R.id.scroll);
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    /**
                     * ①getScrollY()--スライダーのスライド距離
                     * ②getMeasuredHeight()--全体の高度
                     * ③getHeight
                     */
                    //top state
                    if (scrollView.getScrollY() <= 0) {
                        Log.i(TAG, "onTouch: top state");
                    }

                    //bottom state
                    //// TextViewの総高さ <= 画面の高さ + スライダーのスクロール距離
                    if (scrollView.getChildAt(0).getMeasuredHeight() <= scrollView.getHeight() + scrollView.getScrollY()) {
                        Log.i(TAG, "onTouch: bottom state");
                        Log.i(TAG, "onTouch: getMeasuredHeight " + scrollView.getChildAt(0).getMeasuredHeight());
                        Log.i(TAG, "onTouch: getHeight " + scrollView.getHeight());
                        Log.i(TAG, "onTouch: getScrollY" + scrollView.getScrollY());

                        textView.append("menglanmenglan");
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        //scrollTo,スクロールビューの開始位置から計算
        //scrollBy:
        /**
         *     public void scrollBy(int x, int y) {
         *         scrollTo(mScrollX + x, mScrollY + y);
         *     }
         */
        if (v.getId() == R.id.up) {
            scrollView.scrollTo(0, -30);
        } else if (v.getId() == R.id.down) {
            scrollView.scrollBy(0, 30);
        }
    }
}