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

    private void initView() {
        adapter = new VersionAdapter(MainActivity.this, R.layout.item_version, versionList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }

    private void initData() {
        for (int i = 0; i < 2; i++) {
            AndroidVersionHistory astro = new AndroidVersionHistory("Astro", android.R.mipmap.sym_def_app_icon);
            versionList.add(astro);
            AndroidVersionHistory bender = new AndroidVersionHistory("Bender", android.R.mipmap.sym_def_app_icon);
            versionList.add(bender);
            AndroidVersionHistory cupcake = new AndroidVersionHistory("Cupcake", android.R.mipmap.sym_def_app_icon);
            versionList.add(cupcake);
            AndroidVersionHistory donut = new AndroidVersionHistory("Donut", android.R.mipmap.sym_def_app_icon);
            versionList.add(donut);
            AndroidVersionHistory eclair = new AndroidVersionHistory("Eclair", android.R.mipmap.sym_def_app_icon);
            versionList.add(eclair);
            AndroidVersionHistory froyo = new AndroidVersionHistory("Froyo", android.R.mipmap.sym_def_app_icon);
            versionList.add(froyo);
            AndroidVersionHistory gingerbread = new AndroidVersionHistory("Gingerbread", android.R.mipmap.sym_def_app_icon);
            versionList.add(gingerbread);
            AndroidVersionHistory honeycomb = new AndroidVersionHistory("Honeycomb", android.R.mipmap.sym_def_app_icon);
            versionList.add(honeycomb);
            AndroidVersionHistory ice = new AndroidVersionHistory("Ice Cream Sandwich", android.R.mipmap.sym_def_app_icon);
            versionList.add(ice);
            AndroidVersionHistory jelly = new AndroidVersionHistory("Jelly Bean", android.R.mipmap.sym_def_app_icon);
            versionList.add(jelly);
            AndroidVersionHistory ket = new AndroidVersionHistory("KitKat", android.R.mipmap.sym_def_app_icon);
            versionList.add(ket);
            AndroidVersionHistory lollipop = new AndroidVersionHistory("Lollipop", android.R.mipmap.sym_def_app_icon);
            versionList.add(lollipop);
            AndroidVersionHistory marshmallow = new AndroidVersionHistory("Marshmallow", android.R.mipmap.sym_def_app_icon);
            versionList.add(marshmallow);
            AndroidVersionHistory nougat = new AndroidVersionHistory("Nougat", android.R.mipmap.sym_def_app_icon);
            versionList.add(nougat);
        }
    }
}
