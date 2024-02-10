import com.baiyu.card.Card;
import com.baiyu.card.cards.YunLingJianZong.*;
import com.baiyu.frame.Player;
import com.baiyu.frame.Turn;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // 创建"我"玩家并添加卡组
        Player p1 = new Player("我", 43, 8);
        ArrayList<Card> p1Cards = p1.getCards();
        p1Cards.add(new JianPi(4, 1, 2, 1));
        p1Cards.add(new JianDang(4, 2, 1));
        p1Cards.add(new JianDang(5, 3, 2));
        p1Cards.add(new FeiYaJian(14, 1, 3));
        p1Cards.add(new ZhouFengJian(4, 2, 2));

        // 创建"对手"玩家并添加卡组
        Player p2 = new Player("对手", 46, 9);
        ArrayList<Card> p2Cards = p2.getCards();
        p2Cards.add(new HuShenLingQi(5, 2, 2));
        p2Cards.add(new JianDang(4, 2, 1));
        p2Cards.add(new FeiYaJian(8, 1, 1));
        p2Cards.add(new JianDang(4, 2, 1));
        p2Cards.add(new FeiYaJian(8, 1, 1));

        int[] nums = {1, 2, 3, 4, 5};
        permute(nums, 0, p1Cards, p1, p2);
    }

    public static Player createNewPlayerWithCards(String name, int health, int cultivation, ArrayList<Card> inputCards) {
        // 创建新的 Player 对象
        Player newPlayer = new Player(name, health, cultivation);

        // 复制除卡组之外的其他属性（假设其他属性都相同）
        // 设置剑意属性、防值、灵气值等其他属性...

        // 将传入的卡组加入到新对象的卡组中
        newPlayer.getCards().addAll(inputCards);

        return newPlayer;
    }


    public static void permute(int[] arr, int start, ArrayList<Card> cards, Player me, Player target) {
        if (start == arr.length - 1) {
            int cnt = 0;
            int p1cultivation = me.getCultivation();
            int p1health = me.getHealth();
            int p2health = target.getHealth();
            int p2cultivation = target.getCultivation();
            Player p1 = createNewPlayerWithCards(me.getName(), me.getHealth(), p1cultivation, cards);
            Player p2 = createNewPlayerWithCards(target.getName(), target.getHealth(), p2cultivation, target.getCards());
            // 打印我使用的卡组
            for (int i = 0; i < arr.length; i++) {
                System.out.print(cards.get(i).getLevel() + "级" + cards.get(i).getName() + " ");
            }
            System.out.println();

            Player currentPlayer = p1;
            Player nextPlayer = p2;
            if (p1cultivation < p2cultivation || p1cultivation == p2cultivation && p1health < p2health) {
                currentPlayer = p2;
                nextPlayer = p1;
            }

            while (cnt < 32) {
                Turn turn = new Turn();
                // 对手先行动
                turn.beforeTurn(currentPlayer, nextPlayer);
                turn.duringTurn(currentPlayer, nextPlayer, currentPlayer.nextCard());
                turn.afterTurn(currentPlayer, nextPlayer);
                // 己方行动
                turn.beforeTurn(nextPlayer, currentPlayer);
                turn.duringTurn(nextPlayer, currentPlayer, nextPlayer.nextCard());
                turn.afterTurn(nextPlayer, currentPlayer);

                if (p1.getHealth() < 0 || p2.getHealth() < 0) {
                    if (p1.getHealth() < 0) System.out.println("\033[31m你输了!\033[0m");
                    else System.out.println("\033[32m你赢了\033[0m");
                    break;
                }
                cnt++;
            }


        }
        for (int i = start; i < arr.length; i++) {
            swap(arr, start, i, cards);
            permute(arr, start + 1, cards, me, target);
            swap(arr, start, i, cards); // 还原
        }
    }

    private static void swap(int[] arr, int i, int j, ArrayList<Card> cards) {
        int temp = arr[i];
        Card temp_c = cards.get(i);
        arr[i] = arr[j];
        cards.set(i, cards.get(j));
        arr[j] = temp;
        cards.set(j, temp_c);
    }

}
