package com.baiyu.buff.buffs.YunLingJianZong;

import com.baiyu.buff.Buff;
import com.baiyu.buff.Effectable;

public class JianYi extends Buff implements Effectable {
    private String name = "剑意";

    public JianYi(int value) {
        super(value);
    }

    @Override
    public String toString() {
        return "JianYi{" +
                "jianYiValue=" + value +
                '}';
    }

    @Override
    // 剑意触发效果
    public int effect(int damage) {
        damage = damage + getValue();
        this.alive = false;
        return damage;
    }
}
