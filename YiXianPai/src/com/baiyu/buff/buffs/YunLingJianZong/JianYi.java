package com.baiyu.buff.buffs.YunLingJianZong;

import com.baiyu.buff.Buff;
import com.baiyu.buff.Effectable;

public class JianYi extends Buff implements Effectable {
    private String name = "剑意";
    private int value;
    private boolean alive;



    public JianYi(int jianYiValue) {
        this.value = jianYiValue;
        this.alive = true;
    }

    @Override
    public String toString() {
        return "JianYi{" +
                "jianYiValue=" + value +
                '}';
    }

    public String getName() {
        return name;
    }
    @Override
    public void setValue(int jianYiValue) {
        this.value = jianYiValue;
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

    @Override
    // 剑意触发效果
    public int effect(int damage){
        damage = damage+getValue();
        this.alive = false;
        return damage;
    }
}
