package com.xiao.mobilephonesafe.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.xiao.mobilephonesafe.R;
import com.xiao.mobilephonesafe.Utils.ConstantValue;
import com.xiao.mobilephonesafe.Utils.SpUtil;
import com.xiao.mobilephonesafe.Utils.ToastUtil;

/**
 * Created by hasee on 2016/4/21.
 */
public class Setup3Activity extends BaseSetupActivity {

    private EditText et_phone_number;
    private Button bt_select_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup3);
        initUI();
    }

    private void initUI() {
//        显示电话号码的输入框
        et_phone_number = (EditText) findViewById(R.id.et_phone_number);
//        获取联系人电话号码并回显
        String phone = SpUtil.getString(getApplicationContext(), ConstantValue.CONTACT_PHONE, "");
        et_phone_number.setText(phone);
//        点击选择联系人的对话框
        bt_select_number = (Button) findViewById(R.id.bt_select_number);
        bt_select_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ContactListActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
//        1.返回到当前界面时接收结果的方法
            String phone = data.getStringExtra("phone");
//        2.将特殊字符过滤（中划线转换成空字符串）
            phone = phone.replace("-", "").replace(" ", "").trim();
            et_phone_number.setText(phone);
//         3.存储联系人至sp中
            SpUtil.putString(getApplicationContext(), ConstantValue.CONTACT_PHONE, phone);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void showNextPage() {
//        点击之后，获取输入框中的联系人
        String phone = et_phone_number.getText().toString();
        if (!TextUtils.isEmpty(phone)) {
            Intent intent = new Intent(getApplicationContext(), Setup4Activity.class);
            startActivity(intent);
            finish();
//          如果是输入的号码，则进行保存
            SpUtil.putString(getApplicationContext(), ConstantValue.CONTACT_PHONE, phone);
            overridePendingTransition(R.anim.next_in_anim, R.anim.next_out_anim);


        } else {
            ToastUtil.show(getApplicationContext(), "请输入电话号码");
        }
    }

    @Override
    protected void showPrePage() {
        Intent intent = new Intent(getApplicationContext(), Setup2Activity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.pre_in_anim, R.anim.pre_out_anim);
    }
}