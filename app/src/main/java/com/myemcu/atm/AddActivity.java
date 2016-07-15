package com.myemcu.atm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    private EditText edDate;
    private EditText edInfo;
    private EditText edAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);

        findViews();
    }

    private void findViews() {
        edDate   = (EditText) findViewById(R.id.ed_date);
        edInfo   = (EditText) findViewById(R.id.ed_info);
        edAmount = (EditText) findViewById(R.id.ed_amount);
    }

    //Button事件响应
    public void add(View v) {

    }
}
