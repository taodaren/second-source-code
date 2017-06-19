package io.github.taodaren.filepersistencetest;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.edit_text);

        String inputText = load();
        //一次性进行两种空值判断
        if (!TextUtils.isEmpty(inputText)) {
            //将内容添加到 EditText
            editText.setText(inputText);
            //将输入光标移动到文本末尾
            editText.setSelection(inputText.length());
            Toast.makeText(this, "还原成功", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 读取文件中存储的文本内容
     */
    private String load() {
        FileInputStream fis = null;
        BufferedReader bufferedReader = null;
        StringBuilder sbContent = new StringBuilder();
        try {
            fis = openFileInput("data");
            bufferedReader = new BufferedReader(new InputStreamReader(fis));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                sbContent.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sbContent.toString();
    }

    /**
     * 活动销毁前一定调用此方法
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        String inputText = editText.getText().toString();
        save(inputText);
    }

    /**
     * 将输入的内容存储到文件中
     */
    private void save(String inputText) {
        FileOutputStream fos = null;
        BufferedWriter bufferedWriter = null;
        try {
            //将数据存储到指定文件
            fos = openFileOutput("data", Context.MODE_PRIVATE);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fos));
            bufferedWriter.write(inputText);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
