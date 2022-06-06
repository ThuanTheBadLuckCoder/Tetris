package Tetris;

import java.awt.*;

import javax.sound.sampled.AudioSystem;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.*;

public class Tetris extends JFrame {
    private JLabel statusbar;
    private JLabel statusbar1;
    static JPanel  mainPanel = new JPanel();
    JButton playAgain;
    int player;
    static MainMenu a = new MainMenu();
    
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        
        /* SwingUtilities.invokeLater(new Runnable() {
             
            @Override
            public void run() {
                while (a.getA() != -1) {
                    int numberOfPlayer = a.getA();
                    a.MainMenuFr.setVisible(false);
                    if (numberOfPlayer == 1 || numberOfPlayer == 2) {
                        Tetris game = new Tetris(numberOfPlayer);
                        game.setVisible(true);
                        System.out.print("da chay");
                        
                    }
                    else {
                        System.out.print("khong chay");
                }
            }

            }
        }); */
        
    }

    public void playSound() {{
        new Thread(new Runnable() {
            public void run() {
                try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("music.wav").getAbsoluteFile());
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
                } catch(Exception ex) {
                    System.out.println("Error with playing sound.");
                    ex.printStackTrace();
                }
            }
        }).start();
    }    
}


    

    public Tetris(int numberOfPlayer) {
        playSound();
        mainPanel = new JPanel();
        player = numberOfPlayer;
        playAgain = new JButton("Back To Menu?");
        playAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                a.MainMenuFr.setVisible(true);
            }
        });
        add(playAgain);
        playAgain.setVisible(false);
        if (numberOfPlayer == 1) {
            //System.out.println("so 1");
            initUI();
        }
        else if (numberOfPlayer == 2) {
            //System.out.println("so 2");
            initUI2Players();
        }
        
    }

    int count = 0;
    public void gameOver() {
        count++;
        if(player == 1) {
            playAgain.setBounds(110 ,650, 180, 60);
            playAgain.setVisible(true);
        } else {
            if(count == 2){
                playAgain.setBounds(310 ,650, 180, 60);
                playAgain.setVisible(true);
            }
        }
    }  

    private void initUI2Players() {
        TAdapter adapter = new TAdapter();
 
        statusbar = new JLabel(" 0");
        
        
        Board board = new Board(this, statusbar, adapter);
        board.add(statusbar, BorderLayout.PAGE_END); 
        board.start();

        statusbar1 = new JLabel(" 0");

        
        Board board1 = new Board(this, statusbar1, adapter);
        
        board1.add(statusbar1, BorderLayout.PAGE_END);
        mainPanel.add(board1);
        mainPanel.add(board);
        board1.start();

        

        mainPanel.setLayout(new GridLayout(0, 2,5,2));
        mainPanel.setSize(800,800);
        setBackground(Color.black);

        add(mainPanel);

        setResizable(false);
 
        setSize(800, 800);
        setTitle("Tetris");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);   
        setResizable(false);  

        //set mau nen 
        board.setBackground(Color.DARK_GRAY);
        //board1.setBackground(Color.white);

        
        
    }

    public void initUI() {
        TAdapter adapter = new TAdapter();
        
        statusbar = new JLabel(" 0");
        add(statusbar, BorderLayout.SOUTH);
        Board board = new Board(this, statusbar, adapter);
        add(board);
        board.start();
 
        setSize(400, 800);
        setTitle("Tetris");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  
        setResizable(false);
        
        //set mau nen
        board.setBackground(Color.black);
    }
    
 
    // public JLabel getStatusBar() {
        
    //    return statusbar;
    // }
}