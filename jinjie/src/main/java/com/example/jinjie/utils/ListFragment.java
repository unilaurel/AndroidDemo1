package com.example.jinjie.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.jinjie.R;


//List Fragment
public class ListFragment extends Fragment {


    private static final String TAG = "ListFragment";
    public static final String BUNDLE_TITLE = "bundle_title";
    private String title="imooc";
    public static String value="nihaohiiiiii";

    public static ListFragment newInstance(String title) {
        ListFragment fragment = new ListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE, title);
        fragment.setArguments(bundle);

        return fragment;

    }

    public void setmUser(User mUser) {
        this.mUser = mUser;
    }

    private User mUser;

    public class User{

    }

    public static ListFragment newInstance(String title,User user) {
        ListFragment fragment = new ListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE, title);
        fragment.setArguments(bundle);
        fragment.setmUser(user);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
//        monTitleClickListener = (OnTitleClickListener) activity;
        super.onAttach(activity);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments()!=null){
            title = getArguments().getString(BUNDLE_TITLE);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        TextView textView = view.findViewById(R.id.textView);
        textView.setText(title);

//        Toast.makeText(getActivity(),"已收到9999",Toast.LENGTH_SHORT).show();
//        Toast.makeText(getActivity(),"向Activity发感谢",Toast.LENGTH_SHORT).show();

//        monTitleClickListener.onClick("thank you");
        textView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                value = "静态传值fragment-->Activity";
                Toast.makeText(getActivity(), ListFragment.value,Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onClick: ");
                if(monTitleClickListener!=null){
                    monTitleClickListener.onClick(title);
                }
            }
        });
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //parameter
    OnTitleClickListener monTitleClickListener;

    //interface--method
    public void setOnClickListener(OnTitleClickListener onTitleClickListener){
        monTitleClickListener = onTitleClickListener;
    }

    //interface
    public interface OnTitleClickListener{
        void onClick(String title);
    }


}
