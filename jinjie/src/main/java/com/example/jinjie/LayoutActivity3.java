package com.example.jinjie;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LayoutActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout3);

        View comm_view = findViewById(R.id.comm_title);
        TextView textView = comm_view.findViewById(R.id.comm2);
        textView.setText("标题");

        Button btn= findViewById(R.id.layoutbtn);
        ViewStub stub=findViewById(R.id.stub);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stub.inflate();
            }
        });
    }
}