package io.github.taodaren.activitytest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        Button button = (Button) findViewById(R.id.button_1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(FirstActivity.this, "按钮被点击！", Toast.LENGTH_SHORT).show();

                //销毁当前活动
//                finish();

                //活动跳转-显式
//                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
//                startActivity(intent);

                //活动跳转-隐式
//                Intent intent = new Intent("io.taodaren.github.activitytest.ACTION_START");
//                intent.addCategory("io.taodaren.github.activitytest.TAODAREN_CATEGORY");//添加 category
//                startActivity(intent);

                //更多隐式用法
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("https://taodaren.github.io/"));
//                startActivity(intent);

                //调用系统拨号界面
//                Intent intent = new Intent(Intent.ACTION_DIAL);
//                intent.setData(Uri.parse("tel:10011"));
//                startActivity(intent);

                //传递数据给下一个Activity
                String data = "taodaren is a handsome guy.";
                Intent intent = new Intent(FirstActivity.this, ThirdActivity.class);
                intent.putExtra("taodaren", data);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //为当前活动创建菜单
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //定义菜单响应事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(this, "Add 被点击", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this, "Remove 被点击", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}
