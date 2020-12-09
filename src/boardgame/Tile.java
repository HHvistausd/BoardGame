
package boardgame;

import java.awt.*;

public class Tile {
    
    private Color color;
    
    Tile(Color _color)
    {
        color = _color;        
    }
    public Color getColor()
    {
        return (color);
    }
    

    public void draw(Graphics2D g,int row,int column,int xdelta,int ydelta) {
        g.setColor(color);
        g.fillOval(Window.getX(column*xdelta), Window.getY(row*ydelta), xdelta, ydelta);
        g.setColor(Color.white);
        g.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,15));
        g.drawString("WALL",Window.getX(column*xdelta),Window.getY(row*ydelta)+25);
}
}
