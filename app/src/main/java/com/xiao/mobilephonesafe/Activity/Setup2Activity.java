package com.xiao.mobilephonesafe.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;

import com.xiao.mobilephonesafe.R;
import com.xiao.mobilephonesafe.Utils.ConstantValue;
import com.xiao.mobilephonesafe.Utils.SpUtil;
import com.xiao.mobilephonesafe.Utils.ToastUtil;
import com.xiao.mobilephonesafe.view.SettingItemView;

/**
 * Created by hasee on 2016/4/21.
 */
public class Setup2Activity extends BaseSetupActivity {

    private SettingItemView siv_sim_bound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup2);
        initUI();
    }

    private void initUI() {
        siv_sim_bound = (SettingItemView) findViewById(R.id.siv_sim_bound);
//       1. 回显
        final String sim_number = SpUtil.getString(getApplicationContext(), ConstantValue.SIM_NUMBER, "");
//       2.判断是否sim序列号是否为空
        if (TextUtils.isEmpty(sim_number)) {
            siv_sim_bound.setCheck(false);
        } else {
            siv_sim_bound.setCheck(true);
        }

        siv_sim_bound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//          3.获取原有状态
                boolean isCheck = siv_sim_bound.isCheck();
//          4.将原有状态取反
//                5.状态设置给当前条目
                siv_sim_bound.setCheck(!isCheck);
//                6.存储序列卡号
//                勾选时
                if (!isCheck) {
//                6.1获取sim卡序列号telephoneManager
                    TelephonyManager manager = (TelephonyManager)
                            getSystemService(Context.TELEPHONY_SERVICE);
//                6.2获取sim卡的序列卡号
                    String simSerialNumber = manager.getSimSerialNumber();
//                6.3存储
                    SpUtil.putString(getApplicationContext(), ConstantValue.SIM_NUMBER, simSerialNumber);
                } else {
//                7.将存储序列卡号的节点从sp中删除掉
                    SpUtil.remove(getApplicationContext(), ConstantValue.SIM_NUMBER);
                }
            }
        });
    }

    @Override
    protected void showNextPage() {
//        检查是否有sim卡序列号
        String simSerialNumber = SpUtil.getString(this, ConstantValue.SIM_NUMBER, "");
        if (!TextUtils.isEmpty(simSerialNumber)) {
            Intent intent = new Intent(getApplicationContext(), Setup3Activity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.next_in_anim, R.anim.next_out_anim);
        } else {
            ToastUtil.show(this, "请绑定sim卡");
        }
    }

    @Override
    protected void showPrePage() {
        Intent intent = new Intent(getApplicationContext(), Setup1Activity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.pre_in_anim, R.anim.pre_out_anim);
    }

}
