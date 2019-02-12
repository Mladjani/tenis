/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tennis;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JComponent;
import javax.swing.Timer;

/**
 *
 * @author Nikola
 */
public class Tennis extends JComponent implements ActionListener, KeyListener {

    private boolean play = false;
    private int ballX = 245;
    private int ballY = 245;
    private int racketLX = 0;
    private int racketLY = 190;
    private int racketRX = 475;
    private int racketRY = 200;
    private int ballSpeed = 12;
    private int ballXdirection = -1;
    private int ballYdirection = -1;
    private Timer timer;
    
    
    public Tennis() {
        
        addKeyListener(this);
        setFocusable(true);
        timer = new Timer(ballSpeed, this);
        timer.start();
    }

    
    public void paint(Graphics g) {
        //background
        g.setColor(Color.black);
        g.fillRect(0, 0, 500, 500);
        
        //wall1 (up) and wall2 (down)
        g.setColor(Color.cyan);
        g.fillRect(0, 0, 500, 15);
        g.fillRect(0, 450, 500, 15);
        
        //racketL and racketR
        g.setColor(Color.white);
        g.fillRect(racketLX, racketLY, 15, 65);
        g.fillRect(racketRX, racketRY, 15, 65);
        
        //ball
        g.fillOval(ballX, ballY, 15, 15);
        
        
        //kada je GAMEOVER
        if (ballX < 0) {
            play = false;
            ballXdirection = 0;
            ballYdirection = 0;
            g.setColor(Color.cyan);
            g.setFont(new Font("serif", Font.BOLD, 17));
            g.drawString("Pobedio je takmicar desno", 155, 180);
            g.drawString("Kliknite Space za novu igru", 155, 200);
        }
        
        if (ballX > 480) {
            play = false;
            ballXdirection = 0;
            ballYdirection = 0;
            g.setColor(Color.cyan);
            g.setFont(new Font("serif", Font.BOLD, 17));
            g.drawString("Pobedio je takmicar levo", 155, 180);
            g.drawString("Kliknite Space za novu igru", 155, 200);
        }
        
        g.dispose();
        
    }
    
    //pomeranje levog reketa
    public void moveRacketLup () {
        play = true;
        racketLY -= 15;
    }
    
    public void moveRacketLdown () {
        play = true;
        racketLY += 15;
    }
    
    //pomeranje desnog reketa
    public void moveRacketRup () {
        play = true;
        racketRY -= 15;
    }
    
    public void moveRacketRdown () {
        play = true;
        racketRY += 15;
    }
    
    //logika loptice
    @Override
    public void actionPerformed(ActionEvent e) {
        
        timer.start();
        
        if (play) {
            //kada udari u levi reket
            if (new Rectangle(ballX, ballY, 15, 15).intersects(new Rectangle(racketLX, racketLY, 15, 65))) {
                ballXdirection = -ballXdirection;
                if (ballSpeed != 3) {
                    ballSpeed-=1;
                    timer.setDelay(ballSpeed);
                }
            }
            //kada udari u desni reket
            if (new Rectangle(ballX, ballY, 15, 15).intersects(new Rectangle(racketRX, racketRY, 15, 65))) {
                ballXdirection = -ballXdirection;
                if (ballSpeed != 3) {
                    ballSpeed-=1;
                    timer.setDelay(ballSpeed);
                }
            }
            //kada dodirne gornji zid
            if (new Rectangle(ballX, ballY, 15, 15).intersects(new Rectangle(0, 0, 500, 15))) {
                ballYdirection = -ballYdirection;
            }
            //kada dodirne donji zid
            if (new Rectangle(ballX, ballY, 15, 15).intersects(new Rectangle(0, 450, 500, 15))) {
                ballYdirection = -ballYdirection;
            }
            
            ballX += ballXdirection;
            ballY += ballYdirection;
        }
        
        repaint();
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    //blokira kontrole igraca
    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //levi reket UP   
        if (e.getKeyCode() == KeyEvent.VK_W) {
            if(racketLY <= 15) {
                racketLY = 15;
            }
            else {
                moveRacketLup();
            }
        }
        //levi reket DOWN
        if (e.getKeyCode() == KeyEvent.VK_S) {
            if(racketLY >= 385) {
                racketLY = 385;
            }
            else {
                moveRacketLdown();
            }
        }
        //desni reket UP
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if(racketRY <= 15) {
                racketRY = 15;
            }
            else {
                moveRacketRup();
            }
        }
        //desni reket DOWN
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if(racketRY >= 385) {
                racketRY = 385;
            }
            else {
                moveRacketRdown();
            }
        }
        //restartuje partiju
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            Random rand = new Random();
            int number;
            
            if (!play) {
                play = true;
                ballX = 245;
                ballY = 245;
                ballSpeed = 12;
                timer.setDelay(ballSpeed);
                racketLX = 0;
                racketLY = 190;
                racketRX = 475;
                racketRY = 200;
                //random pravac loptice nakon restarta
                do {
                    number = rand.nextInt(3)-1;
                    ballXdirection=number;
                }
                while (number==0);
                do {
                    number = rand.nextInt(3)-1;
                    ballYdirection=number;
                }
                while (number==0);
                
                repaint();
            }
        }
    }

    
    
    //geteri, seteri i toString
    
    public boolean isPlay() {
        return play;
    }

    public void setPlay(boolean play) {
        this.play = play;
    }

    public int getBallX() {
        return ballX;
    }

    public void setBallX(int ballX) {
        this.ballX = ballX;
    }

    public int getBallY() {
        return ballY;
    }

    public void setBallY(int ballY) {
        this.ballY = ballY;
    }

    public int getRacketLX() {
        return racketLX;
    }

    public void setRacketLX(int racketLX) {
        this.racketLX = racketLX;
    }

    public int getRacketLY() {
        return racketLY;
    }

    public void setRacketLY(int racketLY) {
        this.racketLY = racketLY;
    }

    public int getRacketRX() {
        return racketRX;
    }

    public void setRacketRX(int racketRX) {
        this.racketRX = racketRX;
    }

    public int getRacketRY() {
        return racketRY;
    }

    public void setRacketRY(int racketRY) {
        this.racketRY = racketRY;
    }

    public int getBallSpeed() {
        return ballSpeed;
    }

    public void setBallSpeed(int ballSpeed) {
        this.ballSpeed = ballSpeed;
    }

    public int getBallXdirection() {
        return ballXdirection;
    }

    public void setBallXdirection(int ballXdirection) {
        this.ballXdirection = ballXdirection;
    }

    public int getBallYdirection() {
        return ballYdirection;
    }

    public void setBallYdirection(int ballYdirection) {
        this.ballYdirection = ballYdirection;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    @Override
    public String toString() {
        return "Tennis{" + "play=" + play + ", ballX=" + ballX + ", ballY=" + ballY + ", racketLX=" + racketLX + ", racketLY=" + racketLY + ", racketRX=" + racketRX + ", racketRY=" + racketRY + ", ballSpeed=" + ballSpeed + ", ballXdirection=" + ballXdirection + ", ballYdirection=" + ballYdirection + ", timer=" + timer + '}';
    }
    
}
