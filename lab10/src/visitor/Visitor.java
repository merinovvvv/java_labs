package visitor;

import model.Set;

public interface Visitor<T> {
    Set<T> visit(Set<? extends T> set);
}
