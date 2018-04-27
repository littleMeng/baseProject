package com.genlot.lottery.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.genlot.lottery.R;
import com.genlot.lottery.widget.CustomProgressDialog;
import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Date 2018/3/15
 * Author littlemeng
 * Email yikaishao@163.com
 * Describe Fragment基类
 */

public abstract class BaseFragment extends RxFragment {
    private FragmentActivity activity;
    private Unbinder mBinder;
    private CustomProgressDialog mProgressDialog;

    /**
     * 设置布局layout
     * @return 布局文件id
     */
    public abstract @LayoutRes int getLayoutResId();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View parentView = inflater.inflate(getLayoutResId(), container, false);
        activity = getActivity();
        return parentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinder = ButterKnife.bind(this, view);
        onCreateViewFinished(savedInstanceState);
        initToolBar();
    }

    public abstract void onCreateViewFinished(Bundle state);

    /**
     * 初始化标题
     */
    public abstract void initToolBar();

    /**
     * 加载数据
     */
    public void loadData() {}

    /**
     * 显示进度条
     */
    public void showProgressDialog() {
        showProgressDialog(getString(R.string.loading));
    }

    public void showProgressDialog(String info) {
        if (mProgressDialog == null) {
            mProgressDialog = new CustomProgressDialog(getActivity(), info);
            mProgressDialog.setCancelable(true);
            mProgressDialog.setCanceledOnTouchOutside(false);
        } else {
            mProgressDialog.setMessage(info);
        }
        mProgressDialog.show();
    }

    /**
     * 隐藏进度条
     */
    public void hideProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.hide();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinder.unbind();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (FragmentActivity) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.activity = null;
    }
}
