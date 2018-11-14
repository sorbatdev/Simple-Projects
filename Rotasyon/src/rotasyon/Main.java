/* 
 * Copyright (c) 2018, Kayra Urfalı
 * 
 * 
 * 
 * 
 * 
 * 
 */
package rotasyon;

import javax.swing.JFrame;

/**
 *
 * @author kayra
 */
public class Main {
    
    public static final int WIDTH = 600, HEIGHT = 600;

    public static void main(String[] args) {
        JFrame pencere = new JFrame("kayra");
        Rotation goruntu = new Rotation();
        
        pencere.add(goruntu);
        pencere.setLocationRelativeTo(null);
        pencere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pencere.setSize(WIDTH, HEIGHT);
        pencere.setVisible(true);
    }
    
}
