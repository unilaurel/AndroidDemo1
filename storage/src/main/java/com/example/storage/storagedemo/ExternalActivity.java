package com.example.storage.storagedemo;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.storage.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExternalActivity extends AppCompatActivity {
    private static final String TAG = "ExternalActivity";
    EditText infoEdt;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external);

        infoEdt = findViewById(R.id.info_edt);
        txt = findViewById(R.id.textView);

//        int permisson = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        if (permisson != PackageManager.PERMISSION_GRANTED) {
//            //动态去申请权限
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
//        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            //xxxxxxxxxxxxx
        }
    }

    public void operate(View v) {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/imooc88.txt";
        String filesDir = getExternalFilesDir(null).toString();
        String cacheDir = getExternalCacheDir().toString();

        Log.e(TAG, "operate1: filesDir " + filesDir);
        Log.e(TAG, "operate1: cacheDir" + cacheDir);

        Log.e("TAG", path);
//        if(Environment.getExternalStorageState().equals("mounted"))
        int id = v.getId();
        if (id == R.id.save_btn) {
            File f = new File(path);
            try {
                if (!f.exists()) {
                    f.createNewFile();
                }

                FileOutputStream fos = new FileOutputStream(path, true);
                String str = infoEdt.getText().toString();
                fos.write(str.getBytes());
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        } else if (id == R.id.read_btn) {
            try {
                FileInputStream fis = new FileInputStream(path);
                byte[] b = new byte[1024];
                int len = fis.read(b);
                String str2 = new String(b, 0, len);
                txt.setText(str2);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
