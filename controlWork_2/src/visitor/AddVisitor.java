package visitor;

import model.BinaryTreeModel;

public class AddVisitor implements Visitor {
    private final int value;

    public AddVisitor(int value) {
        this.value = value;
    }

    @Override
    public void visit(BinaryTreeModel model) {
        if (model.getList().isEmpty()) {
            model.getList().add(value);
            return;
        }

        int index = 0;

        while (true) {
            while (index >= model.getList().size()) {
                model.getList().add(0);
            }

            if (model.getList().get(index) == 0) {
                model.getList().set(index, value);
                return;
            }

            if (value == model.getList().get(index)) {
                return;
            }

            if (value < model.getList().get(index)) {
                index = 2 * index + 1;
            } else {
                index = 2 * index + 2;
            }
        }
    }
}