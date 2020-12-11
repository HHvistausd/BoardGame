package boardgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 *
 * @author thebl
 */
    public class Piece extends Tile {
    Color lCol = super.color;
    
    Piece () {
        super(Player.GetCurrentPlayer().getColor());
        
    }
    
    
    
    public void draw(Graphics2D g,int row,int column,int xdelta,int ydelta) {
        g.setColor(lCol);
        g.fillRect(Window.getX(column*xdelta), Window.getY(row*ydelta), xdelta, ydelta);
        g.setColor(Color.white);
        g.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,15));
        g.drawString("WALL",Window.getX(column*xdelta),Window.getY(row*ydelta)+25);
    }
}

