/* 
 * Copyright (c) 2018, Kayra Urfalı
 * 
 * 
 * 
 * 
 * 
 * 
 */
package oyun.pencere;

import oyun.yapi.Isleyici;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import oyun.nesneler.Altin;
import oyun.nesneler.Oyuncu;
import oyun.nesneler.Platform;
import oyun.nesneler.LavDurgun;
import oyun.nesneler.LavHareketli;
import oyun.yapi.Doku;
import oyun.yapi.Kamera;
import oyun.yapi.ResimYukleyici;
import oyun.yapi.NesneID;
import oyun.yapi.OyunNesnesi;
import oyun.yapi.TusGirdisi;



/**
 *
 * @author kayra
 */
public class Oyun extends Canvas implements Runnable{
    
    public static int GENISLIK, YUKSEKLIK;

    private boolean running = false;
    private Thread thread;
    
    private BufferedImage level = null;
    
    Isleyici isleyici;
    Kamera kam;
    static Doku doku;
    
    private void init() {
        
        GENISLIK = getWidth();
        YUKSEKLIK = getHeight();
        
        doku = new Doku();
        
        ResimYukleyici yukleyici = new ResimYukleyici();
        
        level = yukleyici.levelYukle("/oyun/res/level.png");
        
        isleyici = new Isleyici();
        
        kam = new Kamera(0,0);
        
//        isleyici.nesneEkle(new Oyuncu(100, 100, isleyici , NesneID.Oyuncu));        
//        isleyici.bolumOlustur();

        bolumuYukle(level);
        
        this.addKeyListener(new TusGirdisi(isleyici));
    }
    
    @SuppressWarnings("override")
    public void run() {
        init();
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while(running){
                long now = System.nanoTime();
                delta += (now - lastTime) / ns;
                lastTime = now;
                while(delta >= 1){
                        tick();
                        updates++;
                        delta--;
                }
                render();
                frames++;

                if(System.currentTimeMillis() - timer > 1000){
                        timer += 1000;
                        System.out.println("FPS: " + frames + " TICKS: " + updates);
                        frames = 0;
                        updates = 0;
                }
        }
    }
    
    public synchronized void start(){
        if(running)
            return;
        
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    
    public static void main(String[] args) {
        new Pencere(1000, 800, "Oyun", new Oyun());
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        Graphics2D g2 = (Graphics2D)g;
        //////////////////////////////////////////////////////////
        
        //Çizim işlemleri
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        ///////////////////////////////////////
        g2.translate(kam.getX(), kam.getY());
                //Kamera tarafından etkilenecek değişkenler
                
                isleyici.render(g);
                
                /////////////////////////
        g2.translate(-kam.getX(), -kam.getY());
        ///////////////////////////////////////
        ////////////////////////////////////////////////////////
        g.dispose();
        bs.show();
    }
    
    private void bolumuYukle(BufferedImage bolum){
        int g = bolum.getWidth();
        int y = bolum.getHeight();
    
        System.out.println(g+ " " + y);
        
        for(int xx = 0; xx < y; xx++){
            for(int yy = 0 ; yy < g; yy++){
                int pixel =  bolum.getRGB(xx, yy);
                int kirmizi = (pixel >> 16) & 0xff;
                int yesil = (pixel >> 8) & 0xff;
                int mavi = (pixel) & 0xff;
                
                if(kirmizi == 160 && yesil == 85 && mavi == 53){ //Toprak
                    isleyici.nesneler.add(new Platform(xx*32, yy*32, 0, NesneID.Platform));
                }
                else if(kirmizi == 17 && yesil == 158 && mavi == 45){ //Çimen
                    isleyici.nesneler.add(new Platform(xx*32, yy*32, 1, NesneID.Platform));
                }
                if(kirmizi == 255 && yesil == 248 && mavi == 58){
                    isleyici.nesneler.add(new Altin(xx*32, yy*32, NesneID.Altin));
                }
                if(kirmizi == 255 && yesil == 0 && mavi == 0){
                    isleyici.nesneler.add(new LavHareketli(xx*32, yy*32, NesneID.Lav));
                }else if(kirmizi == 190 && yesil == 0 && mavi == 0){
                    isleyici.nesneler.add(new LavDurgun(xx*32, yy*32, NesneID.Lav));
                }
                if(kirmizi == 0 && yesil == 0 && mavi == 255){
                    isleyici.nesneler.add(new Oyuncu(xx*32, yy*32, isleyici, NesneID.Oyuncu));
                }
            }
        }
    }

    private void tick() {
        isleyici.tick();
        for(int i = 0; i < isleyici.nesneler.size(); i++){
            OyunNesnesi n = isleyici.nesneler.get(i);
            
            if(n.getId() == NesneID.Oyuncu){
                kam.tick(n);
            }
            
        }
    }
    
    public static Doku olayCagir(){
        return doku;
    }

}
