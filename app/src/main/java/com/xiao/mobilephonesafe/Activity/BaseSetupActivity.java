package com.xiao.mobilephonesafe.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by hasee on 2016/4/25.
 */
public abstract class BaseSetupActivity extends Activity {
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//2,创建手势管理的对象,用作管理在onTouchEvent(event)传递过来的手势动作
        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//            监听手势的移动
                if (e1.getX() - e2.getX() > 0) {
//                    由右向左，移动到下一页。调用子类下一页的方法
//                   在第一个界面上的时候，跳转到第二个，以此类推
                    showNextPage();
                }
                if (e1.getX() - e2.getX() < 0) {
//                    由左向右，移动到下一页,调用子类的上一页的方法
//                    在第二个界面的时候跳转到第一个，以此类推
                    showPrePage();
                }

                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });
    }
    //   1. 监听屏幕上响应的时间类型（按下，移动，抬起1次）
    @Override
    public boolean onTouchEvent(MotionEvent event) {
//     3.通过手势处理类，接收多种类型的事件，用作处理
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    //下一页的抽象方法，由子类决定跳转到哪个界面
    protected abstract void showNextPage();

    //上一页的抽象方法，由子类决定跳转到哪个界面
    protected abstract void showPrePage();

    //点击下一页按钮的时候，根据子类的shownextpage方法做相应跳转
    public void nextPage(View view) {
        showNextPage();
    }

    //点击上一页按钮的时候，根据子类的showprepage方法做相应跳转
    public void prePage(View view) {
        showPrePage();
    }
}