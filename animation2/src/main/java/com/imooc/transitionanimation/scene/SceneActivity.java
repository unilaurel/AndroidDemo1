package com.imooc.transitionanimation.scene;


import android.os.Bundle;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.imooc.transitionanimation.R;

public class SceneActivity extends AppCompatActivity {

    private Scene mOverViewScene;
    private Scene mInfoScene;
    private boolean isInfoScene = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene);

        ViewGroup sceneRoot = (ViewGroup) findViewById(R.id.scene_root);
        mOverViewScene = Scene.getSceneForLayout(sceneRoot, R.layout.scene_overview, getBaseContext());
        mInfoScene = Scene.getSceneForLayout(sceneRoot, R.layout.scene_info, getBaseContext());

        TransitionManager.go(mOverViewScene);

    }

    public void onClick(View view) {

//
//
        if (view.getId()==R.id.btnInfo) {
//            Transition transition = TransitionInflater.from(getBaseContext())
//                    .inflateTransition(R.transition.transition);
            TransitionManager.go(mInfoScene);
        } else {
            TransitionManager.go(mOverViewScene);
        }
    }

}
