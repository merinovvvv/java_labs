package strategy;

import java.util.List;

public class FindMaxNoStreamApi implements FindMaxStrategy{
    @Override
    public int max(List<Integer> tree) {
        int max = Integer.MIN_VALUE;
        for (int value : tree) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }
}
