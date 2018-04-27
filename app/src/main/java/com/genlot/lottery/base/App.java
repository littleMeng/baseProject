package com.genlot.lottery.base;

import android.app.Application;
import android.content.Context;

import com.genlot.lottery.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;

/**
 * Date 2018/3/20
 * Author littlemeng
 * Email yikaishao@163.com
 * Describe 应用开启前初始化
 */

public class App extends Application {
    private static final String TAG = "App";

    private static App mInstance;

    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater((context, layout) -> {
            layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
            return new BezierRadarHeader(context);
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater((context, layout) -> new BallPulseFooter(context));
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    /**
     * 获取App context
     * @return 上下文
     */
    public static Context getAppContext() {
        return mInstance.getApplicationContext();
    }
}
