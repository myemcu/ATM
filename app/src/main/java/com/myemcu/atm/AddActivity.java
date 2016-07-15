package com.myemcu.atm;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    private EditText edDate;
    private EditText edInfo;
    private EditText edAmount;

    private MyDBHelper helper; // MyDBHelper 就是 MyDBHelper.class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);

        findViews();

        //-创建SQLite数据库对象--------------------------------------------
        //当前活动,数据库名(自取),标准模式处理Cursor,当前版本
        helper = new MyDBHelper(this, "expense.db", null, 1);
    }

    private void findViews() {
        edDate   = (EditText) findViewById(R.id.ed_date);
        edInfo   = (EditText) findViewById(R.id.ed_info);
        edAmount = (EditText) findViewById(R.id.ed_amount);
    }

    //Button事件响应
    public void add_Click(View v) {

        //捕获用户输入
        String cdate = edDate.getText().toString();                         // 读取输入日期
        String  info = edInfo.getText().toString();                         // 读取说明信息
        int   amount = Integer.parseInt(edAmount.getText().toString());     // 读取输入金额

        //产生并收集一笔记录
        ContentValues values = new ContentValues();                         // 产生记录
        values.put("cdate",  cdate);                                        // 收集记录
        values.put("info",   info);
        values.put("amount", amount);

        long id = helper.getWritableDatabase().insert("exp",null,values);   // 插入记录

        //Log.d("ADD",id+"");

        Toast.makeText(this,"SQLite插入记录"+id,Toast.LENGTH_SHORT).show();
    }
}
