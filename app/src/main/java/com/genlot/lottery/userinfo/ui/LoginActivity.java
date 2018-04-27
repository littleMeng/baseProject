package com.genlot.lottery.userinfo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.genlot.lottery.R;
import com.genlot.lottery.base.BaseActivity;
import com.genlot.lottery.net.BaseResponse;
import com.genlot.lottery.net.NetUtil;
import com.genlot.lottery.net.ProgressObserver;
import com.genlot.lottery.net.RetrofitHelper;
import com.genlot.lottery.userinfo.bean.LoginBean;
import com.genlot.lottery.util.GlobalInfo;
import com.genlot.lottery.util.ToastUtil;
import com.google.gson.JsonObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 登录界面
 */
public class LoginActivity extends BaseActivity {
    private static final String TAG = "LoginActivity";

    @BindView(R.id.toolbar)                 //标题栏
    Toolbar mToolbar;
    @BindView(R.id.et_input_account)        //账号输入
    EditText mEtInputAccount;
    @BindView(R.id.et_input_pwd)            //密码输入
    EditText mEtInputPwd;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mEtInputAccount.setText("daniel");
        mEtInputPwd.setText("1234567");
    }

    @Override
    public void initToolBar() {
        setToolBarDefaultConfig(mToolbar, "账号信息登录", true);
    }

    @OnClick({R.id.tv_do_login, R.id.tv_forget_pwd, R.id.tv_account_register})
    public void callListener(View v) {
        switch (v.getId()) {
            case R.id.tv_do_login:
                doLogin();
                break;
            case R.id.tv_forget_pwd:
                openForgetPwdPage();
                break;
            case R.id.tv_account_register:
                openRegisterPage();
                break;
        }
    }

    /**
     * 登录
     */
    private void doLogin() {
        String account = mEtInputAccount.getText().toString().trim();
        String pwd = mEtInputPwd.getText().toString().trim();

        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(pwd)) {
            ToastUtil.showShort(this, "请输入账号/密码");
            return;
        }

        HashMap<String, Object> params = new HashMap<>();
        params.put("username", account);
        params.put("password", pwd);
        JsonObject body = NetUtil.createParams(NetUtil.API_CODE_LOGIN, params);
        RetrofitHelper.getCommApi().login(body)
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .doOnNext(this::saveUserInfo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressObserver<LoginBean>(this) {
                    @Override
                    public void onSuccess(LoginBean result) {
                        ToastUtil.showShort(getApplication(), "login success: session=" + result.getToken());
                    }

                    @Override
                    public void onFailure(Throwable e, String errMsg) {
                        ToastUtil.showShort(getApplication(), "login failed:" + errMsg);
                    }
                });
    }

    /**
     * 保存用户信息
     */
    private void saveUserInfo(BaseResponse<LoginBean> response) {
        if (response.getResCode() != NetUtil.SUCCESS) {
            return;
        }
        GlobalInfo.getInstance().saveUserInfo(response.getContent());
    }

    /**
     * 进入忘记密码界面
     */
    private void openForgetPwdPage() {
        Intent intent = new Intent(this, ForgetPasswordActivity.class);
        startActivity(intent);
    }

    /**
     * 进入注册账号界面
     */
    private void openRegisterPage() {
        Intent intent = new Intent(this, AccountRegisterActivity.class);
        startActivity(intent);
    }
}
