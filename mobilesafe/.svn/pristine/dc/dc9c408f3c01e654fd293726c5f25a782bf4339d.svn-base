package com.xiao.mobilesafe;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class SplashActivity extends Activity {


    private TextView tv_version_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//去除当前activity的titlebar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
//初始化UI
        initUI();
//      初始化数据
        initData();
    }

    //获取数据的方法
    private void initData() {
        tv_version_name.setText(getVersionName());
    }

    private void getVersionName() {
//        包管理对象
        PackageManager pm = getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
            return packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    //        获取版本名称
    private void initUI() {
        tv_version_name = (TextView) findViewById(R.id.tv_version_name);
    }

}


