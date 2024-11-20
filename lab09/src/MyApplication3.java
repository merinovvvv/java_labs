import javax.swing.*;
import java.awt.*;

public class MyApplication3 extends JFrame {

    Icon defaultIcon;
    Icon rolloverIcon;
    Icon pressedIcon;
    Icon selectedIcon;

    JRadioButton radioButton1;
    JRadioButton radioButton2;
    JRadioButton radioButton3;

    public MyApplication3(String string) {
        super(string);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        Font largerFont = new Font("Dialog", Font.BOLD, 16);

        defaultIcon = new ImageIcon((new ImageIcon("iconsForTask3/avatar.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        rolloverIcon = new ImageIcon((new ImageIcon("iconsForTask3/contact.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        pressedIcon = new ImageIcon((new ImageIcon("iconsForTask3/call.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        selectedIcon = new ImageIcon((new ImageIcon("iconsForTask3/cancel.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

        radioButton1 = new JRadioButton("Button1");
        radioButton1.setIcon(defaultIcon);
        radioButton1.setRolloverIcon(rolloverIcon);
        radioButton1.setPressedIcon(pressedIcon);
        radioButton1.setSelectedIcon(selectedIcon);
        radioButton1.setFont(largerFont);

        radioButton2 = new JRadioButton("Button2");
        radioButton2.setIcon(defaultIcon);
        radioButton2.setRolloverIcon(rolloverIcon);
        radioButton2.setPressedIcon(pressedIcon);
        radioButton2.setSelectedIcon(selectedIcon);
        radioButton2.setFont(largerFont);

        radioButton3 = new JRadioButton("Button3");
        radioButton3.setIcon(defaultIcon);
        radioButton3.setRolloverIcon(rolloverIcon);
        radioButton3.setPressedIcon(pressedIcon);
        radioButton3.setSelectedIcon(selectedIcon);
        radioButton3.setFont(largerFont);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        buttonGroup.add(radioButton3);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;

        add(radioButton1, gbc);
        gbc.gridy++;
        add(radioButton2, gbc);
        gbc.gridy++;
        add(radioButton3, gbc);
    }
}
