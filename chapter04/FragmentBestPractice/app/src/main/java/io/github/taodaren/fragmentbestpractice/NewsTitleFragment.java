package io.github.taodaren.fragmentbestpractice;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


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

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
        private List<NewsBean> beanList;

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
