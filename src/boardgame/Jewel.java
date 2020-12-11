package boardgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 *
 * @author thebl
 */
    public class Jewel extends Tile {
//    Color lCol = super.color;
    private Color color;
    
    Jewel(Color _color)
    {
//        color = _color;
          super(Player.GetCurrentPlayer().getColor());
    }
    
    
    public void draw(Graphics2D g,int row,int column,int xdelta,int ydelta) {
        g.setColor(Color.green);
        g.fillRect(Window.getX(column*xdelta), Window.getY(row*ydelta), xdelta, ydelta);
        g.setColor(Color.white);
        g.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,15));
        g.drawString("Jewel",Window.getX(column*xdelta),Window.getY(row*ydelta)+25);
    }
}

