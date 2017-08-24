package io.github.taodaren.cameraalbumtest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final int TAKE_PHOTO = 1;

    private ImageView imgPicture;
    private Uri imgUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        imgPicture = (ImageView) findViewById(R.id.img_picture);

        //处理调用摄像头逻辑
        findViewById(R.id.btn_take_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建 File 对象，用于存储拍照后的照片
                File fileOutputImg = new File(getExternalCacheDir(), "output_image.jpg");//getExternalCacheDir() 可以获取应用关联缓存目录
                try {
                    //如果存在照片则删除
                    if (fileOutputImg.exists()) {
                        fileOutputImg.delete();
                    }
                    //创建新文件
                    fileOutputImg.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT >= 24) {//运行设备的系统版本在 Android 7.0 之上
                    //将 File 对象转换成一个封装过的 Uri 对象
                    imgUri = FileProvider.getUriForFile(MainActivity.this, "io.github.taodaren.fileprovider", fileOutputImg);
                } else {//运行设备的系统版本低于 Android 7.0
                    //将 File 对象转换成 Uri 对象
                    imgUri = Uri.fromFile(fileOutputImg);//图片对应本地真实路径

                }
                //启动相机程序
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);//指定图片输出地址，传入得到 Uri 对象
                startActivityForResult(intent, TAKE_PHOTO);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        //将拍摄的照片显示出来
                        Bitmap bitmap = BitmapFactory
                                .decodeStream(getContentResolver().openInputStream(imgUri));//将照片解析成 Bitmap 对象
                        imgPicture.setImageBitmap(bitmap);//设置到 ImgView 中显示
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }

}
