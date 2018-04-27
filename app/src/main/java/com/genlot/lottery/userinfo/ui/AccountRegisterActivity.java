package com.genlot.lottery.userinfo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.genlot.lottery.R;
import com.genlot.lottery.base.BaseActivity;
import com.genlot.lottery.net.BaseOberver;
import com.genlot.lottery.net.CommonBean;
import com.genlot.lottery.net.NetUtil;
import com.genlot.lottery.net.ProgressObserver;
import com.genlot.lottery.net.RetrofitHelper;
import com.genlot.lottery.util.ToastUtil;
import com.genlot.lottery.widget.CityPickerHelper;
import com.google.gson.JsonObject;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Date 2018/4/17
 * Author littlemeng
 * Email yikai.shao@genlot.com
 * Describe 账号注册界面
 */

public class AccountRegisterActivity extends BaseActivity {
    @BindView(R.id.toolbar)                 //标题栏
    Toolbar mToolbar;
    @BindView(R.id.et_account_num)          //手机号
    EditText mEtPhoneNum;
    @BindView(R.id.et_identify_code)          //验证码
    EditText mEtIdentityCode;
    @BindView(R.id.et_password)          //密码
    EditText mEtPassword;
    @BindView(R.id.et_confirm_password)          //确认密码
    EditText mEtConfirmPassword;
    @BindView(R.id.et_user_name)          //用户姓名
    EditText mEtUserName;
    @BindView(R.id.et_station_name)          //站点名称
    EditText mEtStationName;
    @BindView(R.id.et_machine_code)          //机器编号
    EditText mEtMachineCode;
    @BindView(R.id.tv_province_city)        //站点区域
    TextView mTvProvinceCity;
    @BindView(R.id.et_detailed_addr)        //详细地址
    EditText mEtDetailedAddr;

    private String mProvince = "";          //省份
    private String mCity = "";              //城市

    private CityPickerHelper mCityPickerHelper = new CityPickerHelper();    //城市地区选择器

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCityPickerHelper.init(this);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_account_register;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mCityPickerHelper.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                super.onSelected(province, city, district);
                mProvince = province.getName();
                mCity = city.getName();
                String name = mProvince + mCity;
                mTvProvinceCity.setText(name);
            }
        });
    }

    @Override
    public void initToolBar() {
        setToolBarDefaultConfig(mToolbar, "账号注册", true);
    }

    @OnClick({R.id.btn_commit_register, R.id.ll_pick_city, R.id.btn_identify_code})
    public void callListener(View v) {
        switch (v.getId()) {
            case R.id.btn_commit_register:
                doRegister();
                break;
            case R.id.ll_pick_city:
                pickCity();
                break;
            case R.id.btn_identify_code:
                sendIdentityCode();
                break;
        }
    }

    private void pickCity() {
        mCityPickerHelper.show();
    }

    /**
     * 提交注册
     */
    private void doRegister() {
        String phoneNum = mEtPhoneNum.getText().toString().trim();
        String identityCode = mEtIdentityCode.getText().toString().trim();
        String password = mEtPassword.getText().toString().trim();
        String confirmPwd = mEtConfirmPassword.getText().toString().trim();
        String userName = mEtUserName.getText().toString().trim();
        String stationName = mEtStationName.getText().toString().trim();
        String machineCode = mEtMachineCode.getText().toString().trim();
        String city = mTvProvinceCity.getText().toString().trim();
        String detailedAddr = mEtDetailedAddr.getText().toString().trim();

        if (TextUtils.isEmpty(phoneNum)
                || TextUtils.isEmpty(identityCode)
                || TextUtils.isEmpty(password)
                || TextUtils.isEmpty(confirmPwd)
                || TextUtils.isEmpty(userName)
                || TextUtils.isEmpty(stationName)
                || TextUtils.isEmpty(machineCode)
                || TextUtils.isEmpty(city)
                || TextUtils.isEmpty(detailedAddr)) {
            ToastUtil.showShort(this, "请输入必要信息");
            return;
        }

        if (!password.equals(confirmPwd)) {
            ToastUtil.showShort(this, "两次输入的密码不一致");
            return;
        }

        HashMap<String, Object> params = new HashMap<>();
        params.put("smsCode", identityCode);
        params.put("username", phoneNum);
        params.put("password", password);
        params.put("name", userName);
        params.put("stationName", stationName);
        params.put("province", mProvince);
        params.put("city", mCity);
        params.put("address", detailedAddr);
        JsonObject body = NetUtil.createParams(NetUtil.API_CODE_REGISTER, params);
        RetrofitHelper.getCommApi().commonPost(body)
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressObserver<CommonBean>(this) {
                    @Override
                    public void onSuccess(CommonBean result) {
                        ToastUtil.showShort(getApplication(), "已提交注册，请耐心等候");
                    }

                    @Override
                    public void onFailure(Throwable e, String errMsg) {
                        ToastUtil.showShort(getApplication(), errMsg);
                    }
                });
    }

    /**
     * 发送验证码
     */
    public void sendIdentityCode() {
        String phoneNum = mEtPhoneNum.getText().toString().trim();

        if (TextUtils.isEmpty(phoneNum)) {
            ToastUtil.showShort(this, "请输入手机号");
            return;
        }

        HashMap<String, Object> params = new HashMap<>();
        params.put("mobile", phoneNum);
        params.put("smsType", NetUtil.SMS_TYPE_REGISTER);
        JsonObject body = NetUtil.createParams(NetUtil.API_CODE_IDENTIFY, params);
        RetrofitHelper.getCommApi().commonPost(body)
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseOberver<CommonBean>() {
                    @Override
                    public void onSuccess(CommonBean result) {
                        ToastUtil.showShort(getApplication(), "发送验证码成功");
                    }

                    @Override
                    public void onFailure(Throwable e, String errMsg) {
                        ToastUtil.showShort(getApplication(), errMsg);
                    }
                });
    }
}
