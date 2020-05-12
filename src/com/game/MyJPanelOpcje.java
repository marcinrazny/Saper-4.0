package com.game;

import javax.swing.*;
import java.awt.*;

public class MyJPanelOpcje extends JPanel {

    public MyJPanelOpcje() {
        setPreferredSize(new Dimension(288, 512));
        setLayout(null);
        setBackground(Color.black);
        Font czcionka = new Font("Jokerman", Font.BOLD, 70);

        JLabel tytul = new JLabel();
        tytul.setFont(czcionka);
        tytul.setText("OPCJE");
        tytul.setForeground(Color.white);
        tytul.setBounds(20, 50, 245, 100);
        add(tytul);
    }
}
