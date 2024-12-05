package model.visitors;

import model.Set;
import visitor.Visitor;

public class DifferenceVisitor<T> implements Visitor<T> {

    private final Set<T> set;

    public DifferenceVisitor(Set<T> set) {
        this.set = set;
    }

    @Override
    public Set<T> visit(Set<? extends T> otherSet) {
        Set<T> resultSet = new Set<>(set);
        resultSet.difference(otherSet);
        return resultSet;
    }
}
