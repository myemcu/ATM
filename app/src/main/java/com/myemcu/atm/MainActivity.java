//使用Lantern测试翻墙提交GitHub，代码不变(失败,只能不开翻墙提交)。

package com.myemcu.atm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

//主活动类继承AppCompatActivity并执行AdapterView.OnItemClickListener方法
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    boolean logon = false; // 未登陆

    public static final int FUNC_LOGIN =1 ; // 登陆界面(LoginActivity)的功能常数

    String[] func = {"余额查询","交易明细","最新资讯","投资理财","退出"}; // ListView所用数组

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        if(!logon) { // 若未登陆,则开启登入LoginActivity
            Intent login = new Intent(this, LoginActivity.class);
            startActivityForResult(login, FUNC_LOGIN);
        }

        //-增加ListView--------------------------------------------------------------------------
        ListView list = (ListView) findViewById(R.id.list); // 获取对象
        //为其添加适配器                         主活动   SDK中提供的list_layout资源           数组
        //即：将数组中的资源，以SDK中layout目录所提供的simple_list_item_1.xml文件的方式，适配到主活动
        //C:\Users\Administrator\AppData\Local\Android\sdk\platforms\android-23\data\res\layout
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, func);
        list.setAdapter(adapter);   // 设置id为list的适配器
        list.setOnItemClickListener(this); // 与GridView共用同一个事件处理方法
        //-增加Spinner--------------------------------------------------------------------------
        Spinner notify = (Spinner) findViewById(R.id.notify_spinner); // 获取对象
        //若数组存放在Res(资源文件)中,则用这种方法来创建Adapter(即：从资源中创建)。
        //要进行事件处理就要加final
        final ArrayAdapter<CharSequence> nAdapter =
                ArrayAdapter.createFromResource(this,
                        R.array.notify_array,
                        android.R.layout.simple_spinner_item);
        //下拉不拥挤
        nAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //开始适配
        notify.setAdapter(nAdapter);
        //设置监听(Spinner列表项处理)(匿名内层类)
        notify.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position,long id) {
                Toast.makeText(MainActivity.this, nAdapter.getItem(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //-使用网格视图------------------------------------------------------------------------------

        GridView grid = (GridView) findViewById(R.id.grid);
        ArrayAdapter gAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, func);
        grid.setAdapter(gAdapter);

        //设置事件监听(实现接口方法)
        grid.setOnItemClickListener(this);  // 关联程序顶部(implements)与底部(onItemClick)
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

    //-加入选单----------------------------------------------------------------------
    //-Cti+O(在android.app.Activity中找,操作支持多选)
    //成功登陆后才能看到

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //产生菜单,读取menu的xml
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch(id) {
            case R.id.action_settings:
                Toast.makeText(this, "你点的是设置", Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_help:
                Toast.makeText(this, "你点的是帮助", Toast.LENGTH_SHORT).show();
                break;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    //ListView与GridView的事件处理方案(与implements AdapterView.OnItemClickListener关联)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        switch(position) {
            case 0: Toast.makeText(this, "case 0", Toast.LENGTH_SHORT).show(); break;
            case 1: Toast.makeText(this, "case 1", Toast.LENGTH_SHORT).show(); break;
            case 2: Toast.makeText(this, "case 2", Toast.LENGTH_SHORT).show(); break;
            case 3: Toast.makeText(this, "case 3", Toast.LENGTH_SHORT).show(); break;
            case 4: finish();
        }
    }
}
