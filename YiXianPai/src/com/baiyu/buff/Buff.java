package com.baiyu.buff;

public abstract class Buff {
    public String name;
    public int value; // buff的层数
    public boolean alive; // buff是否还存活

    public Buff(int value,String name) {
        this.value = value;
        this.name = name;
        this.alive = true;
    }

    public boolean isAlive() {
        return this.alive;
    }


    public void setAlive(boolean flag) {
        this.alive = flag;
    }


    public String getName() {
        return name;
    }


    public void increase(int value) {
        this.value += value;
    }


    public void decrease(int value) {
        this.value -= value;
        if (this.value <= 0) this.alive = false;
    }


    public int getValue() {
        return this.value;
    }


    public void setValue(int value) {
        this.value = value;
    }

}
