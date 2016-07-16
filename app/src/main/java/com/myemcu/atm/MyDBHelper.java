package com.myemcu.atm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/7/15.
 */

// 新类继承后一般要点Alt+Enter进行Implement methods
public class MyDBHelper extends SQLiteOpenHelper {
    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    //构建数据表(使用写数据库对象呼叫SQL方法来建表)
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE main.exp"+                        // 数据表名exp
                        "(_id INTEGER PRIMARY KEY NOT NULL,"+   // 主键
                        "cdate DATETIME NOT NULL,"+             // 日期
                        "info VARCHAR,"+                        // 信息
                        "amount INTEGER)"                       // 金额
        );
    }

    @Override
    //数据表的版本更新
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
