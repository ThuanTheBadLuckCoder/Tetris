package Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;



public class MainMenu extends JFrame {
    JButton onePlayer = new JButton("1 Player");
    JButton twoPlayers = new JButton("2 Players");
    JButton howToPlay = new JButton("How To Play");
    JButton exit = new JButton("Exit");
    JButton exit1 = new JButton("Back");
    JFrame MainMenuFr = new JFrame("Tetris Time!");
    int a = -1;
    ActionEvent e;
    Tetris game;


    public MainMenu() {
        addButtons();
    }

    public void addButtons() {
        onePlayer.setBounds(95, 50, 200, 80);
        twoPlayers.setBounds(95, 150, 200, 80);
        howToPlay.setBounds(95, 250, 200, 80);
        exit.setBounds(125, 400, 120, 40);
        
        MainMenuFr.add(onePlayer);
        MainMenuFr.add(twoPlayers);
        MainMenuFr.add(howToPlay);
        MainMenuFr.add(exit);
        try {
            ImageIcon image = new ImageIcon(getClass().getResource("thayThong.jpg"));
            JLabel displayField = new JLabel(image);
            MainMenuFr.add(displayField);
        } catch(Exception e) {
            System.out.println("Image cannot be found");
        }
        MainMenuFr.setSize(400, 500);
        //MainMenuFr.setLayout(null);
        MainMenuFr.setResizable(false);
        MainMenuFr.setVisible(true);
        MainMenuFr.setLocationRelativeTo(null);


        onePlayer.addActionListener(new ActionListener() {
    
            @Override
            public void actionPerformed(ActionEvent e) {
                a = 1;
                //System.out.println(getA());
                game = new Tetris(a);
                MainMenuFr.setVisible(false);
                game.setVisible(true);
                //System.exit(0);
            }
    
        });

        twoPlayers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a = 2;
                //System.out.println(getA());
                game = new Tetris(a);
                MainMenuFr.setVisible(false);
                game.setVisible(true);
                //System.exit(0);
            }
        });

        howToPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame howToPlayButton = new JFrame();
                howToPlayButton.setSize(1000, 800);
                howToPlayButton.setVisible(true);
                howToPlayButton.setResizable(false);
                howToPlayButton.setLocationRelativeTo(null);

                exit1.setBounds(425, 700, 160, 40);
                howToPlayButton.add(exit1);


                try {
                    ImageIcon image = new ImageIcon(getClass().getResource("kethop.png"));
                    JLabel displayField = new JLabel(image);
                    howToPlayButton.add(displayField);
                } catch (Exception i) {
                    //System.out.println("Image cannot be found");
                }
                

                exit1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // TODO Auto-generated method stub
                        //System.out.println("0");
                        howToPlayButton.setVisible(false);
                        
                    }
                });
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                //System.out.println("0");
                System.exit(0);
                
            }
        });
    }
    
    public int getA() {
        return a;
    }
}


