package com.xiao.mobilephonesafe.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by hasee on 2016/4/17.
 */
public class FocusTextView extends TextView {
    //通过java代码创建控件
    public FocusTextView(Context context) {
        super(context);
    }

    //由系统调用
    public FocusTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //由系统调用
    public FocusTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
//    重写获取焦点的方法


    @Override
    public boolean isFocused() {
        return true;
    }
}
