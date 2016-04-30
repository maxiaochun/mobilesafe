package com.xiao.mobilephonesafe.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xiao.mobilephonesafe.R;

/**
 * Created by hasee on 2016/4/26.
 */
public class AToolActivity extends Activity {


    private TextView tv_query_phone_address;

    @Override
    public void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atool);
        //电话归属地查询方法
        initPhoneAddress();
    }

    private void initPhoneAddress() {
        tv_query_phone_address = (TextView) findViewById(R.id.tv_query_phone_address);
        tv_query_phone_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),QueryAddressActivity.class);
                startActivity(intent);
            }
        });
    }
}
