package com.genlot.lottery.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Date 2018/4/23
 * Author littlemeng
 * Email yikai.shao@genlot.com
 * Describe 遗漏数据
 */

public class SpecialOmit {
    private List<Integer> total = new ArrayList<>();            //近100期出现总数
    private List<Integer> maxContinue = new ArrayList<>();      //近100期最大连出
    private List<Integer> maxOmit = new ArrayList<>();          //近100期最大遗漏
    private List<Integer> omitAvgOmit = new ArrayList<>();      //近100期平均遗漏
}
