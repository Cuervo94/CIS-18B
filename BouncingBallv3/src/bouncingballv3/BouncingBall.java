/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bouncingballv3;

/**
 *
 * @author Pedro Longo
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BouncingBall extends JPanel implements Runnable{

    public int xPos, yPos, dx = 5, dy = 5;
    public int diameter = 50;

    public BouncingBall() {
        final JFrame thisFrame = new JFrame();
        thisFrame.add(this);
        thisFrame.setTitle("Bouncing Ball");
        thisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        thisFrame.setLocationRelativeTo(null);
        thisFrame.setSize(500, 500);

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                xPos = e.getX();
                yPos = e.getY();
                Thread t = new Thread() {
                    @Override
                    public void run() {
                        while (true) {
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                System.out.println("Interrupted");
                            };
                            xPos += dx;
                            yPos += dy;

                            if (xPos + diameter >= thisFrame.getWidth() - diameter || xPos <= 0) {
                                dx *= -1;
                            }
                            if (yPos + diameter >= thisFrame.getHeight() - diameter || yPos <= 0) {
                                dy *= -1;
                            }
                            repaint();
                        }
                    }
                };
               t.start(); 
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        thisFrame.setVisible(true);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.blue);
        g.fillOval(xPos, yPos, diameter, diameter);
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
