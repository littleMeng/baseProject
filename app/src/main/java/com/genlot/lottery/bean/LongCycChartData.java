package com.genlot.lottery.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Date 2018/4/24
 * Author littlemeng
 * Email yikai.shao@genlot.com
 * Describe 走势图数据
 */

public class LongCycChartData<T> {
    private List<T> history = new ArrayList<>();
    private SpecialOmit specialOmit;

    public List<T> getHistory() {
        return history;
    }

    public void setHistory(List<T> history) {
        this.history = history;
    }

    public SpecialOmit getSpecialOmit() {
        return specialOmit;
    }

    public void setSpecialOmit(SpecialOmit specialOmit) {
        this.specialOmit = specialOmit;
    }
}
