package io.github.taodaren.listviewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<AndroidVersionHistory> versionList = new ArrayList<>();
    private VersionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        for (int i = 0; i < 2; i++) {
            AndroidVersionHistory astro = new AndroidVersionHistory("Astro", android.R.mipmap.sym_def_app_icon);
            versionList.add(astro);
        }
    }

    private void initView() {
        adapter = new VersionAdapter(MainActivity.this, R.layout.item_version, versionList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }
}
