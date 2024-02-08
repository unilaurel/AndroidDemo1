package com.example.animation.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.animation.R;

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
       TextView textView= findViewById(R.id.viewAlphaAnimation);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.renew,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.renew){
            recreate();
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v){
        if(v.getId()==R.id.viewAlphaAnimation){
            Animation alphaAnimation = AnimationUtils.loadAnimation(this,R.anim.alpha);
            v.startAnimation(alphaAnimation);
        }else if(v.getId()==R.id.viewScaleAnimation){
            Animation scaleAnimation = AnimationUtils.loadAnimation(this,R.anim.scale);
            v.startAnimation(scaleAnimation);
        }else if(v.getId()==R.id.viewTranslationAnimation){
//            Animation translationAnimation = AnimationUtils.loadAnimation(this,R.anim.translate);
//            v.startAnimation(translationAnimation);

             v.animate().translationX(790f).setDuration(100).start();
        }else if(v.getId()==R.id.viewRotateAnimation){
            Animation rotateAnimation = AnimationUtils.loadAnimation(this,R.anim.rotate);
            v.startAnimation(rotateAnimation);
        }else if(v.getId()==R.id.viewSetAnimation){
//            Animation setAnimation = AnimationUtils.loadAnimation(this,R.anim.set);
//            v.startAnimation(setAnimation);

//            Animator rotateAnimator = ObjectAnimator.ofFloat(v, "rotation", 0, 1080);
//            rotateAnimator.setDuration(1000);
//
//            Animator moveAnimator = ObjectAnimator.ofFloat(v, "x", 0, 600);
//            moveAnimator.setDuration(1000);
//
//            AnimatorSet set = new AnimatorSet();
////            set.playTogether(rotateAnimator,moveAnimator);//同时
//            set.playSequentially(moveAnimator,rotateAnimator);
//            set.start();

            v.animate().rotation(720).setDuration(1000).start();
//            v.animate().translationX(700).setDuration(1000).start();
            v.animate().translationX(700).setDuration(1000).setStartDelay(1000).start();

        }else if(v.getId()==R.id.viewAccelerate || v.getId()==R.id.viewLinear){
            View viewLinear = findViewById(R.id.viewLinear);
            View viewAccelerate = findViewById(R.id.viewAccelerate);

            Animation animationLinear = AnimationUtils.loadAnimation(this, R.anim.translate);
            Animation animationAccelerate = AnimationUtils.loadAnimation(this, R.anim.translate);

            animationLinear.setInterpolator(new LinearInterpolator());
            animationAccelerate.setInterpolator(new AccelerateInterpolator());

            viewLinear.startAnimation(animationLinear);
            viewAccelerate.startAnimation(animationAccelerate);
        }
    }
}