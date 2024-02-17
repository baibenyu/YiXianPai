package com.baiyu.frame;

import com.baiyu.buff.Buff;
import com.baiyu.buff.Effectable;
import com.baiyu.buff.buffs.Defense;
import com.baiyu.buff.buffs.IgnoreDefense;
import com.baiyu.buff.buffs.LingQi;
import com.baiyu.buff.buffs.YunLingJianZong.JianYi;
import com.baiyu.card.Card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.max;
import static java.lang.Math.min;

// 玩家类
public class Player {
    private String name; // 玩家姓名
    private int health; // 生命值
    private int maximumHealth; // 生命值上限
    private int realm; // 境界:练气0,筑基1,金丹2,元婴3,化神4,返虚5
    private int cultivation; // 修为值
    private ArrayList<Card> cards = new ArrayList<>(); // 卡组
    private Map<String, Buff> buffs = new HashMap<>(); // 游戏过程中产生的各种增益,减益的buff
    private int cardsLength;// 卡组长度
    private int currentCardId; // 当前遍历的卡牌id
    private int previousUsedCardId; // 上一张使用过的卡牌id
    private int kuangJianCount; // 狂剑使用的次数

    private int step; // 卡牌遍历的步频和方向
    private int defenceRate;

    public int getPreviousUsedCardId() {
        return previousUsedCardId;
    }

    public void setPreviousUsedCardId(int previousUsedCardId) {
        this.previousUsedCardId = previousUsedCardId;
    }


    // 构造函数
    public Player(String name, int health, int cultivation, int cardsLength) {
        this.name = name;
        this.health = health;
        this.cultivation = cultivation;
        this.cardsLength = cardsLength;
        this.step = 1;
    }

    public int getKuangJianCount() {
        return kuangJianCount;
    }

    public void setKuangJianCount(int kuangJianCount) {
        this.kuangJianCount = kuangJianCount;
    }

    public int getMaximumHealth() {
        return maximumHealth;
    }

    public void setMaximumHealth(int maximumHealth) {
        this.maximumHealth = maximumHealth;
    }

    public Map<String, Buff> getBuffs() {
        return buffs;
    }

    public void setBuffs(Map<String, Buff> buffs) {
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

    public void previousCard() {
        setCurrentCardId((getCurrentCardId() + cardsLength - 1) % cardsLength);
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

    public void increaseHealth(int value) {
        this.health += value;
        if (this.health > maximumHealth) setHealth(maximumHealth);
    }

    public void decreaseHealth(int value) {
        this.health -= value;
    }


    // 我方数值buff
    public int ourNumericalBuff(int attackValue) {
        if (buffs.containsKey("剑意")) {
            attackValue = ((Effectable) buffs.get("剑意")).effect(attackValue);
        }
        if (buffs.containsKey("加攻")) {
            attackValue = ((Effectable) buffs.get("加攻")).effect(attackValue);
        }
        return attackValue;
    }

    // 我方数值倍率buff
    public int ourNumericalMultiplicationBuff(int attackRate) {
        return attackRate;
    }

    // 敌方数值buff
    public int targetNumericalBuff(Player target, int attackValue) {
        Map<String, Buff> targetBuffs = target.getBuffs();

        // 无视防御会跳过防御值的结算
        if (buffs.containsKey("无视防御") && buffs.get("无视防御").isAlive()) {
            buffs.get("无视防御").decrease(1);
        } else {
            if (targetBuffs.containsKey("防") && targetBuffs.get("防").isAlive()) {
                Buff defense = targetBuffs.get("防");
                int offsetValue = min(attackValue, defense.getValue()); // 被防抵消的攻
                defense.decrease(offsetValue); // 扣除被攻击的防
                attackValue -= offsetValue;// 剩余的攻
            }
        }
        return attackValue;
    }

    // 敌方数值倍率buff
    public int targetNumericalMultiplicationBuff(Player target, int attackRate) {
        Map<String, Buff> targetBuffs = target.getBuffs();
        return attackRate;
    }


    public void attack(Player target, int attackValue) {
        int attackRate = 1; // 伤害比率,初始不受影响

        // 数值buff结算
        attackValue = ourNumericalBuff(attackValue);
        attackRate = ourNumericalMultiplicationBuff(attackRate);
        attackValue = targetNumericalBuff(target, attackValue);
        attackRate = targetNumericalMultiplicationBuff(target, attackRate);


        // 特殊效果buff
        int damage = attackValue;

        target.decreaseHealth(damage); // 扣除生命值
    }

    public void addBuff(String buffName, int value) {
        if (buffs.containsKey(buffName)) {
            Buff buff = buffs.get(buffName);
            if (!buff.isAlive()) {
                buff.setAlive(true);
                buff.setValue(value);
            } else buff.increase(value);
        } else {
            Buff buff = null;
            switch (buffName) {
                case "灵气":
                    buff = new LingQi(value);
                    break;
                case "剑意":
                    buff = new JianYi(value);
                    break;
                case "无视防御":
                    buff = new IgnoreDefense(value);
                    break;
                case "防":
                    buff = new Defense(value);
                    break;
                default:
                    // 默认情况，当输入不匹配任何选项时执行
                    System.out.println("输入无效,无该buff");
                    break;
            }
            buffs.put(buffName, buff);
        }
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

    public Card getPreviousUsedCard() {
        return cards.get(previousUsedCardId);
    }
}

