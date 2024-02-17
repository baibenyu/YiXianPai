package com.baiyu.card;

import com.baiyu.buff.Buff;
import com.baiyu.buff.buffs.Defense;
import com.baiyu.buff.buffs.IgnoreDefense;
import com.baiyu.buff.buffs.LingQi;
import com.baiyu.buff.buffs.YunLingJianZong.JianYi;
import com.baiyu.frame.Player;

import java.util.Map;

// 卡牌父类
public abstract class Card {
    public String name;
    public int level;

    public Card(int level, String name) {
        this.level = level;
        this.name = name;
    }

    public abstract void initializeAttributes(int level);

    public abstract boolean execute(Player me, Player target);

    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isLingQiEnough(Map<String, Buff> buffs, int lingQiCost) {
        // 灵气是否充足
        if (buffs.containsKey("灵气")) {
            LingQi lingQi = (LingQi) buffs.get("灵气");
            if (lingQi.getValue() >= lingQiCost) {
                lingQi.decrease(lingQiCost);
                return true;
            } else lingQi.increase(1);
        } else buffs.put("灵气", new LingQi(1)); // 灵气不足,执行失败,此回合仅+1点灵气
        return false;
    }

}

