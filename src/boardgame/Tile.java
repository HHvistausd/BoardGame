
package boardgame;

import java.awt.*;

public class Tile {
    
    public Pawn pPoint;
    public Piece piPoint;
    public Jewel jPoint;
    public Color color;
    
    public int wallValue = 2;
    
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
