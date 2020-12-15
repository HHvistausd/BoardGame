
package boardgame;

import java.awt.Color;


public class Player {
    
    
    public static Player currentTurn;
    private static Player players[] = new Player[2];
    private Color color;    
    public int pawns = 10;
    public int walls = 20;
    
    public int rowDir = 0;
    public int columnDir = 0;
    boolean moveCalled = false;
    boolean rightCalled = false;
    boolean leftCalled = false;
    boolean upCalled = false;
    boolean downCalled = false;
    
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
            Board.AddPawnPiece(x, y);
        }
    }
    public void placePiece (int x, int y) {
        if (walls > 0) {
            walls--;
            Board.AddWallPiece(x, y);
        }
    }
    
    
    public void moveRight(){
        rowDir = 0;
        columnDir = 1;
        moveCalled = true;
        rightCalled = true;
    }
     public void moveLeft(){
        rowDir = 0;
        columnDir = -1;
        moveCalled = true;
        leftCalled = true;
    }
     public void moveUp(){
        rowDir = -1;
        columnDir = 0;
        moveCalled = true;
        upCalled = true;
    }
     public void moveDown(){
        rowDir = 1;
        columnDir = 0;
        moveCalled = true;
        downCalled = true;
    }
//    public void movePawn (int x, int y) {
//        Board.AddPawnPiece(x, y);
//    }
        
}