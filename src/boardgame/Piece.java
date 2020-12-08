
package boardgame;
import java.awt.*;

public class Piece {    
    public enum Shape {CIRCLE,TRIANGLE};
    private Shape shape;
    private Color color;

    Piece(Shape _shape)
    {
        shape = _shape;        
//add or modify.
        
        int randomNum = (int)(Math.random()*3);
        if (randomNum == 0)
            color = Color.RED;
        if (randomNum == 1)
            color = Color.BLACK;
        if (randomNum == 2)
            color = Color.BLUE;
    }
    public Color getColor()
    {
        return (color);
    }
    public Shape getShape()
    {
        return (shape);
    }

    public void changeColor() {
        
        int randomNum = (int)(Math.random()*3);
        
        
        
        
//        
//        if(color == Color.BLUE) {
//            if(randomNum ==0)
//              color = Color.RED;
//            if(randomNum ==1)
//              color = Color.BLACK;
//            if(randomNum ==2)
//              color = Color.RED;
//        }
//        if(color == Color.RED) {
//            if(randomNum ==0)
//              color = Color.BLACK;
//            if(randomNum ==1)
//              color = Color.BLUE;
//            if(randomNum ==2)
//              color = Color.BLACK;
//        }
//        if(color == Color.BLACK) {
//            if(randomNum ==0)
//              color = Color.BLUE;
//            if(randomNum ==1)
//              color = Color.RED;
//            if(randomNum ==2)
//              color = Color.BLUE;
//        }
        
        if (randomNum == 0 && color != Color.RED)
            color = Color.RED;
        if (randomNum == 1 && color != Color.BLUE)
            color = Color.BLUE;
        if (randomNum == 2 && color != Color.BLACK)
            color = Color.BLACK;
        
    }
    
    public void draw(Graphics2D g,int row,int column,int xdelta,int ydelta) {
        g.setColor(color);
        if (shape == Shape.CIRCLE)
         g.fillOval(Window.getX(column*xdelta), Window.getY(row*ydelta), xdelta, ydelta);
        else if (shape == Shape.TRIANGLE) {
//            g.fillRect(Window.getX(column*xdelta), Window.getY(row*ydelta), xdelta, ydelta);
            
            int x[] = {Window.getX(column*xdelta+xdelta),Window.getX(column*xdelta+xdelta),Window.getX(column*xdelta)};
            int y[] = {Window.getY(row*ydelta),Window.getY(row*ydelta+ydelta),Window.getY(row*ydelta+ydelta)};
            g.fillPolygon(x,y,x.length);
//
//            g.fillPolygon(x,y,xdelta);
//add or modify.
        }
    }
    
}