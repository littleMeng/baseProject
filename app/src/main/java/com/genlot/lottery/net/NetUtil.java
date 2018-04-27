package com.genlot.lottery.net;

import com.genlot.lottery.util.GlobalInfo;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Date 2018/3/16
 * Author littlemeng
 * Email yikaishao@163.com
 * Describe 网络请求标识码
 */

public class NetUtil {
    public static final int ERROR = -1;
    public static final int SUCCESS = 0;

    public static final int LOTTERY_WELFARE = 1;
    public static final int LOTTERY_SPORT = 2;

    public static final int SMS_TYPE_LOGIN = 0;
    public static final int SMS_TYPE_REGISTER = 1;
    public static final int SMS_TYPE_BINDING = 2;
    public static final int SMS_TYPE_FIND_PWD = 3;

    public static final int USER_TYPE_PATROL = 1;
    public static final int SMS_TYPE_STATION = 2;

    public static final int API_CODE_IDENTIFY = 300001;
    public static final int API_CODE_REGISTER = 300003;
    public static final int API_CODE_LOGIN = 300004;
    public static final int API_CODE_LOGOUT = 300005;
    public static final int API_CODE_RESET_PASSWORD = 300006;
    public static final int API_CODE_CHANGE_PASSWORD = 300007;

    /**
     * @param apiCode   交易码
     * @param params   请求参数
     * @return          请求体
     */
    public static JsonObject createParams(int apiCode, HashMap<String, Object> params) {
        JsonObject object = new JsonObject();
        JsonObject contentObject = new JsonObject();
        object.addProperty("apiCode", apiCode);
        object.addProperty("token", GlobalInfo.getInstance().getUserInfo().getToken());
        object.addProperty("userId", GlobalInfo.getInstance().getUserInfo().getUid());
        object.addProperty("type", GlobalInfo.getInstance().getUserInfo().getType());

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (entry.getValue() instanceof String) {
                contentObject.addProperty(entry.getKey(), String.valueOf(entry.getValue()));
            } else if (entry.getValue() instanceof Number) {
                contentObject.addProperty(entry.getKey(), (Number) entry.getValue());
            }
        }
        object.add("content", contentObject);
        return object;
    }
}
