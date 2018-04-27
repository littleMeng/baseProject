package com.genlot.lottery.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Date 2018/4/23
 * Author littlemeng
 * Email yikai.shao@genlot.com
 * Describe 3D历史开奖数据
 */

public class D3History {
    private int issue;                      //历史期号
    private List<Integer> winNumbers = new ArrayList<>();       //历史开奖号码
    private List<Integer> winNumbersOmit = new ArrayList<>();   //历史号码遗漏
    private int sumValue;                   //和值
    private int spanValue;                  //跨度
    private String playType;                //玩法类型
    private List<Integer> testNumbers = new ArrayList<>();      //试机号
}
