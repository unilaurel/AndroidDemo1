package com.imooc.imooc_expandablelistview;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;

import com.imooc.imooc_expandablelistview.adapter.ChapterAdapter;
import com.imooc.imooc_expandablelistview.bean.Chapter;
import com.imooc.imooc_expandablelistview.biz.ChapterBiz;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ExpandableListView mExpandableListView;
    private List<Chapter> mDatas = new ArrayList<>();

    private ChapterAdapter mAdapter;

    private Button mBtnRefresh;

    private ChapterBiz mChapterBiz=new ChapterBiz();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initEvents();
        loadDatas(true);
    }

    private void loadDatas(boolean useCache) {
        mChapterBiz.loadDatas(this, new ChapterBiz.CallBack() {
            @Override
            public void onSuccess(List<Chapter> chapterList) {
                Log.e(TAG, "onSuccess: ");
                mDatas.clear();
                mDatas.addAll(chapterList);
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailed(Exception ex) {
                ex.printStackTrace();
                Log.e(TAG, "onFailed: " );

            }
        },useCache);
    }

    private void initEvents() {
        mBtnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDatas(false);
            }
        });



//        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//            @Override
//            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//                Log.d(TAG, "onChildClick: groupPosition"+groupPosition+" childPosition"+childPosition);
//                return false;
//            }
//        });
//
//        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//            @Override
//            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
//                Log.d(TAG, "onGroupClick: groupPosition "+groupPosition);
//                return false;
//            }
//        });
//
//        mExpandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//            @Override
//            public void onGroupExpand(int groupPosition) {
//                Log.d(TAG, "onGroupExpand: groupPosition "+groupPosition);
//            }
//        });
//
//        mExpandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
//            @Override
//            public void onGroupCollapse(int groupPosition) {
//                Log.d(TAG, "onGroupCollapse: groupPosition "+groupPosition);
//            }
//        });
    }

    private void initViews() {
        mBtnRefresh = findViewById(R.id.id_btn_refresh);
        mExpandableListView = findViewById(R.id.id_expandableListView);
        mDatas.clear();
//        mDatas.addAll(ChapterLab.generateDatas());
        mAdapter = new ChapterAdapter(mDatas, this);
        mExpandableListView.setAdapter(mAdapter);
    }
}