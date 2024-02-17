package com.baiyu.card.cards.YunLingJianZong.LianQi;

import com.baiyu.card.Card;
import com.baiyu.entry.LianYun;
import com.baiyu.frame.Player;

public class YunJianFeiCe extends Card implements LianYun {
    private int attackValue; // 攻击值
    private int plusAttackValue; // 连云后追加的攻击值


    public YunJianFeiCe(int level) {
        super(level, "云剑·飞刺");
        initializeAttributes(level);
    }

    @Override
    public void initializeAttributes(int level) {
        switch (level) {
            case 1:
                attackValue = 5;
                plusAttackValue = 3;
                break;
            case 2:
                attackValue = 6;
                plusAttackValue = 5;
                break;
            case 3:
                attackValue = 7;
                plusAttackValue = 7;
                break;
            default:
                throw new IllegalArgumentException("Invalid level: " + level);
        }
    }

    @Override
    public boolean execute(Player me, Player target) {
        me.attack(target, attackValue);
        Card card = me.getPreviousUsedCard();
        if (card != null && card.getName().contains("云剑")) lianYun(me,target);
        return true;
    }

    @Override
    public void lianYun(Player me, Player target) {
        me.attack(target,plusAttackValue);
    }
}
