import javax.swing.*;
import java.util.*;

public class Set<T> implements Aggregate<T>{

    List<T> list;

    Set() {
        list = new ArrayList<>();
    }

    @SafeVarargs
    Set(T... elements) {
        list = new ArrayList<>();
        for (T element : elements) {
            if (!list.contains(element)) {
                list.add(element);
            }
        }
    }

    int size() {
        return list.size();
    }

    boolean isEmpty() {
        return list.isEmpty();
    }

    void clear() {
        list.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Set<?> set = (Set<?>) o;
        return Objects.equals(list, set.list);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator<T> iterator = createIterator();
        while(!iterator.isDone()) {
            stringBuilder.append(iterator.currentItem()).append(" ");
            iterator.next();
        }
        return stringBuilder.toString();
    }

    public DefaultListModel<T> toListModel() {
        DefaultListModel<T> listModel = new DefaultListModel<>();
        for (T element : list) {
            listModel.addElement(element);
        }
        return listModel;
    }

    void add(T value) {
        if (!list.contains(value)) {
            list.add(value);
        }
    }

    void remove(T value) {
        if (isEmpty()) {
            throw new EmptySetException();
        }
        list.remove(value);
    }

    void addAll(Collection<? extends T> collection) {
        for (T element : collection) {
            if (!list.contains(element)) {
                list.add(element);
            }
        }
    }

    Set<T> unite(Set<? extends T> anotherSet) {
        Set<T> unitedSet = new Set<>();
        unitedSet.list.addAll(list);
        Iterator<? extends T> iterator = anotherSet.createIterator();
        while(!iterator.isDone()) {
            if (!unitedSet.list.contains(iterator.currentItem())) {
                unitedSet.list.add(iterator.currentItem());
            }
            iterator.next();
        }
        return unitedSet;
    }

    Set<T> intersect(Set<? extends T> anotherSet) {
        Set<T> intersectionSet = new Set<>();
        Iterator<? extends T> iterator = anotherSet.createIterator();
        while(!iterator.isDone()) {
            if (list.contains(iterator.currentItem())) {
                intersectionSet.add(iterator.currentItem());
            }
            iterator.next();
        }
        return intersectionSet;
    }

    Set<T> difference(Set<? extends T> anotherSet) {
        Set<T> differenceSet = new Set<>();
        Iterator<? extends T> iterator = createIterator();
        while(!iterator.isDone()) {
            if (!anotherSet.list.contains(iterator.currentItem())) {
                differenceSet.add(iterator.currentItem());
            }
            iterator.next();
        }
        return differenceSet;
    }

    @Override
    public Iterator<T> createIterator() {
        return new Iterator<>() {

            int index = 0;

            @Override
            public void first() {
                index = 0;
            }

            @Override
            public boolean isDone() {
                return index >= size();
            }

            @Override
            public void next() {
                index++;
            }

            @Override
            public T currentItem() {
                return list.get(index);
            }
        };
    }
}
