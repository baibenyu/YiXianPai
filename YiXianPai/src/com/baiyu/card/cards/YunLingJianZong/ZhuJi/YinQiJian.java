package com.baiyu.card.cards.YunLingJianZong.ZhuJi;

import com.baiyu.buff.Buff;
import com.baiyu.buff.buffs.LingQi;
import com.baiyu.card.Card;
import com.baiyu.frame.Player;

import java.util.Map;

public class YinQiJian extends Card {
    private int lingQiValue;

    public YinQiJian(int level) {
        super(level, "引气剑");
        initializeAttributes(level);
    }

    @Override
    public void initializeAttributes(int level) {
        switch (level) {
            case 1:
                lingQiValue = 2;
                break;
            case 2:
                lingQiValue = 3;
                break;
            case 3:
                lingQiValue = 4;
                break;
            default:
                throw new IllegalArgumentException("Invalid level: " + level);
        }
    }

    @Override
    public boolean execute(Player me, Player target) {
        Map<String, Buff> buffs = me.getBuffs();
        me.addBuff("灵气", lingQiValue);
        if (buffs.get("灵气").getValue() > lingQiValue) {
            for (int i = 0; i < 2; i++) {
                me.attack(target, 2);
            }
        }
        return true;
    }


}
