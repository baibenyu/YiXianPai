package com.baiyu.card.cards.YunLingJianZong.LianQi;

import com.baiyu.entry.Attackable;
import com.baiyu.card.Card;
import com.baiyu.frame.Player;

import static java.lang.Math.min;

public class ZhouFengJian extends Card implements Attackable {
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
            attack(me, target,attackValue);
        }
        me.setSwordPower(0);
        me.setCurrentCardId(me.getCurrentCardId()+1); // 执行成功,牌序+1
        return false;
    }

    @Override
    public void attack(Player me, Player target,int attackValue) {
        damage = me.getSwordPower() + attackValue;
        int offsetValue = min(damage, target.getDefenceValue()); // 被防抵消的攻
        target.setDefenceValue(target.getDefenceValue() - offsetValue); // 扣除被攻击的防后,剩余的防
        damage -= offsetValue; // 剩余的攻
        target.setHealth(target.getHealth() - damage); // 扣除生命值
    }
}
