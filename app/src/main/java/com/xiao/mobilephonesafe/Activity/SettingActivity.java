package com.xiao.mobilephonesafe.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.util.Log;
import android.view.View;

import com.xiao.mobilephonesafe.R;
import com.xiao.mobilephonesafe.Service.AddressService;
import com.xiao.mobilephonesafe.Utils.ConstantValue;
import com.xiao.mobilephonesafe.Utils.ServiceUtil;
import com.xiao.mobilephonesafe.Utils.SpUtil;
import com.xiao.mobilephonesafe.view.SettingClickView;
import com.xiao.mobilephonesafe.view.SettingItemView;

/**
 * Created by hasee on 2016/4/18.
 */
public class SettingActivity extends Activity {

    private SettingClickView scv_toast_style;
    private String[] mToastStyleDes;
    private int mToast_style;
    private SettingClickView scv_location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setting);
        Log.e("test", "设置中心");
        initUpdate();
        initAddress();
        initToastStyle();
        initLocation();
    }

    /**
     * 双击居中view所在平面位置的处理方法
     */
    private void initLocation() {
        scv_location = (SettingClickView) findViewById(R.id.scv_location);
        scv_location.setTitle("归属地提示框位置");
        scv_location.setDes("设置归属地提示框位置");
        scv_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ToastLocationActivity.class));
            }
        });
    }

    private void initToastStyle() {
        scv_toast_style = (SettingClickView) findViewById(R.id.scv_toast_style);
        scv_toast_style.setTitle("设置归属地显示风格");
        //1.创建描述文字所在的string类型数组
        mToastStyleDes = new String[]{"透明", "橙色", "蓝色", "灰色", "绿色"};
        //2.获取吐司显示样式的索引值，用于获取描述文字
        mToast_style = SpUtil.getInt(this, ConstantValue.TOAST_STYLE, 0);
        //3.通过索引，获取字符串数组对应的文字，显示给控件
        scv_toast_style.setDes(mToastStyleDes[mToast_style]);
        //4.监听点击事件，弹出对话框
        scv_toast_style.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //5.显示吐司样式的对话框
                showToastStyleDialog();
            }
        });

    }

    /**
     * 创建选中显示样式的对话框
     */
    private void showToastStyleDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("请选择归属地样式");
       /**
       * 选择单个条目的点击事件(
        * 1.String类型数组，
        * 2.弹出对话框时点中条目的索引
        * 3.点击某一条目触发的点击事件（1.记录选中的索引值2.关闭对话框3.显示选中色值文字）
       * */
        builder.setSingleChoiceItems(mToastStyleDes, mToast_style, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //1.记录选中的索引值2.关闭对话框3.显示选中色值文字
                SpUtil.putInt(getApplicationContext(),ConstantValue.TOAST_STYLE,which);
                dialog.dismiss();
                scv_toast_style.setDes(mToastStyleDes[which]);
            }
        });
        //消极按钮
        builder.setNegativeButton("取消",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    /**
     * 是否显示电话号码归属地的方法
     */
    private void initAddress() {
        final SettingItemView siv_address = (SettingItemView) findViewById(R.id.siv_address);
       //获取已有状态，回显
        boolean isRunning = ServiceUtil.isRunning(this, "com.xiao.mobilephonesafe.service.AddressService");
        siv_address.setCheck(isRunning);
//         点击过程中切换状态
        siv_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean ischeck = siv_address.isCheck();
                siv_address.setCheck(!ischeck);
                if (!ischeck){
                    startService(new Intent(getApplicationContext(),AddressService.class));
                }else{
                    stopService(new Intent(getApplicationContext(),AddressService.class));
                }

            }
        });
    }

    /*
    * 版本更新开关
    * */
    private void initUpdate() {
        final SettingItemView siv_update = (SettingItemView) findViewById(R.id.siv_update);
//        获取已有的开关状态，用作显示
        boolean open_update = SpUtil.getBoolean(this, ConstantValue.OPEN_UPDATE, false);
//        是否选中，根据上次存储的结果做决定
        siv_update.setCheck(open_update);

        siv_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                如果之前是选中的，点击过后变成未选中，反之亦然
//                获取之前的选中状态
                boolean isCheck = siv_update.isCheck();
//                将原有状态取反，等同上述的两步操作
                siv_update.setCheck(!isCheck);
//                将取反后的状态存储到相应sp中
                SpUtil.putBoolean(getApplicationContext(),ConstantValue.OPEN_UPDATE,!isCheck);


            }
        });
    }
}
