package io.github.taodaren.cameraalbumtest;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final int TAKE_PHOTO = 1;
    private static final int CHOOSE_PHOTO = 2;

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

        //从相册选取照片逻辑
        findViewById(R.id.btn_choose_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MainActivity.this,//进行运行时权限处理
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)//动态申请危险权限（同时授予程序对 SD 卡读和写的能力）
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this
                            , new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    openAlbum();
                }
            }
        });
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");//指定 action
        intent.setType("image/*");//为 Intent 对象设置参数
        //打开相册
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
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
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    //判断手机系统版本号
                    if (Build.VERSION.SDK_INT >= 19) {
                        //4.4 及以上系统使用这个方法处理图片
                        handleImageOnKitKat(data);
                    } else {
                        //4.4 以下系统使用这个方法处理图片
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }

    /**
     * 解析封装过的 Uri
     * @param data
     */
    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imgPath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            //如果是 document 类型的 Uri，则通过 document id 处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                //如果 Uri 的 authority 是 media 格式，document id 还需要再解析一次
                String id = docId.split(":")[1];//解析出数字格式的 id
                String selection = MediaStore.Images.Media._ID + "=" + id;//构建新的 Uri 和条件语句
                imgPath = getImagePath(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);//获取图片真实路径
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imgPath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            //如果是 content 类型的 Uri，则使用普通方式处理
            imgPath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            //如果是 file 类型的 Uri，直接获取图片路径即可
            imgPath = uri.getPath();
        }
        //根据图片路径显示图片
        displayImage(imgPath);
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imgPath = getImagePath(uri, null);
        displayImage(imgPath);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        //通过 Uri 和 selection 来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imgPath) {
        if (imgPath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imgPath);
            imgPicture.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "未能获取图片", Toast.LENGTH_SHORT).show();
        }
    }

}
