package io.github.taodaren.fragmentbestpractice;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * 新闻标题 Fragment
 */
public class NewsTitleFragment extends Fragment {
    //是否双页
    private boolean isTwoPage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_title, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        RecyclerView rvNewsTitle = (RecyclerView) view.findViewById(R.id.recycler_news_title);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        rvNewsTitle.setLayoutManager(manager);
        NewsAdapter adapter = new NewsAdapter(getNewsData());
        rvNewsTitle.setAdapter(adapter);
    }

    /**
     * 初始化 50 条模拟新闻数据
     */
    private List<NewsBean> getNewsData() {
        List<NewsBean> beanList = new ArrayList<>();
        for (int i = 1; i < 51; i++) {
            NewsBean newsBean = new NewsBean();
            newsBean.setTitle("新闻标题" + i);
            newsBean.setContent(getRandomLengthContent("新闻详细内容" + i + "。"));
            beanList.add(newsBean);
        }
        return beanList;
    }

    /**
     * 随机生成新闻内容长度
     */
    private String getRandomLengthContent(String content) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(content);
        }
        return builder.toString();
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

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
        private List<NewsBean> beanList;

        public NewsAdapter(List<NewsBean> beanList) {
            this.beanList = beanList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NewsBean newsBean = beanList.get(holder.getAdapterPosition());
                    if (isTwoPage) {
                        //如果是双页模式，则刷新 NewsContentFragment 中的内容
                        NewsContentFragment newsContentFragment =
                                (NewsContentFragment) getFragmentManager().findFragmentById(R.id.news_content_fragment);
                        newsContentFragment.refresh(newsBean.getTitle(), newsBean.getContent());
                    } else {
                        //如果是单页模式，则直接启动 NewsContentActivity
                        NewsContentActivity.actionStart(getActivity(), newsBean.getTitle(), newsBean.getContent());
                    }
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            NewsBean newsBean = beanList.get(position);
            holder.textNewsTitle.setText(newsBean.getTitle());
        }

        @Override
        public int getItemCount() {
            return beanList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView textNewsTitle;

            public ViewHolder(View itemView) {
                super(itemView);
                textNewsTitle = (TextView) itemView.findViewById(R.id.text_news_item);
            }
        }
    }
}
