import javax.swing.*;
import java.awt.*;

public class MyApplication1 extends JFrame {
    final DefaultListModel<JComponent> listModel1;
    final DefaultListModel<JComponent> listModel2;

    final JList<JComponent> list1;
    final JList<JComponent> list2;

    JButton moveToRightButton;
    JButton moveToLeftButton;

    JPanel buttonPanel;

    public MyApplication1(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Font largerFont = new Font("Dialog", Font.BOLD, 16);

        listModel1 = new DefaultListModel<>();
        listModel2 = new DefaultListModel<>();

        list1 = new JList<>(listModel1);
        list2 = new JList<>(listModel2);

        list1.setCellRenderer(new ComponentRenderer());
        list2.setCellRenderer(new ComponentRenderer());

        fillTheLists();

        setFontForAllComponents(listModel1, largerFont);
        setFontForAllComponents(listModel2, largerFont);

        moveToRightButton = new JButton(">");
        moveToRightButton.setFont(largerFont);

        moveToLeftButton = new JButton("<");
        moveToLeftButton.setFont(largerFont);

        moveToRightButton.addActionListener(_ -> moveSelectedItems(list1, listModel1, listModel2));

        moveToLeftButton.addActionListener(_ -> moveSelectedItems(list2, listModel2, listModel1));

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(moveToRightButton, BorderLayout.NORTH);
        buttonPanel.add(moveToLeftButton, BorderLayout.SOUTH);

        add(new JScrollPane(list1), BorderLayout.WEST);
        add(buttonPanel, BorderLayout.CENTER);
        add(new JScrollPane(list2), BorderLayout.EAST);
    }

    private void setFontForAllComponents(DefaultListModel<JComponent> model, Font font) {
        for (int i = 0; i < model.size(); i++) {
            model.getElementAt(i).setFont(font);
        }
    }

    private void moveSelectedItems(JList<JComponent> sourceList, DefaultListModel<JComponent> sourceModel, DefaultListModel<JComponent> targetModel) {
        int[] selectedIndices = sourceList.getSelectedIndices();
        for (int i = selectedIndices.length - 1; i >= 0; i--) {
            JComponent selectedItem = sourceModel.getElementAt(selectedIndices[i]);
            targetModel.addElement(selectedItem);
            sourceModel.removeElementAt(selectedIndices[i]);
        }
    }

    private void fillTheLists() {
        for (int i = 1; i <= 3; i++) {
            listModel1.addElement(new JButton("Button #" + i));
            listModel1.addElement(new JLabel("Label #" + i));
            listModel1.addElement(new JTextField("TextField #" + i));
        }

        for (int i = 4; i <= 6; i++) {
            listModel2.addElement(new JButton("Button #" + i));
            listModel2.addElement(new JLabel("Label #" + i));
            listModel2.addElement(new JTextField("TextField #" + i));
        }
    }

    static class ComponentRenderer implements ListCellRenderer<JComponent> {
        @Override
        public Component getListCellRendererComponent(JList<? extends JComponent> list, JComponent value, int index, boolean isSelected, boolean cellHasFocus) {
            if (isSelected) {
                value.setBackground(list.getSelectionBackground());
                value.setForeground(list.getSelectionForeground());
                value.setOpaque(true);
                if (value instanceof JButton) {
                    ((JButton) value).setContentAreaFilled(false);
                    ((JButton) value).setBorderPainted(false);
                }
            } else {
                value.setBackground(list.getBackground());
                value.setForeground(list.getForeground());
                value.setOpaque(false);
                if (value instanceof JButton) {
                    ((JButton) value).setContentAreaFilled(true);
                    ((JButton) value).setBorderPainted(true);
                }
            }
            return value;
        }
    }
}