package Tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import Tetris.Shape.Tetrominoes;
 
public class Board extends JPanel implements ActionListener {
        //checked
    private Tetris tetris;
    private final int BoardWidth = 10;
    private final int BoardHeight = 22;
    private Timer timer;
    private boolean isFallingFinished = false;
    private boolean isStarted = false;
    boolean isPaused = false;
    private boolean gameOver= false;
    private int numLinesRemoved = 0;
    private int curX = 0;
    private int curY = 0;
    private JLabel statusbar;
    private Shape curPiece;
    private Tetrominoes[] board;
    //checked
    public Board(Tetris parent, JLabel statusbar, TAdapter adapter) {
        initBoard(parent, statusbar, adapter);
    }

    public boolean getIsStarted(){
        return isStarted;
    }
    
	//checked
    private void initBoard(Tetris parent, JLabel statusbar, TAdapter adapter) {
        tetris = parent;
        setFocusable(true);
        curPiece = new Shape();
        timer = new Timer(400, this);
        timer.start(); 
 
        this.statusbar = statusbar;
        statusbar.setFont(new Font("Serif", Font.PLAIN, 30));
        board = new Tetrominoes[BoardWidth * BoardHeight];
        clearBoard();          
        addListener(adapter);
    }

    public void addListener(TAdapter adapter){
        adapter.addBoard(this);
        addKeyListener(adapter);
    }
    
    
    
    //checked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isPaused) {
            if (isFallingFinished) {
                isFallingFinished = false;
                newPiece();
            } else {
                oneLineDown();
            }
        }
        
    }
    //checked
    private int squareWidth() { 
        return (int) getSize().getWidth() / BoardWidth; 
    }
    private int squareHeight() { 
        return (int) getSize().getHeight() / BoardHeight; 
    }
    private Tetrominoes shapeAt(int x, int y) { 
        return board[(y * BoardWidth) + x]; 
    }
 
    //check
    public void start()  {
         
        if (isPaused)
            return;
 
        isStarted = true;
        isFallingFinished = false;
        numLinesRemoved = 0;
        clearBoard();
 
        newPiece();
        timer.start();
    }

    //checked
    public void pause()  {
         
        if (!isStarted)
            return;
 
        isPaused = !isPaused;
         
        if (isPaused) {
            timer.stop();
            statusbar.setText("paused");
        } else {
             
            timer.start();
            statusbar.setText(String.valueOf(numLinesRemoved));
        }
         
        repaint();
    }
    
    //checked
    private void doDrawing(Graphics g) {
            Dimension size = getSize();
            int boardTop = (int) size.getHeight() - BoardHeight * squareHeight();
 
            for (int i = 0; i < BoardHeight; ++i) {
             
                for (int j = 0; j < BoardWidth; ++j) {
                 
                    Tetrominoes shape = shapeAt(j, BoardHeight - i - 1);
                    
                    if (shape != Tetrominoes.NoShape)
                        drawSquare(g, 0 + j * squareWidth(),
                                boardTop + i * squareHeight(), shape);
                }
            }
 
            if (curPiece.getShape() != Tetrominoes.NoShape) {
                
                for (int i = 0; i < 4; ++i) { 
                    int x = curX + curPiece.x(i); 
                    int y = curY - curPiece.y(i); 
                    drawSquare(g, 0 + x * squareWidth(), boardTop + (BoardHeight - y - 1) * squareHeight(), curPiece.getShape()); 
                } 
            }
        
        
    } 
    //checked
    @Override
    public void paintComponent(Graphics g) { 
        super.paintComponent(g); 
        doDrawing(g); 
    } 
    //checked
    public void dropDown() { 
        if(!isPaused) {
            int newY = curY; 
            while (newY > 0) {
                 
                if (!tryMove(0, -1))
                    break;
                --newY;
              
            }
            pieceDropped();  
        }
         
        
    }
    //checked
    public void oneLineDown()  {
        
            if (!tryMove(0, -1))
            pieceDropped();
        
        
    }
 
    //checked
    private void clearBoard() {
        
            for (int i = 0; i < BoardHeight * BoardWidth; ++i)
                board[i] = Tetrominoes.NoShape;
        
        
    }
 
    //checked
    private void pieceDropped() {
        if (!isPaused) {
            for (int i = 0; i < 4; ++i) {
             
                int x = curX + curPiece.x(i);
                int y = curY - curPiece.y(i);
                board[(y * BoardWidth) + x] = curPiece.getShape();
            }
     
            removeFullLines();
     
            if (!isFallingFinished)
                newPiece();
        }
        
    }
    //checked
    private void newPiece()  {
        if (!isPaused) {
            curPiece.setRandomShape();
            curX = BoardWidth / 2 - 1;
            curY = BoardHeight - 1 + curPiece.minY();
     
            if (!tryMove(0, 0)) {
                 
                curPiece.setShape(Tetrominoes.NoShape);
                timer.stop();
                isStarted = false;
                statusbar.setText("Game Over!!!");
                if(!gameOver)
                    tetris.gameOver();
                gameOver = true;
            }
        }
        
    }
    //checked
    public boolean tryMove(int difX, int difY) {
        if(!isPaused) {
            for (int i = 0; i < 4; ++i) {
                int x = curX + difX + curPiece.x(i);
                int y = curY + difY - curPiece.y(i);
                 
                
                if (x < 0 || x >= BoardWidth || y < 0 || y >= BoardHeight)
                    return false;
                 
                if (shapeAt(x, y) != Tetrominoes.NoShape)
                    return false;
            }
    
            curX += difX;
            curY += difY;
     
            repaint();
     
           
        }

        return true;
    }

    public boolean rotateLeft() {
        Shape temp = new Shape(curPiece);
        curPiece = curPiece.rotateLeft();
        if(tryMove(0, 0))
            return true;
        else{
            curPiece = temp;
            return false;
        }
    }
    //checked
    private void removeFullLines() {
        if (!isPaused) {
            int numFullLines = 0;
 
            for (int i = BoardHeight - 1; i >= 0; --i) {
                boolean lineIsFull = true;
     
                for (int j = 0; j < BoardWidth; ++j) {
                    if (shapeAt(j, i) == Tetrominoes.NoShape) {
                        lineIsFull = false;
                        break;
                    }
                }
     
                if (lineIsFull) {
                    ++numFullLines;
                    for (int k = i; k < BoardHeight - 1; ++k) {
                        for (int j = 0; j < BoardWidth; ++j) 
                            board[(k * BoardWidth) + j] = shapeAt(j, k + 1); 
                    } 
                } 
            } 
            if (numFullLines > 0) {
                 
                numLinesRemoved += numFullLines;
                statusbar.setText(String.valueOf(numLinesRemoved));
                isFallingFinished = true;
                curPiece.setShape(Tetrominoes.NoShape);
                repaint();
            }
        }
        
     }
    //checked
    private void drawSquare(Graphics g, int x, int y, Tetrominoes shape)  {
         
        Color colors[] = {
            //NoShape
            new Color(0, 0, 0),
            //ZShape
            new Color(204, 102, 102),
            //oZ
            new Color(102, 204, 102),
            //L
            //I
            new Color(204, 102, 204),
            //T
            new Color(218, 170, 0),
            //Square
            new Color(102, 204, 204),
            new Color(102, 102, 204),
            //oL
            new Color(204, 204, 102)
        };
 
        Color color = colors[shape.ordinal()];
 
        g.setColor(color);
        g.fillRect(x + 4, y + 4, squareWidth() - 4, squareHeight() - 4);
 
        g.setColor(color.brighter());
        g.drawLine(x, y + squareHeight() - 2, x, y);
        g.drawLine(x, y, x + squareWidth() - 2, y);
 
        g.setColor(color.darker());
        g.drawLine(x + 2, y + squareHeight() - 2,
                         x + squareWidth() - 2, y + squareHeight() - 2);
        g.drawLine(x + squareWidth() - 2, y + squareHeight() - 2,
                         x + squareWidth() - 2, y + 2);
 
    }
}
