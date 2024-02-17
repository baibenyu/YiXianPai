package com.baiyu.card.cards.YunLingJianZong.LianQi;

import com.baiyu.buff.Buff;
import com.baiyu.buff.buffs.LingQi;
import com.baiyu.card.Card;
import com.baiyu.frame.Player;

import java.util.Map;

public class JuHuLingJian extends Card {
    private int attackValue; // 攻击值
    private int lingQiCost;

    public JuHuLingJian(int level) {
        super(level, "巨虎灵剑");
        initializeAttributes();
    }

    @Override
    public void initializeAttributes() {
        lingQiCost = 1;
        switch (level) {
            case 1:
                attackValue = 10;
                break;
            case 2:
                attackValue = 13;
                break;
            case 3:
                attackValue = 16;
                break;
            default:
                throw new IllegalArgumentException("Invalid level: " + level);
        }
    }

    @Override
    public boolean execute(Player me, Player target) {
        Map<String, Buff> buffs = me.getBuffs();
        // 灵气是否充足
        if (buffs.containsKey("灵气")) {
            LingQi lingQi = (LingQi) buffs.get("灵气");
            if (lingQi.getValue() >= lingQiCost) {
                lingQi.decrease(lingQiCost);
                me.attack(target, attackValue);
                return true;
            } else lingQi.increase(1);
        } else buffs.put("灵气", new LingQi(1)); // 灵气不足,执行失败,此回合仅+1点灵气
        return false;
    }
}
