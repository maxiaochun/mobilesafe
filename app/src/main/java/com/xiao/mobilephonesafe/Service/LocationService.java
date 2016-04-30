package com.xiao.mobilephonesafe.Service;

import android.app.Service;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.SmsManager;

/**
 * Created by hasee on 2016/4/26.
 */
public class LocationService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
//        获取手机的经纬度坐标
        //1.获取位置管理者对象
        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        //2.以最优的方式获取经纬度坐标
        Criteria criteria = new Criteria();
        criteria.setCostAllowed(true);
        criteria.setAccuracy(Criteria.ACCURACY_FINE);//指定经纬度精确度
        String bestProvider = lm.getBestProvider(criteria, true);
        //3.在一定时间将，移动一定距离后获取经纬度坐标

        MyLocationListener myLocationListener = new MyLocationListener();
        lm.requestLocationUpdates(bestProvider,0,0,myLocationListener);

    }
    class MyLocationListener implements LocationListener{

        @Override
        public void onLocationChanged(Location location) {
            //经度
            double longitude = location.getLongitude();
            //纬度
            double latitude = location.getLatitude();
            //4.发送短信
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage("15565351060",null,"longitude:"+longitude+",latitude:"+latitude,null,null);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
