package com.game;

import javax.swing.*;

public class MyJFrame extends JFrame {

    public MyJFrame(int mine, int field) throws InterruptedException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(150,150);
        setSize(1000, 1000);
        setTitle("Saper 4.0");
        add(new MyJPanelStart());
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}