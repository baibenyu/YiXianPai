package com.baiyu.buff.buffs;

import com.baiyu.buff.Buff;

public class LingQi extends Buff {
    private String name;

    public LingQi(int value) {
        super(value,"灵气");
    }

    @Override
    public String toString() {
        return "LingQi{" +
                "value=" + value +
                '}';
    }
}
