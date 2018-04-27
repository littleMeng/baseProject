package com.genlot.lottery.net;

import android.util.Log;

import com.genlot.lottery.net.api.ApiService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Date 2018/3/16
 * Author littlemeng
 * Email yikaishao@163.com
 * Describe Retrofit帮助类
 */

public class RetrofitHelper {
    private static final String API_HOST = "http://10.13.0.128:18092/";          //域名地址

    private static OkHttpClient mOkHttpClient;          //okhttp客户端

    static {
        initOkHttpClient();
    }

    /**
     * 通用接口
     */
    public static ApiService getCommApi() {
        return createApi(ApiService.class, API_HOST);
    }

    /**
     * 创建retrofit
     * @param baseUrl 域名地址
     */
    private static <T> T createApi(Class<T> tClass, String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(tClass);
    }

    /**
     * 初始化OKHttpClient,设置超时时间,设置打印日志
     */
    private static void initOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor((message) -> Log.i("Retrofit", message));
        if (mOkHttpClient == null) {
            synchronized (RetrofitHelper.class) {
                if (mOkHttpClient == null) {
                    mOkHttpClient = new OkHttpClient.Builder()
                            .addInterceptor(interceptor)
                            .retryOnConnectionFailure(true)
                            .connectTimeout(30, TimeUnit.SECONDS)
                            .writeTimeout(20, TimeUnit.SECONDS)
                            .readTimeout(20, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
    }
}
