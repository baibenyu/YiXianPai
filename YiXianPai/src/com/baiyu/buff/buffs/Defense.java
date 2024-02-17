package com.baiyu.buff.buffs;

import com.baiyu.buff.Buff;

public class Defense extends Buff {

    private String name ;
    private int offsettingRatio; // 攻能抵消几倍的防
    private int consumptionRate; // 回合开始时,防自动除以几

    public Defense(int value) {
        super(value,"防");
        this.consumptionRate = 2;
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
    public String toString() {
        return "Defense{" +
                "value=" + value +
                '}';
    }

}
