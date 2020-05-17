package com.game;
import java.awt.*;


public class Main extends MyJPanelPoleGry {

    Main(int field, int mine, int c) {
        super(field, mine, c);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    MyJFrame pierwsze = new MyJFrame(15,10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}


