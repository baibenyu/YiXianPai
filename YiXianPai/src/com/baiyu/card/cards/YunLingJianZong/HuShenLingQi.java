package com.baiyu.card.cards.YunLingJianZong;

import com.baiyu.card.Card;
import com.baiyu.frame.Player;

public class HuShenLingQi extends Card {
    private int defenceValue; // 增加的防值
    private int LingQi; // 增加的灵气值
    private String name = "护身灵气";

    private int level;


    public HuShenLingQi(int defenceValue, int LingQi,int level) {
        this.defenceValue = defenceValue;
        this.LingQi = LingQi;
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
    public void execute(Player me, Player target) {
        // 增加防值
        me.setDefenceValue(me.getDefenceValue() + defenceValue);
        // 增加人物灵气值
        me.setLingQi(me.getLingQi() + LingQi);
    }
}
