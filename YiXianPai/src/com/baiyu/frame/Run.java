package com.baiyu.frame;

import com.baiyu.card.Card;
import com.baiyu.card.cards.YunLingJianZong.LianQi.*;
import com.baiyu.frame.Player;
import com.baiyu.frame.Turn;

import java.util.ArrayList;

public class Run {
    private static final int cardsLength = 5; // "我"可用的卡组长度

    public static void run() {
        // 创建"我"玩家并添加卡组
        Player p1 = new Player("我", 43, 8, cardsLength);
        ArrayList<Card> p1Cards = p1.getCards();

        ArrayList<Card> p1AllCards = new ArrayList<>(); // 存储所有可选的卡牌
        p1AllCards.add(new JianPi(1));
        p1AllCards.add(new JianDang(1));
        p1AllCards.add(new JianDang(2));
        p1AllCards.add(new FeiYaJian(3));
        p1AllCards.add(new ZhouFengJian(2));


        // 创建"对手"玩家并添加卡牌
        Player p2 = new Player("对手", 46, 9, 5);
        ArrayList<Card> p2Cards = p2.getCards();
        p2Cards.add(new HuShenLingQi(2));
        p2Cards.add(new JianDang(1));
        p2Cards.add(new FeiYaJian(1));
        p2Cards.add(new JianDang(1));
        p2Cards.add(new FeiYaJian(1));

        // 尝试所有卡牌组合,并尝试所有组合的所有排列
        selectFrom(0, p1AllCards, p1Cards, p1, p2);

    }

    // 从n张卡中选m张组成"我"的卡组
    public static void selectFrom(int cur, ArrayList<Card> allCards, ArrayList<Card> p1Cards, Player me, Player target) {
        if (p1Cards.size() + allCards.size() - cur < cardsLength) return;
        if (p1Cards.size() == cardsLength) {
            permute(0, p1Cards, me, target);
            return;
        }
        p1Cards.add(allCards.get(cur));
        selectFrom(cur + 1, allCards, p1Cards, me, target);
        p1Cards.removeLast();
        selectFrom(cur + 1, allCards, p1Cards, me, target);
    }

    public static Player createNewPlayerWithCards(String name, int health, int cultivation, ArrayList<Card> inputCards) {
        // 创建新的 Player 对象
        Player newPlayer = new Player(name, health, cultivation, cardsLength);
        // 将传入的卡组加入到新对象的卡组中
        newPlayer.getCards().addAll(inputCards);
        return newPlayer;
    }


    public static void permute(int cur, ArrayList<Card> cards, Player me, Player target) {
        if (cur == cardsLength - 1) {
            // 创建新的玩家实例
            int p1cultivation = me.getCultivation();
            int p2cultivation = target.getCultivation();
            int p1health = me.getHealth();
            int p2health = target.getHealth();
            Player p1 = createNewPlayerWithCards(me.getName(), p1health, p1cultivation, cards);
            Player p2 = createNewPlayerWithCards(target.getName(), p2health, p2cultivation, target.getCards());

            // 打印"我"使用的卡组
            for (int i = 0; i < cardsLength; i++) {
                System.out.print(cards.get(i).getLevel() + "级" + cards.get(i).getName() + " ");
            }
            System.out.println();

            // 根据修为决定谁先出手
            Player currentPlayer = p1;
            Player nextPlayer = p2;
            if (p1cultivation < p2cultivation || p1cultivation == p2cultivation && p1health < p2health) {
                currentPlayer = p2;
                nextPlayer = p1;
            }

            int cnt = 1; // 回合数
            boolean isDead = false; // 是否有玩家的生命值耗尽
            // 开始模拟
            while (cnt <= 64) {
                Turn turn = new Turn();
                // 一方先行动
                System.out.println("回合" + cnt);
                if (judge(p1, p2, currentPlayer, nextPlayer, turn)) {
                    isDead = true;
                    break;
                }
                cnt++;

                // 一方后行动
                System.out.println("回合" + cnt);
                if (judge(p1, p2, nextPlayer, currentPlayer, turn)) {
                    isDead = true;
                    break;
                }
                cnt++;
            }
            // 超出64回合都没决出胜负,那么生命值多的那一方获胜
            if (!isDead) {
                if (p1.getHealth() > p2.getHealth()) System.out.println("\033[32m你赢了\033[0m");
                else System.out.println("\033[31m你输了!\033[0m");
            }
        }
        for (int i = cur; i < cardsLength; i++) {
            swap(cur, i, cards);
            permute(cur + 1, cards, me, target);
            swap(cur, i, cards); // 还原
        }
    }

    private static boolean judge(Player p1, Player p2, Player currentPlayer, Player nextPlayer, Turn turn) {

        turn.start(currentPlayer, nextPlayer, currentPlayer.getCurrentCard());
        System.out.println(currentPlayer.toString());
        System.out.println(nextPlayer.toString());


        if (p1.getHealth() < 0 || p2.getHealth() < 0) {
            if (p1.getHealth() < 0) System.out.println("\033[31m你输了!\033[0m");
            else System.out.println("\033[32m你赢了\033[0m");
            return true;
        }
        return false;
    }

    private static void swap(int i, int j, ArrayList<Card> cards) {
        Card temp_c = cards.get(i);
        cards.set(i, cards.get(j));
        cards.set(j, temp_c);
    }

}
