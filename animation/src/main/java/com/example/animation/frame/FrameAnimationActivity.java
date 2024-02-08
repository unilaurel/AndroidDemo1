package com.example.animation.frame;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.animation.R;

public class FrameAnimationActivity extends AppCompatActivity {

    private AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_animation);

        View view = findViewById(R.id.view);
        animationDrawable = (AnimationDrawable) view.getBackground();
//        animationDrawable.setOneShot(true);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btnStart) {
            animationDrawable.start();
        } else if (v.getId() == R.id.btnStop) {
            animationDrawable.stop();
        }
    }
}