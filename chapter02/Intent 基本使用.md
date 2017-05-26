## 使用 Intent 在活动之间穿梭

`Intent` 是 Android 中各组件间进行交互的一种重要方式，它不仅可以指明当前组件想要执行的动作，还可以在不同组件之间传递数据。Intent 一般可被用于`启动活动`、`启动服务`以及`发送广播`等场景。

### 显式 Intent

首先创建 SecondActivity 并自动生成布局文件，在布局文件中添加一个按钮。

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="io.github.taodaren.activitytest.SecondActivity">

    <Button
        android:id="@+id/button_2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:text="Button 2" />
</LinearLayout>
```

SecondActivity 中的代码自动生成无需改动，需要注意的是，Activity 都需要在 AndroidManifest.xml 中注册，在 Android Studio 中已自动完成，无需再注册。

```xml
<activity android:name=".SecondActivity"></activity>
```

下面，我们继续修改 FirstActivity 的按钮点击事件，如下所示：

```java
Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
startActivity(intent);
```

Intent 方法中有两个参数：第一个参数 `Context` 要求提供一个启动活动的上下文，第二个参数 `Class` 则是要启动的目标活动。接着就可以运行程序查看结果了！

### 隐式 Intent

隐式 Intent 不同于显式 Intent 意图那么明显，它指定了更为抽象的 `action` 和 `category` 等信息，然后交由系统去分析这个 Intent，并帮我们找出合适的活动去启动。

```xml
<activity android:name=".SecondActivity">
    <intent-filter>
        <action android:name="io.taodaren.github.activitytest.ACTION_START" />
        <category android:name="android.intent.category.DEFAULT" />
    </intent-filter>
</activity>
```

按照如上代码配置 `<action>` 和 `<category>` ，只有他们同时匹配才可以相应 Intent，下面继续处理 FirstActivity 中的点击事件：

```java
Intent intent = new Intent("io.taodaren.github.activitytest.ACTION_START");
startActivity(intent);
```

这里由于 `DEFAULT` 是默认的，所有不用指定 category 。每个 Intent 中只能制定一个 action ，但却能指定多个 category。我们再添加一个 category，继续修改代码。

```java
Intent intent = new Intent("io.taodaren.github.activitytest.ACTION_START");
intent.addCategory("io.taodaren.github.activitytest.TAODAREN_CATEGORY");//添加 category
startActivity(intent);
```

运行程序，无需多想，肯定报错！原因不言而喻，少指定了 category 呗！别忘了加上它！

![错误信息.png](http://upload-images.jianshu.io/upload_images/3171392-5285ab4607f112cc.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 隐式 Intent 更多用法

#### 启动浏览器

```java
Intent intent = new Intent(Intent.ACTION_VIEW);
intent.setData(Uri.parse("https://taodaren.github.io/"));
startActivity(intent);
```

如代码所示，首先指定了 action 是 ACTION_VIEW，然后传网址，即可打开浏览器。

对应的，还可以在 `<intent-filter>` 标签中再配置一个 `<data>` 标签，更精确的指定相应的类型数据。`<data>` 标签主要可以配置以下内容：

|属性|作用|用途|
|:-|:-|:-|
|android:scheme|用于指定数据的协议部分|如上例中的 https 部分|
|android:host|用于指定数据的主机名部分|如上例中的 taodaren.github.io 部分|
|android:port|用于指定数据的端口部分|一般紧随在主机名之后|
|android:path|用于指定主机名和端口之后的部分|如一段网址中跟在域名之后的内容|
|android:mimeType|用于指定可以处理的数据类型|允许使用通配符的方式进行指定|

#### 多活动同时启动浏览器

新建 ThirdActivity 并在其 <intent-filter> 标签中添加属性：

```xml
<activity android:name=".ThirdActivity">
    <intent-filter>
        <action android:name="android.intent.action.VIEW" />
        <category android:name="android.intent.category.DEFAULT" />
        <data android:scheme="https" />
    </intent-filter>
</activity>
```

系统会自动弹出一个列表，显示目前能响应该 Intent 的所有程序，按需即可。

![选择响应 Intent 的程序.png](http://upload-images.jianshu.io/upload_images/3171392-ad4394e06cb2a87a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

#### 调用系统拨号界面

```java
Intent intent = new Intent(Intent.ACTION_DIAL);
intent.setData(Uri.parse("tel:10011"));
startActivity(intent);
```

### 数据传递

其实 Intent 的数据传递就是一系列 putExtra() 方法的重载，可以把我们想要传递的数据暂存在 Intent 中，启动了另一个活动后，再把这些数据从 Intent 取出即可。

#### 向下一个活动传递数据

首先，修改 FirstActivity 中的代码暂存数据

```java
String data = "taodaren is a handsome guy.";
Intent intent = new Intent(FirstActivity.this, ThirdActivity.class);
intent.putExtra("taodaren", data);
startActivity(intent);
```

其次，修改 ThirdActivity 中的代码去除数据

```java
Intent intent = getIntent();
String data = intent.getStringExtra("taodaren");
Log.i("taodaren", "onCreate: " + data);
```

最后，打印一下 log 信息，完美了！

```
.../io.github.taodaren.activitytest I/taodaren: onCreate: taodaren is a handsome guy.
```

#### 返回数据给上一个活动

核心在于一个方法 `startActivityForResult()` ，该方法在活动销毁的时候能够返回一个结果给上一个活动。它接收两个参数，第一个是 Intent ，第二个是请求码，用于在之后的回掉中判断数据的来源。老样子，继续修改点击事件：

```java
Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
startActivityForResult(intent, 1);
```

其实请求码只要是一个唯一值就可以了。接下来在 SecondActivity 中修改代码如下：

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_second);
    findViewById(R.id.button_2).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.putExtra("data_return", "Hello FirstActivity");
            setResult(RESULT_OK, intent);//向上一个活动返回数据
            finish();
        }
    });
}
```

这里的 `setResult()` 非常重要，它专门用于向上一个活动返回数据。第一个参数用于向上一个活动返回处理结果（一般只使用 `RESULT_OK` 或 `RESULT_CANCELED` 这两个值），第二个参数是把带有数据的 Intent 传递回去。

由于使用 startActivityForResult() 方法来启动 SecondActivity 的，在其被销毁后会回掉上一个活动的 onActivityResult() 方法，因此需要在 FirstActivity 中进行重写：

```java
/**
 * 用于回掉返回的数据
 *
 * @param requestCode 启动活动时传入的请求码
 * @param resultCode  返回数据时传入处理结果
 * @param data        携带返回数据 intent
 */
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    switch (requestCode) {
        case 1:
            if (resultCode == RESULT_OK) {
                String returnedData = data.getStringExtra("data_return");
                Log.d("taodaren", "onActivityResult: FirstActivity===" + returnedData);
            }
            break;
        default:
    }
}
```

重新运行程序，在 FirstActivity 界面点击 SecondActivity ，然后点击按钮返回，这是查看 log 信息如下所示：

```
.../io.github.taodaren.activitytest D/taodaren: onActivityResult: FirstActivity===Hello FirstActivity
```

如果通过 Back 键返回，这样数据就无法返回，处理方法也很简单，在 SecondActivity 中重写 onBackPressed() 方法即可解决，代码如下：

```java
@Override
public void onBackPressed() {
    Intent intent = new Intent();
    intent.putExtra("data_return", "Hello FirstActivity ! I'm Back.");
    setResult(RESULT_OK, intent);
    finish();
}
```