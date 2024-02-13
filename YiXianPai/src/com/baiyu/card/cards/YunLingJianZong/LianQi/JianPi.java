package com.baiyu.card.cards.YunLingJianZong.LianQi;

import com.baiyu.buff.Buff;
import com.baiyu.buff.buffs.YunLingJianZong.JianYi;
import com.baiyu.card.Card;
import com.baiyu.frame.Player;

import java.util.Map;

import static java.lang.Math.min;

public class JianPi extends Card {
    private int level;
    private int attackValue; // 攻击值
    private int attackCount; // 攻击次数
    private int jianYi; // 增加的剑意值
    private int damage; // 己方单次理论攻击力
    private String name = "剑劈";

    public JianPi(int attackValue, int attackCount, int jianYi, int level) {
        this.attackValue = attackValue;
        this.attackCount = attackCount;
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
        // 攻击attackCount次
        for (int i = 0; i < attackCount; i++) {
            // 根据对方状态决定实际扣除的生命值
            me.attack(target, attackValue);
        }
        // 增加人物剑意值
        Map<String, Buff> buffs = me.getBuffs();
        if (buffs.containsKey("剑意")){
            Buff buff = buffs.get("剑意");
            buff.setAlive(true);
            buff.setValue(jianYi);
        } else buffs.put("剑意",new JianYi(jianYi));

        me.nextCard(); // 执行成功,牌序+1
        return true;
    }

}
