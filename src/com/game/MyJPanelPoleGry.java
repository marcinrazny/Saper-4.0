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
    ImageIcon IconMine = new ImageIcon(this.getClass().getResource("images/mina.jpg"));
    ImageIcon IconFieldChor = new ImageIcon(this.getClass().getResource("images/poleObraz14Flaga.png"));
    ImageIcon IconFieldZnak = new ImageIcon(this.getClass().getResource("images/poleObraz14znak.png"));
    ImageIcon IconMineBlow = new ImageIcon(this.getClass().getResource("images/minaBum.jpg"));
    ImageIcon IconNoMine = new ImageIcon(this.getClass().getResource("images/noMine.jpg"));

    int fieldSize = 10;

    JButton[][] buttonField = new JButton[fieldSize][fieldSize];
    JLabel[][] cells = new JLabel[fieldSize][fieldSize];

    double mineQuantity = 10;

    void FillField(){

        for (int i = 0; i < fieldSize; i++) {

            for (int j = 0; j < fieldSize; j++) {

                buttonField[i][j] = new JButton();
                buttonField[i][j].setName("button["+String.valueOf(i)+"]"+"["+String.valueOf(i)+"]");
                buttonField[i][j].setBounds(i * 30, j * 30, 30, 30);
                buttonField[i][j].setIcon(IconField);
                add(buttonField[i][j]);
                buttonField[i][j].setFocusable(false);
                buttonField[i][j].setVisible(true);
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

    public void MinesQuantityCounter() {

        for (int j = 0; j < fieldSize; j++) {

            for (int i = 0; i < fieldSize; i++) {

                if (cells[j][i].getIcon() != null) {

                    if ((cells[j][i].getIcon() == null)) {
                        if (cells[j][i].getText().equals("")) cells[j][i].setText(String.valueOf(0));
                        cells[j][i].setText(String.valueOf(Integer.parseInt(cells[j][i].getText()) + 1));
                    }
                    if (i > 0) {
                        if ((cells[j][i - 1].getIcon() == null)) {
                            if (cells[j][i - 1].getText().equals("")) cells[j][i - 1].setText(String.valueOf(0));
                            cells[j][i - 1].setText(String.valueOf(Integer.parseInt(cells[j][i - 1].getText()) + 1));
                        }
                    }
                    if (i < fieldSize - 1) {
                        if ((cells[j][i + 1].getIcon() == null)) {
                            if (cells[j][i + 1].getText().equals("")) cells[j][i + 1].setText(String.valueOf(0));
                            cells[j][i + 1].setText(String.valueOf(Integer.parseInt(cells[j][i + 1].getText()) + 1));
                        }
                    }
                    if (j > 0) {
                        if ((cells[j - 1][i].getIcon() == null)) {
                            if (cells[j - 1][i].getText().equals("")) cells[j - 1][i].setText(String.valueOf(0));
                            cells[j - 1][i].setText(String.valueOf(Integer.parseInt(cells[j - 1][i].getText()) + 1));
                        }
                    }
                    if (j < fieldSize - 1) {
                        if ((cells[j + 1][i].getIcon() == null)) {
                            if (cells[j + 1][i].getText().equals(""))
                                cells[j + 1][i].setText(String.valueOf(0));
                            cells[j + 1][i].setText(String.valueOf(Integer.parseInt(cells[j + 1][i].getText()) + 1));
                        }
                    }
                    if ((j > 0) && (i < fieldSize - 1)) {
                        if ((cells[j - 1][i + 1].getIcon() == null)) {
                            if (cells[j - 1][i + 1].getText().equals(""))
                                cells[j - 1][i + 1].setText(String.valueOf(0));
                            cells[j - 1][i + 1].setText(String.valueOf(Integer.parseInt(cells[j - 1][i + 1].getText()) + 1));
                        }
                    }
                    if ((j < fieldSize - 1) && (i > 0)) {
                        if ((cells[j + 1][i - 1].getIcon() == null)) {
                            if (cells[j + 1][i - 1].getText().equals(""))
                                cells[j + 1][i - 1].setText(String.valueOf(0));
                            cells[j + 1][i - 1].setText(String.valueOf(Integer.parseInt(cells[j + 1][i - 1].getText()) + 1));
                        }
                    }
                    if ((j > 0) && (i > 0)) {
                        if ((cells[j - 1][i - 1].getIcon() == null)) {
                            if (cells[j - 1][i - 1].getText().equals(""))
                                cells[j - 1][i - 1].setText(String.valueOf(0));
                            cells[j - 1][i - 1].setText(String.valueOf(Integer.parseInt(cells[j - 1][i - 1].getText()) + 1));
                        }
                    }
                    if ((i < fieldSize - 1) && (j < fieldSize - 1)) {
                        if ((cells[j + 1][i + 1].getIcon() == null)) {
                            if (cells[j + 1][i + 1].getText().equals(""))
                                cells[j + 1][i + 1].setText(String.valueOf(0));
                            cells[j + 1][i + 1].setText(String.valueOf(Integer.parseInt(cells[j + 1][i + 1].getText()) + 1));
                        }
                    }

                }
            }
        }
    }



    void SetMines() {

        for (int i = 1; i <= mineQuantity; i++) {

            int x = ((int) (Math.random() * fieldSize + 0));
            int y = ((int) (Math.random() * fieldSize + 0));

            if (cells[x][y].getName().equals("Mine")) {

                i--;

            } else {
                cells[x][y].setIcon(IconMine);
                cells[x][y].setName("Mine");
            }
        }
    }

    void MinesQuantityColor() {

        int a = 0;

        for (int j = 0; j < fieldSize; j++) {

            for (int i = 0; i < fieldSize; i++) {

                if (cells[i][j].getText().equals("3")) {
                    a = 90;
                }

                if (cells[i][j].getText().equals("4")) {
                    a = 160;
                }

                if (cells[i][j].getText().equals("5")) {
                    a = 220;
                }

                if (cells[i][j].getText().equals("6")) {
                    a = 220;
                }

                if (cells[i][j].getText().equals("7")) {
                    a = 220;
                }

                cells[i][j].setForeground(new Color(220 - a, 0, 0));

                if (cells[i][j].getText().equals("1")) {

                    cells[i][j].setForeground(new Color(5, 12, 192));

                }
            }
        }
    }


    MyJPanelPoleGry() {

        setLayout(null);
        setPreferredSize(new Dimension(300, 300));

        FillField();

        SetMines();

        MinesQuantityCounter();


    }
}
