
package boardgame;

import java.awt.*;

public class Piece {
    
    private Color color;
    
    Piece(Color _color)
    {
        color = _color;        
    }
    public Color getColor()
    {
        return (color);
    }
    

    public void draw(Graphics2D g,int row,int column,int xdelta,int ydelta) {
        g.setColor(color);
        g.fillRect(Window.getX(column*xdelta), Window.getY(row*ydelta), xdelta, ydelta);
        g.setColor(Color.white);
        g.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,15));
        g.drawString("PIECE",Window.getX(column*xdelta),Window.getY(row*ydelta)+25);
}
}
