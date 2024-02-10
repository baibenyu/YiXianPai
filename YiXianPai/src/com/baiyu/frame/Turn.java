package com.baiyu.frame;

import com.baiyu.card.Card;

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
    }

    // 回合结束时
    public void afterTurn(Player me, Player target) {

    }
}
