package com.baiyu.card.cards.YunLingJianZong.LianQi;

import com.baiyu.buff.Buff;
import com.baiyu.buff.buffs.LingQi;
import com.baiyu.card.Card;
import com.baiyu.entry.JiShang;
import com.baiyu.frame.Player;

import java.util.Map;

public class ZhenLeiJian extends Card implements JiShang {
    private int attackValue; // 攻击值
    private int plusAttackValue; // 击伤后追加的攻击值
    private int targetInitialHP; // 对方在该卡牌执行之前的生命值
    private int lingQiCost;
    public ZhenLeiJian(int level) {
        super(level, "震雷剑");
        initializeAttributes();
    }

    @Override
    public void initializeAttributes() {
        lingQiCost = 1;
        switch (level) {
            case 1:
                attackValue = 5;
                plusAttackValue = 6;
                break;
            case 2:
                attackValue = 6;
                plusAttackValue = 8;
                break;
            case 3:
                attackValue = 7;
                plusAttackValue = 10;
                break;
            default:
                throw new IllegalArgumentException("Invalid level: " + level);
        }
    }

    @Override
    public boolean execute(Player me, Player target) {
        Map<String, Buff> buffs = me.getBuffs();
        // 灵气是否充足
        if (buffs.containsKey("灵气")){
            LingQi lingQi = (LingQi) buffs.get("灵气");
            if (lingQi.getValue() >= lingQiCost){
                lingQi.decrease(lingQiCost);
                // 保存玩家的状态
                targetInitialHP = target.getHealth();

                me.attack(target, attackValue);
                if (target.getHealth() < targetInitialHP) JiShang(me, target); // 击伤效果是否触发
                return true;
            } else lingQi.increase(1); // 灵气不足,执行失败,此回合仅+1点灵气
        } else buffs.put("灵气",new LingQi(1)); // 灵气不足,执行失败,此回合仅+1点灵气
        return false;
    }


    @Override
    public void JiShang(Player me, Player target) {
        me.attack(target,plusAttackValue);
    }
}
