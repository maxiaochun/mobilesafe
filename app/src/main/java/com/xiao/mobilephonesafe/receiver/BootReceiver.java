package com.xiao.mobilephonesafe.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.xiao.mobilephonesafe.Utils.ConstantValue;
import com.xiao.mobilephonesafe.Utils.SpUtil;

/**
 * Created by hasee on 2016/4/25.
 */
public class BootReceiver extends BroadcastReceiver {
    private static final String tag =null;

    @Override
    public void onReceive(Context context, Intent intent) {
//        一旦监听到开机广播，就需要去发送短信给指定号码
        Log.e(tag,"开关机广播~~~~");
//   1.获取本地存储的sim卡序列号
        String spSimSerialNumber = SpUtil.getString(context, ConstantValue.SIM_NUMBER, "");
//        2.获取当前插入手机的sim卡序列号
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String simSerialNumber = tm.getSimSerialNumber()+"456";
//        3.两个sim卡序列号对比
        if (!spSimSerialNumber.equals(simSerialNumber)){
//        4.如果序列号不一致，就给指定联系人发送短信（发送短信权限）
            SmsManager sm = SmsManager.getDefault();
            String phone = SpUtil.getString(context, ConstantValue.CONTACT_PHONE, "");
            sm.sendTextMessage(phone,null,"sim change!",null,null);

        }

    }
}
