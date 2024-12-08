package model.visitors;

import iterator.Iterator;
import model.Set;
import visitor.Visitor;

public class IntersectionVisitor<T> implements Visitor<T> {

    private final Set<T> set;
    private final Set<T> intersectionSet;

    public IntersectionVisitor(Set<T> set) {
        this.set = set;
        intersectionSet = new Set<>();
    }

    @Override
    public void visit(Set<? extends T> otherSet) {
        Iterator<? extends T> iterator = otherSet.createIterator();
        while(!iterator.isDone()) {
            if (set.getList().contains(iterator.currentItem())) {
                intersectionSet.add(iterator.currentItem());
            }
            iterator.next();
        }
    }

    public Set<T> getResult() {
        return intersectionSet;
    }
}
