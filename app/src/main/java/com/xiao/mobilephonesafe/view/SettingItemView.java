package com.xiao.mobilephonesafe.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiao.mobilephonesafe.R;

/**
 * Created by hasee on 2016/4/18.
 */
public class SettingItemView extends RelativeLayout {

    private CheckBox cb_box;
    private TextView tv_des;
    private static final String tag = "SettingItemView";
   private static final String NAMESPACE = "http://schemas.android.com/apk/res/com.xiao.mobilephonesafe";
    private String mDestitle;
    private String mDesoff;
    private String mDeson;

    public SettingItemView(Context context) {
        this(context, null);
    }

    public SettingItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SettingItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        xml的一个设置界面的条目转换成view,
// 将setting_item_view放入inflate，又添加到当前SettingItemView对应的view中
        View.inflate(context, R.layout.setting_item_view, this);
//        自定义组合控件中的标题描述
        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        tv_des = (TextView) findViewById(R.id.tv_des);
        cb_box = (CheckBox) findViewById(R.id.cb_box);
//        获取自定义以及原生属性的操作，从attrs对象中获取
        initAttrs(attrs);

        tv_title.setText(mDestitle);

    }
/*
* 返回属性集合中自定义属性属性值
* attrs 构造方法中维护好的属性集合
* */
    private void initAttrs(AttributeSet attrs) {
/*//     获取属性总个数
        Log.e(tag,"attrs.getAttributeCount:"+attrs.getAttributeCount());
//        获取属性名称及属性值
        for (int i = 0;i<attrs.getAttributeCount();i++){
            Log.e(tag,"attrs.getAttributeName:"+attrs.getAttributeName(i));
            Log.e(tag,"attrs.getAttributeValue:"+attrs.getAttributeValue(i));
            Log.e(tag,"===================");*/


//    通过名空间和属性名称获取属性值
        mDestitle = attrs.getAttributeValue(NAMESPACE,"destitle");
        mDesoff = attrs.getAttributeValue(NAMESPACE,"desoff");
        mDeson = attrs.getAttributeValue(NAMESPACE,"deson");
       /* Log.e(tag,mDestitle);
        Log.e(tag,mDesoff);
        Log.e(tag,mDeson);*/

    }
    /*
    * 判断是否开启的方法，返回值为true表示开启，false表示关闭
    * */
    public boolean isCheck() {
//        由checkbox的选中结果，决定是否开启
        return cb_box.isChecked();
    }

    /*ischeck作为是否开启的变量，在点击过程中传递
    * 点击过程中内容切换，当前条目在选择过程中，cb_box选中状态也在跟随ischeck变化
    * */
    public void setCheck(boolean isCheck) {
        cb_box.setChecked(isCheck);
        if (isCheck) {
//                开启
            tv_des.setText(mDeson);
        } else {
//                关闭
            tv_des.setText(mDesoff);
        }
    }
}



