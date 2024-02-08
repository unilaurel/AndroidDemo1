package com.example.jinjie;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jinjie.utils.ImageAdapter;

public class GalleryActivity3 extends AppCompatActivity implements AdapterView.OnItemSelectedListener, ViewSwitcher.ViewFactory {
    //１、dataを用意する
    //2、Adapterを用意する

    private int[] resIds = {R.drawable.wawacai, R.drawable.suncainiuroumian,
            R.drawable.suanlatang, R.drawable.qingzhengluyu,
            R.drawable.hongshaorou, R.drawable.shuizhuyu, R.drawable.malaxiangguo
    };
    private ImageAdapter imageAdapter;
    private Gallery gallery;
    private ImageSwitcher imageSwitcher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery3);

        gallery = findViewById(R.id.gallery);
        //3，GalleryにAdapterをロードする
        imageAdapter = new ImageAdapter(resIds, this);
        gallery.setAdapter(imageAdapter);
        //4,Listenerを設定する,imageSwitcherを設定する
        gallery.setOnItemSelectedListener(this);

        imageSwitcher = findViewById(R.id.is);
        imageSwitcher.setFactory(this);
        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        imageSwitcher.setBackgroundResource(resIds[position % resIds.length]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public View makeView() {
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        return imageView;
    }
}