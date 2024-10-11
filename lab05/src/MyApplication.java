import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyApplication extends JFrame {
    public static void main(String[] args) {
        MyApplication myApp = new MyApplication("Series");
        myApp.setSize(800, 800);
        myApp.pack();
        myApp.setVisible(true);
    }

    Series series;
    JLabel firstElemLabel;
    JTextField firstElemTextField;
    JLabel stepLabel;
    JTextField stepTextField;
    JLabel numOfElementsLabel;
    JTextField numOfElementsTextField;
    JButton calcSumButton;
    JRadioButton linerRadioButton;
    JRadioButton exponentialRadioButton;
    JLabel jElemTextLabel;
    JComboBox <Integer> jElemComboBox;
    JButton calcJElemButton;
    JTextField resSumTextField;
    JTextField resJElemTextField;

    MyApplication(String str) {
        super(str);

        this.setLayout(new FlowLayout());

        firstElemLabel = new JLabel("first element: ");
        firstElemTextField = new JTextField(10);

        stepLabel = new JLabel("step: ");
        stepTextField = new JTextField(10);

        numOfElementsLabel = new JLabel("number of elements: ");
        numOfElementsTextField = new JTextField(10);

        linerRadioButton = new JRadioButton("Linear");
        exponentialRadioButton = new JRadioButton("Exponential");

        resSumTextField = new JTextField("sum result");
        resSumTextField.setEnabled(false);

        calcSumButton = new JButton("calculate sum");

        jElemTextLabel = new JLabel("input j index");
        jElemComboBox = new JComboBox<>();

        resJElemTextField = new JTextField("j element result");
        resJElemTextField.setEnabled(false);

        calcJElemButton = new JButton("calculate j element");

        this.add(firstElemLabel);
        this.add(firstElemTextField);

        this.add(stepLabel);
        this.add(stepTextField);

        this.add(numOfElementsLabel);
        this.add(numOfElementsTextField);

        this.add(linerRadioButton);
        this.add(exponentialRadioButton);

        this.add(calcSumButton);
        this.add(resSumTextField);

        this.add(jElemTextLabel);
        this.add(jElemComboBox);
        this.add(resJElemTextField);

        calcSumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                fillJComboBox(Integer.parseInt(numOfElementsTextField.getText()));
                if (linerRadioButton.isSelected()) {
                    series = new Linear(Double.parseDouble(firstElemTextField.getText()), Double.parseDouble(stepTextField.getText()), Integer.parseInt(numOfElementsTextField.getText()));
                } else if (exponentialRadioButton.isSelected()) {
                    series = new Exponential(Double.parseDouble(firstElemTextField.getText()), Double.parseDouble(stepTextField.getText()), Integer.parseInt(numOfElementsTextField.getText()));
                }
                resSumTextField.setText(String.valueOf(series.sumOfSeries()));
                resJElemTextField.setText(String.valueOf(jElemComboBox.getSelectedItem()));
            }
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void fillJComboBox(int numOfElements) {
        for (int i = 1; i <= numOfElements; i++) {
            jElemComboBox.addItem(i);
        }
    }
}
