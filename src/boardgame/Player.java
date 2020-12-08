
package boardgame;

public class Player {
    
    private static Player currentTurn;
    private static Player players[] = new Player[2];
    private Piece.Shape shape;    

    public static void Reset()
    {
        players[0] = new Player(Piece.Shape.CIRCLE);
        players[1] = new Player(Piece.Shape.TRIANGLE);
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
    
    Player(Piece.Shape _shape)
    {
        shape = _shape;
    }
    public Piece.Shape getShape()
    {
        return (shape);
    }

        
}