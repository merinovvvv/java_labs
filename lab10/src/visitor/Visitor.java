package visitor;

import model.SetModel;

public interface Visitor<T> {
    void visit(SetModel<? extends T> set);
}
