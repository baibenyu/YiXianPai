package com.baiyu.card.cards.YunLingJianZong.ZhuJi;

import com.baiyu.buff.Buff;
import com.baiyu.buff.buffs.LingQi;
import com.baiyu.buff.buffs.YunLingJianZong.JianYi;
import com.baiyu.card.Card;
import com.baiyu.card.cards.YunLingJianZong.LianQi.JianPi;
import com.baiyu.frame.Player;

import java.util.Map;

public class LingXiJianZhen extends Card {
    private int defenseIncrease; // 防御增加值

    public LingXiJianZhen(int level) {
        super(level, "灵犀剑阵");
        initializeAttributes(level);
    }

    @Override
    public void initializeAttributes(int level) {
        switch (level) {
            case 1:
                defenseIncrease = 9;
                break;
            case 2:
                defenseIncrease = 14;
                break;
            case 3:
                defenseIncrease = 19;
                break;
            default:
                throw new IllegalArgumentException("Invalid level: " + level);
        }
    }

    @Override
    public boolean execute(Player me, Player target) {
        // 增加防御值
        Map<String, Buff> buffs = me.getBuffs();
        me.addBuff("防", defenseIncrease);

        // 消耗所有剑意并增加等量的灵气
        if (buffs.containsKey("剑意")) {
            int swordIntent = buffs.get("剑意").getValue();
            buffs.get("剑意").decrease(swordIntent);
            me.addBuff("灵气", swordIntent);
        }
        return true;
    }


}
