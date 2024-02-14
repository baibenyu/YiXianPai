package com.baiyu.buff.buffs;

import com.baiyu.buff.Buff;

public class IgnoreDenfense extends Buff {
    private String name = "无视防御";
    private int value;
    private boolean alive;

    @Override
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public void setAlive(boolean flag) {
        this.alive = flag;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void increase(int value) {
        this.value += value;
    }

    @Override
    public void decrease(int value) {
        this.value -= value;
    }

    @Override
    public int getValue() {
        return this.value;
    }


}
