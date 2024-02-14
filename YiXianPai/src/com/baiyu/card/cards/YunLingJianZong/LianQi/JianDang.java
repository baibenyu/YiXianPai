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
    private String name = "剑挡";
    private int level;

    public JianDang(int defenceValue, int jianYi, int level) {
        this.defenceValue = defenceValue;
        this.jianYi = jianYi;
        this.level = level;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public boolean execute(Player me, Player target) {
        Map<String, Buff> buffs = me.getBuffs();
        // 增加防值
        if (buffs.containsKey("防")) buffs.get("防").increase(defenceValue);
        else buffs.put("防", new Defense(defenceValue));
        // 增加人物剑意值
        if (buffs.containsKey("剑意")) buffs.get("剑意").increase(jianYi);
        else buffs.put("剑意", new JianYi(jianYi));
        // 增加牌序
        me.nextCard(); // 执行成功,牌序+1
        return true;
    }
}
