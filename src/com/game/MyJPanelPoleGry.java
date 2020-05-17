package com.game;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.io.InputStream;


public class MyJPanelPoleGry extends JPanel {

    int fieldSize;
    int mineQuantity;
    int mineLeft;
    int buttonsVisibleLeft = fieldSize * fieldSize;
    boolean correctMineMarked;

    JButton[][] buttonField;
    JLabel[][] cells;

    ImageIcon IconField = new ImageIcon(this.getClass().getResource("images/poleObraz12.png"));
    ImageIcon IconMine = new ImageIcon(this.getClass().getResource("images/mina.jpg"));
    ImageIcon IconFieldChor = new ImageIcon(this.getClass().getResource("images/poleObraz14Flaga.png"));
    ImageIcon IconFieldZnak = new ImageIcon(this.getClass().getResource("images/poleObraz14znak.png"));
    ImageIcon IconMineBlow = new ImageIcon(this.getClass().getResource("images/minaBum.jpg"));
    ImageIcon IconNoMine = new ImageIcon(this.getClass().getResource("images/noMine.jpg"));
    ImageIcon IconEmot = new ImageIcon(this.getClass().getResource("images/smile.png"));

    private boolean mouseListenerIsActive = true;

    JLabel timeElapsed = new JLabel();

    int secondsThread = 0;

    class ThreadNew extends Thread {
        private Thread t;
        private String threadName;
        int flag = 0;

        ThreadNew( String name) {
            threadName = name;
        }

        public void run() {
            try {
                do {
                    secondsThread++;
                    timeElapsed.setText(String.format("%03d", secondsThread));
                    Thread.sleep(1000);

                }while(flag<99);

            } catch (InterruptedException e) {
            }
        }
        public void start () {
            if (t == null) {
                t = new Thread (this, threadName);
                t.start ();
            }
        }
        public void stops(){
            flag = 99;
        }
    }

    private void playSound()
    {
        try
        {
            InputStream inputStream = getClass().getResourceAsStream("explosion.wav");
            AudioStream audioStream = new AudioStream(inputStream);
            AudioPlayer.player.start(audioStream);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    MyJPanelPoleGry( int mine, int field, int c) {

        fieldSize = field;
        mineQuantity = mineLeft = mine;

        buttonField = new JButton[fieldSize][fieldSize];
        cells = new JLabel[fieldSize][fieldSize];

        Font f = new Font ("Arial", Font.BOLD , 18);

        JPanel toolBarPanel = new JPanel();
        toolBarPanel.setLayout(new BoxLayout(toolBarPanel,BoxLayout.PAGE_AXIS));
        toolBarPanel.setPreferredSize(new Dimension(field*30,25));
        add(toolBarPanel);

        JLabel score = new JLabel();
        score.setFont(f);
        score.setBackground(Color.black);
        score.setForeground(Color.red);
        score.setOpaque(true);
        score.setPreferredSize(new Dimension(30,25));
        score.setText(String.format("%03d", mineLeft));

        timeElapsed.setFont(f);
        timeElapsed.setBackground(Color.black);
        timeElapsed.setForeground(Color.red);
        timeElapsed.setOpaque(true);
        timeElapsed.setPreferredSize(new Dimension(30,25));
        timeElapsed.setText(String.format("%03d", secondsThread));

        JButton emot = new JButton();
        emot.setPreferredSize(new Dimension(15 ,15));
        emot.setOpaque(true);
        emot.setBorderPainted(false);
        emot.setFocusable(false);
        emot.setIcon(IconEmot);
        emot.addActionListener(e -> {
                Window activeWindow = FocusManager.getCurrentManager().getActiveWindow();
                activeWindow.dispose();
                if (mine == 15) {
                    MyJFramePoleGry.new_game2(mine, field, 1);
                }
                if (mine == 60) {
                    MyJFramePoleGry.new_game2(mine, field, 2);
                }
                if (mine == 120) {
                    MyJFramePoleGry.new_game2(mine, field, 3);
                }
        });

        JToolBar resultsBar = new JToolBar();
        resultsBar.setBorder(null);
        resultsBar.setPreferredSize(new Dimension(field*30,25));
        resultsBar.setFloatable(false);

        resultsBar.add(timeElapsed);
        resultsBar.addSeparator(new Dimension(field*10,25));
        resultsBar.add(emot);
        resultsBar.addSeparator(new Dimension(field*10,25));
        resultsBar.add(score);

        resultsBar.setOpaque(false);
        toolBarPanel.add(resultsBar);

        JPanel kolejny = new JPanel();
        kolejny.setLayout(null);
        kolejny.setPreferredSize(new Dimension(field*30,field*30));
        add(kolejny);

        setPreferredSize(new Dimension(fieldSize * 30, fieldSize * 30+35));

        ThreadNew threadNew = new ThreadNew( "threadNew");

        for (int i = 0; i < fieldSize; i++) {

            for (int j = 0; j < fieldSize; j++) {
                buttonField[i][j] = new JButton();
                buttonField[i][j].setName("button[" + String.valueOf(i) + "]" + "[" + String.valueOf(i) + "]");
                buttonField[i][j].setBounds(i * 30, j * 30, 30, 30);
                buttonField[i][j].setIcon(IconField);
                kolejny.add(buttonField[i][j]);
                buttonField[i][j].setFocusable(false);
                buttonField[i][j].setBorder((BorderFactory.createBevelBorder(0)));
                buttonField[i][j].addMouseListener(new MouseListener() {

                    @Override

                    public void mouseClicked(MouseEvent e) {

                        if (mouseListenerIsActive) {
                            JButton button = (JButton) e.getSource();
                            int x = button.getX();
                            int y = button.getY();

                            if (SwingUtilities.isRightMouseButton(e)) {

                                if (button.getIcon().equals(IconField)) {
                                    button.setIcon(IconFieldChor);
                                    mineLeft--;
                                    score.setText(String.format("%03d", mineLeft));

                                    if (cells[x / 30][y / 30].getName().equals("Mine")) {
                                        correctMineMarked = true;

                                        if ((mineLeft == 0) && (CheckCovered(fieldSize,buttonField) == mineQuantity)) {
                                            JOptionPane.showMessageDialog(null, "Wygrałeś");
                                        }
                                    } else
                                        correctMineMarked = false;

                                } else if (button.getIcon().equals(IconFieldChor)) {
                                    button.setIcon(IconFieldZnak);
                                    mineLeft++;

                                } else if (button.getIcon().equals(IconFieldZnak)) {
                                    button.setIcon(IconField);
                                }
                            }
                            if (SwingUtilities.isLeftMouseButton(e)) {
                                threadNew.start();

                                if ((button.getIcon().equals(IconField)) || (button.getIcon().equals(IconFieldZnak))) {

                                    if (cells[x / 30][y / 30].getIcon() != null) {
                                        threadNew.stops();
                                        playSound();
                                        mouseListenerIsActive = false;
                                        cells[x / 30][y / 30].setIcon(IconMineBlow);
                                        button.setVisible(false);
                                        ShowMines(fieldSize,buttonField);
                                        JOptionPane.showMessageDialog(null, "Przegrałeś!");
                                    }
                                    else {
                                        button.setVisible(false);
                                        buttonsVisibleLeft--;
                                        CheckEmptyFields(x / 30, y / 30, new boolean[fieldSize][fieldSize]);
                                        CheckCovered(fieldSize,buttonField);

                                        if ((mineLeft == 0) && ( CheckCovered(fieldSize,buttonField) == mineQuantity) && (correctMineMarked)) {

                                            threadNew.stops();
                                            JOptionPane.showMessageDialog(null, "Wygrałeś!\nWyniki: "+secondsThread+" sek");
                                            mouseListenerIsActive = false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    @Override
                    public void mousePressed(MouseEvent e) {
                    }
                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }
                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }
                    @Override
                    public void mouseExited(MouseEvent e) {
                    }
                });
                cells[i][j] = new JLabel("");
                cells[i][j].setOpaque(true);
                cells[i][j].setBounds(i * 30, j * 30, 30, 30);
                kolejny.add(cells[i][j]);
                cells[i][j].setHorizontalAlignment(0);
                cells[i][j].setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
                cells[i][j].setFont(new Font(null, Font.BOLD, 15));
                cells[i][j].setName("");
                cells[i][j].setVisible(true);
                kolejny.add(cells[i][j]);
            }
        }

        SetMines();

        MinesQuantityCounter();

        MinesQuantityColor();
    }

    public void CheckEmptyFields(int a, int b, boolean[][] checked) {

        if ((a > fieldSize - 1) || (a < 0) || (b > fieldSize - 1) || (b < 0)) return;

        if (checked[a][b]) return;

        checked[a][b] = true;

        if (!cells[a][b].getText().equals("")) {
            buttonField[a][b].setVisible(false);
            return;
        }
        if (!cells[a][b].getName().equals("Mine")) {
            buttonField[a][b].setVisible(false);
        }
        CheckEmptyFields(a - 1, b - 1, checked);
        CheckEmptyFields(a - 1, b, checked);
        CheckEmptyFields(a - 1, b + 1, checked);
        CheckEmptyFields(a, b - 1, checked);
        CheckEmptyFields(a, b + 1, checked);
        CheckEmptyFields(a + 1, b - 1, checked);
        CheckEmptyFields(a + 1, b, checked);
        CheckEmptyFields(a + 1, b + 1, checked);
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

    public void ShowMines(int fieldSize, JButton[][] buttonField) {

        for (int i = 0; i < fieldSize; i++) {

            for (int j = 0; j < fieldSize; j++) {

                if (cells[i][j].getName().equals("Mine")) this.buttonField[i][j].setVisible(false);

                if ((!cells[i][j].getName().equals("Mine")) && (this.buttonField[i][j].getIcon().equals(IconFieldChor))) {

                    this.buttonField[i][j].setIcon(IconNoMine);

                }
            }
        }
    }

    void MinesQuantityColor() {

        int a = 0;

        for (int j = 0; j < fieldSize; j++) {

            for (int i = 0; i < fieldSize; i++) {

                if (cells[i][j].getText().equals("2")) {
                    a = 25;
                }

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

    void SetMines() {

        for (int i = 1; i <= mineQuantity; i++) {

            int x = ((int) (Math.random() * fieldSize  + 0));
            int y = ((int) (Math.random() * fieldSize + 0));

            if (cells[x][y].getName().equals("Mine")) {
                i--;
            } else {
                cells[x][y].setIcon(IconMine);
                cells[x][y].setName("Mine");
            }
        }
    }
    public int CheckCovered(int fieldSize, JButton[][] buttonField){

        int counterVisibleButtons = 0;

        for (int c=0; c<fieldSize;c++){
            for (int d=0;d<fieldSize;d++){

                if (this.buttonField[c][d].isVisible()) counterVisibleButtons++;

            }
        }
        return counterVisibleButtons;
    }
}

