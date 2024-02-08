package com.example.jinjie;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener {
    private static final String TAG = "ListViewActivity";
    private ListView listView;
    private ArrayAdapter<String> arr_adapter;
    private SimpleAdapter simp_adapter;
    private List<Map<String, Object>> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
//ArrayAdapter
        listView = findViewById(R.id.listView);
        String[] items = {"Java", "MySQL", "Android", "HTML", "C", "JavaScript"};
        dataList = new ArrayList<Map<String, Object>>();
        //①adapterを作成する　②adapterにdatasourceをロードする
        arr_adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);

        //SimpleAdapter
        //data:dataSource mapから構成するList
        //ここのMapはListViewの中の一行に対応している
        //ここのMapの中の鍵はぜひFromの全部鍵を含まれている
        //From　Mapのなかの鍵名
        //To　data　ViewのIDにバンディングする
        simp_adapter = new SimpleAdapter(this, getData(), R.layout.items, new String[]{"pic", "text"}, new int[]{R.id.pic, R.id.text});

        //③ListViewにadapterをロードする
//        listView.setAdapter(arr_adapter);
        listView.setAdapter(simp_adapter);

        listView.setOnItemClickListener(this);
        listView.setOnScrollListener(this);
    }

    private List<Map<String, Object>> getData() {
        for (int i = 0; i < 20; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("pic", R.mipmap.ic_launcher);
            map.put("text", "花花的"+i);
            dataList.add(map);
        }
        return dataList;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if(scrollState==SCROLL_STATE_FLING){
            Log.i(TAG, "SCROLL_STATE_FLING: ");//（フリング）スクロール状態で、ユーザーが画面上で指をスライドさせ、迅速に指を離すと、リストは慣性に従って継続的にスクロールします。"
            Map<String, Object> map = new HashMap<>();
            map.put("pic", R.mipmap.ic_launcher_round);
            map.put("text","增加项");
            dataList.add(map);
            simp_adapter.notifyDataSetChanged();
        }else if (scrollState==SCROLL_STATE_IDLE){
            Log.i(TAG, "SCROLL_STATE_IDLE: ");
        }else if(scrollState==SCROLL_STATE_TOUCH_SCROLL){//（タッチスクロール）
            Log.i(TAG, "SCROLL_STATE_TOUCH_SCROLL: ");
        }

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String text = listView.getItemAtPosition(position).toString();
        Toast.makeText(this, "postion= "+position+" text= "+text, Toast.LENGTH_SHORT).show();
    }
}