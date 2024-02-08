package com.example.animation.property;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.animation.R;

public class PropertyActivity extends AppCompatActivity {
    private static final String TAG = "PropertyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);
    }


    public void onClick(View v) {
        if (v.getId() == R.id.btnValueAnimator) {
//            ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 100);
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1f);
            valueAnimator.setDuration(100);
            valueAnimator.setInterpolator(new LinearInterpolator());
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                    float animatedFraction = animation.getAnimatedFraction();//完成度
                    float animatedValue = (Float) animation.getAnimatedValue();
                    Log.d(TAG, "onAnimationUpdate: " + String.format("%.3f %.3f", animatedFraction, animatedValue));
                }
            });
            valueAnimator.start();
        }
    }
}