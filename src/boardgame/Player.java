
package boardgame;

public class Player {
    
    
    private static Player currentTurn;
    private static Player players[] = new Player[2];
    private Tile.Shape shape;    

    public static void Reset()
    {
        players[0] = new Player(Tile.Shape.CIRCLE);
        players[1] = new Player(Tile.Shape.TRIANGLE);
        currentTurn = players[0];
    }
    public static Player GetCurrentPlayer()
    {
        return(currentTurn);
    }
    public static void SwitchTurn()
    {
        if (currentTurn == players[0])
            currentTurn = players[1];
        else
            currentTurn = players[0];
    }    
    
    Player(Tile.Shape _shape)
    {
        shape = _shape;
    }
    public Tile.Shape getShape()
    {
        return (shape);
    }

        
}