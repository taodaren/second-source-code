package io.github.taodaren.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class HelloActivity extends AppCompatActivity {
    //输入「logt」快速生成常量 TAG
    private static final String TAG = "HelloActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        //日志工具Log
        Log.d("taodaren", "onCreate: debug");
        Log.i(TAG, "onCreate: info");
    }
}
