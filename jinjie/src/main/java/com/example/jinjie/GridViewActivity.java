package com.example.jinjie;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GridViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private GridView gridView;
    private List<Map<String, Object>> dataList;
    private int[] icon = {R.drawable.shuizhuyu, R.drawable.malahuoguo,
            R.drawable.muxurou, R.drawable.hongshaorou, R.drawable.guilin,
            R.drawable.qingzhengluyu, R.drawable.suanlatang, R.drawable.suncainiuroumian, R.drawable.wawacai

    };

    private String[] iconName = new String[icon.length];
    private SimpleAdapter simpleAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        gridView = findViewById(R.id.gridView);

        //dataを用意する
        //Adapterを用意する
        //GridviewにAdapterをロードする
        //Gridviewのリスナーを設置する


        for (int i = 0; i < icon.length; i++) {
            iconName[i] = "图标" + i;
        }

        dataList = new ArrayList<Map<String, Object>>();
        getData();
        simpleAdapter = new SimpleAdapter(this, getData(), R.layout.grid_item, new String[]{"pic", "text"},
                new int[]{R.id.grid_image,R.id.grid_text});

        gridView.setAdapter(simpleAdapter);
        gridView.setOnItemClickListener(this);
    }

    private List<Map<String, Object>> getData() {

        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<String,Object>();
            map.put("pic",icon[i]);
            map.put("text",iconName[i]);
            dataList.add(map);
        }

        return dataList;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String str = gridView.getItemAtPosition(position).toString();
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }
}