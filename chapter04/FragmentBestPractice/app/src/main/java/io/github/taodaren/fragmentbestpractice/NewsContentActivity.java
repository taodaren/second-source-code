package io.github.taodaren.fragmentbestpractice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class NewsContentActivity extends AppCompatActivity {

    public static void actionStart(Context context, String newsTitle, String newsContent) {
        Intent intent = new Intent(context, NewsContentActivity.class);
        intent.putExtra("news_title", newsTitle);
        intent.putExtra("news_content", newsContent);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);
        //获取传入的新闻标题
        String newsTitle = getIntent().getStringExtra("news_title");
        //获取传入的新闻内容
        String newsContent = getIntent().getStringExtra("news_content");
        //Fragment 与 Activity 通信
        NewsContentFragment newsContentFragment = (NewsContentFragment) getSupportFragmentManager().findFragmentById(R.id.news_content_fragment);
        //刷新 NewsContentFragment 界面
        newsContentFragment.refresh(newsTitle, newsContent);
    }
}
