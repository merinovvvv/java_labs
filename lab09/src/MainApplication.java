import javax.swing.*;
import java.awt.*;

public class MainApplication extends JFrame {

    MyApplication1 myApplication1;
    MyApplication2 myApplication2;
    MyApplication3 myApplication3;

    JTabbedPane tabbedPane;

    MainApplication(String string) {
        super(string);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Font largerFont = new Font("Dialog", Font.BOLD, 16);

        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(largerFont);

        myApplication1 = new MyApplication1("task1");
        myApplication2 = new MyApplication2("task2");
        myApplication3 = new MyApplication3("task3");

        tabbedPane.addTab("task1", myApplication1.getContentPane());
        tabbedPane.addTab("task2", myApplication2.getContentPane());
        tabbedPane.addTab("task3", myApplication3.getContentPane());

        add(tabbedPane, BorderLayout.CENTER);
    }
}
