package com.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyJPanelStart extends JPanel {
    class Start implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            Window activeWindow = javax.swing.FocusManager.getCurrentManager().getActiveWindow();
            activeWindow.dispose();
            JFrame frame2 = new JFrame();
            frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame2.setVisible(true);
            frame2.setLocationRelativeTo(null);
            frame2.setResizable(false);
            frame2.setTitle("Saper");
            frame2.add(new MyJPanelPoleGry());
            frame2.pack();
        }
    }

    private static void dispose() {
    }


    class MojaPomoc implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae){
            JOptionPane.showMessageDialog(null, "STEROWANIE: " +
                    "Lewym przyciskiem myszy zaznaczasz pola....");
        }
    }
    ImageIcon IconStart = new ImageIcon(this.getClass().getResource("images/start.png"));
    ImageIcon IconOpcje = new ImageIcon(this.getClass().getResource("images/opcje.png"));
    ImageIcon IconPomoc = new ImageIcon(this.getClass().getResource("images/pomoc.png"));


    public MyJPanelStart() {
        setPreferredSize(new Dimension(288, 512));
        setLayout(null);
        setBackground(Color.black);
        Font czcionka = new Font("Jokerman", Font.BOLD, 70);
        //  txt.setFont(font);
        //  txt.setForeground(Color.BLUE);

        JLabel tytul = new JLabel();
        tytul.setFont(czcionka);
        tytul.setText("SAPER");
        tytul.setForeground(Color.white);
        tytul.setBounds(20, 50, 245, 100);
        add(tytul);


        JButton button = new JButton();
        button.setIcon(IconStart);
        button.setBounds(21, 200,245,60);
        button.addActionListener(new Start());
        add(button);

        JButton button2 = new JButton();
        button2.setIcon(IconOpcje);
        button2.setBounds(21, 300, 245, 60);
        //button2.addActionListener(new MojeOpcje());
        add(button2);

        JButton button3 = new JButton();
        button3.setBounds(21, 400, 245, 60);
        button3.setIcon(IconPomoc);
        button3.addActionListener(new MojaPomoc());
        add(button3);
    }

}

