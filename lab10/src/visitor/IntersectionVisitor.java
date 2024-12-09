package visitor;

import iterator.Iterator;
import model.SetModel;

public class IntersectionVisitor<T> implements Visitor<T> {

    private final SetModel<T> set;
    private final SetModel<T> intersectionSet;

    public IntersectionVisitor(SetModel<T> set) {
        this.set = set;
        intersectionSet = new SetModel<>();
    }

    @Override
    public void visit(SetModel<? extends T> otherSet) {
        Iterator<? extends T> iterator = otherSet.createIterator();
        while(!iterator.isDone()) {
            if (set.getList().contains(iterator.currentItem())) {
                intersectionSet.add(iterator.currentItem());
            }
            iterator.next();
        }
    }

    public SetModel<T> getResult() {
        return intersectionSet;
    }
}
