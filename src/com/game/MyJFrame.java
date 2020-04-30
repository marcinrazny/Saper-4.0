package com.game;

import javax.swing.*;

public class MyJFrame extends JFrame {
    public MyJFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(500, 500);
        setTitle("Saper");
        add(new MyJPanelPoleGry());
        pack();
    }
}
