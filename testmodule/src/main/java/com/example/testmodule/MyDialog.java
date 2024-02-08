package com.example.testmodule;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

//1.ダイアログの様式を設定する-->dialog_my.xml
//2. styleを設定する,(APPBarを設定する、背景を除去する)。themes.xmlーstyle中設定
//４.ダイアログをインスタンス化する（// パラメータ1：コンテキスト、パラメータ2：第2ステップで作成したスタイル）,表示するshow()
//参数1:环境上下文，参数2:第二步创建的style，通过R.style.xxname
public class MyDialog extends Dialog {
    public MyDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        //3. ダイアログにレイアウトを設定する，为对话框设置布局
        setContentView(R.layout.dialog_my);
        findViewById(R.id.yes_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);//// 終了。退出
            }
        });

        findViewById(R.id.no_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();//ダイアログを閉じる，让对话框消失
            }
        });
    }
}
