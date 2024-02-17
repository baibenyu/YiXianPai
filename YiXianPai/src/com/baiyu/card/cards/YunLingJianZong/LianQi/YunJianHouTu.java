package com.baiyu.card.cards.YunLingJianZong.LianQi;

import com.baiyu.buff.Buff;
import com.baiyu.card.Card;
import com.baiyu.entry.LianYun;
import com.baiyu.frame.Player;

import java.util.Map;

public class YJHouTu extends Card implements LianYun {
    private int attackValue; // 攻击值
    private int plusDefenseValue; // 连云后追加的防值


    public YJHouTu(int level) {
        super(level, "云剑·厚土");
        initializeAttributes(level);
    }

    @Override
    public void initializeAttributes(int level) {
        switch (level) {
            case 1:
                attackValue = 4;
                plusDefenseValue = 4;
                break;
            case 2:
                attackValue = 6;
                plusDefenseValue = 6;
                break;
            case 3:
                attackValue = 8;
                plusDefenseValue = 8;
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
        addBuff(me.getBuffs(),"防",plusDefenseValue);
    }
}
