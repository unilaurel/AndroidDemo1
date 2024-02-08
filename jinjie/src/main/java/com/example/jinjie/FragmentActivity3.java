package com.example.jinjie;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.jinjie.utils.ListFragment;


public class FragmentActivity3 extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, ListFragment.OnTitleClickListener {
    private static final String TAG = "FragmentActivity3";
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment3);

        radioGroup = findViewById(R.id.radiogroup);
        radioGroup.setOnCheckedChangeListener(this);


    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        if (checkedId == R.id.first) {
            Log.i(TAG, "onCheckedChanged: ");
            startActivity(new Intent(FragmentActivity3.this, StaticLoadFragmentActivity.class));
        } else if (checkedId == R.id.second) {
            ListFragment fragment = ListFragment.newInstance("9999");
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            beginTransaction.add(R.id.frame, fragment).commit();
            beginTransaction.addToBackStack(null);
//

        } else if (checkedId == R.id.third) {

        } else if (checkedId == R.id.fourth) {

        }
    }

    @Override
    public void onClick(String title) {
        setTitle(title);
        Toast.makeText(FragmentActivity3.this, "已接收到" + title+"客气了", Toast.LENGTH_SHORT).show();
    }
}