package model.visitors;

import model.Set;
import visitor.Visitor;

public class IntersectionVisitor<T> implements Visitor<T> {

    private final Set<T> set;

    public IntersectionVisitor(Set<T> set) {
        this.set = set;
    }

    @Override
    public Set<T> visit(Set<? extends T> otherSet) {
        Set<T> resultSet = new Set<>(set);
        resultSet.intersect(otherSet);
        return resultSet;
    }
}
