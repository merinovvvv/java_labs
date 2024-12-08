package model.visitors;

import iterator.Iterator;
import model.Set;
import visitor.Visitor;

public class UnionVisitor<T> implements Visitor<T> {

    private final Set<T> set;
    private final Set<T> unitedSet;

    public UnionVisitor(Set<T> set) {
        this.set = set;
        unitedSet = new Set<>();
    }

    @Override
    public void visit(Set<? extends T> otherSet) {
        unitedSet.getList().addAll(set.getList());
        Iterator<? extends T> iterator = otherSet.createIterator();
        while(!iterator.isDone()) {
            if (!unitedSet.getList().contains(iterator.currentItem())) {
                unitedSet.getList().add(iterator.currentItem());
            }
            iterator.next();
        }
    }

    public Set<T> getResult() {
        return unitedSet;
    }
}
