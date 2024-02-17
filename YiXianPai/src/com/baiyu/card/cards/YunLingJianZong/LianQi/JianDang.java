package com.baiyu.card.cards.YunLingJianZong.LianQi;

import com.baiyu.buff.Buff;
import com.baiyu.buff.buffs.Defense;
import com.baiyu.buff.buffs.LingQi;
import com.baiyu.buff.buffs.YunLingJianZong.JianYi;
import com.baiyu.card.Card;
import com.baiyu.frame.Player;

import java.util.Map;

public class JianDang extends Card {
    private int defenceValue; // 增加的防值
    private int jianYi; // 增加的剑意值

    public JianDang(int level) {
        super(level,"剑挡");
        initializeAttributes();
    }

    @Override
    public void initializeAttributes() {
        switch (level) {
            case 1:
                defenceValue = 4;
                jianYi = 2;
                break;
            case 2:
                defenceValue = 5;
                jianYi = 3;
                break;
            case 3:
                defenceValue = 6;
                jianYi = 4;
                break;
            default:
                throw new IllegalArgumentException("Invalid level: " + level);
        }
    }

    @Override
    public boolean execute(Player me, Player target) {
        Map<String, Buff> buffs = me.getBuffs();
        // 增加防值
        addBuff(buffs,"防",defenceValue);
        // 增加人物剑意值
        addBuff(buffs,"剑意",jianYi);
        return true;
    }
}
