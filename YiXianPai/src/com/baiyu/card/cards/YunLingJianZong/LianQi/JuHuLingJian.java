package com.baiyu.card.cards.YunLingJianZong.LianQi;

import com.baiyu.buff.Buff;
import com.baiyu.buff.buffs.LingQi;
import com.baiyu.card.Card;
import com.baiyu.frame.Player;

import java.util.Map;

public class JuHuLingJian extends Card {
    private int attackValue; // 攻击值
    private int lingQiCost;

    public JuHuLingJian(int level) {
        super(level, "巨虎灵剑");
        initializeAttributes(level);
    }

    @Override
    public void initializeAttributes(int level) {
        lingQiCost = 1;
        switch (level) {
            case 1:
                attackValue = 10;
                break;
            case 2:
                attackValue = 13;
                break;
            case 3:
                attackValue = 16;
                break;
            default:
                throw new IllegalArgumentException("Invalid level: " + level);
        }
    }

    @Override
    public boolean execute(Player me, Player target) {
        Map<String, Buff> buffs = me.getBuffs();
        if (isLingQiEnough(buffs,lingQiCost)) {
            me.attack(target, attackValue);
            return true;
        }
        return false;
    }
}
