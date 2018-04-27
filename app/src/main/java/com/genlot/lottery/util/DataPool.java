package com.genlot.lottery.util;

import com.genlot.lottery.bean.GameAttr;

/**
 * Date 2018/4/24
 * Author littlemeng
 * Email yikai.shao@genlot.com
 * Describe 数据存储
 */

public class DataPool {
    public static final int GAME_ID_B001 = 3;
    public static final int GAME_ID_C522 = 1;
    public static final int GAME_ID_3D = 4;
    public static final int GAME_ID_K3 = 7;

    private GameAttr gameAttr = new GameAttr();         //游戏当前期信息

    public GameAttr getGameAttr() {
        return gameAttr;
    }

    public void setGameAttr(GameAttr gameAttr) {
        this.gameAttr = gameAttr;
    }
}
