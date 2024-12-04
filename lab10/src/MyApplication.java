import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MyApplication extends JFrame {

    JList<String> listA;
    JList<String> listB;
    DefaultListModel<String> listModelA;
    DefaultListModel<String> listModelB;

    Set<String> setA;
    Set<String> setB;
    JTextField elemToAddTextField;

    JButton addAButton;
    JButton addBButton;

    JButton clearAButton;
    JButton clearBButton;

    JButton removeButton;

    JLabel setOperationsLabel;
    JTextField resultTextField;

    JButton uniteButton;
    JButton intersectButton;
    JButton differenceABButton;

    MyApplication(String string) {
        super(string);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        Font largerFont = new Font("Dialog", Font.BOLD, 16);
        UIManager.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));

        setA = new Set<>();
        setB = new Set<>();

        listModelA = new DefaultListModel<>();
        listA = new JList<>(listModelA);

        listModelB = new DefaultListModel<>();
        listB = new JList<>(listModelB);

        elemToAddTextField = new JTextField();

        addAButton = new JButton("add element");
        addAButton.setEnabled(false);
        addBButton = new JButton("add element");
        addBButton.setEnabled(false);

        clearAButton = new JButton("clear");
        clearAButton.setEnabled(false);
        clearBButton = new JButton("clear");
        clearBButton.setEnabled(false);
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

        listA.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                removeButton.setEnabled(!listA.isSelectionEmpty());
            }
        });

        listB.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                removeButton.setEnabled(!listB.isSelectionEmpty());
            }
        });

        elemToAddTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                toggleButtonState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                toggleButtonState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                toggleButtonState();
            }

            private void toggleButtonState() {
                boolean isTextFieldEmpty = elemToAddTextField.getText().trim().isEmpty();
                addAButton.setEnabled(!isTextFieldEmpty);
                addBButton.setEnabled(!isTextFieldEmpty);
            }
        });

        addAButton.addActionListener(_ -> addElementsToSetAndList(setA, listA, clearAButton));

        addBButton.addActionListener(_ -> addElementsToSetAndList(setB, listB, clearBButton));

        clearAButton.addActionListener(_ -> clearSetAndList(setA, listA, clearAButton));

        clearBButton.addActionListener(_ -> clearSetAndList(setB, listB, clearBButton));

        removeButton.addActionListener(_ -> removeSelectedElementsFromLists());

        uniteButton.addActionListener(_ -> resultTextField.setText(setA.unite(setB).toString()));

        intersectButton.addActionListener(_ -> resultTextField.setText(setA.intersect(setB).toString()));

        differenceABButton.addActionListener(_ -> resultTextField.setText(setA.difference(setB).toString()));

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
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        add(elemToAddTextField, gbc);
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        add(addAButton, gbc);
        gbc.gridx = 1;
        add(addBButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        add(clearAButton, gbc);
        gbc.gridx = 1;
        add(clearBButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        add(removeButton, gbc);

        gbc.gridy = 9;
        gbc.fill = GridBagConstraints.NONE;
        add(setOperationsLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 10;
        add(resultTextField, gbc);

        gbc.gridy = 11;
        add(uniteButton, gbc);

        gbc.gridy = 12;
        add(intersectButton, gbc);

        gbc.gridy = 13;
        add(differenceABButton, gbc);
    }

    private void addElementsToSetAndList(Set<String> set, JList<String> list, JButton clearButton) {
        StringTokenizer st = new StringTokenizer(elemToAddTextField.getText());
        List<String> tokens = new ArrayList<>();
        while (st.hasMoreTokens()) {
            tokens.add(st.nextToken());
        }
        set.addAll(tokens);
        list.setModel(set.toJList().getModel());
        clearButton.setEnabled(true);
    }

    private void clearSetAndList(Set<String> set, JList<String> list, JButton clearButton) {
        set.clear();
        list.setModel(set.toJList().getModel());
        clearButton.setEnabled(false);
        removeButton.setEnabled(false);
    }

    private void removeSelectedElementsFromLists() {
        List<String> selectedValuesA = listA.getSelectedValuesList();
        List<String> selectedValuesB = listB.getSelectedValuesList();

        for (String value : selectedValuesA) {
            setA.remove(value);
        }
        for (String value : selectedValuesB) {
            setB.remove(value);
        }

        listA.setModel(setA.toJList().getModel());
        listB.setModel(setB.toJList().getModel());

        if (setA.isEmpty()) {
            clearAButton.setEnabled(false);
        }
        if (setB.isEmpty()) {
            clearBButton.setEnabled(false);
        }
        if (setA.isEmpty() && setB.isEmpty()) {
            removeButton.setEnabled(false);
        }
    }
}