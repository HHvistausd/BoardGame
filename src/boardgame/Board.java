
package boardgame;
import java.awt.*;

public class Board {
    private final static int NUM_ROWS = 24;
    private final static int NUM_COLUMNS = 24;      
    private static Tile board[][] = new Tile[NUM_ROWS][NUM_COLUMNS];

    
    public static void Reset() {
        for (int zrow=0;zrow<NUM_ROWS;zrow++)
            for (int zcol=0;zcol<NUM_COLUMNS;zcol++)
                board[zrow][zcol] = null;  
    }

    public static void AddWallPiece(int xpixel,int ypixel) {
        if (xpixel < 0 || xpixel > Window.getWidth2())
            return;
        if (ypixel < 0 || ypixel > Window.getHeight2())
            return;   
        
        int ydelta = Window.getHeight2()/NUM_ROWS;
        int xdelta = Window.getWidth2()/NUM_COLUMNS;
        
//Find the column to add the piece to.        
        int column = xpixel/xdelta;   
        int row = ypixel/ydelta;   

        
//Find the row to add the piece to.        
//        int theRow = NUM_ROWS-1;
//        while (theRow != -1 && board[theRow][column] != null)
//        {
//            theRow--; 
//        }
        
//Check if the column is full.        
//        if (theRow == -1)
//            return;               

//Add a piece to the board.
//add or modify.  Pass in the shape of the current player.
        board[row][column] = new Tile(Player.GetCurrentPlayer().getShape());
        
        Player.SwitchTurn();
    }
    
    
    
    public static void Draw(Graphics2D g) {
        int ydelta = Window.getHeight2()/NUM_ROWS;
        int xdelta = Window.getWidth2()/NUM_COLUMNS;


        g.setColor(Color.black);
        for (int zi = 1;zi<NUM_ROWS;zi++)
        {
            g.drawLine(Window.getX(0),Window.getY(zi*ydelta),
                    Window.getX(Window.getWidth2()),Window.getY(zi*ydelta));
        }
        
        for (int zi = 1;zi<NUM_COLUMNS;zi++)
        {
            g.drawLine(Window.getX(zi*xdelta),Window.getY(0),
                    Window.getX(zi*xdelta),Window.getY(Window.getHeight2()));
        }
        for (int zrow=0;zrow<NUM_ROWS;zrow++)
        {
            for (int zcol=0;zcol<NUM_COLUMNS;zcol++)        
            {
                if (board[zrow][zcol] != null)
                    board[zrow][zcol].draw(g, zrow, zcol,xdelta, ydelta);
            }
        }         
        
    }
    
    
 
}