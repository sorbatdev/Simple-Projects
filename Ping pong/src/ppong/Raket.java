/* 
 * Copyright (c) 2018, Kayra Urfalı
 * 
 * 
 * 
 * 
 * 
 * 
 */
package ppong;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author kayra
 */
public class Raket {

    public int raketnum;
    
    public boolean kazanan;

    public int x, y, genislik = 20, uzunluk = 100;
    
    int skor = 0;
    
    
    
    public static boolean bekle;

    public Raket(Pong pong, int raketNumara) {
        this.raketnum = raketNumara;
        if (raketNumara == 1) {
            this.x = 0;
        } else {
            this.x = Pong.WIDTH - genislik;
        }
        this.y = Pong.HEIGHT / 2 - this.uzunluk / 2;
    }

    public void render(Graphics g) {
            g.setColor(Color.white);
            g.fillRect(x, y, genislik, uzunluk);
        if(Pong.oyunDurumu == 4){   
            if(this.kazanan){
                g.setColor(Color.green);
                g.fillRect(x, y, genislik, uzunluk);
            }else{
                g.setColor(Color.red);
                g.fillRect(x, y, genislik, uzunluk);
            }
        }
        
    }

    boolean moveCheck(boolean direction) {
        if (direction) {
            return this.y >= 0;
        }else {
            return this.y <= Pong.HEIGHT-100;
        }
    }
    
    void move(boolean direction, int hiz) {
        if (direction) {
            if (this.y >= 0) {
                this.y -= hiz;
            } else {
                this.y = 0;
            }
        } else {
            if (this.y <= Pong.HEIGHT-100) {
                this.y += hiz;
            } else {
                this.y = Pong.HEIGHT-100;
            }
        }
    }
}