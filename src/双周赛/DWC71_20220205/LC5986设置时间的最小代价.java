package 双周赛.DWC71_20220205;

/**
 * @author Tomoyo  email:amedeusmaho@163.com
 * @create 2022-02-06 20:26
 * 模拟、分类讨论
 */
public class LC5986设置时间的最小代价 {
    int cur = 0;

    public int minCostSetTime(int startAt, int moveCost, int pushCost, int targetSeconds) {
        int minute = targetSeconds / 60, second = targetSeconds % 60;
        if (minute >= 99) {
            minute = 99;
            second = second + 60;
        }
        if (minute == 0 && second < 10) return startAt == second ? pushCost : pushCost + moveCost;
        this.cur = startAt; // 当前键位数字
        int minCost = minuteCost(moveCost, pushCost, minute);
        //System.out.println(minCost + " " + minute);
        minCost += secondCost(moveCost, pushCost, second);
        if (minute > 0 && second <= 39) {
            minute--;
            second += 60;
            this.cur = startAt;
            minCost = Math.min(minCost, minuteCost(moveCost, pushCost, minute) + secondCost(moveCost, pushCost, second));
        }
        return minCost;
    }

    private int minuteCost(int moveCost, int pushCost, int minute) {
        int cost = 0;
        // 当minute为0时直接返回0, 前导零
        if (minute == 0) return cost;
        if (minute > 0 && minute < 10) {
            cost += pushCost;
            cost += cur == minute ? 0 : moveCost;
            cur = minute;
            return cost;
        }
        cost += pushCost;
        cost += cur == minute / 10 ? 0 : moveCost;
        cur = minute / 10;
        cost += pushCost;
        cost += cur == minute % 10 ? 0 : moveCost;
        cur = minute % 10;
        return cost;
    }

    private int secondCost(int moveCost, int pushCost, int second) {
        int cost = 0;
        if (second == 0) {
            cost += pushCost * 2;
            cost += cur == 0 ? 0 : moveCost;
            return cost;
        }
        if (second > 0 && second < 10) {
            cost += cur == 0 ? 0 : moveCost;
            cost += pushCost * 2 + moveCost;
            return cost;
        }
        cost += pushCost;
        cost += cur == second / 10 ? 0 : moveCost;
        cur = second / 10;
        cost += pushCost;
        cost += cur == second % 10 ? 0 : moveCost;
        return cost;
    }
}
