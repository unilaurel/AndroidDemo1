package com.example.jinjie;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jinjie.Model.Msg;
import com.example.jinjie.Model.MsgLab;
import com.example.jinjie.utils.MsgAdpater;

import java.util.ArrayList;
import java.util.List;

public class CardViewActivity4 extends AppCompatActivity {

    private ListView mLvMsgList;
    private List<Msg> mDatas=new ArrayList<>();
    private MsgAdpater mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view4);

        mLvMsgList = findViewById(R.id.id_lv_msglist);

        mDatas.addAll(MsgLab.generateMockList());
        mAdapter = new MsgAdpater(this, mDatas);
        mLvMsgList.setAdapter(mAdapter);
    }
}