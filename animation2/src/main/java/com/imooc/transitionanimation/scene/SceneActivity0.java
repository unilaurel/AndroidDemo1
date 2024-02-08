package com.imooc.transitionanimation.scene;

import android.os.Bundle;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.imooc.transitionanimation.R;

public class SceneActivity0 extends AppCompatActivity {
    private static final String TAG = "SceneActivity";
    private Scene mOverViewScene;
    private Scene mInfoScene;
    private boolean isInfoScene = false; // 标记当前Scene状态

    private ImageButton btnInfo;
    private ImageButton btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene);

        ViewGroup sceneRoot = findViewById(R.id.scene_root);
        mOverViewScene = Scene.getSceneForLayout(sceneRoot, R.layout.scene_overview, getBaseContext());
        mInfoScene = Scene.getSceneForLayout(sceneRoot, R.layout.scene_info, getBaseContext());

        // 初始状态加载mOverViewScene
        TransitionManager.go(mOverViewScene);

        // 点击btnInfo和btnClose都会执行toggleScene方法
        btnInfo = mOverViewScene.getSceneRoot().findViewById(R.id.btnInfo);


        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleScene();
            }
        });
    }

    // 切换Scene状态的方法
    private void toggleScene() {
//        TransitionManager.go(isInfoScene ? mOverViewScene : mInfoScene);
//        isInfoScene = !isInfoScene;
        if (isInfoScene) {
            Transition transition = TransitionInflater.from(getBaseContext())
                    .inflateTransition(R.transition.transition);
            TransitionManager.go(mInfoScene, transition);
            btnClose = mInfoScene.getSceneRoot().findViewById(R.id.btnClose);
            btnClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toggleScene();
                }
            });
            isInfoScene = !isInfoScene;
        } else {
            TransitionManager.go(mOverViewScene);
            btnInfo = mOverViewScene.getSceneRoot().findViewById(R.id.btnInfo);
            btnInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toggleScene();
                }
            });
            isInfoScene = !isInfoScene;
        }
    }
}
