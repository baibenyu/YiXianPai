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
        initializeAttributes();
    }

    @Override
    public void initializeAttributes() {
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
        Map<String, Buff> buffs = me.getBuffs();
        // 增加防值
        if (buffs.containsKey("防")) buffs.get("防").increase(defenceValue);
        else buffs.put("防", new Defense(defenceValue));
        // 增加人物灵气值
        if (buffs.containsKey("灵气")) buffs.get("灵气").increase(lingQiValue);
        else buffs.put("灵气", new LingQi(lingQiValue));

        me.nextCard(); // 执行成功,牌序+1
        return true;
    }
}
