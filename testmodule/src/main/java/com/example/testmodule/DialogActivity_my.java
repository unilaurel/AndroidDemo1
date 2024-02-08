package com.example.testmodule;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DialogActivity_my extends AppCompatActivity {

    private static final String TAG = "DialogActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_test);
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

    public void showNormalDialog() {
        AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setTitle("提示");
        dialog.setMessage("您确定退出程序吗1？");
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.e(TAG, "确定");
            }
        });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.e(TAG, "取消");
            }
        });
        dialog.show();
    }

    public void myClick(View v) {
        if (v.getId() == R.id.normal_dialog_btn) {
            Log.e("TAG", "normal_dialog_btn");
            showNormalDialog();
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setTitle("提示");
//            builder.setMessage("您确定退出程序吗？");
//            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    finish();
//                }
//            });
//            builder.setNegativeButton("取消", null);
//            builder.show();
//            AlertDialog dialog = builder.create();
//            dialog.show();
        } else if (v.getId() == R.id.diy_dialog_btn) {
            Log.e("TAG", "diy_dialog_btn");
            MyDialog md = new MyDialog(this, R.style.mydialog);
            md.show();
        } else if (v.getId() == R.id.popwindow_btn) {
            showPopupWindow(v);
        } else if (v.getId() == R.id.adapter_btn) {
            showArrayDialog();
        }
    }

    private void showArrayDialog() {
        String[] items = {"Java", "MySQL", "Android", "HTML", "C", "JavaScript"};
        //数组适配，只能用来显示单一的文本
        //参数1:环境
        //参数2:布局资源索引，指的是每一项数据所呈现的样式，比如android.R.layout.xxx
        //参数3:数据源
        // 配列アダプター、単一のテキストの表示にしか使用できません
// パラメータ1: コンテキスト
// パラメータ2: レイアウトリソースのインデックス、各データ項目のスタイルを指します。例：android.R.layout.xxx
// パラメータ3: データソース
// パラメータ3: データソース3,textView id

//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, items);
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.array_item_layout, R.id.item_txt, items);
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("请选择")
                //-パラメータ１、adapterのオブジェクト，适配器对象，对数据显示样式的规则制定器
                //-パラメータ２、リスナー
                .setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity_my.this,items[which],Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
        builder.show();
    }

    private void showPopupWindow(View view) {
        //①PopupWindowのオブジェクトをインスタンス化する，
        // // レイアウト全体を1つのViewに変換し、nullは親コンテナがないことを示します，（把整个布局变成一个View,null表示没有父容器）
        View v = LayoutInflater.from(this).inflate(R.layout.popwindow, null);
        //-パラメータ１、PopupWindowの中に使用するVIEW
        PopupWindow window = new PopupWindow(v, 350, 100, true);
        //②設定する(背景、動画)
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 外部のクリックイベントに応答できるように設定
        window.setOutsideTouchable(true);
        // 自分のクリックイベントに応答できるように設定
        window.setTouchable(true);

        //（1）创建动画资源，（2）创建一个style应用动画资源，（3）对当前弹窗的动画风格设置为第2步的资源索引
        // （1）アニメーションリソースを作成する、res-anim-translate.xml
        // （2）スタイルにアニメーションリソースを適用する、res-values-styles
        // （3）現在のポップアップのアニメーションスタイルを第2ステップのリソースインデックスに設定する
        window.setAnimationStyle(R.style.translate_anim);


        //③表示する
        //// パラメータ1、アンカー，anchor
        // パラメータ2、3，アンカーに対するxおよびy方向のオフセットに関するパラメータ
        window.showAsDropDown(view, -350, 0);
        //④PopupWindowのTextViewのクリックイベントに応答できるように設定
        v.findViewById(R.id.choose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DialogActivity_my.this, "你点击了选择", Toast.LENGTH_SHORT).show();
                window.dismiss();
            }
        });
        v.findViewById(R.id.choose_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DialogActivity_my.this, "你点击了全选", Toast.LENGTH_SHORT).show();
                window.dismiss();
            }
        });
        v.findViewById(R.id.copy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DialogActivity_my.this, "你点击了复制", Toast.LENGTH_SHORT).show();
                window.dismiss();
            }
        });
    }
}