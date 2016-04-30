package com.xiao.mobilephonesafe.Utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by hasee on 2016/4/20.
 */

public class ToastUtil {

    public static void show(Context ctx, String str) {
        Toast.makeText(ctx, str, Toast.LENGTH_SHORT).show();

    }
}
