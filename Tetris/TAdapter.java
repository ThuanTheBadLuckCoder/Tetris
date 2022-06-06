package Tetris;

import java.awt.event.*;
import java.util.ArrayList;

public class TAdapter extends KeyAdapter {
    ArrayList<Board> boards = new ArrayList<>();

    public void addBoard(Board board) {
        boards.add(board);
    }
         
    @Override
    public void keyPressed(KeyEvent e) {
        /*
        if (!isStarted || curPiece.getShape() == Tetrominoes.NoShape) {  
            return;
        }*/

        int keycode = e.getKeyCode();

        if (keycode == 'p' || keycode == 'P') {
            for(Board board : boards)
                board.pause();
            return;
        }
        

        /*if (isPaused)
            return;*/
        
        switch (boards.size()) {
            case 2:
                controlPlayer2(keycode);
            case 1:
                controlPlayer1(keycode);
                break;
            default:
                break;
        } 
    }

    private void controlPlayer1(int keycode){
        Board board = boards.get(0);
        if (!board.isPaused) {
            switch (keycode) {
                case KeyEvent.VK_LEFT:
                    board.tryMove(-1, 0);
                    break;
    
                case KeyEvent.VK_RIGHT:
                    board.tryMove(1, 0);
                    break;
    
                case KeyEvent.VK_UP:
                    board.rotateLeft();
                    break;
    
                case KeyEvent.VK_DOWN:
                    board.oneLineDown();
                    break;
                case KeyEvent.VK_L:
                    board.dropDown();
                    break;
                case KeyEvent.VK_P:
                    board.pause();
                    break;
            }
        }
        
    }

    private void controlPlayer2(int keycode){
        Board board = boards.get(1);
        if (!board.isPaused) {
            switch (keycode) {
                case KeyEvent.VK_A:
                    board.tryMove(-1, 0);
                    break;
    
                case KeyEvent.VK_D:
                    board.tryMove(1, 0);
                    break;
    
                case KeyEvent.VK_W:
                    board.rotateLeft();
                    break;
    
                case KeyEvent.VK_S:
                    board.oneLineDown();
                    break;
    
                case KeyEvent.VK_G:
                    board.dropDown();
                    break;
            }
        }
        
    }
}
