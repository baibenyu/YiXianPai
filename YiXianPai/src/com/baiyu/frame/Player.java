package com.baiyu.frame;

import com.baiyu.card.Card;

import java.util.ArrayList;

// 玩家类
public class Player {
    private String name; // 玩家姓名
    private int health; // 生命值
    private int realm; // 境界:练气0,筑基1,金丹2,元婴3,化神4,返虚5
    private int cultivation; // 修为值
    private ArrayList<Card> cards = new ArrayList<>(); // 卡组
    private int cardsLength;
    private int defenceValue; // 防值
    private int swordPower; // 剑意值
    private int LingQi; // 灵气值
    private int currentCardId; // 当前遍历到卡组中的第几张牌
    private int defenceRate = 2;

    // 构造函数
    public Player(String name, int health, int cultivation, int cardsLength) {
        this.name = name;
        this.health = health;
        this.cultivation = cultivation;
        this.cardsLength = cardsLength;
    }

    public int getCurrentCardId() {
        return currentCardId;
    }

    public void setCurrentCardId(int currentCardId) {
        this.currentCardId = currentCardId % cardsLength; // 卡组循环进行,不可能超出卡组长度
    }

    public Card getCurrentCard() {
        return cards.get(currentCardId);
    }

    public int getDefenceRate() {
        return defenceRate;
    }

    public void setDefenceRate(int defenceRate) {
        this.defenceRate = defenceRate;
    }


    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public int getRealm() {
        return realm;
    }

    public void setRealm(int realm) {
        this.realm = realm;
    }

    public int getCultivation() {
        return cultivation;
    }

    public void setCultivation(int cultivation) {
        this.cultivation = cultivation;
    }


    public int getLingQi() {
        return LingQi;
    }

    public void setLingQi(int lingQi) {
        LingQi = lingQi;
    }


    public int getDefenceValue() {
        return defenceValue;
    }

    public void setDefenceValue(int defenceValue) {
        this.defenceValue = defenceValue;
    }

    // 获取玩家姓名
    public String getName() {
        return name;
    }

    // 获取生命值
    public int getHealth() {
        return health;
    }

    // 获取剑意属性
    public int getSwordPower() {
        return swordPower;
    }

    // 设置生命值
    public void setHealth(int health) {
        this.health = health;
    }

    // 设置剑意属性
    public void setSwordPower(int swordPower) {
        this.swordPower = swordPower;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", defenceValue=" + defenceValue +
                ", swordPower=" + swordPower +
                ", LingQi=" + LingQi +
                ", currentCardId=" + currentCardId +
                '}';
    }

}

