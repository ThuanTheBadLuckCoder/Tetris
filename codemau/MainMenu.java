package codemau;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class MainMenu extends JFrame {
    JButton onePlayer = new JButton("1 Player");
    JButton twoPlayers = new JButton("2 Players");
    JButton exit = new JButton("exit");
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
        exit.setBounds(125, 400, 120, 40);
        MainMenuFr.add(onePlayer);
        MainMenuFr.add(twoPlayers);
        MainMenuFr.add(exit);
        MainMenuFr.setSize(400, 500);
        MainMenuFr.setLayout(null);
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
