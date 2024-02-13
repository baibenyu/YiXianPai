package com.baiyu.buff;

public abstract class Buff {
    public abstract boolean isAlive();
    public abstract void setAlive(boolean flag);

    public abstract String getName();

    // 增加数值
    public abstract void increase(int value);

    // 减少数值
    public abstract void decrease(int value);

//    // 效果
//    public abstract void effect();

    // 获取数值
    public abstract int getValue();

    // 设定数值
    public abstract void setValue(int value);

}
