package com.piecergames.display;

import java.awt.Canvas;

import javax.swing.JFrame;

public class Display extends Canvas{

    public static final short WIDTH = 800;
    public static final short HEIGHT = 600;

    public static final String TITLE = "FIRST 3D GAME";

    private static final long serialVersionUID = 1L;

    Display() {
        Main m = new Main();
        JFrame f = new JFrame(TITLE);
        f.add(m);
        f.pack();
        f.setSize(WIDTH, HEIGHT);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setVisible(true);
    }

}