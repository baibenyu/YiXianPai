package com.baiyu.buff.buffs;

import com.baiyu.buff.Buff;

public class Defense extends Buff {

    private String name = "防";
    private int value;
    private int offsettingRatio; // 攻能抵消几倍的防
    private int consumptionRate = 2; // 回合开始时,防自动除以几
    private boolean alive;

    public Defense(int value) {
        this.value = value;
        this.alive = true;
    }

    public int getOffsettingRatio() {
        return offsettingRatio;
    }

    public void setOffsettingRatio(int offsettingRatio) {
        this.offsettingRatio = offsettingRatio;
    }

    public int getConsumptionRate() {
        return consumptionRate;
    }

    public void setConsumptionRate(int consumptionRate) {
        this.consumptionRate = consumptionRate;
    }

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
        if (this.value <= 0 ) alive = false;
    }

    @Override
    public String toString() {
        return "Defense{" +
                "value=" + value +
                '}';
    }

    @Override
    public int getValue() {
        return this.value;
    }
}
