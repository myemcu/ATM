package com.myemcu.atm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    boolean logon = false; // 未登陆

    public static final int FUNC_LOGIN =1 ; // 登陆界面(LoginActivity)的功能常数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        if(!logon) { // 若未登陆,则开启登入LoginActivity
            Intent login = new Intent(this, LoginActivity.class);
            //startActivity(login);
            startActivityForResult(login, FUNC_LOGIN);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == FUNC_LOGIN) { //由LoginActivity返回的功能码
            if(resultCode == RESULT_OK){ //由LoginActivity返回的结果码
                String uid = data.getStringExtra("LOGIN_USERID");
                String pwd = data.getStringExtra("LOGON_PASSWD");
                Log.d("RESULT",uid+"/"+pwd);
            }
            else {
                finish();
            }
        }
    }
}
