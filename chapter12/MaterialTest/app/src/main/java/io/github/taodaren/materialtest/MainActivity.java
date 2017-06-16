package io.github.taodaren.materialtest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private FruitBean[] fruits = {
            new FruitBean("Apple", R.drawable.apple), new FruitBean("Banana", R.drawable.banana),
            new FruitBean("Cherry", R.drawable.cherry), new FruitBean("Grape", R.drawable.grape),
            new FruitBean("Mango", R.drawable.mango), new FruitBean("Origin", R.drawable.orange),
            new FruitBean("Pear", R.drawable.pear), new FruitBean("Pineapple", R.drawable.pineapple),
            new FruitBean("Strawberry", R.drawable.strawberry), new FruitBean("Watermelon", R.drawable.watermelon)
    };
    private List<FruitBean> fruitList = new ArrayList<>();
    private FruitAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置 ToolBar
        setToolBar();
        //设置滑动菜单
        setDrawerLayout();
        setNavigationView();
        //设置悬浮控件
        setFloatingActionButton();

        initData();
        initView();
    }

    private void initData() {
        //清空数据
        fruitList.clear();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }

    private void initView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);
    }

    private void setFloatingActionButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //使用 Snackbar 提示工具
                Snackbar.make(view, "数据删除", Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "数据恢复", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });
    }

    private void setNavigationView() {
        //获取 NavigationView 实例
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        //设置默认选中项
        navView.setCheckedItem(R.id.nav_call);
        //设置监听器
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //将滑动菜单关闭
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    private void setDrawerLayout() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //显示 ToolBar 左侧导航按钮
            actionBar.setDisplayHomeAsUpEnabled(true);
            //设置（自定义）导航按钮图标
            actionBar.setHomeAsUpIndicator(android.R.drawable.ic_menu_sort_by_size);
        }
    }

    private void setToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home://导航按钮固定 id
                //打开滑动菜单
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:
                Toast.makeText(this, "You clicked Backup", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "You clicked Delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "You clicked Settings", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}
