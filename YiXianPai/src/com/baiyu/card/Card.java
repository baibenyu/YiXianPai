package com.baiyu.card;

import com.baiyu.frame.Player;

// 卡牌父类
public abstract class Card {
    public abstract boolean execute(Player me, Player target);
    public abstract String getName();
    public abstract int getLevel();

}

