package app;

import view.View;

import java.awt.*;

public class Main {

    /*
    М – BinaryTreeModel пассивная;
    V – View;
    С – BinaryTreeController.
     */

    public static void main(String[] args) {
        View view = new View("");
        view.setMinimumSize(new Dimension(500, 650));
        view.setVisible(true);
    }
}