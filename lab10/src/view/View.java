package view;

import controller.SetController;
import model.SetModel;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public class View extends JFrame {

    JList<String> listA;
    JList<String> listB;

    SetModel<String> setA;
    SetModel<String> setB;
    JTextField elemToAddTextField;

    JButton addButton;

    JButton clearButton;

    JButton removeButton;

    JLabel setOperationsLabel;
    JTextField resultTextField;

    JButton uniteButton;
    JButton intersectButton;
    JButton differenceABButton;

    JRadioButton setAButton;
    JRadioButton setBButton;
    ButtonGroup setGroup;

    public View(String string) {
        super(string);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        Font largerFont = new Font("Dialog", Font.BOLD, 16);
        UIManager.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
        UIManager.put("RadioButton.focus", new ColorUIResource(new Color(0, 0, 0, 0)));

        setA = new SetModel<>();
        setB = new SetModel<>();

        listA = setA.toJList();

        listB = setB.toJList();

        elemToAddTextField = new JTextField();

        addButton = new JButton("add element");
        addButton.setEnabled(false);

        clearButton = new JButton("clear");
        clearButton.setEnabled(false);
        removeButton = new JButton("remove element");
        removeButton.setEnabled(false);

        setOperationsLabel = new JLabel("set operations");
        setOperationsLabel.setFont(largerFont);

        resultTextField = new JTextField();
        resultTextField.setEditable(false);
        uniteButton = new JButton("unite sets");
        intersectButton = new JButton("intersect sets");
        differenceABButton = new JButton("find difference of sets (A-B)");

        JScrollPane scrollPaneA = new JScrollPane(listA);
        JScrollPane scrollPaneB = new JScrollPane(listB);

        scrollPaneA.setPreferredSize(new Dimension(150, 200));
        scrollPaneB.setPreferredSize(new Dimension(150, 200));

        setAButton = new JRadioButton("Set A");
        setBButton = new JRadioButton("Set B");
        setGroup = new ButtonGroup();
        setGroup.add(setAButton);
        setGroup.add(setBButton);


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 5;
        add(scrollPaneA, gbc);
        gbc.gridx = 1;
        add(scrollPaneB, gbc);

        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridheight = 1;
        add(setAButton, gbc);
        gbc.gridx = 1;
        add(setBButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        add(elemToAddTextField, gbc);

        gbc.gridy = 7;
        add(addButton, gbc);

        gbc.gridy = 8;
        add(clearButton, gbc);

        gbc.gridy = 9;
        add(removeButton, gbc);

        gbc.gridy = 10;
        gbc.fill = GridBagConstraints.NONE;
        add(setOperationsLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 11;
        add(resultTextField, gbc);

        gbc.gridy = 12;
        add(uniteButton, gbc);

        gbc.gridy = 13;
        add(intersectButton, gbc);

        gbc.gridy = 14;
        add(differenceABButton, gbc);

        new SetController(setA, setB, this);
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getClearButton() {
        return clearButton;
    }

    public JButton getRemoveButton() {
        return removeButton;
    }

    public JButton getUniteButton() {
        return uniteButton;
    }

    public JButton getIntersectButton() {
        return intersectButton;
    }

    public JButton getDifferenceABButton() {
        return differenceABButton;
    }

    public JTextField getElemToAddTextField() {
        return elemToAddTextField;
    }

    public JTextField getResultTextField() {
        return resultTextField;
    }

    public JList<String> getListA() {
        return listA;
    }

    public JList<String> getListB() {
        return listB;
    }

    public boolean isSetASelected() {
        return setAButton.isSelected();
    }

    public void updateSetDisplay(SetModel<String> set) {
        if (set == setA) {
            listA.setModel(set.toJList().getModel());
        } else {
            listB.setModel(set.toJList().getModel());
        }
    }
}