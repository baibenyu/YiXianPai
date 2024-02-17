package com.baiyu.card.cards.YunLingJianZong.ZhuJi;

import com.baiyu.buff.Buff;
import com.baiyu.buff.buffs.LingQi;
import com.baiyu.card.Card;
import com.baiyu.frame.Player;

import java.util.Map;

public class JuJingLingJian extends Card {
    private int attackValue; // 攻击值
    private int lingQiCost;

    public JuJingLingJian(int level) {
        super(level, "巨鲸灵剑");
        initializeAttributes(level);
    }

    @Override
    public void initializeAttributes(int level) {
        lingQiCost = 2;
        switch (level) {
            case 1:
                attackValue = 16;
                break;
            case 2:
                attackValue = 20;
                break;
            case 3:
                attackValue = 24;
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
