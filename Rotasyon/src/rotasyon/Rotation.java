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

import java.awt.Graphics;
import javax.swing.JPanel;
import matematik.MatriksIslem;
import matematik.Vektor;

/**
 *
 * @author kayra
 */
class Rotation extends JPanel{
    public static final double[][] YANSITMA = {
        {1, 0, 0},
        {0, 1, 0}
    };
    double aci = 0d;
    
    Vektor[] projected = new Vektor[8];
    
    Vektor[] noktalar = {
        new Vektor(-0.5d, -0.5d, -0.5d),
        new Vektor(0.5d, -0.5d, -0.5d),
        new Vektor(0.5d, 0.5d, -0.5d),
        new Vektor(-0.5d, 0.5d, -0.5d),
        new Vektor(-0.5d, -0.5d, 0.5d),
        new Vektor(0.5d, -0.5d, 0.5d),
        new Vektor(0.5d, 0.5d, 0.5d),
        new Vektor(-0.5d, 0.5d, 0.5d)
    };
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        MatriksIslem is = new MatriksIslem(aci);
        g.translate(Main.WIDTH/2, Main.HEIGHT/2);
        int index = 0;
        for(Vektor v: noktalar){
            Vektor rotated = MatriksIslem.matriksCarpim(is.ROTASYON_Z, v);
            rotated = MatriksIslem.matriksCarpim(is.ROTASYON_X, rotated);
            rotated = MatriksIslem.matriksCarpim(is.ROTASYON_Y, rotated);
            
            Vektor projected2d = MatriksIslem.matriksCarpim(YANSITMA, rotated);
            projected2d.carp(100 + noktalar[1].z);
            projected[index] = projected2d;
            index++;
        }
        
        Connector connect = new Connector();

        for (int i = 0; i < 4; i++) {
            /*boya.paintComponent(yansitilmis[2], yansitilmis[6], yansitilmis[7], yansitilmis[3],Color.GRAY, g);
            boya.paintComponent(yansitilmis[1], yansitilmis[5], yansitilmis[4], yansitilmis[0],Color.GRAY, g);
            boya.paintComponent(yansitilmis[2], yansitilmis[6], yansitilmis[5], yansitilmis[1],Color.RED, g);
            boya.paintComponent(yansitilmis[3], yansitilmis[7], yansitilmis[4], yansitilmis[0],Color.RED, g);
            boya.paintComponent(yansitilmis[6], yansitilmis[7], yansitilmis[4], yansitilmis[5],Color.CYAN, g);
            boya.paintComponent(yansitilmis[2], yansitilmis[3], yansitilmis[0], yansitilmis[1],Color.cyan , g);
            */
            connect.paintComponent(i, (i + 1) % 4, projected, g);
            connect.paintComponent(i, (i % 4) + 4, projected, g);
            connect.paintComponent(i + 4, ((i + 1) % 4) + 4, projected, g);
            
        }
        aci += 0.05;
        repaint();
    }
}
