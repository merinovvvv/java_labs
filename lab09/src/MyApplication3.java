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

    Font largerFont = new Font("Dialog", Font.BOLD, 16);

    public MyApplication3(String string) {
        super(string);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());


        defaultIcon = new ImageIcon((new ImageIcon("iconsForTask3/avatar.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        rolloverIcon = new ImageIcon((new ImageIcon("iconsForTask3/contact.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        pressedIcon = new ImageIcon((new ImageIcon("iconsForTask3/call.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        selectedIcon = new ImageIcon((new ImageIcon("iconsForTask3/cancel.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));


        radioButton1 = new JRadioButton("Button1");
        radioButton2 = new JRadioButton("Button2");
        radioButton3 = new JRadioButton("Button3");

        initRadioButton(radioButton1);
        initRadioButton(radioButton2);
        initRadioButton(radioButton3);

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

    private void initRadioButton(JRadioButton radioButton) {
        radioButton.setIcon(defaultIcon);
        radioButton.setRolloverIcon(rolloverIcon);
        radioButton.setPressedIcon(pressedIcon);
        radioButton.setSelectedIcon(selectedIcon);
        radioButton.setFont(largerFont);
    }
}
