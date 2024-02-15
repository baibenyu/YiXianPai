package com.baiyu.card;

import com.baiyu.frame.Player;

// 卡牌父类
public abstract class Card {
    public String name;
    public int level;

    public Card(int level,String name) {
        this.level = level;
        this.name = name;
    }

    public abstract void initializeAttributes();

    public abstract boolean execute(Player me, Player target);

    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}

