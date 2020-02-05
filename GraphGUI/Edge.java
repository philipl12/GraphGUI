import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Line2D;


public class Edge {
    protected boolean isComplete = false;
    private Vertex start, end;
    private Shape edge;
    private Color edgeColor;
    private int weight; // weight is simply distance formula, displayed onto GUI
    private int x, y; // draw weight string in middle of line with offset

    public Edge(Vertex start, Vertex end) {
        this.start = start;
        this.end = end;
        edge = new Line2D.Double(start.getX() + 6, start.getY() + 6, end.getX() + 6, end.getY() + 6);
        weight = (int) Math.sqrt((end.getY() - start.getY()) * (end.getY() - start.getY()) + 
        		           (end.getX() - start.getX()) * (end.getX() - start.getX()));
        x = (start.getX() + end.getX()) / 2  + 20; // Causes weight to be displayed 
        y = (start.getY() + end.getY()) / 2 + 20; // close to midpoint
        edgeColor = Color.BLUE;
        isComplete = true;
    }
    
    public void changeStartLocation(Vertex newStart) {
    	this.start = newStart;
        edge = new Line2D.Double(start.getX() + 6, start.getY() + 6, end.getX() + 6, end.getY() + 6);
        weight = (int) Math.sqrt((end.getY() - start.getY()) * (end.getY() - start.getY()) + 
		           (end.getX() - start.getX()) * (end.getX() - start.getX()));
        x = (start.getX() + end.getX()) / 2  + 20;
        y = (start.getY() + end.getY()) / 2 + 20;
        edgeColor = Color.BLUE;
    }
    public void changeEndLocation(Vertex newEnd) {
    	this.end = newEnd;
        edge = new Line2D.Double(start.getX() + 6, start.getY() + 6, end.getX() + 6, end.getY() + 6);
        weight = (int) Math.sqrt((end.getY() - start.getY()) * (end.getY() - start.getY()) + 
		           (end.getX() - start.getX()) * (end.getX() - start.getX()));
        x = (start.getX() + end.getX()) / 2  + 20;
        y = (start.getY() + end.getY()) / 2 + 20;
        edgeColor = Color.BLUE;
    }

    public Vertex getStartVertex() { return start; }

    public Vertex getEndVertex() { return end; }
    
    public int getWeight() { return weight; }
    
    public void setWeight(int weight) { this.weight = weight; }
    
    public int getX() { return x; }
    
    public int getY() { return y; }
    
    public Shape getLine() { return edge; }

    public Color getEdgeColor() { return edgeColor; }

}
