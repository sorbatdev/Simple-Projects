package com.piecergames.display;

import java.awt.Canvas;
import java.io.File;
import java.util.Arrays;

import com.piecergames.structure.ReaderWriter;

public class Main extends Canvas{

    private static final long serialVersionUID = 1L;

    public static File crds = new File("src/main/java/com/piecergames/display/objectcoords.txt");

    public static void main(String[] args) {
        //new Display();
        System.out.println(Arrays.deepToString(new ReaderWriter().getCoords(9)));
    }
}