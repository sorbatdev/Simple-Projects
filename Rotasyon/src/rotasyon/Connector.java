/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rotasyon;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import javax.swing.JPanel;
import matematik.Vektor;

/**
 *
 * @author kayra
 */
public class Connector extends JPanel {
    public void paintComponent(int i, int j, Vektor[] noktalar , Graphics g){
        Vektor a = noktalar[i];
        Vektor b = noktalar[j];
        
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        g2.setStroke(new BasicStroke(3));
        g2.setColor(Color.blue);
        g2.draw(new Line2D.Double(a.x+10, a.y+8, b.x+10, b.y+8));
    }
}