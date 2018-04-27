package com.genlot.lottery.refreshlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

/**
 * Date 2018/3/21
 * Author littlemeng
 * Email yikaishao@163.com
 * Describe 刷新布局(可当做独立布局使用)
 */

public class RefreshLayoutHelper extends SmartRefreshLayout{
    public RefreshLayoutHelper(Context context) {
        this(context, null);
    }

    public RefreshLayoutHelper(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshLayoutHelper(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //默认当布局使用
        setEnablePureScrollMode(true);
        setEnableOverScrollDrag(true);
        setHeaderMaxDragRate((float) 0.5);
    }

    /**
     * 设置是否开启上拉加载更多
     */
    @Override
    public SmartRefreshLayout setEnableLoadmore(boolean enable) {
        return super.setEnableLoadmore(enable);
    }

    /**
     * 设置是否开启下拉刷新
     */
    @Override
    public SmartRefreshLayout setEnableRefresh(boolean enable) {
        return super.setEnableRefresh(enable);
    }

    /**
     * 设置是否开启纯滚动模式
     */
    @Override
    public SmartRefreshLayout setEnablePureScrollMode(boolean enable) {
        return super.setEnablePureScrollMode(enable);
    }

    /**
     * 设置在内容不满一页的时候，是否可以上拉加载更多
     */
    @Override
    public SmartRefreshLayout setEnableLoadmoreWhenContentNotFull(boolean enable) {
        return super.setEnableLoadmoreWhenContentNotFull(enable);
    }

    /**
     * 设置是否启用越界拖动（仿苹果效果）
     */
    @Override
    public SmartRefreshLayout setEnableOverScrollDrag(boolean enable) {
        return super.setEnableOverScrollDrag(enable);
    }

    /**
     * 设置指定的Content
     */
    @Override
    public RefreshLayout setRefreshContent(View content) {
        return super.setRefreshContent(content);
    }

    /**
     * 设置数据全部加载完成，将不能再次触发加载功能
     */
    @Override
    public SmartRefreshLayout setLoadmoreFinished(boolean finished) {
        return super.setLoadmoreFinished(finished);
    }

    /**
     * 完成刷新
     */
    @Override
    public SmartRefreshLayout finishRefresh() {
        return super.finishRefresh();
    }

    /**
     * 完成加载
     */
    @Override
    public SmartRefreshLayout finishLoadmore() {
        return super.finishLoadmore();
    }

    /**
     * 完成加载并标记没有更多数据
     */
    @Override
    public SmartRefreshLayout finishLoadmoreWithNoMoreData() {
        return super.finishLoadmoreWithNoMoreData();
    }
}
