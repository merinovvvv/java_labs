package visitor;

import iterator.Iterator;
import model.SetModel;

public class DifferenceVisitor<T> implements Visitor<T> {

    private final SetModel<T> set;
    private final SetModel<T> differenceSet;

    public DifferenceVisitor(SetModel<T> set) {
        this.set = set;
        differenceSet = new SetModel<>();
    }

    @Override
    public void visit(SetModel<? extends T> otherSet) {
        Iterator<? extends T> iterator = set.createIterator();
        while(!iterator.isDone()) {
            if (!otherSet.getList().contains(iterator.currentItem())) {
                differenceSet.add(iterator.currentItem());
            }
            iterator.next();
        }
    }

    public SetModel<T> getResult() {
        return differenceSet;
    }
}
