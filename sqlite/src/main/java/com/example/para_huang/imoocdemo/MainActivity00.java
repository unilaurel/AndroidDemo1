package com.example.para_huang.imoocdemo;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.para_huang_imoocdemo.R;

public class MainActivity00 extends Activity {
    private static final String TAG = "MainActivity";
    
    private EditText nameEdt, ageEdt, idEdt;
    private RadioGroup genderGp;
    private ListView stuList;
    private RadioButton malerb;
    private String genderStr = "男";
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        int permisson = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        if (permisson != PackageManager.PERMISSION_GRANTED) {
//            //动态去申请权限
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
//        }

        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // 如果没有权限，请求权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }



        //添加操作
        //参数2，数据库名称
        //如果只有一个数据库名称，那么这个数据库的位置会是在私有目录中
        //如果带SD卡路径，那么数据库位置则在指定的路径下
//        String path = Environment.getExternalStorageDirectory()+ "/Download/stu4.db";
        String path = getExternalFilesDir(null).getAbsolutePath() + "/studb.db";
//        String path2 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/imooc81.txt";
//        Log.e("TAG", path2);
//        File f = new File(path);
//        try {
//            if (!f.exists()) {
//                f.createNewFile();
//            }
//
//            FileOutputStream fos = new FileOutputStream(path, true);
////            String str = infoEdt.getText().toString();
////            fos.write(str.getBytes());
//            fos.close();
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }


        Log.e(TAG, "onCreate: "+path );
//        String path = "/storage/emulated/0/Download/studb.db";
        SQLiteOpenHelper helper = new SQLiteOpenHelper(this, path, null, 1) {
            @Override
            public void onCreate(SQLiteDatabase sqLiteDatabase) {
                //创建
                Toast.makeText(MainActivity00.this, "数据库创建", Toast.LENGTH_SHORT).show();
                //如果数据库不存在，则会调用onCreate方法，那么我们可以将表的创建工作放在这里面完成
                        /*
                        String sql = "create table test_tb (_id integer primary key autoincrement," +
                                "name varhcar(20)," +
                                "age integer)";
                        sqLiteDatabase.execSQL(sql);*/
            }

            @Override
            public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
                //升级
                Toast.makeText(MainActivity00.this, "数据库升级", Toast.LENGTH_SHORT).show();
            }
        };

        //用于获取数据库库对象
        //1.数据库存在，则直接打开数据库
        //2.数据库不存在，则调用创建数据库的方法，再打开数据库
        //3.数据库存在，但版本号升高了，则调用数据库升级方法

        db = helper.getReadableDatabase();

        nameEdt = (EditText) findViewById(R.id.name_edt);
        ageEdt = (EditText) findViewById(R.id.age_edt);
        idEdt = (EditText) findViewById(R.id.id_edt);
        malerb = (RadioButton) findViewById(R.id.male);

        genderGp = (RadioGroup) findViewById(R.id.gender_gp);
        genderGp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.male) {
                    //“男”
                    genderStr = "男";
                } else {
                    //"女"
                    genderStr = "女";
                }
            }
        });

        stuList = (ListView) findViewById(R.id.stu_list);
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == 1) {
//            //xxxxxxxxxxxxx
//        }
//    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        if (requestCode == 1) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // 权限被授予，执行文件操作
//                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/studb.db";
//                // 执行打开数据库等操作
//                checkPermissionStatus(true);
//            } else {
//                // 权限被拒绝，可以在这里进行相应的处理
//                Toast.makeText(this, "需要读写外部存储权限才能使用应用程序", Toast.LENGTH_SHORT).show();
//                checkPermissionStatus(false);
//            }
//        }
//    }

    private void checkPermissionStatus(boolean granted) {
        if (granted) {
            Log.d(TAG, "Permission granted!");
        } else {
            Log.d(TAG, "Permission denied!");
        }
    }


    //    SQLiteOpenHelper
//    SQLiteDatabase
    public void operate(View v) {

        String nameStr = nameEdt.getText().toString();
        String ageStr = ageEdt.getText().toString();
        String idStr = idEdt.getText().toString();
        int id = v.getId();
        if (id == R.id.insert_btn) {//                db.rawQuery();        查询    select * from 表名
//                db.execSQL();         添加、删除、修改、创建表

            //String sql = "insert into info_tb (name,age,gender) values ('"+nameStr+"',"+ageStr+",'"+genderStr+"')";
            String sql = "insert into info_tb (name,age,gender) values (?,?,?)";
            db.execSQL(sql, new String[]{nameStr, ageStr, genderStr});
            Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.select_btn) {//SQLiteOpenHelper
            //select * from 表名 where _id = ?
            String sql2 = "select * from info_tb";
            if (!idStr.equals("")) {
                sql2 += " where _id=" + idStr;
            }
            //查询结果
            Cursor c = db.rawQuery(sql2, null);

            //SimpleCursorAdapter
            //SimpleAdapter a = new SimpleAdapter()
            //参数3：数据源
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                    this, R.layout.item, c,
                    new String[]{"_id", "name", "age", "gender"},
                    new int[]{R.id.id_item, R.id.name_item, R.id.age_item, R.id.gender_item});
            stuList.setAdapter(adapter);
        } else if (id == R.id.delete_btn) {//' '   "23"   23
            String sql3 = "delete from info_tb where _id=?";
            db.execSQL(sql3, new String[]{idStr});
            Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.update_btn) {
            String sql4 = "update info_tb set name=? , age=? , gender=?  where _id=?";
            db.execSQL(sql4, new String[]{nameStr, ageStr, genderStr, idStr});
            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
        }
        nameEdt.setText("");
        ageEdt.setText("");
        idEdt.setText("");
        malerb.setChecked(true);
    }
}
