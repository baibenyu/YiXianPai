package com.baiyu.buff.buffs;

import com.baiyu.buff.Buff;

public class LingQi extends Buff {
    private String name = "灵气";
    private int lingQiValue;
    private boolean alive;

    public LingQi(int lingQiValue) {
        this.lingQiValue = lingQiValue;
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
        lingQiValue += value;
    }

    @Override
    public void decrease(int value) {
        lingQiValue -= value;
        if (value <= 0) alive = false;
    }

    @Override
    public int getValue() {
        return lingQiValue;
    }

    @Override
    public void setValue(int value) {
        lingQiValue = value;
    }

    @Override
    public String toString() {
        return "LingQi{" +
                "lingQiValue=" + lingQiValue +
                '}';
    }
}
