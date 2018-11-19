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
import java.util.Random;

/**
 *
 * @author kayra
 */
public class Top {

    final int skor = 2;
    final int hit = 1;

    public int x, y, yaricap = 25;

    public int hareketX, hareketY;

    Random random;

    static int vurus = 0;

    public Top() {
        random = new Random();
        spawn();

    }

    public void update(Raket paddle1, Raket paddle2) {
        int speed = 4;

        this.x += this.hareketX * speed;
        this.y += this.hareketY * speed;

        if (this.y + yaricap >= Pong.HEIGHT || this.y <= 0) {
            if (this.y <= 0) {
                this.hareketY = 2;
            }
            if (this.y > 0) {
                this.hareketY = -2;
            }
        }

        if (collision(paddle1) == 1) {
            
            this.hareketX = 1 + vurus / 6;
            
            if (this.hareketY < 0) {
                this.hareketY = -3;
            } else {
                this.hareketY = +3;
            }
            if (hareketY == 0) {
                this.hareketY = 1;
            }
        } else if (collision(paddle2) == 1) {
            this.hareketX = -1 - vurus / 6;
            if (this.hareketY < 0) {
                this.hareketY = -3;
            } else {
                this.hareketY = +3;
            }
            if (hareketY == 0) {
                this.hareketY = 1;
            }
        }
        if (collision(paddle1) == 2) {
            paddle2.skor++;
            spawn();
            Raket.bekle = true;
        } else if (collision(paddle2) == 2) {
            paddle1.skor++;
            spawn();
            Raket.bekle = true;
        }
    }

    public int collision(Raket paddle) {
        if (this.x < paddle.x + paddle.genislik && this.x + this.yaricap > paddle.x && this.y < paddle.y + paddle.uzunluk && this.y + this.yaricap > paddle.y) {
            return hit;
        } else if ((paddle.x > x + yaricap && paddle.raketnum == 1) || (paddle.x < x && paddle.raketnum == 2)) {
            return skor;
        }
        return 0;
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x, y, yaricap, yaricap);
    }

    public void spawn() {
        this.y = Pong.HEIGHT / 2 - this.yaricap;
        this.x = Pong.WIDTH / 2 - this.yaricap + 8;

        this.hareketY = -2 + random.nextInt(4);

        if (hareketY == 0) {
            this.hareketY = 1;
        }
        if (random.nextBoolean()) {
            this.hareketX = 1 + vurus / 6;
        } else {
            this.hareketX = -1 - vurus / 6;
        }

    }
}
