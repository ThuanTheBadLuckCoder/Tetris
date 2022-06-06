package Tetris;
import java.util.Random;
 
public class Shape {
 
    public enum Tetrominoes { NoShape, ZShape, oZShape, IShape, 
            TShape, SquareShape, LShape, oLShape };
 
    private Tetrominoes pieceShape;
    private int coords[][] = new int[4][2];
    private int[][][] coordsTable;
 
 
    public Shape() {
        setShape(Tetrominoes.NoShape);
    }

    public Shape(Shape shape) {
        this.pieceShape = shape.pieceShape;
        for (int i = 0; i < 4; ++i) {
            this.setX(i, shape.x(i));
            this.setY(i, shape.y(i));
        }
    }
 
    public void setShape(Tetrominoes shape) {
 
        coordsTable = new int[][][] {
            //NoShape1
            {{0,0}, {0,0}, {0,0}, {0,0}},
            //ZShape2
            {{0,1}, {0,0}, {1,0}, {1,-1}},
            //oZShape3
            {{0,1}, {0,0}, {-1, 0}, {-1,-1}},
            //IShape6
            //{{2,0}, {1,0}, {0,0}, {-1,0}},
            {{ 0, -1 },  { 0, 0 },   { 0, 1 },   { 0, 2 }},
            //TShape7
            {{1,0}, {0,0}, {0,1}, {-1,0}},
            //SquareShape
            {{1,0}, {1,1}, {0,0}, {0,1}},
            //LShape4
            {{1,-1}, {0,-1}, {0,0}, {0,1}},
            //oLShape5
            {{0,-1}, {0,0}, {0,1}, {-1,-1}}
        };
 
        for (int i = 0; i < 4 ; i++) {
             
            for (int j = 0; j < 2; ++j) {
                 
                coords[i][j] = coordsTable[shape.ordinal()][i][j];
            }
        }
         
        pieceShape = shape;
    }
 
    
    
 
    public void setRandomShape() {
         
        Random r = new Random();
        int x = Math.abs(r.nextInt()) % 7 + 1;
        Tetrominoes[] values = Tetrominoes.values(); 
        setShape(values[x]);
    }
 
    public int minX() {
         
        int m = coords[0][0];
        
        for (int i = 0; i < 4; i++) {
            
            m = Math.min(m, coords[i][0]);
        }
        
        return m;
    }
 
 
    public int minY() {
         
        int m = coords[0][1];
        
        for (int i = 0; i < 4; i++) {
            
            m = Math.min(m, coords[i][1]);
      }
       
      return m;
    }

    public int x(int index) { 
        return coords[index][0]; 
    }
    public int y(int index) { 
        return coords[index][1]; 
    }
    public Tetrominoes getShape()  { 
        return pieceShape; 
    }

    private void setX(int index, int x) { 
        coords[index][0] = x; 
    }

    private void setY(int index, int y) { 
        coords[index][1] = y; 
    }

    public Shape rotateLeft() {
        if (pieceShape == Tetrominoes.SquareShape) {
            return this;
        }

        Shape result = new Shape();
        result.pieceShape = pieceShape;
        for (int i = 0; i < 4; ++i) {
            result.setX(i, y(i));
            result.setY(i, -x(i));
        }
        return result;
    }
 
    /*public Shape rotateRight() {
         
        if (pieceShape == Tetrominoes.SquareShape) {
            return this;
        }

        else if (pieceShape == Tetrominoes.IShape) {

        }
        
        else {    
            Shape result = new Shape();
            result.pieceShape = pieceShape;
 
            for (int i = 0; i < 4; ++i) {
 
                result.setX(i, -y(i));
                result.setY(i, x(i));
        }
         
        return result;
        }
        
        }  */

        
    }
