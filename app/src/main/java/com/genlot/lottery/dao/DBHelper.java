package com.genlot.lottery.dao;

import com.genlot.lottery.base.App;
import com.genlot.lottery.dao.greendao.DaoMaster;
import com.genlot.lottery.dao.greendao.DaoSession;

/**
 * Date 2018/3/29
 * Author littlemeng
 * Email yikaishao@163.com
 * Describe 数据库帮助类
 */

public class DBHelper {
    private static DBHelper mDBHelper;

    private DaoSession mDaoSession;

    private DBHelper() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(App.getAppContext(), "USER", null);
        DaoMaster master = new DaoMaster(helper.getWritableDatabase());
        mDaoSession = master.newSession();
    }

    public static DaoSession getInstance() {
        if (mDBHelper == null) {
            synchronized (DBHelper.class) {
                if (mDBHelper == null) {
                    mDBHelper = new DBHelper();
                }
            }
        }
        return mDBHelper.mDaoSession;
    }
}
