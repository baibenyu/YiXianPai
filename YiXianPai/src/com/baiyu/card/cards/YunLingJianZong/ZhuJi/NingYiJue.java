package com.baiyu.card.cards.YunLingJianZong.ZhuJi;

import com.baiyu.buff.buffs.LingQi;
import com.baiyu.buff.buffs.YunLingJianZong.JianYi;
import com.baiyu.card.Card;
import com.baiyu.frame.Player;

public class NingYiJue extends Card {
    private int lingQiValue;
    private int jianYiValue;

    public NingYiJue(int level) {
        super(level, "凝意决");
        initializeAttributes(level);
    }

    @Override
    public void initializeAttributes(int level) {
        lingQiValue = 1;
        switch (level) {
            case 1:
                jianYiValue = 3;
                break;
            case 2:
                jianYiValue = 4;
                break;
            case 3:
                jianYiValue = 5;
                break;
            default:
                throw new IllegalArgumentException("Invalid level: " + level);
        }
    }

    @Override
    public boolean execute(Player me, Player target) {
        // 提升灵气值
        me.addBuff("灵气", lingQiValue);

        // 提升剑意值
        me.addBuff("剑意", jianYiValue);

        return true;
    }
}