package com.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyJFramePoleGry extends JFrame  {

    public MyJFramePoleGry(int mine, int field, int c) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(150, 150);
        setSize(1000, 1000);
        setTitle("Saper 4.0");
        add(new MyJPanelPoleGry(mine,field,c));
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    static void new_game2(int mine, int field, int c) {

        MyJFramePoleGry window2 = new MyJFramePoleGry(mine, field, c);
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Menu");
        JMenu options = new JMenu("Opcje");
        JMenu info = new JMenu("Info");

        JMenuItem info2 = new JMenuItem("Autorzy");
        info2.addActionListener(e -> JOptionPane.showMessageDialog(FocusManager.getCurrentManager().getActiveWindow(),
                "Developerzy \n   Marcin Raźny & Radek Borowski",
                "O autorach...",
                JOptionPane.PLAIN_MESSAGE));
        info.add(info2);

        JMenuItem newGame = new JMenuItem("Nowa gra");
        newGame.addActionListener(new MenuActionListener());

        JMenuItem exit = new JMenuItem("Koniec");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JMenu level = new JMenu("Poziom");
        options.add(level);

        JRadioButtonMenuItem easy = new JRadioButtonMenuItem("łatwy");
        if (c==1) {
            easy.setSelected(true);
        }
        easy.setActionCommand("easy");
        easy.addActionListener(e -> {
            Window activeWindow = FocusManager.getCurrentManager().getActiveWindow();
            activeWindow.dispose();
            new_game2(15,10,1);
            easy.setSelected(true);
        });
        level.add(easy);

        JRadioButtonMenuItem middle = new JRadioButtonMenuItem("średni");
        if (c==2) {
            middle.setSelected(true);

        }
        middle.setActionCommand("middle");
        middle.addActionListener(e -> {
            Window activeWindow = FocusManager.getCurrentManager().getActiveWindow();
            activeWindow.dispose();
            new_game2(60,20,2);
            middle.setSelected(true);
        });
        level.add(middle);

        JRadioButtonMenuItem hard = new JRadioButtonMenuItem("trudny");

        if (c==3) {
            hard.setSelected(true);
        }
        hard.setActionCommand("hard");
        hard.addActionListener(e -> {
            Window activeWindow = FocusManager.getCurrentManager().getActiveWindow();
            activeWindow.dispose();
            new_game2(120,30,3);
            hard.setSelected(true);
        });
        level.add(hard);

        ButtonGroup group = new ButtonGroup();
        group.add(easy);
        group.add(middle);
        group.add(hard);

        menuBar.add(menu);
        menu.add(newGame);
        menu.add(exit);
        menuBar.add(options);
        menuBar.add(info);

        window2.setJMenuBar(menuBar);
        window2.pack();
        window2.setVisible(true);
    }
    static class MenuActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            Window activeWindow = javax.swing.FocusManager.getCurrentManager().getActiveWindow();
            activeWindow.dispose();
            new_game2(15,10,1);
        }
    }
}

