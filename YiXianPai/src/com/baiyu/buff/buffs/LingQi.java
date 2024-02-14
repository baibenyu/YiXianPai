package com.baiyu.buff.buffs;

import com.baiyu.buff.Buff;

public class LingQi extends Buff {
    private String name = "灵气";
    private int value;
    private boolean alive;

    public LingQi(int lingQiValue) {
        this.value = lingQiValue;
        alive = true;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public void setAlive(boolean flag) {
        alive = flag;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void increase(int value) {
        value += value;
    }

    @Override
    public void decrease(int value) {
        this.value -= value;
        if (this.value <= 0) alive = false;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void setValue(int value) {
        value = value;
    }

    @Override
    public String toString() {
        return "LingQi{" +
                "lingQiValue=" + value +
                '}';
    }
}
