package com.myemcu.atm;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    private EditText UserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        UserId = (EditText) findViewById(R.id.user);

        //帐号读取
        SharedPreferences setting = getSharedPreferences("atm", MODE_PRIVATE);
        UserId.setText(setting.getString(getString(R.string.userid_write), ""));
    }

    //"登陆"事件处理(根据帐号密码来登陆)
    public void login(View v) {

        // 获取编辑框对象
        EditText user = (EditText) findViewById(R.id.user);
        EditText psd  = (EditText) findViewById(R.id.psd);

        // 获取编辑框输入
        String uid = user.getText().toString(); // 输入的帐号(文本转字符串)
        String pwd = psd.getText().toString();  // 输入的密码(文本转字符串)

        /*
        // 校验帐号密码(引入网络登陆前的处理)
        if(uid.equals("老婆") && pwd.equals("520025")) { // 一个预设的帐号，密码

            //帐号存储(密码不存储)
            SharedPreferences setting = getSharedPreferences("atm", MODE_PRIVATE);
            setting.edit().putString(getString(R.string.userid_write), uid).commit();

            //消息提示
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
        }*/

        //-验证网络登陆------------------------------------------------------------------------------
        String url = new StringBuffer("http://atm201605.appspot.com/login?uid=").append(uid)
                                                                                .append("&pw=")
                                                                                .append(pwd)
                                                                                .toString();
        new LoginTask().execute(url);
    }

    // 登陆网络的AsyncTask内部类(只在该LoginActivity中使用)
    class LoginTask extends AsyncTask<String, Void, Boolean> { // 传入Web;连线时间短,不需要回传资料;登入状态

        @Override
        // 此处为登陆验证程序
        protected Boolean doInBackground(String... params) { // 需Alt+Enter

            boolean logon = false; // 定义登陆标志

            try {
                    URL url = new URL(params[0]);       //敲完红浪
                    InputStream is = url.openStream();  //敲完红浪
                    int data = is.read();
                    Log.d("HTTP", String.valueOf(data));
                    if (data==49) {
                        logon = true;
                    }
                    is.close();
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            return logon;
        }

        @Override // 需Ctrl+O
        //  此处为验证完成后的登陆成功与失败的对应处理程序
        protected void onPostExecute(Boolean logon) { // 连线完成后，仍需要给用户回应，故才重写该方法
            super.onPostExecute(logon);

            if (logon) {
                Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_LONG).show();
                setResult(RESULT_OK, getIntent());
                finish();
            }
            else {
                new AlertDialog.Builder(LoginActivity.this)
                               .setTitle("ATM")
                               .setMessage("登陆失败")
                               .setPositiveButton("确定", null)
                               .show();
            }
        }
    }

    public void cancel(View v) {

    }
}


