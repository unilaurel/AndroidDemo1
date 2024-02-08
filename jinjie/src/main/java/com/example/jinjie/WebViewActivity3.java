package com.example.jinjie;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WebViewActivity3 extends AppCompatActivity {

    public static final String URL = "https://www.google.com";
    private WebView webView;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view3);
//        //Intent
//        Uri uri=Uri.parse(URL);
//        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//        startActivity(intent);

        init();
    }

    private void init() {
        webView = findViewById(R.id.webView);
        webView.loadUrl(URL);
        //javaScriptを設定する
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        //cacheを設定する
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值为true的时候，控制网页在WebView中打开；如果为false就是调用系统浏览器或者第三方浏览器打开
                view.loadUrl(url);
                return true;
            }
            //WebViewClient帮助WebView去处理一些页面控制和请求通知

        });

        //進捗を表示
        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    //load完了，ProgressDialogを閉じる
                    closeDialog();

                } else {
                    //load中，ProgressDialogを開ける
                    openDiaglog(newProgress);

                }
            }
        });

    }

    private void closeDialog() {
        if(dialog!=null &&dialog.isShowing()){
            dialog.dismiss();
            dialog=null;
        }
    }

    private void openDiaglog(int newProgress) {
        if (dialog == null) {
            dialog = new ProgressDialog(this);
            dialog.setTitle("正在加载");
            dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dialog.setProgress(newProgress);
            dialog.show();
        }else{
            dialog.setProgress(newProgress);
        }
    }

    //更改物理按键的返回逻辑， 物理戻るキーの挙動を変更するためのコード
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Toast.makeText(this, webView.getUrl(), Toast.LENGTH_SHORT).show();
            if (webView.canGoBack()) {
                webView.goBack();//前のページに戻る
                return true;
            } else {
                System.exit(0);
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}