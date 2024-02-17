package com.baiyu.card;

import com.baiyu.buff.Buff;
import com.baiyu.buff.buffs.Defense;
import com.baiyu.buff.buffs.IgnoreDefense;
import com.baiyu.buff.buffs.LingQi;
import com.baiyu.buff.buffs.YunLingJianZong.JianYi;
import com.baiyu.frame.Player;

import java.util.Map;

// 卡牌父类
public abstract class Card {
    public String name;
    public int level;

    public Card(int level, String name) {
        this.level = level;
        this.name = name;
    }

    public abstract void initializeAttributes();

    public abstract boolean execute(Player me, Player target);

    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void addBuff(Map<String, Buff> buffs, String buffName, int value) {

        if (buffs.containsKey(buffName)) {
            Buff buff = buffs.get(buffName);
            if (!buff.isAlive()) {
                buff.setAlive(true);
                buff.setValue(value);
            } else buff.increase(value);
        } else {
            Buff buff = null;
            switch (buffName) {
                case "灵气":
                    buff = new LingQi(value);
                    break;
                case "剑意":
                    buff = new JianYi(value);
                    break;
                case "无视防御":
                    buff = new IgnoreDefense(value);
                    break;
                case "防":
                    buff = new Defense(value);
                    break;
                default:
                    // 默认情况，当输入不匹配任何选项时执行
                    System.out.println("输入无效,无该buff");
                    break;
            }
            buffs.put(buffName, buff);
        }
    }

}

