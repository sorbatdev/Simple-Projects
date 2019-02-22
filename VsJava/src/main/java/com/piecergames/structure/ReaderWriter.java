package com.piecergames.structure;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReaderWriter {

    private final String COORDS = "src/main/java/com/piecergames/objects/objectcoords.crds";

    public void writeCoordinates(String[][] coords, GameObject obj){
        float[] x = new float[coords.length];
        float[] y = new float[coords.length];
        float[] z = new float[coords.length];
        
        for(String[] s: coords){
            for(int i = 0; i < s.length; i++){
                x[i] = s[0];
                y[i] = s[1];
                z[i] = s[2];
            }
        }

        
    }

    /*
    * Object dosyası içindeki koordinatları çeker.
    */
    public String[][] getCoords(int start) {

        String[] line = new String[3];
        String[][] out = null;

        Scanner br = null;

        byte index = 0;

        int currentLine = 1;
        int desiredLine = 1;


        try{
            String next = null;
            br = new Scanner(new File(COORDS));
            while(br.hasNextLine()){
                next = br.nextLine();
                if(currentLine == start){
                    desiredLine = currentLine;
                }if(desiredLine == start){
                    if(next.equals("-")){
                        out = new String[index][1];
                        break;
                    }
                    index++;
                }
                currentLine++;
            }

            desiredLine = 1;
            currentLine = 1;
            index = 0;
            br.reset();
            br = new Scanner(new File(COORDS));

            while(br.hasNextLine()){
                next = br.nextLine();
                if(currentLine == start){
                    desiredLine = currentLine;
                }if(desiredLine == start){                    
                    if(next.equals("-")){
                        break;
                    }
                    line = next.split(" ");
                    //System.out.println(Arrays.deepToString(line));
                    out[index] = line;
                    index++;
                }
                currentLine++;
            }
        }catch(FileNotFoundException e){
            e.getStackTrace();
        }
        return out;
    }

}