/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package wiregram;

import javax.swing.JFrame;
import wiregram.gui.DiagramEditor;

/**
 *
 * @author scyth
 */
public class Wiregram {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Wiregram");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setContentPane(new DiagramEditor());
        frame.setSize(1024,768);
        frame.setVisible(true);
    }
    
}
