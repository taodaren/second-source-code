package io.github.taodaren.recyclerviewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<RecyclerBean> beanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerAdapter adapter = new RecyclerAdapter(beanList);
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        beanList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            RecyclerBean astro = new RecyclerBean(getRandomLengthName("Astro"), android.R.drawable.sym_def_app_icon);
            beanList.add(astro);
            RecyclerBean bender = new RecyclerBean(getRandomLengthName("Bender"), android.R.drawable.sym_def_app_icon);
            beanList.add(bender);
            RecyclerBean cupcake = new RecyclerBean(getRandomLengthName("Cupcake"), android.R.drawable.sym_def_app_icon);
            beanList.add(cupcake);
            RecyclerBean donut = new RecyclerBean(getRandomLengthName("Donut"), android.R.drawable.sym_def_app_icon);
            beanList.add(donut);
            RecyclerBean eclair = new RecyclerBean(getRandomLengthName("Eclair"), android.R.drawable.sym_def_app_icon);
            beanList.add(eclair);
            RecyclerBean froyo = new RecyclerBean(getRandomLengthName("Froyo"), android.R.drawable.sym_def_app_icon);
            beanList.add(froyo);
            RecyclerBean gingerbread = new RecyclerBean(getRandomLengthName("Gingerbread"), android.R.drawable.sym_def_app_icon);
            beanList.add(gingerbread);
            RecyclerBean honeycomb = new RecyclerBean(getRandomLengthName("Honeycomb"), android.R.drawable.sym_def_app_icon);
            beanList.add(honeycomb);
            RecyclerBean ice = new RecyclerBean(getRandomLengthName("Ice Cream"), android.R.drawable.sym_def_app_icon);
            beanList.add(ice);
            RecyclerBean jelly = new RecyclerBean(getRandomLengthName("Jelly Bean"), android.R.drawable.sym_def_app_icon);
            beanList.add(jelly);
            RecyclerBean kitKat = new RecyclerBean(getRandomLengthName("KitKat"), android.R.drawable.sym_def_app_icon);
            beanList.add(kitKat);
            RecyclerBean lollipop = new RecyclerBean(getRandomLengthName("Lollipop"), android.R.drawable.sym_def_app_icon);
            beanList.add(lollipop);
            RecyclerBean marshmallow = new RecyclerBean(getRandomLengthName("Marshmallow"), android.R.drawable.sym_def_app_icon);
            beanList.add(marshmallow);
            RecyclerBean nougat = new RecyclerBean(getRandomLengthName("Nougat"), android.R.drawable.sym_def_app_icon);
            beanList.add(nougat);
        }
    }

    private String getRandomLengthName(String name) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(name);
        }
        return builder.toString();
    }
}
