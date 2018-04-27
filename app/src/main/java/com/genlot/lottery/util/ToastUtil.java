package com.genlot.lottery.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Date 2018/4/4
 * Author littlemeng
 * Email yikaishao@163.com
 * Describe Toast提示工具类
 */

public class ToastUtil {
    private static Toast toast;

    public static void showShort(Context ctx, String msg) {
        show(ctx, msg, true);
    }

    public static void showLong(Context ctx, String msg) {
        show(ctx, msg, false);
    }

    private static void show(Context ctx, String msg, boolean isShort) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }

        if (toast == null) {
            toast = Toast.makeText(ctx, msg, isShort ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
        } else {
            toast.setText(msg);
            toast.setDuration(isShort ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
        }
        toast.show();
    }
}
