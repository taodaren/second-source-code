2003年10月，Andy Rubin等人一起创办了Android公司。
2005年8月谷歌收购了这家仅仅成立了22个月的公司，并让Andy Rubin继续负责Android项目。
2008年推出了Android系统的第一个版本。

2010年被Linux团队从Linux内核主线中除名。又由于Android中的应用程序都是使用Java开发的，甲骨文则针对Android侵犯Java知识产权一事对谷歌提起了诉讼……
仅仅推出两年后，Android就超过了已经霸占市场逾十年的诺基亚Symbian，成为了全球第一大智能手机操作系统

## Android系统架构

![Android 系统架构](http://upload-images.jianshu.io/upload_images/3171392-476ec9a7b854033d.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### Linux内核层

Android系统是基于Linux内核的，这一层为Android设备的各种硬件提供了底层的驱动，如显示驱动、音频驱动、照相机驱动、蓝牙驱动、Wi-Fi驱动、电源管理等。

### 系统运行库层

这一层通过一些C/C++库来为Android系统提供了主要的特性支持。如SQLite库提供了数据库的支持，OpenGL|ES库提供了3D绘图的支持，Webkit库提供了浏览器内核的支持等。

同样在这一层还有Android运行时库，它主要提供了一些核心库，能够允许开发者使用Java语言来编写Android应用。另外，Android运行时库中还包含了Dalvik虚拟机（5.0系统之后改为ART运行环境），它使得每一个Android应用都能运行在独立的进程当中，并且拥有一个自己的Dalvik虚拟机实例。相较于Java虚拟机，Dalvik是专门为移动设备定制的，它针对手机内存、CPU性能有限等情况做了优化处理。

### 应用框架层

这一层主要提供了构建应用程序时可能用到的各种API，Android自带的一些核心应用就是使用这些API完成的，开发者也可以通过使用这些API来构建自己的应用程序。

### 应用层

所有安装在手机上的应用程序都是属于这一层的，比如系统自带的联系人、短信等程序，或者是你从Google Play上下载的小游戏，当然还包括你自己开发的程序。

## 四大组件

>Android系统四大组件分别是活动（Activity）、服务（Service）、广播接收器（Broadcast Receiver）和内容提供器（Content Provider）。

其中`活动`是所有Android应用程序的门面，凡是在应用中你看得到的东西，都是放在活动中的。而`服务`就比较低调了，你无法看到它，但它会一直在后台默默地运行，即使用户退出了应用，服务仍然是可以继续运行的。`广播接收器`允许你的应用接收来自各处的广播消息，比如电话、短信等，当然你的应用同样也可以向外发出广播消息。`内容提供器`则为应用程序之间共享数据提供了可能，比如你想要读取系统电话簿中的联系人，就需要通过内容提供器来实现。

## 掌握日志工具的使用

### 使用Android的日志工具Log

Android中的日志工具类是Log（android.util.Log），这个类中提供了如下5个方法来供我们打印日志。Log.v()→Log.d()→Log.i()→Log.w()→Log.e()。

Log.d()方法中传入了两个参数：第一个参数是tag，一般传入当前的类名就好，主要用于对打印信息进行过滤；第二个参数是msg，即想要打印的具体的内容。

### 快捷键

对于快捷输入，在Android Studio当中也是有的，比如你想打印一条debug级别的日志，那么只需要输入logd，然后按下Tab键，就会帮你自动补全一条完整的打印语句。输入logi，然后按下Tab键，会自动补全一条info级别的打印日志。输入logw，按下Tab键，会自动补全一条warn级别的打印日志，以此类推。

另外，由于Log的所有打印方法都要求传入一个tag参数，每次写一遍显然太过麻烦。这里还有一个小技巧，我们在onCreate()方法的外面输入logt，然后按下Tab键，这时就会以当前的类名作为值自动生成一个TAG常量，如下所示：

```java
public class HelloWorldActivity extends AppCompatActivity {
    private static final String TAG = "HelloWorldActivity";
    ...
}
```