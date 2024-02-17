package com.baiyu.card.cards.YunLingJianZong.ZhuJi;

import com.baiyu.card.Card;
import com.baiyu.entry.KuangJian;
import com.baiyu.frame.Player;

public class KuangJianYiShi extends Card implements KuangJian {
    private int baseAttack; // 基础攻击值
    private int kuangJianBonus; // 狂剑增加的攻击值

    public KuangJianYiShi(int level) {
        super(level, "狂剑·一式");
        initializeAttributes(level);
    }

    @Override
    public void initializeAttributes(int level) {
        switch (level) {
            case 1:
                baseAttack = 5;
                kuangJianBonus = 2;
                break;
            case 2:
                baseAttack = 8;
                kuangJianBonus = 3;
                break;
            case 3:
                baseAttack = 10;
                kuangJianBonus = 5;
                break;
            default:
                throw new IllegalArgumentException("Invalid level: " + level);
        }
    }

    @Override
    public boolean execute(Player me, Player target) {
        // 对目标造成攻击
        int cnt = me.getKuangJianCount();
        me.attack(target, baseAttack+kuangJianBonus*cnt);
        me.setKuangJianCount(cnt+1);
        return true;
    }

    @Override
    public void kuangJian(Player me, Player target) {
        this.baseAttack += kuangJianBonus; // 每使用过一次狂剑多几攻
    }
}
