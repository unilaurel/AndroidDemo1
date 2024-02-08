package com.example.testmodule;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_test);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//ContextMenu
        //1.register登録
//        registerForContextMenu(findViewById(R.id.ctx_btn));
        //2，作成す，上書き"（うわがき）或 "オーバーライド"（Overwrite）onCreateContextMenu（）
        //３.menu item の操作，onContextItemSelected（）を上書きする

        //模式2. 为按钮设置上下文操作模式，ボタンにコンテキスト操作モードを設定する
        //--1. 实现ActionMode CallBack，ActionMode CallBackを実装する
        //--2. 在view的长按事件中启动上下文模式，Viewの長押しイベントでコンテキストモードを起動する
        findViewById(R.id.ctx_btn).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                startActionMode(cb);
                return false;
            }
        });

        //popup_menu
        Button popupBtn = findViewById(R.id.poppup_btn);
        popupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //①实例化PopupMenu对象，参数2是被锚定的view,也就是出现在谁的下方
                // PopupMenuのオブジェクトをインスタンスかする，パラメータ2がアンカーとなるビュー
                PopupMenu menu = new PopupMenu(MenuActivity.this, popupBtn);

                //②加载菜单资源：利用MenuInflater将Menu资源加载到PopMenu.getMenu()所返回的menu对象中
                //将R.menu.xx的资源加载到弹出式菜单中
                menu.getMenuInflater().inflate(R.menu.popup, menu.getMenu());

                //为PopupMenu设置监听器
                //③PopupMenuにリスナーを設定する
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.copy) {
                            Toast.makeText(MenuActivity.this, "复制", Toast.LENGTH_SHORT).show();
                        } else if (item.getItemId() == R.id.paste) {
                            Toast.makeText(MenuActivity.this, "粘贴", Toast.LENGTH_SHORT).show();

                        }
                        return false;
                    }
                });

                //④忘れないで，PopupMenuを表示する
                menu.show();
            }
        });


    }

    ActionMode.Callback cb = new ActionMode.Callback() {
        //创建，在启动上下文模式时(startActionMode(Callback))时调用
        // 作成：コンテキストモードの開始時（startActionMode(Callback)）に呼び出されます。
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            Log.e(TAG, "创建");
            getMenuInflater().inflate(R.menu.context, menu);

            return true;
        }

        //创建方法后使用
        // 作成されたメソッドの後使用する
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            Log.e(TAG, "准备");
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            Log.e(TAG, "点击");
            if (item.getItemId() == R.id.delete) {
                Toast.makeText(MenuActivity.this, "删除", Toast.LENGTH_SHORT).show();
            } else if (item.getItemId() == R.id.opera1) {
                Toast.makeText(MenuActivity.this, "操作1", Toast.LENGTH_SHORT).show();

            } else if (item.getItemId() == R.id.opera2) {

                finish();//終了
            }
            return true;
        }

        //上下文操作结束时调用
        // コンテキスト操作が終了したときに呼び出されます
        @Override
        public void onDestroyActionMode(ActionMode mode) {
            Log.e(TAG, "结束");
        }
    };

    //OptionMenuの作成
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //メニューリソースの読み込み
        getMenuInflater().inflate(R.menu.option, menu);
        return true;
    }

    //OptionMenuアイテムの選択方法
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save) {
            Toast.makeText(this, "保存", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.setting) {
            Toast.makeText(this, "设置", Toast.LENGTH_SHORT).show();

        } else if (item.getItemId() == R.id.exit) {
            finish();//終了
        }
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        getMenuInflater().inflate(R.menu.context, menu);
//        super.onCreateContextMenu(menu, v, menuInfo);
//    }
//
//    @Override
//    public boolean onContextItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == R.id.delete) {
//            Toast.makeText(this, "删除", Toast.LENGTH_SHORT).show();
//        } else if (item.getItemId() == R.id.opera1) {
//            Toast.makeText(this, "操作1", Toast.LENGTH_SHORT).show();
//
//        } else if (item.getItemId() == R.id.opera2) {
//
//            finish();//終了
//        }
//        return super.onContextItemSelected(item);
//    }


}
