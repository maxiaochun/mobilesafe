package com.xiao.mobilephonesafe.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by hasee on 2016/4/19.
 */
public class SpUtil {

    private static SharedPreferences sp;

    //    写入boolean类型的标示至sp中
    /**ctx:上下文环境
    * key:存储节点名称
    * value:存储节点的值
    * */

    public static void putBoolean(Context ctx, String key, boolean value) {
//        存储节点文件名称，读写方式
        if (sp == null) {
            sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        sp.edit().putBoolean(key, value).apply();
    }

    //   读取boolean类型的标示至sp中
    /**ctx:上下文环境
    * key:存储节点名称
    * defvalue:没有此节点默认值
    * */
    public static boolean getBoolean(Context ctx, String key, boolean defValue) {
//        存储节点文件名称，读写方式
        if (sp == null) {
            sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
//        返回默认值或此节点读取到的结果
        return sp.getBoolean(key, defValue);
    }

    //   写入string类型的标示至sp中
    /**ctx:上下文环境
    * key:存储节点名称
    * defvalue:没有此节点默认值
    * */
    public static void putString(Context ctx, String key, String value) {
        if (sp == null) {
            sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        sp.edit().putString(key, value).commit();
    }

    //   读取string类型的标示至sp中
    /**ctx:上下文环境
    * key:存储节点名称
    * defvalue:没有此节点默认值
    * */
    public static String getString(Context ctx, String key, String defValue) {
//        存储节点文件名称，读写方式
        if (sp == null) {
            sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
//        返回默认值或此节点读取到的结果
        return sp.getString(key, defValue);
    }

    /**
     * 从sp中移除指定节点
     *
     * @param ctx 上下文环境
     * @param key 需要移除节点的名称
     */
    public static void remove(Context ctx, String key) {
        if (sp == null) {
            sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        sp.edit().remove(key).commit();
    }

    public static int getInt(Context ctx, String key, int defValue) {
        //        存储节点文件名称，读写方式
        if (sp == null) {
            sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
//        返回默认值或此节点读取到的结果
        return sp.getInt(key, defValue);
    }

    public static void putInt(Context ctx, String key, int value) {
        if (sp == null) {
            sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        sp.edit().putInt(key, value).commit();
    }

}

