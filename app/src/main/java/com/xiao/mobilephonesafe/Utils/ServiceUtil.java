package com.xiao.mobilephonesafe.Utils;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * Created by hasee on 2016/4/27.
 */

/**
 * servicename 判断是否正在运行的服务
 * return true 运行 false 没有运行
 */
public class ServiceUtil {
    public static boolean isRunning(Context ctx,String serviceName){
        //1.获取管理者对象去获取当前手机正在运行的所以服务
        ActivityManager mAM = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        //2.获取正在运行的服务集合
        List<ActivityManager.RunningServiceInfo> runningServices = mAM.getRunningServices(1000);
        //3.遍历获取的所有服务集合，拿到每一个服务的类名，和传递进来的类名比较
        for (ActivityManager.RunningServiceInfo runningServiceInfo:runningServices){
            //4.获取每一个运行服务的名称，比对
            if (serviceName.equals(runningServiceInfo.service.getClassName())){
                return true;
            }

        }
        return false;
    }
}
