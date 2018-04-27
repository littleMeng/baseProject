package com.genlot.lottery.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.genlot.lottery.R;
import com.genlot.lottery.base.BaseActivity;
import com.genlot.lottery.util.DataPool;
import com.genlot.lottery.util.DrawTool;
import com.genlot.lottery.util.GlobalInfo;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Date 2018/4/24
 * Author littlemeng
 * Email yikai.shao@genlot.com
 * Describe 公共标题
 */

public class MyTitleView extends RelativeLayout {
    private BaseActivity mActivity; //防止rxandroid内存泄露
    private TextView tvIssue;       //期号
    private TextView tvTime;        //倒计时
    private TextView tvGameName;    //游戏名称

    public MyTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (context instanceof BaseActivity) {
            mActivity = (BaseActivity) context;
        }
        initView(context);
        updateContent();
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.view_title_view, MyTitleView.this);
        tvIssue = (TextView) view.findViewById(R.id.title_issue);
        tvGameName = (TextView) view.findViewById(R.id.title_game_name);
        tvTime = (TextView) view.findViewById(R.id.title_time);
        ImageView drop_down = (ImageView) view.findViewById(R.id.drop_down);
        drop_down.setImageBitmap(DrawTool.readBitmap(getContext(), R.drawable.drop_down_white));
    }

    /**
     * 标题内容定时刷新
     */
    private void updateContent() {
        if (mActivity == null) {
            return;
        }
        Observable.interval(500, TimeUnit.MILLISECONDS)
                .compose(mActivity.bindToLifecycle())
                .subscribeOn(Schedulers.computation())
                .map(aLong -> getCountdown())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    tvIssue.setText(String.valueOf(GlobalInfo.getInstance().getDataPool().getGameAttr().getIssue()));
                    tvGameName.setText(getGameName(GlobalInfo.getInstance().getDataPool().getGameAttr().getGameID()));
                    tvTime.setText(s);
                });
    }

    /**
     * 获取游戏名称
     * @param gameId 游戏ID
     * @return 游戏名称
     */
    private String getGameName(int gameId) {
        switch (gameId) {
            case DataPool.GAME_ID_3D:
                return "3D";
            case DataPool.GAME_ID_B001:
                return "双色球";
            case DataPool.GAME_ID_C522:
                return "22选5";
            case DataPool.GAME_ID_K3:
                return "快3";
            default:
                return "未知";
        }
    }

    /**
     * 获取倒计时
     */
    private String getCountdown() {
        int gameId = GlobalInfo.getInstance().getDataPool().getGameAttr().getGameID();
        long currentTime = 0;
        int hour, min, second;
        long cdTime = getGameEndTime() - currentTime;
        if (cdTime >= 0) {
            hour = (int) cdTime / 3600;
            min = (int) cdTime % 3600 / 60;
            second = (int) cdTime % 60;
        } else {
            hour = 0;
            min = 0;
            second = 0;
        }

        String showTime;
        if (gameId == DataPool.GAME_ID_K3) {
            showTime = "开奖倒计时:" + String.format(Locale.getDefault(), "%02d:%02d", min, second);
        } else {
            showTime = "截止倒计时:" + String.format(Locale.getDefault(), "%02d:%02d:%02d", hour, min, second);
        }
        return showTime;
    }

    private long getGameEndTime() {
        return GlobalInfo.getInstance().getDataPool().getGameAttr().getEndSaleTime();
    }
}
