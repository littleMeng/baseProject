package com.genlot.lottery;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.genlot.lottery.base.BaseActivity;
import com.genlot.lottery.dao.DBHelper;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

    }

    @Override
    public void initToolBar() {
        setToolBarDefaultConfig(mToolbar, "hellomeng", true);
    }

    @Override
    public void loadData() {
    }

    private void testDateBase() {
        Observable.empty()
                .doOnSubscribe(disposable -> {
                    DBHelper.getInstance()
                            .getNoticeDao().queryBuilder().limit(10).list();
                })
                .subscribeOn(Schedulers.io());

    }
}
