package com.game;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyJPanelPoleGry extends JPanel {

    ImageIcon IconField = new ImageIcon(this.getClass().getResource("images/poleObraz12.png"));
    int fieldSize = 10;

    JButton[][] buttonField = new JButton[fieldSize][fieldSize];
    JLabel[][] cells = new JLabel[fieldSize][fieldSize];

    double iloscMin = fieldSize * 1.7;


    MyJPanelPoleGry() {

        setLayout(null);
        setPreferredSize(new Dimension(300, 300));
        Border border = LineBorder.createGrayLineBorder();

        for (int i = 0; i < fieldSize; i++) {

            for (int j = 0; j < fieldSize; j++) {

                buttonField[i][j] = new JButton();
                buttonField[i][j].setName("button["+String.valueOf(i)+"]"+"["+String.valueOf(i)+"]");
                buttonField[i][j].setBounds(i * 30, j * 30, 30, 30);
                buttonField[i][j].setIcon(IconField);
                add(buttonField[i][j]);
                buttonField[i][j].setFocusable(false);
                buttonField[i][j].setBorder((BorderFactory.createBevelBorder(0)));

                cells[i][j] = new JLabel("");
                cells[i][j].setOpaque(true);
                cells[i][j].setBounds(i * 30, j * 30, 30, 30);
                cells[i][j].setHorizontalAlignment(0);
                cells[i][j].setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
                cells[i][j].setFont(new Font(null, Font.BOLD, 15));
                cells[i][j].setName("");
                add(cells[i][j]);
            }
        }

    }
}
