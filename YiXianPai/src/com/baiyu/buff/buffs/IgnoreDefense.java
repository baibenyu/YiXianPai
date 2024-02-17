package com.baiyu.buff.buffs;

import com.baiyu.buff.Buff;

public class IgnoreDefense extends Buff {
    private String name;

    public IgnoreDefense(int value){
        super(value,"无视防御");
    }

    @Override
    public String toString() {
        return "IgnoreDefense{" +
                "value=" + value +
                '}';
    }
}
