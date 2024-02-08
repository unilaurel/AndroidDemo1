package com.example.testmodule;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testmodule.model.User;

public class TestActivity extends AppCompatActivity {

    private static final String TAG = "TestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate: ");
        TextView textView = findViewById(R.id.text_view);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                setResult(RESULT_OK);
                Intent intent=new Intent();
                intent.putExtra("imooc", "慕课网987");
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        if (getIntent() != null) {
//            String str = getIntent().getStringExtra(LifeCycleActivity.BUTTON_TITLE);
//            Bundle bundleExtra = getIntent().getBundleExtra(LifeCycleActivity.BUTTON_TITLE);
//            if (bundleExtra!=null){
//                String str = bundleExtra.getString(LifeCycleActivity.BUTTON_TITLE);
//                textView.setText(str);
//            }

            User user = (User) getIntent().getSerializableExtra(LifeCycleActivity.BUTTON_TITLE);
            textView.setText(user.getTitle());

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }
}