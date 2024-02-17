package com.baiyu.card.cards.YunLingJianZong.ZhuJi;

import com.baiyu.card.Card;
import com.baiyu.entry.LianYun;
import com.baiyu.frame.Player;

public class YunJianWuFeng extends Card implements LianYun {
    private int attackValue;

    public YunJianWuFeng(int level) {
        super(level, "云剑·无锋");
        initializeAttributes(level);
    }

    @Override
    public void initializeAttributes(int level) {
        switch (level) {
            case 1:
                attackValue = 9;
                break;
            case 2:
                attackValue = 13;
                break;
            case 3:
                attackValue = 17;
                break;
            default:
                throw new IllegalArgumentException("Invalid level: " + level);
        }
    }

    @Override
    public boolean execute(Player me, Player target) {
        Card card = me.getPreviousUsedCard();
        // 云剑·无锋卡牌在执行时不直接造成伤害，仅在连云状态下生效
        if (card != null && card.getName().contains("云剑")) lianYun(me,target);
        return true;
    }

    @Override
    public void lianYun(Player me, Player target) {
        me.attack(target, attackValue);
    }
}
