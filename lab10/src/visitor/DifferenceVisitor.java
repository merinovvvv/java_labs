package visitor;

import iterator.Iterator;
import model.Set;

public class DifferenceVisitor<T> implements Visitor<T> {

    private final Set<T> set;
    private final Set<T> differenceSet;

    public DifferenceVisitor(Set<T> set) {
        this.set = set;
        differenceSet = new Set<>();
    }

    @Override
    public void visit(Set<? extends T> otherSet) {
        Iterator<? extends T> iterator = set.createIterator();
        while(!iterator.isDone()) {
            if (!otherSet.getList().contains(iterator.currentItem())) {
                differenceSet.add(iterator.currentItem());
            }
            iterator.next();
        }
    }

    public Set<T> getResult() {
        return differenceSet;
    }
}
