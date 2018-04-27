package com.genlot.lottery.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.genlot.lottery.R;
import com.genlot.lottery.widget.CustomProgressDialog;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Date 2018/3/15
 * Author littlemeng
 * Email yikaishao@163.com
 * Describe Activity基类
 */

public abstract class BaseActivity extends RxAppCompatActivity {
    private Unbinder mBinder;
    private CustomProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置布局内容
        setContentView(getLayoutResId());
        //用于取消ButterKnife绑定
        mBinder = ButterKnife.bind(this);
        initViews(savedInstanceState);
        initToolBar();
        initListener();
    }

    /**
     * 设置布局layout
     * @return 布局文件id
     */
    public abstract @LayoutRes int getLayoutResId();

    /**
     * 初始化视图
     * @param savedInstanceState 恢复状态
     */
    public abstract void initViews(Bundle savedInstanceState);

    /**
     * 初始化标题
     */
    public abstract void initToolBar();

    /**
     * 初始化监听事件
     */
    protected void initListener() {}

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
            mProgressDialog = new CustomProgressDialog(this, info);
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
    protected void onDestroy() {
        super.onDestroy();
        mBinder.unbind();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    /**
     * 设置标题栏公共配置
     */
    public void setToolBarDefaultConfig(Toolbar toolbar, String title, boolean back) {
        setSupportActionBar(toolbar);
        TextView tvTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        tvTitle.setText(title);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(back);
            ab.setDisplayShowTitleEnabled(false);
            ab.setTitle(null);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
