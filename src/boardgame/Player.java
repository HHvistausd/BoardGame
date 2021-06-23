
package boardgame;

import java.awt.Color;


public class Player {
    
    
    public static Player currentTurn;
    private static Player players[] = new Player[2];
    private Color color;    
    public int pawns = 13;
    public int walls = 25;
    
    public int rowDir = 0;
    public int columnDir = 0;
    
    public int rowSelect = 0;
    public int columnSelect = 0;
    public boolean selected = false;
    
    boolean moveCalled = false;
    boolean rightCalled = false;
    boolean leftCalled = false;
    boolean upCalled = false;
    boolean downCalled = false;
    
    sound placePiece = null;
    sound placePiece2 = null;
    public static void Reset()
    {
        players[0] = new Player(Color.red);
        players[1] = new Player(Color.blue);
        currentTurn = players[0];
    }
    public static Player GetCurrentPlayer()
    {
        return(currentTurn);
    }
    public static Player GetRedPlayer()
    {
        return(players[0]);
    }
    public static Player GetBluePlayer()
    {
        return(players[1]);
    }
    
    public static void SwitchTurn()
    {
        if (currentTurn == players[0]) 
            currentTurn = players[1];
        
        else
            currentTurn = players[0];
        
    } 
    public static void mouseValues(int _rowSelect, int _columnSelect, boolean _selected){
        Player.GetCurrentPlayer().rowSelect = _rowSelect;
        Player.GetCurrentPlayer().columnSelect = _columnSelect;
        Player.GetCurrentPlayer().selected = _selected;
    }
    
    
    Player(Color _color)
    {
        color = _color;
    }
    public Color getColor()
    {
        return (color);
    }
    public void placePawn (int x, int y) {
        if (pawns > 0) {
            pawns--;
            placePiece = new sound("piece-placed.wav");
            Board.AddPawnPiece(x, y);
        }
    }
    public void placePiece (int x, int y) {
        if (walls > 0) {
            walls--;
            placePiece2 = new sound("menu-click-1.wav");
            Board.AddWallPiece(x, y);
        }
    }
    
    
    public void moveRight(){
        if(selected) {
        rowDir = 0;
        columnDir = 1;
        moveCalled = true;
        rightCalled = true;
        }
    }
     public void moveLeft(){
        if(selected) {
        rowDir = 0;
        columnDir = -1;
        moveCalled = true;
        leftCalled = true;
         }
    }
     public void moveUp(){
        if(selected) {
        rowDir = -1;
        columnDir = 0;
        moveCalled = true;
        upCalled = true;
         }
    }
     public void moveDown(){
        if(selected) {
        rowDir = 1;
        columnDir = 0;
        moveCalled = true;
        downCalled = true;
         }
        
    }
     
//    public void movePawn (int x, int y) {
//        Board.AddPawnPiece(x, y);
//    }
        
}