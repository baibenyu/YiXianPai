package com.baiyu.card.cards.YunLingJianZong.LianQi;

import com.baiyu.card.Card;
import com.baiyu.frame.Player;

public class QingJian extends Card {
    private int attackValue; // 攻击值
    private int lingQiValue;

    public QingJian(int level) {
        super(level, "轻剑");
        initializeAttributes(level);
    }

    @Override
    public void initializeAttributes(int level) {
        attackValue = 4;
        switch (level) {
            case 1:
                lingQiValue = 1;
                break;
            case 2:
                lingQiValue = 2;
                break;
            case 3:
                lingQiValue = 3;
                break;
            default:
                throw new IllegalArgumentException("Invalid level: " + level);
        }
    }

    @Override
    public boolean execute(Player me, Player target) {
        me.attack(target, attackValue);
        me.addBuff("灵气",lingQiValue);
        return true;
    }
}
