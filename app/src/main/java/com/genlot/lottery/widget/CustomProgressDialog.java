package com.genlot.lottery.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

import com.genlot.lottery.R;

/**
 * Date 2018/3/19
 * Author littlemeng
 * Email yikaishao@163.com
 * Describe 进度条
 */

public class CustomProgressDialog extends Dialog {
    private static final String TAG = "CustomProgressDialog";

    public CustomProgressDialog(Context context, String strMessage) {
        this(context, R.style.CustomProgressDialog, strMessage);
    }

    private CustomProgressDialog(Context context, int theme, String strMessage) {
        super(context, theme);
        this.setContentView(R.layout.dialog_progress_loading);
        this.getWindow().getAttributes().gravity = Gravity.CENTER;
        TextView tvMsg = (TextView) this.findViewById(R.id.progress_text);
        if (tvMsg != null) {
            tvMsg.setText(strMessage);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
    }

    public void setMessage(String msg) {
        TextView tvMsg = (TextView) this.findViewById(R.id.progress_text);
        if (tvMsg != null) {
            tvMsg.setText(msg);
        }
    }
}
