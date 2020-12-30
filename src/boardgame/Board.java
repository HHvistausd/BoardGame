
package boardgame;
import java.awt.*;
import javax.sound.sampled.*;


public class Board {
    private final static int NUM_ROWS = 25;
    private final static int NUM_COLUMNS = 25;      
    private static Tile board[][] = new Tile[NUM_ROWS][NUM_COLUMNS];
    private static boolean invalidPlacement;
    private static boolean setupPhase = true;
    public static boolean win = false;
    
    private static boolean turtle = false;
    private static boolean horse = false;
    private static boolean custom = false;
    
    public static void Reset() {
        if(turtle) {
        for (int zrow=0;zrow<NUM_ROWS;zrow++)
            for (int zcol=0;zcol<NUM_COLUMNS;zcol++)
                board[zrow][zcol] = null;  
                Player.GetBluePlayer().pawns = 0;
                Player.GetBluePlayer().walls = 0;
                Player.GetRedPlayer().pawns = 0;
                Player.GetRedPlayer().walls = 0;
                Board.presets(true, false, false);
        }
        else if(horse) {
        for (int zrow=0;zrow<NUM_ROWS;zrow++)
            for (int zcol=0;zcol<NUM_COLUMNS;zcol++)
                board[zrow][zcol] = null;  
                Player.GetBluePlayer().pawns = 0;
                Player.GetBluePlayer().walls = 0;
                Player.GetRedPlayer().pawns = 0;
                Player.GetRedPlayer().walls = 0;
                Board.presets(false, true, false);
        }
        else if(custom) {
        for (int zrow=0;zrow<NUM_ROWS;zrow++)
            for (int zcol=0;zcol<NUM_COLUMNS;zcol++)
                board[zrow][zcol] = null;  
        }
        
        
        setUpJewels();
        invalidPlacement = false;
        setupPhase = true;
        win = false;
    }
    
    public static void presets(boolean _turtle, boolean _horse, boolean _custom) {
        
       Board.turtle = _turtle;
       Board.horse = _horse;
       Board.custom = _custom;
       
       
        if(turtle) {
            setupPhase = false;
            Player.GetBluePlayer().pawns = 0;
            Player.GetBluePlayer().walls = 0;
            Player.GetRedPlayer().pawns = 0;
            Player.GetRedPlayer().walls = 0;
            
            int y[] = {0,1,2,0,1,2,3,0,1,2,3,1,2,3,0,1,2,3,0,1,2,3,0,1,2};
            int x[] = {9,9,9,10,10,10,10,11,11,11,11,12,12,12,13,13,13,13,14,14,14,14,15,15,15};

            int yp[] = {0,0,3,3,4,4,4,10,10,10,10,10,10};
            int xp[] = {8,16,9,15,11,12,13,3,7,10,14,17,21};
            
            int y2[] = {24,23,22,24,23,22,21,24,23,22,21,23,22,21,24,23,22,21,24,23,22,21,24,23,22};
            int x2[] = {9,9,9,10,10,10,10,11,11,11,11,12,12,12,13,13,13,13,14,14,14,14,15,15,15};

            int yp2[] = {24,24,21,21,20,20,20,14,14,14,14,14,14};
            int xp2[] = {8,16,9,15,11,12,13,3,7,10,14,17,21};
            
            int i = 0;
            int e = 0;
            

            //red player 
            while(i<x.length && e < y.length) {
                Piece piece = new Piece();
                if(board[x[i]][y[e]] == null) {
                board[x[i]][y[e]] = new Tile(Player.GetCurrentPlayer().getColor());
                board[x[i]][y[e]] = piece;
                board[x[i]][y[e]].piPoint = piece;
                i++;
                e++;
                }
            }
            i = 0;
            e = 0;
            while(i<xp.length && e < yp.length) {
                Pawn pawn = new Pawn();
                if(board[xp[i]][yp[e]] == null) {
                board[xp[i]][yp[e]] = new Tile(Player.GetCurrentPlayer().getColor());
                board[xp[i]][yp[e]] = pawn;
                board[xp[i]][yp[e]].pPoint = pawn;
                i++;
                e++;
                }
            }
            i = 0;
            e = 0;
            Player.SwitchTurn();
            //blue player
            while(i<x2.length && e < y2.length) {
                Piece piece2 = new Piece();
                if(board[x2[i]][y2[e]] == null) {
                board[x2[i]][y2[e]] = new Tile(Player.GetCurrentPlayer().getColor());
                board[x2[i]][y2[e]] = piece2;
                board[x2[i]][y2[e]].piPoint = piece2;
                i++;
                e++;
                }
            }
            i = 0;
            e = 0;
            while(i<xp2.length && e < yp2.length) {
                Pawn pawn2 = new Pawn();
                if(board[xp2[i]][yp2[e]] == null) {
                board[xp2[i]][yp2[e]] = new Tile(Player.GetCurrentPlayer().getColor());
                board[xp2[i]][yp2[e]] = pawn2;
                board[xp2[i]][yp2[e]].pPoint = pawn2;
                i++;
                e++;
                }
            }
            i = 0;
            e = 0;
        }
        if(horse) {
            setupPhase = false;
            Player.GetBluePlayer().pawns = 0;
            Player.GetBluePlayer().walls = 0;
            Player.GetRedPlayer().pawns = 0;
            Player.GetRedPlayer().walls = 0;
            
            int y[] = {6};
            int x[] = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24};
            int y2[] = {18};
            
            int yp[] = {8};
            int xp[] = {0,2,4,6,8,10,12,14,16,18,20,22,24};
            int y2p[] = {16};
            
            for(int i = 0;i<x.length;i++) {
                for(int e = 0;e<y.length;e++) {
                    Piece piece = new Piece();
                    board[x[i]][y[e]] = new Tile(Player.GetCurrentPlayer().getColor());
                    board[x[i]][y[e]] = piece;
                    board[x[i]][y[e]].piPoint = piece;
                }
            }
            for(int i = 0;i<xp.length;i++) {
                for(int e = 0;e<yp.length;e++) {
                    Pawn pawn = new Pawn();
                    board[xp[i]][yp[e]] = new Tile(Player.GetCurrentPlayer().getColor());
                    board[xp[i]][yp[e]] = pawn;
                    board[xp[i]][yp[e]].pPoint = pawn;
                }
            }
            
           Player.SwitchTurn();
            
            for(int i = 0;i<x.length;i++) {
                for(int e = 0;e<y2.length;e++) {
                    Piece piece2 = new Piece();
                    board[x[i]][y2[e]] = new Tile(Player.GetCurrentPlayer().getColor());
                    board[x[i]][y2[e]] = piece2;
                    board[x[i]][y2[e]].piPoint = piece2;
                }
            }
            for(int i = 0;i<x.length;i++) {
                for(int e = 0;e<y2.length;e++) {
                    Pawn pawn2 = new Pawn();
                    board[xp[i]][y2p[e]] = new Tile(Player.GetCurrentPlayer().getColor());
                    board[xp[i]][y2p[e]] = pawn2;
                    board[xp[i]][y2p[e]].pPoint = pawn2;
                }
            }
            

        }
        if(custom) {
            
        }
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
    
    public static void MouseSelect(int xpixel,int ypixel) {
        boolean selected = false;
        
        if (xpixel < 0 || xpixel > Window.getWidth2())
            return;
        if (ypixel < 0 || ypixel > Window.getHeight2())
            return;

        int ydelta = Window.getHeight2()/NUM_ROWS;
        int xdelta = Window.getWidth2()/NUM_COLUMNS;
        
        int columnSelect = xpixel/xdelta;   
        int rowSelect = ypixel/ydelta; 
        
        if(Player.GetBluePlayer().walls == 0 && Player.GetBluePlayer().pawns == 0 && 
           Player.GetRedPlayer().walls == 0 && Player.GetRedPlayer().pawns == 0) 
        {
            if(board[rowSelect][columnSelect] == board[rowSelect][columnSelect].pPoint 
               && board[rowSelect][columnSelect].getColor() == Player.GetCurrentPlayer().getColor()) {
               selected = true;
            }
        }
            
        else if(Player.GetCurrentPlayer() == Player.GetRedPlayer() && board[rowSelect][columnSelect].getColor() == Player.GetBluePlayer().getColor() 
           || board[rowSelect][columnSelect] == null) {
            selected = false;
        }
        else if(Player.GetCurrentPlayer() == Player.GetBluePlayer() && board[rowSelect][columnSelect].getColor() == Player.GetRedPlayer().getColor()
            || board[rowSelect][columnSelect] == null) {
            selected = false;
        }
        else
         selected = false;

        if(win)
           selected = false;
        if(selected)
        Player.mouseValues(rowSelect, columnSelect, selected);
        
    }
    
    public static void MovePawnPiece() {
        
        sound attack1 = null;
        sound attack2 = null;
        sound attack3 = null;
        sound attack4 = null;
        sound shatter = null;
        sound wallbreak = null;
        
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
            else if(board[currentRow+rowDir][currentColumn+columnDir] == board[currentRow+rowDir][currentColumn+columnDir].pPoint && 
                    board[currentRow+rowDir][currentColumn+columnDir].getColor() != Player.GetCurrentPlayer().getColor()){
                        board[currentRow+rowDir][currentColumn+columnDir] = null;
                        currentRow += rowDir;
                        currentColumn+= columnDir;
                        setupPhase = false;
                        Board.ShiftPawnPiece(currentRow, currentColumn, selected);
                        int attack = (int)(Math.random()*4+1);
                        switch(attack) {
                            case 1: 
                                attack1 = new sound("attack1.wav");
                                break;
                            case 2:
                                attack2 = new sound("attack2.wav");
                                break;
                            case 3:
                                attack3 = new sound("attack3.wav");
                                break;
                            case 4: 
                                attack4 = new sound("attack4.wav");
                                break;
                            default:
                                attack1 = new sound("attack4.wav");
                                
                        }
//                performTurnAction(currentRow, currentColumn, selected, columnDir, rowDir);     
            }
            else if(board[currentRow+rowDir][currentColumn+columnDir] == board[currentRow+rowDir][currentColumn+columnDir].jPoint && 
                    board[currentRow+rowDir][currentColumn+columnDir].getColor() != Player.GetCurrentPlayer().getColor()){
                    board[currentRow+rowDir][currentColumn+columnDir] = null;
                        currentRow += rowDir;
                        currentColumn+= columnDir;
                        Board.ShiftPawnPiece(currentRow, currentColumn, selected);
                        Player.SwitchTurn();
                        shatter = new sound("shatter.wav");
                        win = true;
                        
            }
            else if(board[currentRow+rowDir][currentColumn+columnDir] == board[currentRow+rowDir][currentColumn+columnDir].piPoint && 
                    board[currentRow+rowDir][currentColumn+columnDir].getColor() != Player.GetCurrentPlayer().getColor()){
                        board[currentRow+rowDir][currentColumn+columnDir].wallValue--;
                        Player.SwitchTurn();
                        if(board[currentRow+rowDir][currentColumn+columnDir].wallValue <= 0) {
                        wallbreak = new sound("wallbreak.wav");
                        board[currentRow+rowDir][currentColumn+columnDir] = null;
                        currentRow += rowDir;
                        currentColumn+= columnDir;
                        
                        }
            }
        }

        Player.GetCurrentPlayer().selected = false;
        Player.GetCurrentPlayer().rowDir = 0;
        Player.GetCurrentPlayer().columnDir = 0;
        
    }
    
    public static void ShiftPawnPiece(int currentRow,int currentColumn,boolean selected) {

        if(selected)  {
        Pawn pawn = new Pawn();

        board[currentRow][currentColumn] = new Tile(Player.GetCurrentPlayer().getColor());
        board[currentRow][currentColumn] = pawn;
        board[currentRow][currentColumn].pPoint = pawn;
        
        if(Player.GetCurrentPlayer().rightCalled == true && board[currentRow][currentColumn-1] != null) {
            if(board[currentRow][currentColumn-1].getColor() == Player.GetCurrentPlayer().getColor()){
            board[currentRow][currentColumn-1] = null;
            Player.GetCurrentPlayer().rightCalled = false;
            Player.GetCurrentPlayer().selected = false;
            }
        }
        if(Player.GetCurrentPlayer().leftCalled == true && board[currentRow][currentColumn+1] != null) {
            if(board[currentRow][currentColumn+1].getColor() == Player.GetCurrentPlayer().getColor()) {
            board[currentRow][currentColumn+1] = null;
            Player.GetCurrentPlayer().leftCalled = false;
            Player.GetCurrentPlayer().selected = false;
            }
        }
        if(Player.GetCurrentPlayer().upCalled == true && board[currentRow+1][currentColumn] != null) {
            if(board[currentRow+1][currentColumn].getColor() == Player.GetCurrentPlayer().getColor()) {
            board[currentRow+1][currentColumn] = null;
            Player.GetCurrentPlayer().upCalled = false;
            Player.GetCurrentPlayer().selected = false;
            }
        }
        if(Player.GetCurrentPlayer().downCalled == true && board[currentRow-1][currentColumn] != null){
            if(board[currentRow-1][currentColumn].getColor() == Player.GetCurrentPlayer().getColor()) {
            board[currentRow-1][currentColumn] = null;
            Player.GetCurrentPlayer().downCalled = false;
            Player.GetCurrentPlayer().selected = false;
            }
        }
        
        if(Player.GetCurrentPlayer().walls == 0 && Player.GetCurrentPlayer().pawns == 0) {
        Player.SwitchTurn();
        }
        
        }
        
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
        
        
        g.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,20));
        g.setColor(Color.RED);
        g.drawString("RED",Window.getWidth2()/2-300,Window.getHeight()+50);
        g.setColor(Color.BLUE);
        g.drawString("BLUE",Window.getWidth2()/2+295,Window.getHeight()+50);
        
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
        ////////////////////////////drawing movable squares when selected

        if(Player.GetCurrentPlayer().selected){
            int row = Player.GetCurrentPlayer().rowSelect;
            int column = Player.GetCurrentPlayer().columnSelect;
//            int xpixel =  column*xdelta;
//            int ypixel = row*ydelta;
            
            g.setColor(Color.LIGHT_GRAY);
            if(board[row][column-1] == null){
            g.fillRect(Window.getX(column*xdelta-xdelta),Window.getY(row*ydelta),xdelta,ydelta);
            }
            else if(Player.GetCurrentPlayer().getColor() != board[row][column-1].getColor()){
            g.fillRect(Window.getX(column*xdelta-xdelta),Window.getY(row*ydelta),xdelta,ydelta);
            }
            if(board[row][column+1] == null){
            g.fillRect(Window.getX(column*xdelta+xdelta),Window.getY(row*ydelta),xdelta,ydelta);
            }
            else if(Player.GetCurrentPlayer().getColor() != board[row][column+1].getColor()){
            g.fillRect(Window.getX(column*xdelta+xdelta),Window.getY(row*ydelta),xdelta,ydelta);
            }
            if(board[row-1][column] == null){
            g.fillRect(Window.getX(column*xdelta),Window.getY(row*ydelta-ydelta),xdelta,ydelta);
            }
            else if(Player.GetCurrentPlayer().getColor() != board[row-1][column].getColor()){
            g.fillRect(Window.getX(column*xdelta),Window.getY(row*ydelta-ydelta),xdelta,ydelta);
            }
            if(board[row+1][column] == null) {
            g.fillRect(Window.getX(column*xdelta),Window.getY(row*ydelta+ydelta),xdelta,ydelta);
            }
            else if(Player.GetCurrentPlayer().getColor() != board[row+1][column].getColor()){
            g.fillRect(Window.getX(column*xdelta),Window.getY(row*ydelta+ydelta),xdelta,ydelta);
            }

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
            if(win) {
              if(Player.GetCurrentPlayer() == Player.GetRedPlayer()) {
              g.setColor(Color.red);
              g.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,45));
              g.drawString("RED PLAYER HAS WON",Window.getWidth2()/2-200,Window.getHeight2()/2);  
              g.drawString("(press [esc] to see options)",Window.getWidth2()/2-100,Window.getHeight2()/2+80); 
              }
              else if(Player.GetCurrentPlayer() == Player.GetBluePlayer()) {
              g.setColor(Color.blue);
              g.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,45));
              g.drawString("BLUE PLAYER HAS WON",Window.getWidth2()/2-200,Window.getHeight2()/2);  
              g.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,25));
              g.drawString("(press [esc] to see options)",Window.getWidth2()/2-100,Window.getHeight2()/2+80);  
              }
            }
            //pause 
            g.setColor(Color.white);
              g.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,30));
              g.drawString("[esc] to pause",Window.getWidth2()/2-30,Window.getHeight2()+110);  
    }
    
    
 
}