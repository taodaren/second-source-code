package io.github.taodaren.fragmentbestpractice;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * 新闻内容 Fragment
 */
public class NewsContentFragment extends Fragment {
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news_content, container, false);
        return view;
    }

    /**
     * 显示新闻界面
     *
     * @param newsTitle   新闻标题
     * @param newsContent 新闻内容
     */
    public void refresh(String newsTitle, String newsContent) {
        View visibilityLayout = view.findViewById(R.id.visibility_layout);
        visibilityLayout.setVisibility(View.VISIBLE);
        TextView textNewsTitle = (TextView) view.findViewById(R.id.text_news_title);
        TextView textNewsContent = (TextView) view.findViewById(R.id.text_news_content);
        textNewsTitle.setText(newsTitle);
        textNewsContent.setText(newsContent);
    }

}
