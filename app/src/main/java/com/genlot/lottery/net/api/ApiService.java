package com.genlot.lottery.net.api;

import com.genlot.lottery.net.BaseResponse;
import com.genlot.lottery.net.CommonBean;
import com.genlot.lottery.userinfo.bean.LoginBean;
import com.google.gson.JsonObject;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Date 2018/3/16
 * Author littlemeng
 * Email yikaishao@163.com
 * Describe 网络请求服务
 */

public interface ApiService {
    @POST(".")
    Observable<BaseResponse<CommonBean>> commonPost(@Body JsonObject body);

    @POST(".")
    Observable<BaseResponse<LoginBean>> login(@Body JsonObject body);
}
