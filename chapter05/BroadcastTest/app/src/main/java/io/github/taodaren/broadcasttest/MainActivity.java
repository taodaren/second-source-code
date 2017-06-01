package io.github.taodaren.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private IntentFilter intentFilter;
    private NetWorkChangeReceiver netWorkChangeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intentFilter = new IntentFilter();
        netWorkChangeReceiver = new NetWorkChangeReceiver();
        //添加 action
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        //注册
        registerReceiver(netWorkChangeReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册
        unregisterReceiver(netWorkChangeReceiver);
    }

    /**
     * 发送自定义广播（标准广播）
     * @param view
     */
    public void onMyBroadcast(View view) {
        Intent intent = new Intent("io.github.taodaren.broadcasttest.MY_BROADCAST");
        //发送标准广播
//        sendBroadcast(intent);
        //发送有序广播
        sendOrderedBroadcast(intent, null);
    }

    class NetWorkChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //网络连接管理
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            //判断是否有网络
            if (networkInfo != null && networkInfo.isAvailable()) {
                Toast.makeText(context, "网络可用", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "网络不可用", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
