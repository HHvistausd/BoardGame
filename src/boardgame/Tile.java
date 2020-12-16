
package boardgame;

import java.awt.*;

public class Tile {
    
    public Pawn pPoint;
    public Piece piPoint;
    
    public Color color;
    
    
    Tile(Color _color)
    {
        color = _color;        
    }
    public Color getColor()
    {
        return (color);
    }
    public Player getCurrentPlayer() {
        return(Player.GetCurrentPlayer());
    }

    
}
