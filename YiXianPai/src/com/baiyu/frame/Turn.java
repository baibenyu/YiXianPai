package com.baiyu.frame;

import com.baiyu.buff.Buff;
import com.baiyu.card.Card;
import com.baiyu.buff.buffs.Defense;

import java.util.ArrayList;
import java.util.Iterator;
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
        // 默认情况下,防值会在回合开始时减半(根绝卡牌效果减少的值不同)
        if (me.getBuffs().containsKey("防")){
            Defense defense = (Defense) me.getBuffs().get("防");
            defense.decrease(defense.getValue()-defense.getValue()/defense.getConsumptionRate());
        }
        eliminateDeadBuffs(me,target);
    }

    // 回合进行中
    public void duringTurn(Player me, Player target, Card card) {
        if(card.execute(me, target)) {
            // 执行成功,存储成功执行的卡牌id,并使得牌序+1
            me.setPreviousUsedCardId(me.getCurrentCardId());
            me.nextCard();
        }
        eliminateDeadBuffs(me,target);
    }

    private void eliminateDeadBuffs(Player me, Player target) {
        Map<String,Buff> buffs = me.getBuffs();
        // 如果已经死亡,消除该buff
        Iterator<Map.Entry<String, Buff>> iterator = buffs.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Buff> buffEntry = iterator.next();
            String key = buffEntry.getKey();
            Buff value = buffEntry.getValue();
            if (!value.isAlive()) {
                iterator.remove(); // 使用迭代器的 remove() 方法安全地删除元素
            }
        }

        iterator = target.getBuffs().entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Buff> buffEntry = iterator.next();
            String key = buffEntry.getKey();
            Buff value = buffEntry.getValue();
            if (!value.isAlive()) {
                iterator.remove(); // 使用迭代器的 remove() 方法安全地删除元素
            }
        }

    }

    // 回合结束时
    public void afterTurn(Player me, Player target) {
        eliminateDeadBuffs(me,target);
    }
}
