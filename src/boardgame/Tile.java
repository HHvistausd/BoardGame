
package boardgame;

import java.awt.*;

public class Tile {
    public enum Shape {CIRCLE,TRIANGLE};
    private Shape shape;
    private Color color;

    Tile(Shape _shape)
    {
        shape = _shape;        

    }
    public Color getColor()
    {
        return (color);
    }
    public Shape getShape()
    {
        return (shape);
    }

    public void draw(Graphics2D g,int row,int column,int xdelta,int ydelta) {
        g.setColor(Color.GRAY);
        g.fillOval(Window.getX(column*xdelta), Window.getY(row*ydelta), xdelta, ydelta);
       
}
}
