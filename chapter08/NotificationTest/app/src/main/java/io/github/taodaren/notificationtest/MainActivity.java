package io.github.taodaren.notificationtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_send_notice).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send_notice:
                //获取 NotificationManager 实例
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                //兼容所有版本，设置通知内容
                Notification notification = new Notification.Builder(this)
                        .setContentTitle("这是通知标题")
                        .setContentText("通知正文内容")
                        //指定创建时间
                        .setWhen(System.currentTimeMillis())
                        //通知小图标
                        .setSmallIcon(R.mipmap.ic_launcher)
                        //通知大图标
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .build();
                //显示通知
                manager.notify(1, notification);
                break;
            default:
                break;
        }
    }
}
