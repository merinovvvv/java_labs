package view;

import controller.BinaryTreeController;
import model.BinaryTreeModel;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public class View extends JFrame {
    JTextArea displayArea;
    JTextField inputField;

    JButton addButton;
    JButton openButton;
    JButton maxButton;
    JButton maxButtonStream;
    JButton pathButton;
    JButton traversalButton;

    BinaryTreeModel model;

    public View(String string) {
        super(string);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Font largerFont = new Font("Dialog", Font.BOLD, 16);
        UIManager.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
        UIManager.put("RadioButton.focus", new ColorUIResource(new Color(0, 0, 0, 0)));

        model = new BinaryTreeModel();
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(2, 3));

        inputField = new JTextField();
        addButton = new JButton("Add");
        openButton = new JButton("Open");
        maxButton = new JButton("Max");
        maxButtonStream = new JButton("Max with stream api");
        pathButton = new JButton("Path to Max");
        traversalButton = new JButton("Traversal");

        controlPanel.add(new JLabel("Input:"));
        controlPanel.add(inputField);
        controlPanel.add(addButton);
        controlPanel.add(openButton);
        controlPanel.add(maxButton);
        controlPanel.add(maxButtonStream);
        controlPanel.add(pathButton);
        controlPanel.add(traversalButton);

        add(scrollPane, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);


        new BinaryTreeController(model, this);
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getOpenButton() {
        return openButton;
    }

    public JButton getMaxButton() {
        return maxButton;
    }

    public JButton getMaxButtonStream() {
        return maxButtonStream;
    }

    public JButton getPathButton() {
        return pathButton;
    }

    public JButton getTraversalButton() {
        return traversalButton;
    }

    public JTextField getInputField() {
        return inputField;
    }

    public void display(String message) {
        displayArea.setText(message);
    }
}