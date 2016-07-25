package com.myemcu.atm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

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
                parseJSON(json);                        // JSON解析
            }
        });
    }

    private void parseJSON(String json) {
    }
}
