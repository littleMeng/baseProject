package com.genlot.lottery.util;

import com.genlot.lottery.userinfo.bean.LoginBean;

/**
 * Date 2018/4/19
 * Author littlemeng
 * Email yikai.shao@genlot.com
 * Describe 全局信息
 */

public class GlobalInfo {
    private static GlobalInfo mInstance;

    private LoginBean userInfo = new LoginBean();       //用户信息
    private DataPool mDataPool = new DataPool();        //数据池

    public static GlobalInfo getInstance() {
        if (mInstance == null) {
            synchronized (GlobalInfo.class) {
                if (mInstance == null) {
                    mInstance = new GlobalInfo();
                }
            }
        }
        return mInstance;
    }

    private GlobalInfo() {}

    /**
     * 保存用户信息
     */
    public void saveUserInfo(LoginBean bean) {
        userInfo.setUid(bean.getUid());
        userInfo.setToken(bean.getToken());
        userInfo.setType(bean.getType());
    }

    /**
     * 清除用户信息
     */
    public void clearUserInfo() {
        userInfo.setUid("");
        userInfo.setToken("");
        userInfo.setType("");
    }

    public LoginBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(LoginBean userInfo) {
       this.userInfo = userInfo;
    }

    public DataPool getDataPool() {
        return mDataPool;
    }

    public void setDataPool(DataPool dataPool) {
        mDataPool = dataPool;
    }
}
