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
    public class Piece extends Tile {
    
    Image wallImage;
    Color lCol = super.color;
//    int wallValue = super.wallValue;
    
    Piece () {
        super(Player.GetCurrentPlayer().getColor());
        
    }
    
    
    
    public void draw(Graphics2D g,int row,int column,int xdelta,int ydelta) {
        if(lCol == Color.red) {
        wallImage = Toolkit.getDefaultToolkit().getImage("./Brick_Tile_Red.png");
        g.drawImage(wallImage,Window.getX(column*xdelta),Window.getY(row*ydelta),null);
        g.setColor(Color.black);
        g.setFont(new Font("Franklin Gothic",Font.PLAIN,30));
        g.drawString(""+super.wallValue,Window.getX(column*xdelta)+13,Window.getY(row*ydelta)+27);
        }
        if(lCol == Color.blue) {
        wallImage = Toolkit.getDefaultToolkit().getImage("./Brick_Tile_Blue.png");
        g.drawImage(wallImage,Window.getX(column*xdelta),Window.getY(row*ydelta),null);
        g.setColor(Color.black);
        g.setFont(new Font("Franklin Gothic",Font.PLAIN,30));
        g.drawString(""+super.wallValue,Window.getX(column*xdelta)+13,Window.getY(row*ydelta)+27);
        }
    }
}

