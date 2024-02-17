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
        initializeAttributes(level);
    }

    @Override
    public void initializeAttributes(int level) {
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
        if (isLingQiEnough(buffs,lingQiCost)){
            // 保存玩家的状态
            targetInitialHP = target.getHealth();
            me.attack(target, attackValue);
            if (target.getHealth() < targetInitialHP) JiShang(me, target); // 击伤效果是否触发
            return true;
        }
        return false;
    }


    @Override
    public void JiShang(Player me, Player target) {
        me.attack(target,plusAttackValue);
    }
}
