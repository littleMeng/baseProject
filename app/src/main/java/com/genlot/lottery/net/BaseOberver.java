package com.genlot.lottery.net;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Date 2018/3/16
 * Author littlemeng
 * Email yikaishao@163.com
 * Describe 返回结果处理
 */

public abstract class BaseOberver<T> implements Observer<BaseResponse<T>> {

    @Override
    public void onNext(@NonNull BaseResponse<T> response) {
        if (response.getResCode() == NetUtil.SUCCESS) {
            onSuccess(response.getContent());
        } else {
            onFailure(new Exception(response.getResMsg()), response.getResMsg());
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {
        onFailure(e, ExceptionUtil.exceptionHandler(e));
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    public abstract void onSuccess(T result);

    public abstract void onFailure(Throwable e, String errMsg);
}
