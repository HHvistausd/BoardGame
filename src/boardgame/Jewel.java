package boardgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author thebl
 */
    public class Jewel extends Tile {
    Image jewelImage;
    Color lCol = super.color;
    
    private Color color;
    private Tile tile;
    
    Jewel(Color _color)
    {
          
//        color = _color;
          super(_color);
          color = _color;
    }
    
    public void draw(Graphics2D g,int row,int column,int xdelta,int ydelta) {
        
        if(lCol == Color.red) {
        jewelImage = Toolkit.getDefaultToolkit().getImage("./jewel_red.png");
        g.drawImage(jewelImage,Window.getX(column*xdelta),Window.getY(row*ydelta),null);
        }
        if(lCol == Color.blue) {
        jewelImage = Toolkit.getDefaultToolkit().getImage("./jewel_blue.png");
        g.drawImage(jewelImage,Window.getX(column*xdelta),Window.getY(row*ydelta),null);
        }
    }
}

