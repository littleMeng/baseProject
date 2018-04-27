package com.genlot.lottery.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Date 2018/4/24
 * Author littlemeng
 * Email yikai.shao@genlot.com
 * Describe 快3历史开奖数据
 */

public class K3History {
    private long issue;//历史期号
    private int sumValue;//和值
    private int spanValue;//跨度值
    private int oddValue;//奇数个数
    private int bigValue;//大数个数
    private List<Integer> winNumbers = new ArrayList<>();//历史开奖号码
    private List<Integer> winNumbersOmit = new ArrayList<>();//历史开奖号码遗漏
    private List<Integer> sumOmit = new ArrayList<>();//和值遗漏
    private List<Integer> spanOmit = new ArrayList<>();//跨度遗漏
    private List<Integer> oddOmit = new ArrayList<>();//奇数遗漏
    private List<Integer> bigOmit = new ArrayList<>();//大数遗漏
}
