package com.baiyu.card.cards.YunLingJianZong.LianQi;

import com.baiyu.buff.Buff;
import com.baiyu.buff.buffs.LingQi;
import com.baiyu.card.Card;
import com.baiyu.entry.JiShang;
import com.baiyu.frame.Player;

import java.util.Map;

import static java.lang.Math.min;

public class FeiYaJian extends Card implements JiShang {
    private int level;
    private String name = "飞牙剑";
    private int attackValue; // 攻击值
    private int attackCount; // 攻击次数
    private int damage; // 己方单次理论攻击力
    private int targetInitialHP; // 对方在该卡牌执行之前的生命值
    private int initialJianYi; // 我方在该卡牌执行之前的剑意值
    private final int lingQiCost = 1;

    public FeiYaJian(int attackValue, int attackCount, int level) {
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
        Map<String, Buff> buffs = me.getBuffs();
        // 灵气是否充足
        if (buffs.containsKey("灵气")){
            if (buffs.get("灵气").getValue() >= lingQiCost){
                buffs.get("灵气").decrease(lingQiCost);
                // 保存玩家的状态
                targetInitialHP = target.getHealth();
                if (buffs.containsKey("剑意")) initialJianYi = buffs.get("剑意").getValue();
                else initialJianYi = 0;

                // 攻击attackCount次
                for (int i = 0; i < attackCount; i++) me.attack(target, attackValue);
                if (target.getHealth() < targetInitialHP) JiShang(me, target); // 击伤效果是否触发
                me.nextCard(); // 执行成功,牌序+1
                return true;
            } else {
                buffs.get("灵气").increase(1);
                return false;
            }
        } else {
            buffs.put("灵气",new LingQi(1)); // 灵气不足,执行失败,此回合仅+1点灵气
            return false;
        }

    }

    @Override
    public void JiShang(Player me, Player target) {
        Map<String, Buff> buffs = me.getBuffs();
        if (buffs.containsKey("剑意")) buffs.get("剑意").setAlive(true); // 返还所有剑意值
    }
}
