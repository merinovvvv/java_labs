package visitor;

import model.Set;

public interface Visitor<T> {
    void visit(Set<? extends T> set);
}
