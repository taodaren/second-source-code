# Activity 的基本用法

>Activity是一个应用组件，用户可与其提供的屏幕进行交互，以执行拨打电话、拍摄照片、发送电子邮件或查看地图等操作。每个 Activity 都会获得一个用于绘制其用户界面的窗口。窗口通常会充满屏幕，但也可小于屏幕并浮动在其他窗口之上。

### 创建 Activity

要创建 Activity，您必须创建 Activity 的子类（或使用其现有子类）。您需要在子类中实现 Activity 在其生命周期的各种状态之间转变时（例如创建 Activity、停止 Activity、恢复 Activity 或销毁 Activity 时）系统调用的回调方法。两个最重要的回调方法是：

- onCreate()

  必须实现此方法。系统会在创建您的 Activity 时调用此方法。您应该在实现内`初始化` Activity 的必需组件。 最重要的是，您必须在此方法内调用 `setContentView()`，以定义 Activity 用户界面的布局。

- onPause()

  系统将此方法作为用户离开 Activity 的`第一个信号`（但并不总是意味着 Activity 会被销毁）进行调用。 您通常应该在此方法内确认在当前用户会话结束后仍然有效的任何更改（因为用户可能不会返回）。

您还应使用几种其他生命周期回调方法，以便提供流畅的 Activity 间用户体验，以及处理导致您的 Activity 停止甚至被销毁的意外中断。

![新建Activity.jpg](http://upload-images.jianshu.io/upload_images/3171392-0018bbebf541980c.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

如图所示，可以快速创建一个活动，为了更好的理解，我们手动分步开始创建：

###### 首先，新建FirstAtivity继承并自AppCompatActivity

```java
public class FirstActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
```

可以看到，这里调用了`setContentView()`方法来给当前的活动加载一个布局，在此方法中，一般都会传入一个布局文件的id。

###### 第二，创建加载布局文件

![新建布局资源文件.jpg](http://upload-images.jianshu.io/upload_images/3171392-84dc0e1ef7d0762d.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

在res的layout目录右键`Layout resource file`，按图示创建布局文件，并添加代码如下：

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <Button
        android:id="@+id/button1"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Button 1"/>
</LinearLayout>
```

现在可以通过右侧工具栏的Preview来预览一下当前布局，如图所示：

![预览当前布局.jpg](http://upload-images.jianshu.io/upload_images/3171392-bbcddbd1fb67ee04.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

###### 第三，在清单文件中声明 Activity

```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
          package="cn.ucai.activitytest">
    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".FirstActivity"></activity>
    </application>
</manifest>
```

在`<activity>`标签中我们使用了`android:name`来指定具体注册哪一个活动；在`<application>`标签中我们使用了`android:label`来指定活动中标题栏的内容。

###### 最后，当一切准备就绪，运行程序就可以看到首次运行的结果，有好奇心的可以一试！

### 在活动中使用 Toast

Toast是Android中一个很好的提醒方式，形状跟吐司面包很像，姑且如此记下吧。

![toast.jpg](https://upload-images.jianshu.io/upload_images/3171392-aab4db75ba05dc61.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

多说无益，上代码！在`onCreate()`中使用Toast。

```java
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.first_layout);
    Button button1 = (Button) findViewById(R.id.button1);
    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(FirstActivity.this, "You clicked Button 1", Toast.LENGTH_SHORT).show();
        }
    });
}
```

可以看到，出现了一个新的方法—`findViewById()`，它返回的是一个View对象，该方法可以获取布局文件中定义的元素。Toast的静态方法`makeText()`需传入三个参数：

- 参数一：Context（Toast要求的上下文）
- 参数二：Toast显示的文本内容
- 参数三：Toast显示的时长（有两个内置常量`Toast.LENGTH_SHORT`和`Toast.LENGTH_LONG`）

### 在活动中使用 Menu

###### 创建菜单

 首先在res目录下新建menu文件夹，右击menu文件夹 → New → Menu resources file 创建菜单。输入文件名`main`，完成后添加如下代码，使用`<item>`创建两个菜单项。

```xml
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:id="@+id/add_item"
        android:title="Add"/>
    <item
        android:id="@+id/remove_item"
        android:title="Remove"/>
</menu>
```

接着回到FirstActivity中重写`onCreateOptionMenu()`方法，重写快捷键`Alt+Insert`（Mac系统是`control+O`）

```java
@Override
public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
}
```

getMenuInflater()的inflate()方法可以给当前活动创建菜单，它接收两个参数:

- 参数一：指定通过哪一个资源文件创建菜单
- 参数二：指定菜单项将添加到哪个Menu对象中

然后返回true，若返回false，创建的菜单将无法显示。

###### 定义菜单响应事件

继续在FirstActivity中重写`onOptionsItemSelected()`方法，通过调用`item.getItemId()`来判断点击菜单项。

```java
@Override
public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
        case R.id.add_item:
            Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show();
            break;
        case R.id.remove_item:
            Toast.makeText(this, "You clicked Remove", Toast.LENGTH_SHORT).show();
            break;
        default:
    }
    return true;
}
```

###### 销毁 Activity

正常按下`Back`键就会销毁活动，如果想用代码销毁，可以通过Activity类中的`finish()`实现。

```java
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Toast.makeText(FirstActivity.this, "You clicked Button 1", Toast.LENGTH_SHORT).show();
        finish();
    }
});
```