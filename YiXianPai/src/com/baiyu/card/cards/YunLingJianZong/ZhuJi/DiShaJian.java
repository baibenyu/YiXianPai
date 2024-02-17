package com.baiyu.card.cards.YunLingJianZong.ZhuJi;

import com.baiyu.buff.Buff;
import com.baiyu.card.Card;
import com.baiyu.entry.JiShang;
import com.baiyu.frame.Player;

import java.util.Map;

public class DiShaJian extends Card implements JiShang {
    private int attackValue; // 攻击值
    private int initialHealth;

    public DiShaJian(int level) {
        super(level, "地煞剑");
        initializeAttributes(level);
    }

    @Override
    public void initializeAttributes(int level) {
        switch (level) {
            case 1:
                attackValue = 8;
                break;
            case 2:
                attackValue = 11;
                break;
            case 3:
                attackValue = 14;
                break;
            default:
                throw new IllegalArgumentException("Invalid level: " + level);
        }
    }

    @Override
    public boolean execute(Player me, Player target) {
        // 获取对方的初始生命值
        initialHealth = target.getHealth();

        // 对目标造成攻击
        me.attack(target, attackValue);

        // 计算伤害
        int damage = initialHealth - target.getHealth();

        // 如果伤害导致对方生命值减少，则触发击伤效果
        if (damage > 0) JiShang(me,target);

        return true;
    }

    @Override
    public void JiShang(Player me, Player target) {
        // 增加防御值
        me.addBuff( "防", initialHealth - target.getHealth());
    }
}
