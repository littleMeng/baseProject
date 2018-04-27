package com.genlot.lottery.net;

import android.content.Context;

import com.genlot.lottery.R;
import com.genlot.lottery.widget.CustomProgressDialog;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Date 2018/3/19
 * Author littlemeng
 * Email yikaishao@163.com
 * Describe 进度条观察者
 * 需要进度条时，调用onSubscribe同时显示进度条，
 * 流程走到onComplete/onError时隐藏进度条。
 */

public abstract class ProgressObserver<T> extends BaseOberver<T> {
    private CustomProgressDialog mProgressDialog;
    private Context mContext;
    private String mText;

    public ProgressObserver(Context context) {
        this(context, context.getString(R.string.loading));
    }

    public ProgressObserver(Context context, String text) {
        this.mContext = context;
        this.mText = text;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        super.onSubscribe(d);
        if (d.isDisposed()) return;
        if (mProgressDialog == null) {
            mProgressDialog = new CustomProgressDialog(mContext, mText);
            mProgressDialog.setCancelable(true);
            mProgressDialog.setCanceledOnTouchOutside(false);
        }
        mProgressDialog.show();
    }

    @Override
    public void onComplete() {
        super.onComplete();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {
        super.onError(e);
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }
}
