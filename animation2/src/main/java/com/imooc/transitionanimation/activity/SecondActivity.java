package com.imooc.transitionanimation.activity;

import android.app.Activity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Transition;
import android.widget.ImageView;

import com.imooc.transitionanimation.R;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        int resId = getIntent().getExtras().getInt("resId");
        ImageView iv = (ImageView) findViewById(R.id.iv);
        iv.setTransitionName("img");
        iv.setImageResource(resId);

        Transition transition = new Explode();
        transition.excludeTarget(android.R.id.statusBarBackground, true);
        getWindow().setEnterTransition(transition);
        getWindow().setExitTransition(transition);
    }

}
