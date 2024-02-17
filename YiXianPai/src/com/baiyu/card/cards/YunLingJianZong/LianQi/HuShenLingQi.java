package com.baiyu.card.cards.YunLingJianZong.LianQi;

import com.baiyu.buff.Buff;
import com.baiyu.buff.buffs.Defense;
import com.baiyu.buff.buffs.LingQi;
import com.baiyu.card.Card;
import com.baiyu.frame.Player;

import java.util.Map;

public class HuShenLingQi extends Card {
    private int defenceValue; // 增加的防值
    private int lingQiValue; // 增加的灵气值

    public HuShenLingQi(int level) {
        super(level,"护身灵气");
        initializeAttributes(level);
    }

    @Override
    public void initializeAttributes(int level) {
        switch (level) {
            case 1:
                defenceValue = 5;
                lingQiValue = 1;
                break;
            case 2:
                defenceValue = 5;
                lingQiValue = 2;
                break;
            case 3:
                defenceValue = 5;
                lingQiValue = 3;
                break;
            default:
                throw new IllegalArgumentException("Invalid level: " + level);
        }
    }


    @Override
    public boolean execute(Player me, Player target) {
        // 增加防值
        me.addBuff("防",defenceValue);
        // 增加人物灵气值
        me.addBuff("灵气",lingQiValue);

        return true;
    }
}
