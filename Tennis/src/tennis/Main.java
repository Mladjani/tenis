/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tennis;

import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author Nikola
 */
public class Main {
    public static void main(String[] args) {
        
        Tennis tennis = new Tennis();
        
        JFrame jf = new JFrame("Tennis");
        jf.setSize(500, 500);
        jf.setResizable(false);
        jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        jf.add(tennis);
        
        jf.setVisible(true);
        
    }
}
