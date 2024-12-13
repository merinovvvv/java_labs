package model;

import iterator.Aggregate;
import iterator.Iterator;
import strategy.FindMaxStrategy;
import visitor.Element;
import visitor.Visitor;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.BufferedReader;
import java.util.Objects;

public class BinaryTreeModel implements Aggregate<Integer>, Element {
    private final List<Integer> tree;
    private FindMaxStrategy strategy;

    public BinaryTreeModel() {
        tree = new ArrayList<>();
        tree.add(0);
    }

//    public void add(int value) {
//        if (tree.isEmpty()) {
//            tree.add(value);
//            return;
//        }
//
//        int index = 0;
//
//        while (true) {
//            while (index >= tree.size()) {
//                tree.add(0);
//            }
//
//            if (tree.get(index) == 0) {
//                tree.set(index, value);
//                return;
//            }
//
//            if (value == tree.get(index)) {
//                return;
//            }
//
//            if (value < tree.get(index)) {
//                index = 2 * index + 1;
//            } else {
//                index = 2 * index + 2;
//            }
//        }
//    }


    public List<Integer> prefixTraversal() { // IS DONE WITH ITERATOR
        List<Integer> result = new ArrayList<>();
        Iterator<Integer> iterator = createIterator();
        while (!iterator.isDone()) {
            result.add(iterator.currentItem());
            iterator.next();
        }
        return result;
    }

    public List<Integer> moveToMax() {
        int max = strategy.max(tree);
        List<Integer> path = new ArrayList<>();
        int index = 0;
        while (index < tree.size() && tree.get(index) != max) {
            path.add(tree.get(index));
            if (max < tree.get(index)) {
                index = 2 * index + 1;
            } else {
                index = 2 * index + 2;
            }
        }
        if (index < tree.size()) {
            path.add(tree.get(index));
        }
        return path;
    }

    public void open(String filePath) throws Exception {
        File file = new File(filePath);

        if (!file.exists()) {
            throw new IllegalArgumentException("File is not found: " + filePath);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            if (line == null || line.trim().isEmpty()) {
                throw new IllegalArgumentException("File is empty or has no data.");
            }

            String[] numbers = line.trim().split("\\s+"); //regex use
            tree.clear();
            for (String num : numbers) {
                tree.add(Integer.parseInt(num));
            }
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Incorrect data. Make sure that the file contains only numbers separated by spaces.");
        } catch (Exception ex) {
            throw new Exception("File reading error: " + ex.getMessage());
        }
    }


    public String displayTreeAsArray() {
        StringBuilder builder = new StringBuilder();
        builder.append("Current tree as an array:\n");

        for (int i = 0; i < tree.size(); i++) {
            builder.append(String.format("[%d]: %d", i, tree.get(i))).append("\n");
        }

        return builder.toString();
    }

    public void setStrategy(FindMaxStrategy strategy) {
        this.strategy = strategy;
    }

    public FindMaxStrategy getStrategy() {
        return strategy;
    }

    public List<Integer> getList() {
        return tree;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BinaryTreeModel that = (BinaryTreeModel) o;
        return Objects.equals(tree, that.tree);
    }

    @Override
    public String toString() {
        return "BinaryTreeModel{" +
                "tree=" + tree +
                '}';
    }

    @Override
    public Iterator<Integer> createIterator() {
        return new Iterator<>() {

            int index = 0;

            @Override
            public void first() {
                index = 0;
            }

            @Override
            public boolean isDone() {
                return index >= tree.size();
            }

            @Override
            public void next() {
                index++;
            }

            @Override
            public Integer currentItem() {
                return tree.get(index);
            }
        };
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
