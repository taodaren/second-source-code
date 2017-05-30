package io.github.taodaren.recyclerviewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

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
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerAdapter adapter = new RecyclerAdapter(beanList);
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        beanList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            RecyclerBean astro = new RecyclerBean("Astro", android.R.drawable.sym_def_app_icon);
            beanList.add(astro);
            RecyclerBean bender = new RecyclerBean("Bender", android.R.drawable.sym_def_app_icon);
            beanList.add(bender);
            RecyclerBean cupcake = new RecyclerBean("Cupcake", android.R.drawable.sym_def_app_icon);
            beanList.add(cupcake);
            RecyclerBean donut = new RecyclerBean("Donut", android.R.drawable.sym_def_app_icon);
            beanList.add(donut);
            RecyclerBean eclair = new RecyclerBean("Eclair", android.R.drawable.sym_def_app_icon);
            beanList.add(eclair);
            RecyclerBean froyo = new RecyclerBean("Froyo", android.R.drawable.sym_def_app_icon);
            beanList.add(froyo);
            RecyclerBean gingerbread = new RecyclerBean("Gingerbread", android.R.drawable.sym_def_app_icon);
            beanList.add(gingerbread);
            RecyclerBean honeycomb = new RecyclerBean("Honeycomb", android.R.drawable.sym_def_app_icon);
            beanList.add(honeycomb);
            RecyclerBean ice = new RecyclerBean("Ice Cream Sandwich", android.R.drawable.sym_def_app_icon);
            beanList.add(ice);
            RecyclerBean jelly = new RecyclerBean("Jelly Bean", android.R.drawable.sym_def_app_icon);
            beanList.add(jelly);
            RecyclerBean kitKat = new RecyclerBean("KitKat", android.R.drawable.sym_def_app_icon);
            beanList.add(kitKat);
            RecyclerBean lollipop = new RecyclerBean("Lollipop", android.R.drawable.sym_def_app_icon);
            beanList.add(lollipop);
            RecyclerBean marshmallow = new RecyclerBean("Marshmallow", android.R.drawable.sym_def_app_icon);
            beanList.add(marshmallow);
            RecyclerBean nougat = new RecyclerBean("Nougat", android.R.drawable.sym_def_app_icon);
            beanList.add(nougat);
        }
    }
}
