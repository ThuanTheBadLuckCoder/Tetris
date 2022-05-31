package codemau;

import java.awt.*;

import javax.swing.*;

 
 
public class Tetris extends JFrame {
    private JLabel statusbar;
    private JLabel statusbar1;
    
    static MainMenu a = new MainMenu();
    
    public static void main(String[] args) {
        
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


    public Tetris(int numberOfPlayer) {
    
        if (numberOfPlayer == 1) {
            System.out.println("so 1");
            initUI();
        }
        else if (numberOfPlayer == 2) {
            System.out.println("so 2");
            initUI2Players();
        }
        
    }

    
     
    private void initUI2Players() {
        JPanel mainPanel = new JPanel();

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
        mainPanel.setBackground(Color.black);

        add(mainPanel);

        setResizable(false);
 
        setSize(800, 800);
        setTitle("Tetris");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);   
        setResizable(false);  

        //set mau nen 
        board.setBackground(Color.DARK_GRAY);
        board1.setBackground(Color.white);
        
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
        
        //set mau nen
        board.setBackground(Color.black);
    }
    
 
    public JLabel getStatusBar() {
        
       return statusbar;
    }
}