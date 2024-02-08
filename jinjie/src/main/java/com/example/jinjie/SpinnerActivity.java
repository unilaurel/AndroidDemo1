package com.example.jinjie;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class SpinnerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private TextView textView;
    private Spinner spinner;

    private List<String> list = Arrays.asList("北京", "上海", "广州", "东京");

    private ArrayAdapter<String> arr_adaper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        textView = findViewById(R.id.text);
        spinner = findViewById(R.id.spinner);

        //dataを用意する
        //Adapterを用意する
        //pull時のスタイルを設定する　　　setDropDownViewResource()
        //SpinnerにAdapterをロードする
        //Spinnerのリスナーを設置する

        arr_adaper = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);//android自分のLayout
        arr_adaper.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//android自分のLayout

        spinner.setAdapter(arr_adaper);
        spinner.setOnItemSelectedListener(this);



    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String str = list.get(position);
        textView.setText("你点击了："+str);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        textView.setText("请选择城市：");
    }
}