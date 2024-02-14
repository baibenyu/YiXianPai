package com.baiyu.frame;

import com.baiyu.buff.Buff;
import com.baiyu.buff.Effectable;
import com.baiyu.card.Card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.min;

// 玩家类
public class Player {
    private String name; // 玩家姓名
    private int health; // 生命值
    private int realm; // 境界:练气0,筑基1,金丹2,元婴3,化神4,返虚5
    private int cultivation; // 修为值
    private ArrayList<Card> cards = new ArrayList<>(); // 卡组
    private Map<String,Buff> buffs = new HashMap<>(); // 游戏过程中产生的各种增益,减益的buff
    private int cardsLength;
    private int defenceValue; // 防值
    private int currentCardId; // 当前遍历到卡组中的第几张牌
    private int defenceRate = 2;

    // 构造函数
    public Player(String name, int health, int cultivation, int cardsLength) {
        this.name = name;
        this.health = health;
        this.cultivation = cultivation;
        this.cardsLength = cardsLength;
    }


    public Map<String,Buff> getBuffs() {
        return buffs;
    }

    public void setBuffs(Map<String,Buff> buffs) {
        this.buffs = buffs;
    }

    public int getCurrentCardId() {
        return currentCardId;
    }

    public void setCurrentCardId(int currentCardId) {
        this.currentCardId = currentCardId;
    }

    public void nextCard() {
        setCurrentCardId((getCurrentCardId() + 1) % cardsLength); // 卡组循环进行,不可能超出卡组长度
    }

    public Card getCurrentCard() {
        return cards.get(currentCardId);
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

    // 获取玩家姓名
    public String getName() {
        return name;
    }

    // 获取生命值
    public int getHealth() {
        return health;
    }

    // 设置生命值
    public void setHealth(int health) {
        this.health = health;
    }

    // 我方数值buff
    public int ourNumericalBuff(int attackValue){
        if (buffs.containsKey("剑意")){
            attackValue = ((Effectable) buffs.get("剑意")).effect(attackValue);
        }
        if (buffs.containsKey("加攻")){
            attackValue = ((Effectable) buffs.get("加攻")).effect(attackValue);
        }
        return attackValue;
    }
    // 我方数值倍率buff
    public int ourNumericalMultiplicationBuff(int attackRate){
        return attackRate;
    }
    // 敌方数值buff
    public int targetNumericalBuff(Player target,int attackValue){
        Map<String,Buff> targetBuffs = target.getBuffs();
        if (targetBuffs.containsKey("防")){
            Buff defense = targetBuffs.get("防");
            int offsetValue = min(attackValue, defense.getValue()); // 被防抵消的攻
            defense.decrease(offsetValue); // 扣除被攻击的防
            attackValue -= offsetValue;// 剩余的攻
        }
        return attackValue;
    }
    // 敌方数值倍率buff
    public int targetNumericalMultiplicationBuff(Player target,int attackRate){
        Map<String,Buff> targetBuffs = target.getBuffs();
        return attackRate;
    }



    public void attack(Player target, int attackValue) {
        int attackRate = 1; // 伤害比率,初始不受影响

        // 数值buff结算
        attackValue = ourNumericalBuff(attackValue);
        attackRate = ourNumericalMultiplicationBuff(attackRate);
        attackValue = targetNumericalBuff(target,attackValue);
        attackRate = targetNumericalMultiplicationBuff(target,attackRate);



        // 特殊效果buff
        int damage = attackValue;

        target.setHealth(target.getHealth() - damage); // 扣除生命值
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", buffs=" + buffs +
                ", currentCardId=" + currentCardId +
                '}';
    }
}

