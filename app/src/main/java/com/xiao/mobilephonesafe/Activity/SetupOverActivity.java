package com.xiao.mobilephonesafe.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiao.mobilephonesafe.R;
import com.xiao.mobilephonesafe.Utils.ConstantValue;
import com.xiao.mobilephonesafe.Utils.SpUtil;

/**
 * Created by hasee on 2016/4/21.
 */
public class SetupOverActivity extends Activity {

    private TextView     tv_phone;

    @Override
    public void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);

        boolean setup_over = SpUtil.getBoolean(this, ConstantValue.SETUP_OVER, false);
        if (setup_over){
//        密码输入成功，且四个导航界面设置完成，则停留在设置完成功能列表界面
            setContentView(R.layout.activity_setup_over);
            initUI();
        }else{
//        密码输入成功，但四个导航界面没有设置完成，跳转到导航界面第一个
            Intent intent = new Intent(this, Setup1Activity.class);
            startActivity(intent);
//            开启新界面之后，关闭功能文件
            finish();
        }
    }

    private void initUI() {
//        从sp中获取存储的联系人号码，将其显示在控件上
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        String phone = SpUtil.getString(this, ConstantValue.CONTACT_PHONE, "");
        tv_phone.setText(phone);
//        让textview具备可点击的操作，设置一个点击事件
        TextView tv_reset_setup = (TextView) findViewById(R.id.tv_reset_setup);
        tv_reset_setup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Setup1Activity.class);
                startActivity(intent);
//            开启新界面之后，关闭功能文件
                finish();

            }
        });

    }
}
