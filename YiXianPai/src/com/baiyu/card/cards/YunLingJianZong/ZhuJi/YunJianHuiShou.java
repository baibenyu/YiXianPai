package com.baiyu.card.cards.YunLingJianZong.ZhuJi;

import com.baiyu.card.Card;
import com.baiyu.entry.LianYun;
import com.baiyu.frame.Player;

public class YunJianHuiShou extends Card implements LianYun {
    private int defenseValue; // 追加的防御值
    private int lifeRegainValue; // 连云后恢复的生命值

    public YunJianHuiShou(int level) {
        super(level, "云剑·回守");
        initializeAttributes(level);
    }

    // 更新initializeAttributes方法，根据等级直接设置属性
    @Override
    public void initializeAttributes(int level) {
        switch (level) {
            case 1:
                defenseValue = 8;
                lifeRegainValue = 3;
                break;
            case 2:
                defenseValue = 11;
                lifeRegainValue = 5;
                break;
            case 3:
                defenseValue = 14;
                lifeRegainValue = 7;
                break;
            default:
                throw new IllegalArgumentException("Invalid level: " + level);
        }
    }


    @Override
    public boolean execute(Player me, Player target) {
        me.addBuff("防", defenseValue);
        Card card = me.getPreviousUsedCard();
        if (card != null && card.getName().contains("云剑")) lianYun(me, target);
        return true;
    }

    @Override
    public void lianYun(Player me, Player target) {
        me.increaseHealth(lifeRegainValue);
    }
}
