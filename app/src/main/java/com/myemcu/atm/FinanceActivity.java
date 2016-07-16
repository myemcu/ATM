package com.myemcu.atm;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class FinanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finance);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //按下"浮动按钮将跳到AddAvtivity"
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

                startActivity(new Intent(FinanceActivity.this, AddActivity.class));
            }
        });

        //获取content_finance.xml中的ListView对象
        ListView list = (ListView) findViewById(R.id.list);

        //产生MyDBHelper对象并查询exp表格
        //MyDBHelper helper = new MyDBHelper(this, "expense.db", null, 1);

        MyDBHelper helper = MyDBHelper.getInstance(this);

        Cursor c = helper.getReadableDatabase().query("exp",null,null,null,null,null,null);

        //产生SimpleCursorAdapter对象(若数据来源为查询结果Cursor时,则使用SimpleCursorAdapter建立Adapter)
        /*SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                                                              this,
                                                              android.R.layout.simple_expandable_list_item_2,
                                                              c,
                                                              new String[] {"info","amount"},
                                                              new int[] {android.R.id.text1,android.R.id.text2},
                                                              0
                                                             );*/


        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                R.layout.finance_row,
                c,
                new String[] {"_id", "cdate", "info", "amount"},
                new int[] {R.id.item_id, R.id.item_cdate, R.id.item_info, R.id.item_amount},
                0
        );

        list.setAdapter(adapter);
    }

}
