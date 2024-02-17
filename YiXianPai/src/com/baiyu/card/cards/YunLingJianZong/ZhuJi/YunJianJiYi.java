package com.baiyu.card.cards.YunLingJianZong.ZhuJi;

import com.baiyu.card.Card;
import com.baiyu.entry.LianYun;
import com.baiyu.frame.Player;

public class YunJianJiYi extends Card implements LianYun {
    private int attackValue;
    private int jianYiValue;

    public YunJianJiYi(int level) {
        super(level, "云剑·极意");
        initializeAttributes(level);
    }

    @Override
    public void initializeAttributes(int level) {
        switch (level) {
            case 1:
                attackValue = 6;
                jianYiValue = 2;
                break;
            case 2:
                attackValue = 8;
                jianYiValue = 3;
                break;
            case 3:
                attackValue = 10;
                jianYiValue = 4;
                break;
            default:
                throw new IllegalArgumentException("Invalid level: " + level);
        }
    }

    @Override
    public boolean execute(Player me, Player target) {
        me.attack(target, attackValue);
        Card card = me.getPreviousUsedCard();
        // 如果连云条件满足，则添加剑意Buff
        if (card != null && card.getName().contains("云剑")) {lianYun(me,target);}
        return true;
    }

    @Override
    public void lianYun(Player me, Player target) {me.addBuff( "剑意", jianYiValue);}
}
