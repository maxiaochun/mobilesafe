package com.xiao.mobilephonesafe.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xiao.mobilephonesafe.R;
import com.xiao.mobilephonesafe.Utils.ConstantValue;
import com.xiao.mobilephonesafe.Utils.Md5Util;
import com.xiao.mobilephonesafe.Utils.SpUtil;
import com.xiao.mobilephonesafe.Utils.ToastUtil;

/**
 * Created by hasee on 2016/4/17.
 */
public class HomeActivity extends Activity {

    private GridView gv_home;
    private String[] mTitleStrs;
    private int[] mMipmapIDs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        初始化UI
        initUI();
//        初始化数据
        initDate();
    }

    private void initDate() {
//   准备数据（文字，图片）
        mTitleStrs = new String[]{
                "手机防盗", "通信卫士", "软件管理", "进程管理", "流量统计", "手机杀毒", "缓存清理", "高级工具", "设置中心"
        };
        mMipmapIDs = new int[]{
                R.mipmap.home_safe, R.mipmap.home_callmsgsafe, R.mipmap.home_apps,
                R.mipmap.home_taskmanager, R.mipmap.home_netmanager, R.mipmap.home_trojan,
                R.mipmap.home_sysoptimize, R.mipmap.home_tools, R.mipmap.home_settings
        };
//        九宫格控件设置数据适配器
        gv_home.setAdapter(new MyAdapter());
//        注册九宫格单个条目点击事件
        gv_home.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        showDialog();
                        break;
                    case 7:
                        Intent intent1 = new Intent(getApplicationContext(), AToolActivity.class);
                        startActivity(intent1);
                        break;
                    case 8:
                        Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                        startActivity(intent);
                        break;

                }
            }
        });

    }

    protected void showDialog() {
//        判断本地是否有存储密码
        String psd = SpUtil.getString(this, ConstantValue.MOBILE_SAFE_PSD, "");
        if (TextUtils.isEmpty(psd)) {
//        1.初始设置密码对话框
            showSetPsdDialog();
        } else {
//        2.确认密码对话框
            showConfirmPsdDialog();
        }
    }

    /**
    * 设置密码对话框
    * */
    private void showSetPsdDialog() {
//        使用自定义对话框，将自己编写的xml转换成view
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        final View view = View.inflate(this, R.layout.dialog_set_psd, null);
//        让对话框显示一个自己定义的对话框效果
        dialog.setView(view);
        dialog.show();
        Button bt_submit = (Button) view.findViewById(R.id.bt_submit);
        Button bt_cancel = (Button) view.findViewById(R.id.bt_cancel);

//        点击确认
        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et_set_psd = (EditText) view.findViewById(R.id.et_set_psd);
                EditText et_confirm_psd = (EditText) view.findViewById(R.id.et_confirm_psd);
//
                String psd = et_set_psd.getText().toString();
                String confirmPsd = et_confirm_psd.getText().toString();

                if (!TextUtils.isEmpty(psd) && !TextUtils.isEmpty(confirmPsd)) {
                    if (psd.equals(confirmPsd)) {
                        // 进入手机防盗模块
                        Intent intent = new Intent(getApplicationContext(), SetupOverActivity.class);
                        startActivity(intent);
//                        进入新页面后隐藏对话框
                        dialog.dismiss();
//                        保存密码
                        SpUtil.putString(getApplicationContext(),ConstantValue.MOBILE_SAFE_PSD, Md5Util.encoder(confirmPsd));
                    } else {
                        ToastUtil.show(getApplicationContext(), "两次输入的密码不一致");
                    }
                } else {
//                    提示用户名密码输入有为空的情况
                    ToastUtil.show(getApplicationContext(),"请输入密码");
                }
            }
        });
//        点击取消
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    /*
    * 确认密码对话框
    * */
    private void showConfirmPsdDialog() {
//        使用自定义对话框，将自己编写的xml转换成view
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        final View view = View.inflate(this, R.layout.dialog_confirm_psd, null);
//        让对话框显示一个自己定义的对话框效果
        dialog.setView(view);
        dialog.show();
        Button bt_submit = (Button) view.findViewById(R.id.bt_submit);
        Button bt_cancel = (Button) view.findViewById(R.id.bt_cancel);
        //        点击确认
        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              EditText et_confirm_psd = (EditText) view.findViewById(R.id.et_confirm_psd);
                String confirmPsd = et_confirm_psd.getText().toString();
                String psd = SpUtil.getString(getApplicationContext(), ConstantValue.MOBILE_SAFE_PSD, "");

                if (!TextUtils.isEmpty(confirmPsd)) {
                    if (psd.equals((Md5Util.encoder(confirmPsd)))) {
                        // 进入手机防盗模块
                        Intent intent = new Intent(getApplicationContext(), SetupOverActivity.class);
                        startActivity(intent);
//                        进入新页面后隐藏对话框
                        dialog.dismiss();
                    } else {
                        ToastUtil.show(getApplicationContext(), "密码输入错误");
                    }
                } else {
                    ToastUtil.show(getApplicationContext(),"请输入密码");
                }
            }
        });
//        点击取消
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }


    private void initUI() {
        gv_home = (GridView) findViewById(R.id.gv_home);
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
//            条目总数
            return mTitleStrs.length;
        }

        @Override
        public Object getItem(int position) {
            return mTitleStrs[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(getApplicationContext(), R.layout.gridview, null);
            TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
            ImageView iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
            tv_title.setText(mTitleStrs[position]);
            iv_icon.setBackgroundResource(mMipmapIDs[position]);

            return view;
        }
    }
}
