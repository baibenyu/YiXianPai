package com.baiyu.card.cards.YunLingJianZong.LianQi;

import com.baiyu.buff.Buff;
import com.baiyu.buff.buffs.LingQi;
import com.baiyu.card.Card;
import com.baiyu.frame.Player;

import java.util.Map;

public class LingQiGuanZhu extends Card {
    private int lingQiValue;

    public LingQiGuanZhu(int level) {
        super(level, "灵气灌注");
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
        // 增加灵气
        me.addBuff( "灵气", lingQiValue);
        // 增加无视防御
        me.addBuff( "无视防御", 1);

        return true;
    }
}
