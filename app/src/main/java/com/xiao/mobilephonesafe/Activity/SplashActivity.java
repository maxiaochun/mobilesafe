package com.xiao.mobilephonesafe.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiao.mobilephonesafe.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class SplashActivity extends Activity {
    private TextView tv_version_name;
    private String versionname;
    private int mLocalversionCode;
    private Button bt;
    private RelativeLayout rl_root;


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
//        初始化动画
        initAnimation();
        //初始化数据库
        initDB();
//        设置点击事件
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initDB() {
        //1.归属地数据库拷贝过程
        initAddressDB("address.db");
    }

    /**
     * 拷贝数据库到files文件夹下
     * @param dbName 数据库名称
     */
    private void initAddressDB(String dbName) {
        //1.在file文件夹下创建同名dbmame数据库文件过程
        File files = getFilesDir();
        File file = new File(files, dbName);
        if (file.exists()) {
            return;
        }
        InputStream stream = null;
        FileOutputStream fos = null;
            //2.输入流读取第三方资产目录下的文件
            try {
                stream = getAssets().open(dbName);
                //3.将读取到的内容写入到指定文件夹的文件中
                fos = new FileOutputStream(file);
                //4.每次读取内容大小
                byte[] bs = new byte[1024];
                int temp = -1;
                while ((temp = stream.read(bs))!= -1){
                    fos.write(bs,0,temp);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if (stream!=null&& fos!=null){
                    try {
                        stream.close();
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

        Log.e("data","ok");

    }

    //设置淡入动画效果
    private void initAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
        alphaAnimation.setDuration(3000);
        rl_root.startAnimation(alphaAnimation);
    }


    //获取数据的方法
    private void initData() {
//     1.  同步 版本名称
        getVersionName();
        tv_version_name.setText("版本名称："+versionname);
//      检测版本号（本地版本号和服务器版本号对比）是否有更新，如果有，提示用户下载新版本
//       获取本地版本号
       getVersionCode();
//        获取服务器版本号

    }

    private void getVersionCode() {
        PackageManager pm = getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
            mLocalversionCode = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void getVersionName() {
//        包管理对象
        PackageManager pm = getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
            versionname = packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //        获取版本名称
    private void initUI() {
        tv_version_name = (TextView) findViewById(R.id.tv_version_name);
        bt = (Button) findViewById(R.id.bt);
        rl_root = (RelativeLayout) findViewById(R.id.rl_root);
    }

}

