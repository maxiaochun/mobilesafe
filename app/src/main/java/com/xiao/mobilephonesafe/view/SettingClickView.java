package com.xiao.mobilephonesafe.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiao.mobilephonesafe.R;

/**
 * Created by hasee on 2016/4/18.
 */
public class SettingClickView extends RelativeLayout {

    private CheckBox cb_box;
    private TextView tv_des;
    private TextView tv_title;


    public SettingClickView(Context context) {
        this(context, null);
    }

    public SettingClickView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SettingClickView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        xml的一个设置界面的条目转换成view,
// 将setting_item_view放入inflate，又添加到当前SettingItemView对应的view中
        View.inflate(context, R.layout.setting_click_view, this);
//        自定义组合控件中的标题描述
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_des = (TextView) findViewById(R.id.tv_des);

    }

    /**
     * @param title 设置标题内容
     */
    public void setTitle(String title){
        tv_title.setText(title);
    }
    /**
     * @param des 设置描述内容
     */
    public void setDes(String des){
        tv_des.setText(des);
    }
}

