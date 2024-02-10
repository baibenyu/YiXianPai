package com.baiyu.card.cards.YunLingJianZong.LianQi;

import com.baiyu.card.Card;
import com.baiyu.frame.Player;

public class JianDang extends Card {
    private int defenceValue; // 增加的防值
    private int swordPower; // 增加的剑意值
    private String name = "剑挡";
    private int level;

    public JianDang(int defenceValue, int swordPower, int level) {
        this.defenceValue = defenceValue;
        this.swordPower = swordPower;
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
        // 增加人物剑意值
        me.setSwordPower(me.getSwordPower() + swordPower);
        // 增加牌序
        me.setCurrentCardId(me.getCurrentCardId()+1); // 执行成功,牌序+1
        return true;
    }
}
