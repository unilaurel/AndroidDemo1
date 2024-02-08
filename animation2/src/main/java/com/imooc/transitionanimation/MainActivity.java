package com.imooc.transitionanimation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.imooc.transitionanimation.activity.FirstActivity;
import com.imooc.transitionanimation.reveal.RevealActivity;
import com.imooc.transitionanimation.scene.SceneActivity0;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnReveal) {
            startActivity(new Intent(this, RevealActivity.class));
        } else if (id == R.id.btnScene) {
            startActivity(new Intent(this, SceneActivity0.class));
        } else if (id == R.id.btnActivity) {
            startActivity(new Intent(this, FirstActivity.class));
        }
    }

}
