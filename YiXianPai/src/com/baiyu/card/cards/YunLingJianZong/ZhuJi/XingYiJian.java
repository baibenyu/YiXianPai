package com.baiyu.card.cards.YunLingJianZong.ZhuJi;

import com.baiyu.buff.Buff;
import com.baiyu.buff.buffs.LingQi;
import com.baiyu.card.Card;
import com.baiyu.entry.JiShang;
import com.baiyu.frame.Player;

import java.util.Map;

public class XingYiJian extends Card implements JiShang {
    private int attackValue; // 攻击值
    private int jianYiValue;
    private int targetInitialHP; // 对方在该卡牌执行之前的生命值
    private int lingQiCost;

    public XingYiJian(int level) {
        super(level, "形意剑");
        initializeAttributes(level);
    }

    @Override
    public void initializeAttributes(int level) {
        lingQiCost = 1;
        switch (level) {
            case 1:
                attackValue = 8;
                jianYiValue = 3;
                break;
            case 2:
                attackValue = 11;
                jianYiValue = 4;
                break;
            case 3:
                attackValue = 14;
                jianYiValue = 5;
                break;
            default:
                throw new IllegalArgumentException("Invalid level: " + level);
        }
    }

    @Override
    public boolean execute(Player me, Player target) {
        Map<String, Buff> buffs = me.getBuffs();
        // 灵气是否充足
        if (isLingQiEnough(buffs, lingQiCost)) {
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
        me.addBuff("剑意", jianYiValue);
    }
}
