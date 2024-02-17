package com.baiyu.buff.buffs.YunLingJianZong;

import com.baiyu.buff.Buff;
import com.baiyu.buff.Effectable;

public class JianYi extends Buff implements Effectable {
    private String name ;

    public JianYi(int value) {
        super(value,"剑意");
    }

    @Override
    public String toString() {
        return "JianYi{" +
                "value=" + value +
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
