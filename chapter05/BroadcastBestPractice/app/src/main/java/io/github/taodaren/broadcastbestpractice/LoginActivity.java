package io.github.taodaren.broadcastbestpractice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {
    private EditText editAccount, editPassword;
    private CheckBox cbRememberPass;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editAccount = (EditText) findViewById(R.id.edit_account);
        editPassword = (EditText) findViewById(R.id.edit_password);

        sp = PreferenceManager.getDefaultSharedPreferences(this);
        cbRememberPass = (CheckBox) findViewById(R.id.checkbox_remember_password);
        boolean isRemember = sp.getBoolean("remember_password", false);
        if (isRemember) {
            //将账号密码都设置到文本框中
            String account = sp.getString("account", "");
            String password = sp.getString("password", "");
            editAccount.setText(account);
            editPassword.setText(password);
            cbRememberPass.setChecked(true);
        }

        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = editAccount.getText().toString();
                String password = editPassword.getText().toString();
                //如果账号是 taodaren 且密码是 123456 ，就认为登陆成功
                if (account.equals("taodaren") && password.equals("123456")) {
                    editor = sp.edit();
                    //检查复选框是否被选中
                    if (cbRememberPass.isChecked()) {
                        editor.putBoolean("remember_password", true);
                        editor.putString("account", account);
                        editor.putString("password", password);
                    } else {
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "account or password is invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
