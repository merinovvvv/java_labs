package strategy;


import java.util.List;

public class FindMaxUsingStreamApi implements FindMaxStrategy{

    @Override
    public int max(List<Integer> tree) {
        return tree.stream()
                .filter(x -> x > 0)
                .max(Integer::compare)
                .orElse(0);
    }
}
