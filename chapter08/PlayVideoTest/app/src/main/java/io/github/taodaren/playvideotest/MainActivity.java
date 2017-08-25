package io.github.taodaren.playvideotest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        videoView = (VideoView) findViewById(R.id.video_view);
        findViewById(R.id.btn_play).setOnClickListener(this);
        findViewById(R.id.btn_pause).setOnClickListener(this);
        findViewById(R.id.btn_replay).setOnClickListener(this);

        //运行时权限处理，动态申请 WRITE_EXTERNAL_STORAGE 权限
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            //初始化 MediaPlayer
            initVideoPath();
        }
    }

    private void initVideoPath() {
        File file = new File(Environment.getExternalStorageDirectory(), "nian_lun.mp4");
        videoView.setVideoPath(file.getPath());//指定视频文件路径
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //如果用户同意权限申请，那么对 MediaPlayer 对象初始化
                    initVideoPath();
                } else {
                    //如果用户拒绝了权限申请，那么直接关闭程序
                    Toast.makeText(this, "拒绝权限将无法使用程序", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_play:
                //如果没有正在播放视频
                if (!videoView.isPlaying()) {
                    //开始播放
                    videoView.start();
                }
                break;
            case R.id.btn_pause:
                //如果正在播放视频
                if (videoView.isPlaying()) {
                    //暂停播放
                    videoView.pause();
                }
                break;
            case R.id.btn_replay:
                //如果正在播放视频
                if (videoView.isPlaying()) {
                    //重新播放
                    videoView.resume();
                }
                break;
            default:
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoView != null) {
            //释放掉 VideoView 所占用资源
            videoView.suspend();
        }
    }

}
