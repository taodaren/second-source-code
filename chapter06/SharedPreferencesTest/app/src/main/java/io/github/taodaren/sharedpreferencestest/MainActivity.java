package io.github.taodaren.sharedpreferencestest;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

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
    }
}
