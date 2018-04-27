package com.genlot.lottery.userinfo.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.genlot.lottery.R;
import com.genlot.lottery.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Date 2018/4/16
 * Author littlemeng
 * Email yikai.shao@genlot.com
 * Describe 忘记密码界面
 */

public class ForgetPasswordActivity extends BaseActivity {
    @BindView(R.id.toolbar)                 //标题栏
    Toolbar mToolbar;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_forget_password;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

    }

    @Override
    public void initToolBar() {
        setToolBarDefaultConfig(mToolbar, "找回密码", true);
    }

    @OnClick({R.id.btn_confirm})
    public void callListener(View v) {
        switch (v.getId()) {
            case R.id.btn_confirm:
                commit();
                break;
        }
    }

    private void commit() {

    }
}
