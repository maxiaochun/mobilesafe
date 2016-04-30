package com.xiao.mobilephonesafe.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xiao.mobilephonesafe.R;
import com.xiao.mobilephonesafe.engine.AddressDao;

/**
 * Created by hasee on 2016/4/26.
 */
public class QueryAddressActivity extends Activity{

    private EditText et_query_phone;
    private Button bt_query;
    private TextView tv_query_result;
    private String mAddress;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //4.控件使用查询结果
            tv_query_result.setText(mAddress);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_phone_address);
        initUI();
    }

    private void initUI() {
        et_query_phone = (EditText) findViewById(R.id.et_query_phone);
        bt_query = (Button) findViewById(R.id.bt_query);
        tv_query_result = (TextView) findViewById(R.id.tv_query_result);
        //1.点击查询
        bt_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = et_query_phone.getText().toString();
                if (!TextUtils.isEmpty(phone)) {
                    //2.开启子线程
                    query(phone);
                }else {
                    //抖动
                    Animation shake = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake);
                    et_query_phone.startAnimation(shake);
                    //手机震动效果
                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    //震动毫秒值
                    vibrator.vibrate(2000);
                    //规律震动
                    vibrator.vibrate(new long[]{1000,2000,1000,2000},-1);
                }
            }
        });
        et_query_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String phone = et_query_phone.getText().toString();
                query(phone);

            }
        });
    }



    /**
     * 耗时操作
     * 获取电话号码归属地
     * @param phone 查询电话号码
     */
    protected void query(final String phone) {
        new Thread(){
            public void run() {
                mAddress = AddressDao.getAddress(phone);
                //3,消息机制,告知主线程查询结束,可以去使用查询结果
                mHandler.sendEmptyMessage(0);
            };
        }.start();
    }
}
