package com.example.viewpage;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class TabViewPagerActivity extends AppCompatActivity implements TabHost.TabContentFactory {

    private TabHost mTabHost;
    private static final String TAG = "TabViewPagerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_view_pager);

        //①总Layoutを初期化する
        mTabHost = (TabHost) findViewById(R.id.tab_host);
        mTabHost.setup();
        //②3个Tabを処理する
        //-init data
        int[] titleIDs = {R.string.home, R.string.message, R.string.me};
        int[] drawableIDs = {
                R.drawable.main_tab_icon_home,
                R.drawable.main_tab_icon_message,
                R.drawable.main_tab_icon_me};

        //③ 3个Fragment構成するViewPager
        Fragment[] fragments = new Fragment[]{
                TestFragment.newInstance("home"),
                TestFragment.newInstance("message"),
                TestFragment.newInstance("me"),
        };

        //- data--view
        for (int i = 0; i < titleIDs.length; i++) {
            View view = getLayoutInflater().inflate(R.layout.main_tab_layout, null, false);
            ImageView icon = view.findViewById(R.id.main_tab_icon);
            TextView title = view.findViewById(R.id.main_tab_txt);
            View tab = view.findViewById(R.id.tab_bg);

            icon.setImageResource(drawableIDs[i]);
            title.setText(titleIDs[i]);

            tab.setBackgroundColor(getResources().getColor(R.color.white));

            mTabHost.addTab(mTabHost.newTabSpec(
                    getString(titleIDs[i]))
                    .setIndicator(view)
                    .setContent(this)

            )

            ;
        }


        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }

//            @NonNull
//            @Override
//            public Object instantiateItem(@NonNull ViewGroup container, int position) {
//                return super.instantiateItem(container, position);
//            }

            @Override
            public int getCount() {
                return fragments.length;
            }

            @Override
            public void destroyItem(@NonNull View container, int position, @NonNull Object object) {
                Log.i(TAG, "destroyItem: ");
                super.destroyItem(container, position, object);
                
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (mTabHost != null) {
                    mTabHost.setCurrentTab(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if(mTabHost!=null){
                    int position = mTabHost.getCurrentTab();
                    viewPager.setCurrentItem(position);

                }
            }
        });
    }

    @Override
    public View createTabContent(String tag) {
        View view = new View(this);
        //隠す
        view.setMinimumHeight(0);
        view.setMinimumWidth(0);
        return view;
    }
}