import java.awt.*;
import java.awt.geom.Line2D;

public class Edge {
    protected boolean isComplete = false;
    private Vertex start, end;
    private Shape edge;
    private Color edgeColor;


    public Edge(Vertex start, Vertex end) {
        this.start = start;
        this.end = end;
        edge = new Line2D.Double(start.getX() + 6, start.getY() + 6, end.getX() + 6, end.getY() + 6);
        edgeColor = Color.BLUE;
        isComplete = true;
    }
    
    public void changeStartLocation(Vertex newStart) {
    	this.start = newStart;
        edge = new Line2D.Double(start.getX() + 6, start.getY() + 6, end.getX() + 6, end.getY() + 6);
        edgeColor = Color.BLUE;
    }
    public void changeEndLocation(Vertex newEnd) {
    	this.end = newEnd;
        edge = new Line2D.Double(start.getX() + 6, start.getY() + 6, end.getX() + 6, end.getY() + 6);
        edgeColor = Color.BLUE;
    }

    public Vertex getStartVertex() {
        return start;
    }

    public Vertex getEndVertex() {
        return end;
    }
    
    public Shape getLine() {
        return edge;
    }

    public Color getEdgeColor() { return edgeColor; }

}
