package com.xiao.mobilephonesafe.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.telephony.SmsMessage;
import android.util.Log;

import com.xiao.mobilephonesafe.R;
import com.xiao.mobilephonesafe.Service.LocationService;
import com.xiao.mobilephonesafe.Utils.ConstantValue;
import com.xiao.mobilephonesafe.Utils.SpUtil;

/**
 * Created by hasee on 2016/4/26.
 */
public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//        1.判断是否开启了防盗保护
        boolean open_security = SpUtil.getBoolean(context, ConstantValue.OPEN_SECURITY, false);
        if (open_security) {
            // 2.获取短信内容
            Object[] objects = (Object[]) intent.getExtras().get("pdus");
            Log.e("test","获取短信内容");
//        3.循环遍历短信内容
            for (Object object : objects) {
//        4.获取短信对象
                SmsMessage sms = SmsMessage.createFromPdu((byte[]) object);
//        5.获取短信对象的基本信息
                String originatingAddress = sms.getOriginatingAddress();
                String messageBody = sms.getMessageBody();
                Log.e("test","2");
//        6.判断是否包含播放音乐的关键字
                if (messageBody.contains("#*alarm*#")) {
                    Log.e("test","3");
//        7.播放音乐
                    MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.ylzs);
                    mediaPlayer.setLooping(true);
                    mediaPlayer.start();
                    Log.e("text","播放音乐啦");
                }
                if (messageBody.contains("#*Location*#")){
                    //8.开启获取位置服务
                    Intent intent1 = new Intent(context, LocationService.class);
                    context.startService(intent1);
                }

            }

        }

    }
}
