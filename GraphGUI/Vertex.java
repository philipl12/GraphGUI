import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Vertex {
    private Point p;
    private Color vertexColor;
    private Shape vertex;


    public Vertex(int x, int y) {
        p = new Point(x, y);
        vertex = new Ellipse2D.Double( x, y, 12, 12);
        vertexColor = Color.RED;
    }


    public int getX() {
        return (int)p.getX();
    }

    public int getY() { return (int)p.getY(); }

    public void changeLocation(int x, int y) {
        p.move(x, y);
        vertex = new Ellipse2D.Double( x, y, 12, 12);
        vertexColor = Color.RED;
    }

    public Color getVertexColor() {
        return vertexColor;
    }

    public void setVertexColor(Color vertexColor) {
        this.vertexColor = vertexColor;
    }

    public Shape getShape() {
        return vertex;
    }

}
