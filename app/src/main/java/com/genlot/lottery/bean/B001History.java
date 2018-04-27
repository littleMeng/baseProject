package com.genlot.lottery.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Date 2018/4/23
 * Author littlemeng
 * Email yikai.shao@genlot.com
 * Describe 双色球历史开奖数据
 */

public class B001History {
    private int issue;                          //历史期号
    private List<Integer> winNumbers = new ArrayList<>();           //历史开奖号码
    private List<Integer> winNumbersOmit = new ArrayList<>();       //历史号码遗漏
    private List<Integer> zoneCount = new ArrayList<>();            //开奖号码区间个数
    private String oePercent;                   //奇偶比
    private String bsPercent;                   //大小比
    private int umValue;                        //和值
    private int eValue;                         //奇偶值
    private int sValue;                         //大小值
}
