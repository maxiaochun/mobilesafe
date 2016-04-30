package com.xiao.mobilephonesafe.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.xiao.mobilephonesafe.R;
import com.xiao.mobilephonesafe.Utils.ConstantValue;
import com.xiao.mobilephonesafe.Utils.SpUtil;
import com.xiao.mobilephonesafe.Utils.ToastUtil;

/**
 * Created by hasee on 2016/4/21.
 */
public class Setup4Activity extends BaseSetupActivity {

    private CheckBox cb_box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup4);
        initUI();
    }

    private void initUI() {
        cb_box = (CheckBox) findViewById(R.id.cb_box);
//        1.是否选中状态的回显
        boolean open_security = SpUtil.getBoolean(getApplicationContext(), ConstantValue.OPEN_SECURITY, false);
//        2.根据状态，修改checkbox后面的文字显示
        cb_box.setChecked(open_security);
        if (open_security){
            cb_box.setText("安全设置已开启");
        }else{
            cb_box.setText("安全设置已关闭");
        }
//        3.点击过程中进行checkbox状态的切换
       cb_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
 //        4.切换后状态进行存储
            SpUtil.putBoolean(getApplicationContext(),ConstantValue.OPEN_SECURITY,cb_box.isChecked());
//               5.根据开启关闭状态，存储点击后的状态
               if (isChecked){
                   cb_box.setText("安全设置已开启");
               }else{
                   cb_box.setText("安全设置已关闭");
               }
           }
       });
    }
    @Override
    protected void showNextPage() {
        boolean open_security = SpUtil.getBoolean(getApplicationContext(), ConstantValue.OPEN_SECURITY, false);
        if (open_security){
            Intent intent = new Intent(getApplicationContext(), SetupOverActivity.class);
            startActivity(intent);
            finish();
            SpUtil.putBoolean(this, ConstantValue.SETUP_OVER, true);
            overridePendingTransition(R.anim.next_in_anim,R.anim.next_out_anim);
        }else {
            ToastUtil.show(getApplicationContext(),"请开启防盗保护");
        }
    }

    @Override
    protected void showPrePage() {
        SpUtil.putBoolean(getApplicationContext(),ConstantValue.OPEN_SECURITY,cb_box.isChecked());
        Intent intent = new Intent(getApplicationContext(), Setup3Activity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.pre_in_anim,R.anim.pre_out_anim);
    }

}