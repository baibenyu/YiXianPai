package com.baiyu.card.cards.YunLingJianZong.LianQi;

import com.baiyu.card.Card;
import com.baiyu.frame.Player;

import static java.lang.Math.min;

public class ZhouFengJian extends Card {
    private int attackValue; // 攻击值
    private int attackCount; // 攻击次数
    private int damage; // 己方单次理论攻击力
    private String name = "骤风剑";
    private int level;


    public ZhouFengJian(int attackValue, int attackCount,int level) {
        this.attackValue = attackValue;
        this.attackCount = attackCount;
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
        // 攻击attackCount次
        for (int i = 0; i < attackCount; i++) {
            // 根据对方状态决定实际扣除的生命值
            me.attack(target,attackValue);
        }
        me.nextCard();// 执行成功,牌序+1
        return false;
    }

}
