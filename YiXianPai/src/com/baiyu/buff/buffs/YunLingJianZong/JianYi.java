package com.baiyu.buff.buffs.YunLingJianZong;

import com.baiyu.buff.Buff;
import com.baiyu.buff.Effectable;

public class JianYi extends Buff implements Effectable {
    private String name = "剑意";
    private int jianYiValue;
    private boolean alive;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JianYi(int jianYiValue) {
        this.jianYiValue = jianYiValue;
        this.alive = true;
    }

    @Override
    public String toString() {
        return "JianYi{" +
                "jianYiValue=" + jianYiValue +
                '}';
    }

    @Override
    public void setValue(int jianYiValue) {
        this.jianYiValue = jianYiValue;
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
    public void increase(int amount) {
        this.jianYiValue += amount;
    }

    @Override
    public void decrease(int amount) {
        this.jianYiValue -= amount;
    }

    @Override
    public int getValue() {
        return this.jianYiValue;
    }

    @Override
    // 剑意触发效果
    public int effect(int damage){
        damage = damage+getValue();
        this.alive = false;
        return damage;
    }
}
