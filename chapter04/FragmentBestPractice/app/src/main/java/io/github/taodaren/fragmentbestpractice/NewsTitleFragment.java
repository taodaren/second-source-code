package io.github.taodaren.fragmentbestpractice;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsTitleFragment extends Fragment {
    //是否双页
    private boolean isTwoPage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_title, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //碎片中调用活动
        if (getActivity().findViewById(R.id.news_content_layout) != null) {
            //可以找到 news_content_layout 布局时，为双页模式
            isTwoPage = true;
        } else {
            //找不到 news_content_layout 布局时，为单页模式
            isTwoPage = false;
        }
    }
}
