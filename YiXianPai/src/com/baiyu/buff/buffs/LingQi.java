package com.baiyu.buff.buffs;

import com.baiyu.buff.Buff;

public class LingQi extends Buff {
    private String name = "灵气";

    public LingQi(int value) {
        super(value);
    }

    @Override
    public String toString() {
        return "LingQi{" +
                "lingQiValue=" + value +
                '}';
    }
}
