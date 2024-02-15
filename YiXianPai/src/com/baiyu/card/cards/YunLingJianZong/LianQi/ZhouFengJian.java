package com.baiyu.card.cards.YunLingJianZong.LianQi;

import com.baiyu.card.Card;
import com.baiyu.frame.Player;

import static java.lang.Math.min;

public class ZhouFengJian extends Card {
    private int attackValue; // 攻击值
    private int attackCount; // 攻击次数

    public ZhouFengJian(int level) {
        super(level,"骤风剑");
        initializeAttributes();
    }

    @Override
    public void initializeAttributes() {
        switch (level) {
            case 1:
                attackValue = 3;
                attackCount = 2;
                break;
            case 2:
                attackValue = 4;
                attackCount = 2;
                break;
            case 3:
                attackValue = 6;
                attackCount = 2;
                break;
            default:
                throw new IllegalArgumentException("Invalid level: " + level);
        }
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
