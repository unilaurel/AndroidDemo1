package com.example.testmodule;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UITestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_test);


    }

    public void register1(View v) {
        Intent it=new Intent(UITestActivity.this, FrameActivity.class);
        startActivity(it);


    }

    public void register(View v) {

        //1，ユーザー名が空です。パスワードが空です。
        EditText userExt = findViewById(R.id.name);
        EditText pwdExt = findViewById(R.id.pwd);
        ProgressBar pb = findViewById(R.id.pro_bar);

        String name = userExt.getText().toString();
        String pwd = pwdExt.getText().toString();
        // 2，もし空なら、注意を表示する； フォーカスがないヒント
        if (name.length() == 0 || pwd.equals("")) {
            Toast.makeText(this, "姓名或密码不能为空", Toast.LENGTH_SHORT).show();
        } else {

            //3.ProgressBarを表示する
            pb.setVisibility(View.VISIBLE);
            new Thread() {

                @Override
                public void run() {
                    for (int i = 0; i < 100; i++) {
                        pb.setProgress(i);
                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }.start();

        }


    }
}