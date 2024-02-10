package com.baiyu.card.cards.YunLingJianZong.LianQi;

import com.baiyu.entry.Attackable;
import com.baiyu.card.Card;
import com.baiyu.entry.JiShang;
import com.baiyu.frame.Player;

import static java.lang.Math.min;

public class FeiYaJian extends Card implements Attackable, JiShang {
    private int level;
    private String name = "飞牙剑";
    private int attackValue; // 攻击值
    private int attackCount; // 攻击次数
    private int damage; // 己方单次理论攻击力
    private int targetInitialHP; // 对方在该卡牌执行之前的生命值
    private int initialSwordPower; // 我方在该卡牌执行之前的剑意值
    private final int LingQiCost = 1;

    public FeiYaJian(int attackValue, int attackCount,int level) {
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
        // 灵气是否充足
        if (me.getLingQi() >= LingQiCost){
            me.setLingQi(me.getLingQi()-LingQiCost);
            // 保存玩家的状态
            targetInitialHP = target.getHealth();
            initialSwordPower = me.getSwordPower();
            // 攻击attackCount次
            for (int i = 0; i < attackCount; i++) attack(me,target,attackValue);
            me.setSwordPower(0); // 消耗剑意值
            if (target.getHealth() < targetInitialHP) effect(me,target); // 击伤效果是否触发
            me.setCurrentCardId(me.getCurrentCardId()+1); // 执行成功,牌序+1
            return true;
        }else{
            me.setLingQi(me.getLingQi()+1); // 灵气不足,执行失败,此回合仅+1点灵气
            return false;
        }

    }


    @Override
    public void attack(Player me, Player target,int attackValue) {
        damage = me.getSwordPower() + attackValue;
        int offsetValue = min(damage, target.getDefenceValue()); // 被防抵消的攻
        target.setDefenceValue(target.getDefenceValue() - offsetValue); // 扣除被攻击的防后,剩余的防
        damage -= offsetValue;// 剩余的攻
        target.setHealth(target.getHealth() - damage);
    }

    @Override
    public void effect(Player me, Player target) {me.setSwordPower(initialSwordPower);}
}
