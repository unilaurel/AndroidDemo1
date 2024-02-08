package com.example.jinjie;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProcessBarActivity3 extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ProcessBarActivity3";
    private ProgressBar progressBar;
    private Button add;
    private Button reduce;
    private Button reset;
    private TextView text;
    private Button disp;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //启用窗口特征，启用带进度的和不带进度的进度条
        requestWindowFeature(Window.FEATURE_PROGRESS);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);


        setContentView(R.layout.activity_process_bar3);
        //显示2种进度条
        setProgressBarVisibility(true);
        setSupportProgressBarIndeterminate(true);
        setProgress(30);

        init();


    }

    private void init() {
        progressBar = findViewById(R.id.horiz);
        add = findViewById(R.id.add);
        reduce = findViewById(R.id.reduce);
        reset = findViewById(R.id.reset);
        text = findViewById(R.id.text);
        disp = findViewById(R.id.disp);

        int first = progressBar.getProgress();
        int second = progressBar.getSecondaryProgress();
        int max = progressBar.getMax();
        text.setText("第一进度百分比" + (int) (first * 100 / (float) max) + "% 第二进度" + (int) (second * 100 / (float) max) + "%");

        add.setOnClickListener(this);
        reduce.setOnClickListener(this);
        reset.setOnClickListener(this);
        disp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int num1 = progressBar.getProgress();
        int num2 = progressBar.getSecondaryProgress();
        if (v.getId() == R.id.add) {
            Log.i(TAG, "onClick: add");
            progressBar.incrementProgressBy(5);
            progressBar.incrementSecondaryProgressBy(10);


        } else if (v.getId() == R.id.reduce) {
            Log.i(TAG, "onClick: reduce");
            progressBar.incrementProgressBy(-5);
            progressBar.incrementSecondaryProgressBy(-10);

        } else if (v.getId() == R.id.reset) {
            Log.i(TAG, "onClick: reset");
            progressBar.setProgress(50);
            progressBar.setSecondaryProgress(80);
        } else if (v.getId() == R.id.disp) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            //title
            progressDialog.setTitle("慕课网");
            //contents
            progressDialog.setMessage("欢迎大家来到小猪课程");
            //icon
            progressDialog.setIcon(R.mipmap.ic_launcher);

            //ProgressBarのいくつかの属性を設定します
            progressDialog.setMax(300);
            progressDialog.incrementProgressBy(50);
            //明確に進捗を表示する
            progressBar.setIndeterminate(false);
            //確定Buttonを設定する
            progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(ProcessBarActivity3.this, "欢迎你", Toast.LENGTH_SHORT).show();
                }
            });
            //是否可以通过返回按钮退出对话框，バックボタンでダイアログを閉じることができます
            progressDialog.setCancelable(true);
            //表示する
            progressDialog.show();
        }

        text.setText("当前进度" + progressBar.getProgress() + " 第二进度" + progressBar.getSecondaryProgress());

    }
}