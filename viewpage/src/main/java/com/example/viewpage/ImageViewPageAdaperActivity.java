package com.example.viewpage;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class ImageViewPageAdaperActivity extends AppCompatActivity {

    public static final int INIT_POSITION = 1;
    private ViewPager mviewPager;
    private PagerTabStrip tab;

    private List<String> titleList;


    private int[] mLayoutID = {
            R.layout.view_first, R.layout.view_second, R.layout.view_third,
    };
    private List<View> mviews;

    //布局文件中的容器通常是 ViewGroup 或其子类。ViewGroup 是 View 的一个子类，用于容纳其他的 View 对象
    // レイアウトファイルのコンテナは通常、ViewGroupまたはそのサブクラスです。ViewGroupはViewのサブクラスであり、他のViewオブジェクトを格納するために使用されます。
    private ViewGroup mDotViewGroup;

    private List<ImageView> mDotViews = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mviewPager = findViewById(R.id.view_pager);
        mDotViewGroup = findViewById(R.id.dot_layout);

        // ViewPager にタイトルを設定する
        titleList = new ArrayList<>();
        titleList.add("第1页");
        titleList.add("第2页");
        titleList.add("第3页");

        //tab属性の設定
        tab = findViewById(R.id.tab);
        tab.setBackgroundColor(Color.YELLOW);
        tab.setTextColor(Color.RED);
        tab.setDrawFullUnderline(false);
        tab.setTabIndicatorColor(Color.GREEN);



//dataを初期化する
        mviews = new ArrayList<>();
        for (int i = 0; i < mLayoutID.length; i++) {
            View view = getLayoutInflater().inflate(mLayoutID[i],null);
            View view2=View.inflate(this,mLayoutID[i],null);
            mviews.add(view);
//            ImageView imageView = new ImageView(this);
//            imageView.setImageResource(R.drawable.ic_launcher_foreground);
//            mviews.add(imageView);

            ImageView dot = new ImageView(this);
            dot.setImageResource(R.mipmap.ic_launcher);
            dot.setMaxHeight(100);
            dot.setMaxWidth(100);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(80, 80);
            layoutParams.leftMargin = 20;
            dot.setLayoutParams(layoutParams);
            dot.setEnabled(false);
            mDotViewGroup.addView(dot);
            mDotViews.add(dot);

        }
//Adapterを設定する
        mviewPager.setAdapter(mpagerAdapter);
        mviewPager.setOffscreenPageLimit(4);
        setDotViews(INIT_POSITION);
        mviewPager.setCurrentItem(INIT_POSITION);

        mviewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setDotViews(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setDotViews(int position) {
        for (int i = 0; i < mDotViews.size(); i++) {
            mDotViews.get(i).setImageResource(position == i ? R.color.black : R.mipmap.ic_launcher);
        }
    }

    PagerAdapter mpagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return mLayoutID.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View child = mviews.get(position);
            container.addView(child);
            return child;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(mviews.get(position));
        }
    };
}