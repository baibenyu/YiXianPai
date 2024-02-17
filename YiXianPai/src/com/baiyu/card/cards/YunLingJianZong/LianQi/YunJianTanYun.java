package com.baiyu.card.cards.YunLingJianZong.LianQi;

import com.baiyu.card.Card;
import com.baiyu.frame.Player;


public class YunJianTanYun extends Card {
    private int attackValue; // 攻击值

    public YunJianTanYun(int level) {
        super(level, "云剑·探云");
        initializeAttributes(level);
    }

    @Override
    public void initializeAttributes(int level) {
        switch (level) {
            case 1:
                attackValue = 6;
                break;
            case 2:
                attackValue = 9;
                break;
            case 3:
                attackValue = 12;
                break;
            default:
                throw new IllegalArgumentException("Invalid level: " + level);
        }
    }

    @Override
    public boolean execute(Player me, Player target) {
        me.attack(target, attackValue);
        return true;
    }

}
