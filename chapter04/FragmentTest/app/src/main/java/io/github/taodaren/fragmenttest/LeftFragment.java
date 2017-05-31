package io.github.taodaren.fragmenttest;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class LeftFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_left, container, false);

        //在碎片中调用活动的方法
        MainActivity activity = (MainActivity) getActivity();//得到和当前碎片相关联的活动实例
        return view;
    }

}
