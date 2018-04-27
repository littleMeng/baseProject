package com.genlot.lottery.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Date 2018/4/24
 * Author littlemeng
 * Email yikai.shao@genlot.com
 * Describe 22选5历史开奖数据
 */

public class C522History {
    private int issue;                              //历史期号
    private List<Integer> winNumbers = new ArrayList<>();        //历史开奖号码
    private List<Integer> winNumbersOmit = new ArrayList<>();    //历史号码遗漏
    private int sumValue;                           //和值
    private int endSumValue;                        //尾和
    private int repeatNumber;                       //重号
    private int serialNumber;                       //连号
    private String oePercent;                       //奇偶比
    private String bsPercent;                       //大小比
    private String zonePercent;                     //区间比
}
