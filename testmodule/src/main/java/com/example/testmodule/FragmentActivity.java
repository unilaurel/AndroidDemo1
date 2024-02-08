package com.example.testmodule;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testmodule.utils.ListFragment;


public class FragmentActivity extends AppCompatActivity implements ListFragment.OnTitleClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        findViewById(R.id.textview1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //static load fragment
                startActivity(new Intent(FragmentActivity.this, StaticLoadFragmentActivity.class));
            }
        });

        //1. Container 2. fragment 3. fragment--container
//        ListFragment fragment = new ListFragment();
        ListFragment listFragment = ListFragment.newInstance("guolai");
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.listContainer, listFragment)
                .commit();

        listFragment.setOnClickListener(this);

        ListFragment detailFragment = ListFragment.newInstance("menglan");
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.detailContainer, detailFragment)//注意：1 つのfragmentフラグメントは 1 つのcontainerコンテナにのbindみバインドできます。
                .commit();

        detailFragment.setOnClickListener(this);


//        getSupportFragmentManager()
//                .beginTransaction()
//                .remove(fragment)
//                .commit();
    }

    @Override
    public void onClick(String title) {
        setTitle(title);
    }
}