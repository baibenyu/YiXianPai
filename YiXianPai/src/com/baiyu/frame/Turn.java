package com.baiyu.frame;

import com.baiyu.buff.Buff;
import com.baiyu.card.Card;

import java.util.ArrayList;
import java.util.Map;

public class Turn {
    public Turn() {
    }

    public void start(Player me, Player target, Card card) {
        beforeTurn(me, target);
        duringTurn(me, target, card);
        afterTurn(me, target);
    }

    // 回合开始前
    public void beforeTurn(Player me, Player target) {
        if (me.getDefenceValue() > 0)
            me.setDefenceValue(me.getDefenceValue() / me.getDefenceRate());// 默认情况下,防值会在回合开始时减半
    }

    // 回合进行中
    public void duringTurn(Player me, Player target, Card card) {
        card.execute(me, target);
        eliminateDeadBuffs(me,target);
    }

    private void eliminateDeadBuffs(Player me, Player target) {
        Map<String,Buff> buffs = me.getBuffs();
        // 如果已经死亡,消除该buff
        for(Map.Entry<String,Buff> buffEntry: buffs.entrySet()){
            String key = buffEntry.getKey();
            Buff value = buffEntry.getValue();
            if (!value.isAlive()) buffs.remove(key);
        }
    }

    // 回合结束时
    public void afterTurn(Player me, Player target) {

    }
}
