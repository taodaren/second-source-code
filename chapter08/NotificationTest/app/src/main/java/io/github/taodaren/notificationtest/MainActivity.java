package io.github.taodaren.notificationtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;

import java.io.File;

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
                //延迟 intent
                Intent intent = new Intent(this, NotificationActivity.class);
                PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
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
                        //延迟启动（为通知添加点击功能）
                        .setContentIntent(pi)
//                        //通知自动取消
//                        .setAutoCancel(true)
//                        //设置音频
//                        .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/MI.ogg")))
//                        //设置震动
//                        .setVibrate(new long[]{0, 1000, 1000, 1000})
//                        //设置呼吸灯
//                        .setLights(Color.BLUE, 1000, 1000)
                        //设置通知默认效果
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .build();
                //显示通知
                manager.notify(1, notification);
                break;
            default:
                break;
        }
    }
}
