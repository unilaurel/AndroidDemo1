package com.example.testmodule;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



//       Button bt= findViewById(R.id.searchButton);
//       bt.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               Intent it=new Intent(MainActivity.this,FrameActivity.class);
//               startActivity(it);
//               it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//           }
//       });

//        CheckBox cb= findViewById(R.id.checkBox);
//        ToggleButton tb=findViewById(R.id.togglebutton);

//        if(cb.isChecked()){
//            cb.setChecked(false);
//            System.out.println("11111");
//        }else{
//            cb.setChecked(true);
//            System.out.println(2222222);
//        }

//        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                Log.d(TAG,"oncheckedChanged:"+isChecked);
//            }
//        });

//        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                Log.d(TAG,"toggle:oncheckedChanged:"+isChecked);
//            }
//        });







//        Button btn1 = findViewById(R.id.btn1);
//        btn1.setOnClickListener(new MyClickListener());
//
//        Button btn2 = findViewById(R.id.btn2);
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("tag", "匿名内部类");
//            }
//        });
//
//        Button btn3 = findViewById(R.id.btn3);
//        btn3.setOnClickListener(this);


//        TextView textView = findViewById(R.id.textview_test);
//        textView.requestFocus();

//        LinearLayout ll = new LinearLayout(this);
//        ll.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        ll.setBackgroundColor(Color.RED);
//        setContentView(ll);

    }



    @Override
    public void onClick(View v) {
        Log.e("tag", "activity实现");
    }


    class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Log.e("tag", "btn1,内部类");
        }
    }

    public void myClick(View v) {
        if (v.getId() == R.id.btn4) {
            Log.e("TAG", "btn4===");
        } else if (v.getId() == R.id.btn5) {
            Log.e("TAG", "btn5===");
        }else if (v.getId()== R.id.frame){

        }
    }


}