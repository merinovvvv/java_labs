package controller;

import model.BinaryTreeModel;
import strategy.FindMaxNoStreamApi;
import strategy.FindMaxStrategy;
import strategy.FindMaxUsingStreamApi;
import view.View;
import visitor.AddVisitor;

import javax.swing.*;
import java.io.File;

public class BinaryTreeController {
    private final BinaryTreeModel model;
    private final View view;

    public BinaryTreeController(BinaryTreeModel model, View view) {
        this.model = model;
        this.view = view;
        initController();
    }

    private void initController() {
        view.getAddButton().addActionListener(_ -> {
            try {
                AddVisitor addVisitor = new AddVisitor(Integer.parseInt(view.getInputField().getText()));
                model.accept(addVisitor);
                view.display("Node added. Current tree: " + model.displayTreeAsArray());
            } catch (NumberFormatException ex) {
                view.display("Invalid input!");
            }
        });
        view.getOpenButton().addActionListener(_ -> openFile());
        view.getMaxButton().addActionListener(_ -> {
            model.setStrategy(new FindMaxNoStreamApi());
            showMax();
        });
        view.getMaxButtonStream().addActionListener(_ -> {
            model.setStrategy(new FindMaxUsingStreamApi());
            showMax();
        });
        view.getPathButton().addActionListener(_ -> showPathToMax());
        view.getTraversalButton().addActionListener(_ -> showTraversal());
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                model.open(file.getPath());
                view.display("Tree loaded from file: " + model.displayTreeAsArray());
            } catch (Exception ex) {
                view.display("Error reading file: " + ex.getMessage());
            }
        }
    }

    private void showMax() {
        view.display("Max: " + model.getStrategy().max(model.getList()));
    }

    private void showPathToMax() {
        view.display("Path to Max: " + model.moveToMax());
    }

    private void showTraversal() {
        view.display("Prefix Traversal: " + model.prefixTraversal());
    }
}