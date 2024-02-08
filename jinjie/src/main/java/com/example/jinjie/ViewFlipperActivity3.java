package com.example.jinjie;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

public class ViewFlipperActivity3 extends AppCompatActivity {
    private static final String TAG = "ViewFlipperActivity3";
    private ViewFlipper flipper;
    int[] resIds = {R.drawable.wawacai, R.drawable.suncainiuroumian, R.drawable.suanlatang, R.drawable.qingzhengluyu};
    private float startX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flipper3);

        flipper = findViewById(R.id.flipper);

        //ダイナミック導入
        for (int i = 0; i < resIds.length; i++) {
            flipper.addView(getImageView(resIds[i]));
        }
//        //動画効果を増加する
//        flipper.setInAnimation(this, R.anim.left_in);
//        flipper.setOutAnimation(this, R.anim.left_out);
//        //放送の間隔を設定する
//        flipper.setFlipInterval(3000);
//        //開始する
//        flipper.startFlipping();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // 指がタッチダウン
            startX = event.getX();
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            // 指がスライドする
            if (event.getX() - startX > 100) {
                // 右にスライドして、前のページを見る
                flipper.setInAnimation(this, R.anim.left_in);
                flipper.setOutAnimation(this, R.anim.left_out);
                flipper.showPrevious();
                Log.i(TAG, "onTouchEvent:right ");
            } else if (startX - event.getX() > 100) {
                // 左にスライド
                flipper.setInAnimation(this, R.anim.right_in);
                flipper.setOutAnimation(this, R.anim.right_out);
                flipper.showNext();
                Log.i(TAG, "onTouchEvent: left");
            }

        } else if (event.getAction() == MotionEvent.ACTION_UP) {

        }
        return super.onTouchEvent(event);
    }


    private ImageView getImageView(int resId) {
        ImageView image = new ImageView(this);
//        image.setImageResource(resId);
        image.setBackgroundResource(resId);
        return image;
    }
}