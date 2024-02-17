package com.baiyu.card.cards.YunLingJianZong.ZhuJi;

import com.baiyu.buff.buffs.LingQi;
import com.baiyu.card.Card;
import com.baiyu.frame.Player;

public class HuaLingJue extends Card {
    private int lingQiValue;

    public HuaLingJue(int level) {
        super(level, "化灵决");
        initializeAttributes(level);
    }

    @Override
    public void initializeAttributes(int level) {
        switch (level) {
            case 1:
                lingQiValue = 3;
                break;
            case 2:
                lingQiValue = 4;
                break;
            case 3:
                lingQiValue = 5;
                break;
            default:
                throw new IllegalArgumentException("Invalid level: " + level);
        }
    }

    @Override
    public boolean execute(Player me, Player target) {
        // 化灵决的效果直接在执行时生效
        me.addBuff( "灵气", lingQiValue);
        return true;
    }
}
