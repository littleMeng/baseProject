package com.genlot.lottery.bean;

/**
 * Date 2018/4/24
 * Author littlemeng
 * Email yikai.shao@genlot.com
 * Describe 游戏开奖相关数据
 */

public class GameAttr {
    private int gameID;             //游戏ID
    private long issue;             //当前游戏期号
    private long lastIssue;         //上期游戏期号
    private long startSaleTime;     //当前期游戏开始销售时间
    private long endSaleTime;       //当前期游戏结束销售时间
    private long drawTime;          //当前期游戏开奖时间
    private int lastFlag;           //最后一期结束标志

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public long getIssue() {
        return issue;
    }

    public void setIssue(long issue) {
        this.issue = issue;
    }

    public long getLastIssue() {
        return lastIssue;
    }

    public void setLastIssue(long lastIssue) {
        this.lastIssue = lastIssue;
    }

    public long getStartSaleTime() {
        return startSaleTime;
    }

    public void setStartSaleTime(long startSaleTime) {
        this.startSaleTime = startSaleTime;
    }

    public long getEndSaleTime() {
        return endSaleTime;
    }

    public void setEndSaleTime(long endSaleTime) {
        this.endSaleTime = endSaleTime;
    }

    public long getDrawTime() {
        return drawTime;
    }

    public void setDrawTime(long drawTime) {
        this.drawTime = drawTime;
    }

    public int getLastFlag() {
        return lastFlag;
    }

    public void setLastFlag(int lastFlag) {
        this.lastFlag = lastFlag;
    }
}
