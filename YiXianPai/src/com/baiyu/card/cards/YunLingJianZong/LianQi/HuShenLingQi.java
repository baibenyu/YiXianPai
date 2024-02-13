package com.baiyu.card.cards.YunLingJianZong.LianQi;

import com.baiyu.buff.Buff;
import com.baiyu.buff.buffs.LingQi;
import com.baiyu.card.Card;
import com.baiyu.frame.Player;

import java.util.Map;

public class HuShenLingQi extends Card {
    private int defenceValue; // 增加的防值
    private int lingQiValue; // 增加的灵气值
    private String name = "护身灵气";

    private int level;

    public HuShenLingQi(int defenceValue, int lingQiValue, int level) {
        this.defenceValue = defenceValue;
        this.lingQiValue = lingQiValue;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public boolean execute(Player me, Player target) {
        // 增加防值
        me.setDefenceValue(me.getDefenceValue() + defenceValue);
        // 增加人物灵气值
        Map<String, Buff> buffs = me.getBuffs();
        if (buffs.containsKey("灵气")) buffs.get("灵气").increase(lingQiValue);
        else buffs.put("灵气", new LingQi(lingQiValue));

        me.nextCard(); // 执行成功,牌序+1
        return true;
    }
}
