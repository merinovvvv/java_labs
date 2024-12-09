package visitor;

import iterator.Iterator;
import model.SetModel;

public class UnionVisitor<T> implements Visitor<T> {

    private final SetModel<T> set;
    private final SetModel<T> unitedSet;

    public UnionVisitor(SetModel<T> set) {
        this.set = set;
        unitedSet = new SetModel<>();
    }

    @Override
    public void visit(SetModel<? extends T> otherSet) {
        unitedSet.getList().addAll(set.getList());
        Iterator<? extends T> iterator = otherSet.createIterator();
        while(!iterator.isDone()) {
            if (!unitedSet.getList().contains(iterator.currentItem())) {
                unitedSet.getList().add(iterator.currentItem());
            }
            iterator.next();
        }
    }

    public SetModel<T> getResult() {
        return unitedSet;
    }
}
