package com.game;

import javax.swing.*;

public class MyJFrame extends JFrame {
    public MyJFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Saper");
        add(new MyJPanelStart());
        pack();
    }
}
