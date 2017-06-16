package io.github.taodaren.materialtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class FruitActivity extends AppCompatActivity {
    public static final String FRUIT_NAME = "fruit_name";
    public static final String FRUIT_IMAGE_ID = "fruit_image_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);

        //通过 Intent 传入水果名及其资源id
        Intent intent = getIntent();
        String fruitName = intent.getStringExtra(FRUIT_NAME);
        int fruitImgId = intent.getIntExtra(FRUIT_IMAGE_ID, 0);

        //实例化控件
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_fruit_ctl);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ImageView imgFruit = (ImageView) findViewById(R.id.img_fruit_ctl);
        TextView textFruitContent = (TextView) findViewById(R.id.text_fruit_content);

        //Toolbar 使用
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);//启用 HomeAsUp 按钮
        }

        //填充界面内容
        collapsingToolbar.setTitle(fruitName);//将水果名设置成当前界面标题
        Glide.with(this).load(fruitImgId).into(imgFruit);//设置标题栏背景
        String fruitContent = generateFruitContent(fruitName);
        textFruitContent.setText(fruitContent);//设置内容详情
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 模拟数据
     */
    private String generateFruitContent(String fruitName) {
        StringBuilder fruitContent = new StringBuilder();
        for (int i = 0; i < 500; i++) {
            fruitContent.append(fruitName);
        }
        return fruitContent.toString();
    }
}
