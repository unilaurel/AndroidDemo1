package com.example.storage.storagedemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.storage.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void operate(View v){
        Intent it=null;
        int id = v.getId();
        if (id == R.id.share_btn) {
            it = new Intent(this, ShareActivity.class);
        } else if (id == R.id.external_btn) {
            it = new Intent(this, ExternalActivity.class);
        } else {
            it = new Intent(this, InternalActivity.class);
        }
        startActivity(it);
    }
}
