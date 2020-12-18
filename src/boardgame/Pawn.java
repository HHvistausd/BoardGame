/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class Pawn extends Tile {
    
    public boolean MoveRight;
    Image image;
    Image pawnImage;
   
    
    Color lCol = super.color;
    
    Pawn () {
        super(Player.GetCurrentPlayer().getColor());
        
    }
    
    
    
    public void draw(Graphics2D g,int row,int column,int xdelta,int ydelta) {
        g.setColor(lCol);
        g.fillOval(Window.getX(column*xdelta), Window.getY(row*ydelta), xdelta, ydelta);

//        pawnImage = Toolkit.getDefaultToolkit().getImage("./redHelm.png");
//        g.drawImage(pawnImage,Window.getX(column*xdelta),Window.getY(row*ydelta),null);
//        }
//            pawnImage = Toolkit.getDefaultToolkit().getImage("./redHelm.png");
//          g.drawImage(pawnImage, Window.getX(column*xdelta), Window.getY(row*ydelta),null);
        g.setColor(Color.white);
        g.setFont(new Font("TIMES NEW ROMAN",Font.PLAIN,15));
        g.drawString("PAWN",Window.getX(column*xdelta),Window.getY(row*ydelta)+25);
    }
}
