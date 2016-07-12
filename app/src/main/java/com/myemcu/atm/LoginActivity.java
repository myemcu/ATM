package com.myemcu.atm;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    //"登陆"事件处理(根据帐号密码来登陆)
    public void login(View v) {

        // 获取编辑框对象
        EditText user = (EditText) findViewById(R.id.user);
        EditText psd  = (EditText) findViewById(R.id.psd);

        // 获取编辑框输入
        String uid = user.getText().toString(); // 输入的帐号(文本转字符串)
        String pwd = psd.getText().toString();  // 输入的密码(文本转字符串)

        // 校验帐号密码
        if(uid.equals("老婆") && pwd.equals("520025")) { // 一个预设的帐号，密码
            Toast.makeText(this, "登陆到主界面", Toast.LENGTH_SHORT).show();

            //将"帐号"与"密码"放入Intent对象中
            getIntent().putExtra("LOGIN_USERID", uid);
            getIntent().putExtra("LOGON_PASSWD",pwd);
            //设置本活动返回结果(返回到MainActivity)
            setResult(RESULT_OK, getIntent());

            finish(); // 结束本Avtivity，回退到MainActivity
        }
        else {
            new AlertDialog.Builder(this)
                           .setTitle("中国建设很行")
                           .setMessage("登陆失败")
                           .setPositiveButton("确定",null)
                           .show();
        }
    }

    public void cancel(View v) {

    }
}
