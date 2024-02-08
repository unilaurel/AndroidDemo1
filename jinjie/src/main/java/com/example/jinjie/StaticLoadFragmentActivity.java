package com.example.jinjie;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.jinjie.utils.ListFragment;

public class StaticLoadFragmentActivity extends AppCompatActivity {
    private static final String TAG = "StaticLoadFragmentActiv";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_load_fragment);

        FragmentManager fragmentManager = getSupportFragmentManager();
        ListFragment fragment = (ListFragment) fragmentManager.findFragmentById(R.id.listFragment);
        Log.i(TAG, fragment.value);
        if (fragment != null){

            Button btn1 = (Button) fragment.getView().findViewById(R.id.btn1);

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btn1.setText("点击");
                    Toast.makeText(StaticLoadFragmentActivity.this, "你点了text", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}