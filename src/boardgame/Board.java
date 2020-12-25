
package boardgame;
import java.awt.*;
import javax.sound.sampled.*;


public class Board {
    private final static int NUM_ROWS = 25;
    private final static int NUM_COLUMNS = 25;      
    private static Tile board[][] = new Tile[NUM_ROWS][NUM_COLUMNS];
    private static boolean invalidPlacement;
    private static boolean setupPhase = true;
    
    
    public static void Reset() {
        for (int zrow=0;zrow<NUM_ROWS;zrow++)
            for (int zcol=0;zcol<NUM_COLUMNS;zcol++)
                board[zrow][zcol] = null;  
        
        setUpJewels();
        invalidPlacement = false;
        setupPhase = true;
    }
    
    public static void setUpJewels() {
        int row2 = NUM_ROWS/2;
        int col2 = NUM_COLUMNS-1;
        Color color = Player.GetRedPlayer().getColor();
        
        Jewel jewel = new Jewel(color);
        
        board[row2][0] = new Tile(color);
        board[row2][0] = jewel;
        board[row2][0].jPoint = jewel;
        
        color = Player.GetBluePlayer().getColor();
        jewel = new Jewel(color);
        
        board[row2][col2] = new Tile(color);
        board[row2][col2] = jewel;
        board[row2][col2].jPoint = jewel;
    }
    
    
    public static void CheckValidPawnPlacement(int xpixel,int ypixel) {
        sound denySelect = null;
        
        if (xpixel < 0 || xpixel > Window.getWidth2())
            return;
        if (ypixel < 0 || ypixel > Window.getHeight2())
            return;
        
        if(Player.GetCurrentPlayer() == Player.GetRedPlayer() && xpixel > Window.getWidth2()/2) {
            invalidPlacement = true;
            if(setupPhase) {
            denySelect = new sound("denyselect.wav");
            }
            return;
        }
        if(Player.GetCurrentPlayer() == Player.GetBluePlayer() && xpixel < Window.getWidth2()/2) {
            invalidPlacement = true;
            if(setupPhase) {
            denySelect = new sound("denyselect.wav");
            }
            return;
        }
        
        int ydelta = Window.getHeight2()/NUM_ROWS;
        int xdelta = Window.getWidth2()/NUM_COLUMNS;
       
        int column = xpixel/xdelta;   
        int row = ypixel/ydelta; 
        
        invalidPlacement = false;
        
        if(board[row][column] == null) {
            Player.GetCurrentPlayer().placePawn(xpixel,ypixel);
        }

        else
            return;
    }
    public static void CheckValidWallPlacement(int xpixel,int ypixel) {
        
        sound denySelect = null;
        
        if (xpixel < 0 || xpixel > Window.getWidth2())
            return;
        if (ypixel < 0 || ypixel > Window.getHeight2())
            return;   
        
        if(Player.GetCurrentPlayer() == Player.GetRedPlayer() && xpixel > Window.getWidth2()/2) {
            invalidPlacement = true;
            if(setupPhase) {
            denySelect = new sound("denyselect.wav");
            }
            return;
        }
        if(Player.GetCurrentPlayer() == Player.GetBluePlayer() && xpixel < Window.getWidth2()/2) {
            invalidPlacement = true;
            if(setupPhase) {
            denySelect = new sound("denyselect.wav");
            }
            return;
        }
        
        int ydelta = Window.getHeight2()/NUM_ROWS;
        int xdelta = Window.getWidth2()/NUM_COLUMNS;
       
        int column = xpixel/xdelta;   
        int row = ypixel/ydelta; 
        
        invalidPlacement = false;
        
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
    
    public static void ShiftPawnPiece(int currentRow,int currentColumn,boolean selected) {
         
        Pawn pawn = new Pawn();
        
        board[currentRow][currentColumn] = new Tile(Player.GetCurrentPlayer().getColor());
        board[currentRow][currentColumn] = pawn;
        board[currentRow][currentColumn].pPoint = pawn;
        
        if(Player.GetCurrentPlayer().rightCalled == true && board[currentRow][currentColumn-1] != null) {
            if(board[currentRow][currentColumn-1].getCurrentPlayer() == Player.GetCurrentPlayer())
            board[currentRow][currentColumn-1]= null;
        }
        else if(Player.GetCurrentPlayer().leftCalled == true && board[currentRow][currentColumn+1] != null) {
            if(board[currentRow][currentColumn+1].getCurrentPlayer() == Player.GetCurrentPlayer())
            board[currentRow][currentColumn+1]= null;
        }
        else if(Player.GetCurrentPlayer().upCalled == true && board[currentRow+1][currentColumn] != null) {
            if(board[currentRow+1][currentColumn].getCurrentPlayer() == Player.GetCurrentPlayer())
            board[currentRow+1][currentColumn] = null;
        }
        else if(Player.GetCurrentPlayer().downCalled == true && board[currentRow-1][currentColumn] != null){
            if(board[currentRow-1][currentColumn].getCurrentPlayer() == Player.GetCurrentPlayer())
            board[currentRow-1][currentColumn]= null;
        }
        
        if(Player.GetCurrentPlayer().walls == 0 && Player.GetCurrentPlayer().pawns == 0) {
        Player.SwitchTurn();
        }
        
    }
    
    public static void MouseSelect(int xpixel,int ypixel) {
        
        if (xpixel < 0 || xpixel > Window.getWidth2())
            return;
        if (ypixel < 0 || ypixel > Window.getHeight2())
            return;
        
        boolean selected = false;
        
        int ydelta = Window.getHeight2()/NUM_ROWS;
        int xdelta = Window.getWidth2()/NUM_COLUMNS;
        
        int columnSelect = xpixel/xdelta;   
        int rowSelect = ypixel/ydelta; 
        
        if(Player.GetBluePlayer().walls== 0 && Player.GetBluePlayer().pawns == 0 && 
           Player.GetRedPlayer().walls== 0 && Player.GetRedPlayer().pawns == 0) 
        {
            if(board[rowSelect][columnSelect] != null && board[rowSelect][columnSelect] != board[rowSelect][columnSelect].piPoint) {
               selected = true;
            }
        }
        
        if(Player.GetCurrentPlayer() == Player.GetRedPlayer() && board[rowSelect][columnSelect].getColor() == Player.GetBluePlayer().getColor() 
            || board[rowSelect][columnSelect] == null) {
            selected = false;
            return;
        }
        if(Player.GetCurrentPlayer() == Player.GetBluePlayer() && board[rowSelect][columnSelect].getColor() == Player.GetRedPlayer().getColor()
            || board[rowSelect][columnSelect] == null) {
            selected = false;
           return;
        }
        
        
        if(selected){
        System.out.println("successful");
        }
        
        Player.mouseValues(rowSelect, columnSelect, selected);
        
                
    }
    
    public static void MovePawnPiece() {
        
        
        int rowDir = Player.GetCurrentPlayer().rowDir;
        int columnDir = Player.GetCurrentPlayer().columnDir;
        
        int currentRow = Player.GetCurrentPlayer().rowSelect;
        int currentColumn = Player.GetCurrentPlayer().columnSelect;
        boolean selected = Player.GetCurrentPlayer().selected;
        
        if(selected && Player.GetCurrentPlayer().moveCalled == true) {
            if (board[currentRow+rowDir][currentColumn+columnDir] == null)
            {
            currentRow += rowDir;
            currentColumn+= columnDir;
            Board.ShiftPawnPiece(currentRow, currentColumn, selected);
            }
        }
//        selected = false;
        Player.GetCurrentPlayer().selected = false;
        Player.GetCurrentPlayer().rowDir = 0;
        Player.GetCurrentPlayer().columnDir = 0;
        
    }
    
    public static void Draw(Graphics2D g) {
        int ydelta = Window.getHeight2()/NUM_ROWS;
        int xdelta = Window.getWidth2()/NUM_COLUMNS;
        int fontSize = 19;
        
        
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
        for (int zrow=0;zrow<NUM_ROWS;zrow++)
        {
            for (int zcol=0;zcol<NUM_COLUMNS;zcol++)        
            {
                 if (board[zrow][zcol] != null)
                    if (board[zrow][zcol].jPoint != null) {
                        board[zrow][zcol].jPoint.draw(g, zrow, zcol,xdelta, ydelta);
                        
                    }
            }
        }
        //////////Showing whose turn it is
        g.setColor(Color.white);
        g.fillRect(Window.getWidth2()/2-85, Window.getHeight()+35, 200, 30);
        
        if(Player.GetCurrentPlayer() == Player.GetRedPlayer()) {
            g.setColor(Color.RED);
            g.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,fontSize));
            g.drawString("RED PLAYER'S TURN",Window.getWidth2()/2-fontSize*4,Window.getHeight()+50);
        }
        else {
            g.setColor(Color.BLUE);
            g.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,fontSize-1));
            g.drawString("BLUE PLAYER'S TURN",Window.getWidth2()/2-fontSize*4,Window.getHeight()+50);
        }
        if(Player.GetRedPlayer().walls == 0 && Player.GetRedPlayer().pawns == 0 && 
           Player.GetBluePlayer().walls == 0 && Player.GetBluePlayer().pawns == 0) {
            setupPhase = false;
        }
        //////////////////////Showing the phase
        if(setupPhase) {
            if(Player.GetCurrentPlayer() == Player.GetRedPlayer()) {
                g.setColor(Color.RED);
                g.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,fontSize-2));
                g.drawString("SETUP",Window.getWidth2()/2-fontSize,Window.getHeight()+63);
            }
            if(Player.GetCurrentPlayer() == Player.GetBluePlayer()) {
                g.setColor(Color.BLUE);
                g.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,fontSize-2));
                g.drawString("SETUP",Window.getWidth2()/2-fontSize,Window.getHeight()+63);
            }
        }
        if(setupPhase == false) {
            if(Player.GetCurrentPlayer() == Player.GetRedPlayer()) {
                g.setColor(Color.RED);
                g.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,fontSize-2));
                g.drawString("PLAY",Window.getWidth2()/2-fontSize,Window.getHeight()+63);
            }
            if(Player.GetCurrentPlayer() == Player.GetBluePlayer()) {
                g.setColor(Color.BLUE);
                g.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,fontSize-2));
                g.drawString("PLAY",Window.getWidth2()/2-fontSize,Window.getHeight()+63);
            }
        }
        if(Player.GetCurrentPlayer().selected){
            int row = Player.GetCurrentPlayer().rowSelect;
            int column = Player.GetCurrentPlayer().columnSelect;
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(Window.getX(column*xdelta-xdelta),Window.getY(row*ydelta),xdelta,ydelta);
            g.fillRect(Window.getX(column*xdelta+xdelta),Window.getY(row*ydelta),xdelta,ydelta);
            g.fillRect(Window.getX(column*xdelta),Window.getY(row*ydelta-ydelta),xdelta,ydelta);
            g.fillRect(Window.getX(column*xdelta),Window.getY(row*ydelta+ydelta),xdelta,ydelta);
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
            if(invalidPlacement && setupPhase) {
            g.setColor(Color.black);
            g.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,25));
            g.drawString("You can only place pieces on your own side!",Window.getWidth2()/2-200,Window.getHeight2()/2);
            }
//            board[5][5] = new Tile(Color.GRAY);
//            g.setColor(Color.GREEN);
//                    
//            g.drawRect(500,200,50,50);
    }
    
    
 
}