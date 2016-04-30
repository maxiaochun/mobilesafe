package com.xiao.mobilephonesafe.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.xiao.mobilephonesafe.R;
import com.xiao.mobilephonesafe.Utils.ConstantValue;
import com.xiao.mobilephonesafe.Utils.SpUtil;

/**
 * Created by hasee on 2016/4/27.
 */
public class ToastLocationActivity extends Activity {

    private ImageView iv_drag;
    private Button bt_top;
    private Button bt_bottom;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toast_location);
        initUI();
    }

    private void initUI() {
        iv_drag = (ImageView) findViewById(R.id.iv_drag);
        bt_top = (Button) findViewById(R.id.bt_top);
        bt_bottom = (Button) findViewById(R.id.bt_bottom);
        //监听某控件的拖拽过程（按下1次，移动多次，抬起1次）
        iv_drag.setOnTouchListener(new View.OnTouchListener() {

            private int startY;
            private int startX;

            //对不同事件做不同的逻辑处理
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        startX = (int) event.getRawX();
                        startY = (int) event.getRawY();
                        Log.e("test","按下");
                        break;
                    case MotionEvent.ACTION_HOVER_MOVE:
                       int moveX = (int) event.getRawX();
                       int moveY = (int) event.getRawY();

                        int disX = moveX - startX;
                        int disY = moveY - startY;

                        //1.當前控件所在屏幕的角的位置
                        int left = iv_drag.getLeft()+disX;//左侧坐标
                        int top = iv_drag.getTop()+disY;//顶端坐标
                        int right = iv_drag.getRight()+disX;//右侧
                        int bottom = iv_drag.getBottom()+disY;//底部坐标
                        //2.告知移动的控件，按计算出来的坐标去做展示
                        iv_drag.layout(left,top,right,bottom);
                        //3.重置一次起始坐标
                        startX = (int)event.getRawX();
                        startY = (int)event.getRawY();
                        Log.e("test","移动");
                        break;
              case  MotionEvent.ACTION_UP:
                  //4.存储移动到的位置
                  SpUtil.putInt(getApplicationContext(), ConstantValue.LOCATION_X,iv_drag.getLeft());
                  SpUtil.putInt(getApplicationContext(), ConstantValue.LOCATION_Y,iv_drag.getTop());

                        break;
                }
                return true;
            }
        });

    }
}