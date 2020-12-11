
package boardgame;
import java.awt.*;

public class Board {
    private final static int NUM_ROWS = 25;
    private final static int NUM_COLUMNS = 25;      
    private static Tile board[][] = new Tile[NUM_ROWS][NUM_COLUMNS];
    
    
    public static void Reset() {
        for (int zrow=0;zrow<NUM_ROWS;zrow++)
            for (int zcol=0;zcol<NUM_COLUMNS;zcol++)
                board[zrow][zcol] = null;  
    }
    public static void CheckValidPawnPlacement(int xpixel,int ypixel) {
        if (xpixel < 0 || xpixel > Window.getWidth2())
            return;
        if (ypixel < 0 || ypixel > Window.getHeight2())
            return;
        
        if(Player.GetCurrentPlayer().getColor() == Color.RED && xpixel > Window.getWidth2()/2) {
            return;
        }
        if(Player.GetCurrentPlayer().getColor() == Color.BLUE && xpixel < Window.getWidth2()/2) {
            return;
        }
        
        int ydelta = Window.getHeight2()/NUM_ROWS;
        int xdelta = Window.getWidth2()/NUM_COLUMNS;
       
        int column = xpixel/xdelta;   
        int row = ypixel/ydelta; 

        
        if(board[row][column] == null) {
            Player.GetCurrentPlayer().placePawn(xpixel,ypixel);
        }
        else
            return;
    }
    public static void CheckValidWallPlacement(int xpixel,int ypixel) {
        
        if (xpixel < 0 || xpixel > Window.getWidth2())
            return;
        if (ypixel < 0 || ypixel > Window.getHeight2())
            return;   
        
        if(Player.GetCurrentPlayer().getColor() == Color.RED && xpixel > Window.getWidth2()/2) {
            
            return;
        }
        if(Player.GetCurrentPlayer().getColor() == Color.BLUE && xpixel < Window.getWidth2()/2) {
            
            return;
        }
        
        int ydelta = Window.getHeight2()/NUM_ROWS;
        int xdelta = Window.getWidth2()/NUM_COLUMNS;
       
        int column = xpixel/xdelta;   
        int row = ypixel/ydelta; 
        
        
        if(board[row][column] == null) {
            Player.GetCurrentPlayer().placePiece(xpixel,ypixel);
        }
        else
            return;
        
    }
    
    public static void AddWallPiece(int xpixel,int ypixel) {
        
        
        int ydelta = Window.getHeight2()/NUM_ROWS;
        int xdelta = Window.getWidth2()/NUM_COLUMNS;
        
//Find the column to add the piece to.        
        int column = xpixel/xdelta;   
        int row = ypixel/ydelta;   

        Piece piece = new Piece();

        
        board[row][column] = new Tile(Player.GetCurrentPlayer().getColor());
        board[row][column] = piece;
        board[row][column].piPoint = piece;
        
        if(Player.GetCurrentPlayer().walls == 0 && Player.GetCurrentPlayer().pawns == 0) 
        Player.SwitchTurn();
        
        
    }
    
    public static void AddPawnPiece(int xpixel,int ypixel) {
         
        int ydelta = Window.getHeight2()/NUM_ROWS;
        int xdelta = Window.getWidth2()/NUM_COLUMNS;
        
//Find the column to add the piece to.        
        int column2 = xpixel/xdelta;   
        int row2 = ypixel/ydelta;   
        
        Pawn pawn = new Pawn();
        
        board[row2][column2] = new Tile(Player.GetCurrentPlayer().getColor());
        board[row2][column2] = pawn;
        board[row2][column2].pPoint = pawn;
        
        if(Player.GetCurrentPlayer().walls == 0 && Player.GetCurrentPlayer().pawns == 0) 
        Player.SwitchTurn();
        
    }
//    
    
    public static void Draw(Graphics2D g) {
        int ydelta = Window.getHeight2()/NUM_ROWS;
        int xdelta = Window.getWidth2()/NUM_COLUMNS;
        int fontSize = 20;
        
        
            
            
         Color middleGrey = new Color(116,116,116);
        g.setColor(middleGrey);
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
        
        
        //drawing pawns
        for (int zrow=0;zrow<NUM_ROWS;zrow++)
        {
            for (int zcol=0;zcol<NUM_COLUMNS;zcol++)        
            {
                if (board[zrow][zcol] != null)
                    if (board[zrow][zcol].pPoint != null) {
                        board[zrow][zcol].pPoint.draw(g, zrow, zcol,xdelta, ydelta);
                    }
                    
            }
        }    
        //drawing walls (pieces)
        for (int zrow=0;zrow<NUM_ROWS;zrow++)
        {
            for (int zcol=0;zcol<NUM_COLUMNS;zcol++)        
            {
                 if (board[zrow][zcol] != null)
                    if (board[zrow][zcol].piPoint != null) {
                        board[zrow][zcol].piPoint.draw(g, zrow, zcol,xdelta, ydelta);
                        
                    }
            }
        }
        //////////Showing whose turn it is
        g.setColor(Color.white);
        g.fillRect(Window.getWidth2()/2-85, Window.getHeight()+35, 200, 20);
        
        if(Player.GetCurrentPlayer().getColor() == Color.RED) {
            g.setColor(Color.RED);
            g.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,fontSize));
            g.drawString("RED PLAYER'S TURN",Window.getWidth2()/2-fontSize*4,Window.getHeight()+50);
        }
        else {
            g.setColor(Color.BLUE);
            g.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,fontSize-1));
            g.drawString("BLUE PLAYER'S TURN",Window.getWidth2()/2-fontSize*4,Window.getHeight()+50);
        }
        
        ////////////////////////////////// Showing remaining walls and pawns
       
            g.setColor(Color.white);
            g.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,fontSize));
            g.drawString("WALLS LEFT:"+Player.GetBluePlayer().walls+"",Window.getWidth2()/2+150,Window.getHeight()+50);
            g.drawString("PAWNS LEFT:"+Player.GetBluePlayer().pawns+"",Window.getWidth2()/2+350,Window.getHeight()+50);
       
       
            g.setColor(Color.white);
            g.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,fontSize));
            g.drawString("WALLS LEFT:"+Player.GetRedPlayer().walls+"",Window.getWidth2()/2-250,Window.getHeight()+50);
            g.drawString("PAWNS LEFT:"+Player.GetRedPlayer().pawns+"",Window.getWidth2()/2-450,Window.getHeight()+50);
       ////////////////////////////////////////
//        for (int zrow=0;zrow<NUM_ROWS;zrow++)
//        {
//            board[zrow][12] = new Tile(Color.BLACK);
//            g.setColor(Color.BLACK);
//            g.fillRect(Window.getX(12*xdelta), Window.getY(zrow*ydelta), xdelta, ydelta);
//        }
            
    }
    
    
 
}