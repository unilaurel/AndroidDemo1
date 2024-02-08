package com.example.testmodule;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testmodule.model.Food;
import com.example.testmodule.model.Person;

import java.util.ArrayList;
import java.util.List;

public class DinnerActivity extends AppCompatActivity {
    private EditText mNameEditText;
    private RadioGroup mSexRadioGroup;
    private CheckBox mHotCheckBox, mFishCheckBox, mSourCheckBox;
    private SeekBar mSeekBar;
    private Button mSearchButton;
    private ImageView mFoodImageView;
    private ToggleButton mToggleButton;
    private List<Food> mFoods;
    private Person mPerson;
    private List<Food> mFoodResult;
    private boolean mIsFish;
    private boolean mIsSour;
    private boolean mIsHot;
    private int mPrice;
    private int mCurrentIndex;
    private TextView tv1;
    private TextView tv2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dinner_test);

        //1，ウィジェットの初期化
        findViews();
        //2. データの初期化
        initData();
        //3. コントロールにリスナーを追加して，基本機能を実現する
        setListeners();
    }

    private void setListeners() {
        mNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mPerson != null) {
                    mPerson.setName(s.toString());
                }
            }
        });

        mSexRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.maleRadioButton) {
                    mPerson.setSex("男");

                } else if (checkedId == R.id.femaleRadioButton) {
                    mPerson.setSex("女");
                }

            }
        });
        mHotCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mIsHot = isChecked;

            }
        });
        mFishCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mIsFish = isChecked;
            }
        });
        mSourCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mIsSour = isChecked;
            }
        });

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
tv1.setText("正在拖动");
tv2.setText("当前数据："+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                tv1.setText("开始拖动");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mPrice = seekBar.getProgress();
                Toast.makeText(DinnerActivity.this, "价格" + mPrice, Toast.LENGTH_SHORT).show();
            }
        });

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();

            }
        });

        mToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mToggleButton.isChecked()) {
                    mCurrentIndex++;
                    if (mCurrentIndex < mFoodResult.size()) {
                        mFoodImageView.setImageResource(mFoodResult.get(mCurrentIndex).getPic());
                    }
                } else {
                    if (mCurrentIndex < mFoodResult.size()) {
                        String foodName = mFoodResult.get(mCurrentIndex).getName();
//                        String personName = String.valueOf(mNameEditText.getText());
                        String personName=mPerson.getName();
                        String sex = mPerson.getSex();

                        Toast.makeText(DinnerActivity.this, "菜名：" + foodName + "人名" + personName + "性别" + sex, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DinnerActivity.this, "没有啦：", Toast.LENGTH_SHORT).show();
                    }

                }
            }

        });


    }

    private void search() {
        if (mFoodResult == null) {
            mFoodResult = new ArrayList<>();
        }

        mFoodResult.clear();
        mCurrentIndex = 0;

        for (Food mFood : mFoods) {

            boolean isFish = mFood.isFish();
            boolean isHot = mFood.isHot();
            boolean isSour = mFood.isSour();
            if (mFood.getPrice() <= mPrice && (mIsFish == isFish || mIsHot == isHot || mIsSour == isSour)) {
                mFoodResult.add(mFood);
            }
        }
        if (mFoodResult.size() > mCurrentIndex) {
            mFoodImageView.setImageResource(mFoodResult.get(mCurrentIndex).getPic());
        }

        Log.e(TAG, mFoodResult.toString());
        mToggleButton.setChecked(true);
    }


    private void initData() {
        mFoods = new ArrayList<>();
        mFoods.add(new Food("麻辣香锅", 55, R.drawable.malaxiangguo, true, false, false));
        mFoods.add(new Food("水煮鱼", 48, R.drawable.shuizhuyu, true, true, false));
        mFoods.add(new Food("麻辣火锅", 80, R.drawable.malahuoguo, true, true, false));
        mFoods.add(new Food("清蒸鲈鱼", 68, R.drawable.qingzhengluyu, false, true, false));
        mFoods.add(new Food("桂林米粉", 15, R.drawable.guilin, false, false, false));
        mFoods.add(new Food("上汤娃娃菜", 28, R.drawable.wawacai, false, false, false));
        mFoods.add(new Food("红烧肉", 60, R.drawable.hongshaorou, false, false, false));
        mFoods.add(new Food("木须肉", 40, R.drawable.muxurou, false, false, false));
        mFoods.add(new Food("酸菜牛肉面", 35, R.drawable.suncainiuroumian, false, false, true));
        mFoods.add(new Food("西芹炒百合", 38, R.drawable.xiqin, false, false, false));
        mFoods.add(new Food("酸辣汤", 40, R.drawable.suanlatang, true, false, true));

        mPerson = new Person();
        mFoodResult = new ArrayList<>();
    }

    private void findViews() {
        mNameEditText = findViewById(R.id.nameEditText);
        mSexRadioGroup = findViewById(R.id.sexRadioGroup);
        mHotCheckBox = findViewById(R.id.hotCheckBox);
        mFishCheckBox = findViewById(R.id.fishCheckBox);
        mSourCheckBox = findViewById(R.id.sourCheckBox);
        mSeekBar = findViewById(R.id.seekBar);
        mSeekBar.setProgress(30);
        mSearchButton = findViewById(R.id.searchButton);
        mToggleButton = findViewById(R.id.showToggleButton);
        mToggleButton.setChecked(true);
        mFoodImageView = findViewById(R.id.foodImageView);
        tv1 = findViewById(R.id.textView2);
        tv2 = findViewById(R.id.textView3);
    }
}