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
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author kayra
 */
public class Pong implements ActionListener, KeyListener {

    public static final int WIDTH = 600, HEIGHT = 450;
    public static Pong pong;
    public Goruntu renderer;

    JFrame f;

    BufferedImage image;
    public boolean bot = false;

    public boolean up, down, w, s, baslangic = true;

    public Raket p1;
    public Raket p2;

    public Top top;

    Timer timer;

    long sBekleme, bBekleme;
    
    long startingTime, currentTime, oldTime;

    public int hiz = 5;
    public static int oyunDurumu = 0;//0:Başlangıç\Default, 1:Durduruldu, 2:Devam ediyor, 3: Oyun Bitti

    public Pong() throws IOException {
        this.image = ImageIO.read(new File("C:\\Users\\kayra\\Desktop\\background.jpg"));
        timer = new Timer(10, this);
        f = new JFrame("Ping Pong");
        renderer = new Goruntu();

        f.setSize(WIDTH + 16, HEIGHT + 39);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(renderer);
        f.setVisible(true);
        f.addKeyListener(this);

        timer.start();
    }

    public void start() {
        oyunDurumu = 2;
        p1 = new Raket(this, 1/*1. oyuncu*/);
        p2 = new Raket(this, 2/*2. oyuncu*/);
        top = new Top();
    }

    //Her 20 ms' de update yer.
    public void update() {
        System.out.println("Çalıştım");
        currentTime = System.currentTimeMillis();
        
        if (w && p1.moveCheck(true)) {
            p1.move(true, hiz);
        }
        if (up && p2.moveCheck(true)) {
            p2.move(true, hiz);
        }
        if (s && p1.moveCheck(false)) {
            p1.move(false, hiz);
        }
        if (down && p2.moveCheck(false)) {
            p2.move(false, hiz);
        }

        if (currentTime - oldTime >= 9987 && currentTime - oldTime <= 10010) {
            oldTime = System.currentTimeMillis();
            if (!(Top.vurus == 8)) {
                Top.vurus += 4;
            }
            if (!(hiz == 13)) {
                hiz += 4;
            }
            System.out.println("top: " + Top.vurus);
        }

        top.update(p1, p2);

        //İlk 10 ulaşan kazanır.
        if (p1.skor == 10 || p2.skor == 10) {
            oyunDurumu = 3; //bitirir.
        }
    }

    public void render(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (oyunDurumu == 0) {
            g.setColor(Color.black);
            g.fillRect(0, 0, WIDTH, HEIGHT);
            g.setColor(Color.white);
            g.setFont(new Font("Arial", 1, 50));
            g.drawString("PING PONG", WIDTH / 2 - 148, 50);

            g.setFont(new Font("Arial", 1, 30));
            g.drawString("Başlamak için space tuşuna basın.", WIDTH / 2 - 245, 230);
            g.drawString("Bot ile oynamak için Shift tuşuna basın.", WIDTH / 2 - 280, 280);
        }
        if (oyunDurumu == 2 || oyunDurumu == 1) {
            g.drawImage(image, 0, 0, null);
            g.setColor(Color.white);

            g.setFont(new Font("Arial", 1, 25));
            g.drawString("Süre :", WIDTH / 2 - 70, 75);
            g.drawString(String.valueOf((currentTime - startingTime) / 1000), WIDTH / 2 + 20, 75);

            g.setFont(new Font("Arial", 1, 50));
            //Skor tablası
            g.drawString(String.valueOf(p1.skor), WIDTH / 2 - 148, 440);
            g.drawString(String.valueOf(p2.skor), WIDTH / 2 + 110, 440);
            top.render(g);
            p1.render(g);
            p2.render(g);
        }
        if (oyunDurumu == 1) {
            g.setColor(Color.white);
            g.setFont(new Font("Arial", 1, 50));
            g.drawString("DURDURULDU", WIDTH / 2 - 170, 50);
        }

        if (oyunDurumu == 4) {
            g.setColor(Color.black);
            g.fillRect(0, 0, WIDTH, HEIGHT);

            g.setColor(Color.white);
            g.setFont(new Font("Arial", 1, 50));
            //Skor tablası
            g.drawString(String.valueOf(p1.skor), WIDTH / 2 - 148, 440);
            g.drawString(String.valueOf(p2.skor), WIDTH / 2 + 110, 440);

            g.drawString("Oyun Bitti", WIDTH / 2 - 170, 50);

            if (p1.skor == 10 && !(p2.skor == 10)) {
                p1.kazanan = true;
                p2.kazanan = false;
            } else {
                p1.kazanan = false;
                p2.kazanan = true;
            }

            p1.render(g);
            p2.render(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!Raket.bekle){
            if (oyunDurumu == 2) {
                update();
            }
            renderer.repaint();
        }else{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Pong.class.getName()).log(Level.SEVERE, null, ex);
            }
            Raket.bekle = false;
        }
    }

    @Override
    @Deprecated
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                up = true;
                break;
            case KeyEvent.VK_DOWN:
                down = true;
                break;
            case KeyEvent.VK_W:
                w = true;
                break;
            case KeyEvent.VK_S:
                s = true;
                break;
            case KeyEvent.VK_P:
                if (oyunDurumu == 2) {
                    oyunDurumu = 1;
                    break;
                }
                if (oyunDurumu == 1) {
                    oyunDurumu = 2;
                    break;
                }
                if (oyunDurumu == 0) {
                    break;
                }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                up = false;
                break;
            case KeyEvent.VK_DOWN:
                down = false;
                break;
            case KeyEvent.VK_W:
                w = false;
                break;
            case KeyEvent.VK_S:
                s = false;
                break;
            case KeyEvent.VK_SPACE:
                if (oyunDurumu == 0) {
                    if (baslangic) {
                        oldTime = System.currentTimeMillis();
                        startingTime = System.currentTimeMillis();
                        start();
                        bot = false;
                        baslangic = false;
                    }
                }
                break;
            case KeyEvent.VK_SHIFT:
                if (oyunDurumu == 0) {
                    start();
                    bot = true;
                }
                break;
        }
    }

    public static void main(String[] args) throws IOException {
        pong = new Pong();
    }
}
