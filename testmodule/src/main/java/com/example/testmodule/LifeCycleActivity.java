package com.example.testmodule;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testmodule.model.User;

public class LifeCycleActivity extends AppCompatActivity {

    private static final String TAG = "LifeCycleActivity";
    public static final String BUTTON_TITLE = "button_title";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 999 && resultCode == RESULT_OK) {
            setTitle("前一个页面回来了");
            Button btn = findViewById(R.id.test_btn1);
            if (data != null) {
                btn.setText(data.getStringExtra("imooc"));
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initViews();
        Log.i(TAG, "onCreate: ");
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

    private void initViews() {
        findViewById(R.id.test_btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LifeCycleActivity.this, TestActivity.class);
//       ①         intent.putExtra(BUTTON_TITLE, "慕课网");
//       ②        Bundle bundle = new Bundle();
//                bundle.putString(BUTTON_TITLE,"慕课网，123");
//                intent.putExtra(BUTTON_TITLE,bundle);
//                startActivity(intent);
//③
                intent.putExtra(BUTTON_TITLE, new User("慕课网456----fafafafafaf"));
                startActivityForResult(intent, 999);
            }
        });

        findViewById(R.id.buttonDialogActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LifeCycleActivity.this, DialogActivity.class));
            }
        });


        findViewById(R.id.test_btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(LifeCycleActivity.this)
                        .setTitle("你好")
                        .setMessage("我帅吗？")
                        .setNegativeButton("你好帅的", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton("呵呵哒", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create()
                        .show();
            }
        });
    }
}