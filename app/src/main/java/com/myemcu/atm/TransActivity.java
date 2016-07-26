package com.myemcu.atm;

import android.os.TransactionTooLargeException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;                // 呼叫
import okhttp3.Callback;            // 回调
import okhttp3.OkHttpClient;        // 客户端
import okhttp3.Request;             // 请求
import okhttp3.Response;            // 回应

public class TransActivity extends AppCompatActivity {

    OkHttpClient client = new OkHttpClient(); // 加入OkHttpClient属性(该属性提供连线能力)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans);

        //-连线程序---------------------------------------------------------------------
        // 当前状态：未连线
        Request request = new Request.Builder() // 设置连线信息
                                     .url("http://atm201605.appspot.com/h") // 输入URL
                                     .build();  // 产生http请求(request)
        // 当前状态：未连线
        Call call = client.newCall(request);    // 建立呼叫对象

        // 当前状态：建立连接
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 告诉用户连线失败
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // 连接成功的回应处理
                String json = response.body().string(); // 取得服务器端的回应字符串
                Log.d("OKHTTP",json);                   // LogCat跟踪
                //parseJSON(json);                      // JSON解析(Android自带库)
                //parseGson(json);                        // JSON解析(第三方库GoogleGson)
                parseJackson(json);                   // JSON解析
            }
        });
    }

    private void parseJSON(String json) {                   // Android自带JSON解析

        ArrayList<Transcation> trans = new ArrayList<>();   // 准备一个集合

        try {
                JSONArray array = new JSONArray(json);      // 产生json数组

                for (int i=0; i<array.length(); i++) {      // 取出数组所有

                    JSONObject obj = array.getJSONObject(i);// 取得索引值的json对象

                    // 取得对应索引值的每个属性
                    String account = obj.getString("account");
                    String date = obj.getString("date");
                    int amount = obj.getInt("amount");
                    int type = obj.getInt("type");

                    Log.d("JSON",account+"/"+date+"/"+amount+"/"+type);

                    //Transcation t = new Transcation(account,date,amount,type); // 产生一笔记录
                    //trans.add(t);   // 加入到集合
                }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void parseGson(String json) {                   // GoogleGson解析(第三方库)
        Gson gson = new Gson(); // 产生Gson对象
        ArrayList<Transcation> list = gson.fromJson(json, new  TypeToken<ArrayList<Transcation>>(){}.getType());
        Log.d("JSON","GSON解析总数："+list.size());

        //Log.d("JSON",list.get(0).getAccount()+"/"+list.get(0).getDate()+"/"+list.get(0).getAmount()+"/"+list.get(0).getType());

        for (int i=0; i<list.size();i++) {
            Log.d("JSON",list.get(i).getAccount()+"/"+list.get(i).getDate()+"/"+list.get(i).getAmount()+"/"+list.get(i).getType());
        }
    }

    private void parseJackson(String json) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
             final ArrayList<Transcation> list = objectMapper.readValue(json, new TypeReference<List<Transcation>>(){});

             Log.d("JSON","JackSon方式解析总数："+list.size());

             //Log.d("JSON",list.get(0).getAccount()+"/"+list.get(0).getDate()+"/"+list.get(0).getAmount()+"/"+list.get(0).getType());

             for (int i=0; i<list.size();i++) {
                 Log.d("JSON",list.get(i).getAccount()+"/"+list.get(i).getDate()+"/"+list.get(i).getAmount()+"/"+list.get(i).getType());
             }

             runOnUiThread(new Runnable() {
                 @Override
                 public void run() {
                     setupRecyclerView(list);
                 }
             });
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 设计RecyclerView方法
    private void setupRecyclerView(List<Transcation> list) {

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);

        TransactionAdapter adapter = new TransactionAdapter(list);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
