package io.github.taodaren.sharedpreferencestest;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_save_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取 SharedPreferences.Editor 对象
                SharedPreferences.Editor editor = getSharedPreferences("sp_data", MODE_PRIVATE).edit();
                //向对象中添加数据
                editor.putString("name", "taodaren");
                editor.putInt("age", 29);
                editor.putBoolean("married", false);
                //将添加的数据提交
                editor.apply();
            }
        });

        findViewById(R.id.btn_restore_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取 SharedPreferences 对象
                SharedPreferences sp = getSharedPreferences("sp_data", MODE_PRIVATE);
                //获取数据（若没找到对应值，则传入默认值）
                String name = sp.getString("name", "");
                int age = sp.getInt("age", 0);
                boolean married = sp.getBoolean("married", false);
                //打印 log 信息
                Log.e(TAG, "name is: " + name);
                Log.e(TAG, "age is: " + age);
                Log.e(TAG, "married is: " + married);
            }
        });
    }
}
