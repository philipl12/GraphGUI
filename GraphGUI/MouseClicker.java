import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MouseClicker extends MouseAdapter {
    private GraphPicturePanel picture;
    private Vertex newVertex, startPoint, endPoint;
    private Edge newEdge;
    protected ArrayList<Vertex> vertexLocation = new ArrayList<>();
    protected ArrayList<Edge> edgeLocation = new ArrayList<>();

    public MouseClicker(GraphPicturePanel picture) {
        this.picture = picture;
        picture.addMouseListener(this);
    }

    public void mousePressed(MouseEvent e) {
        int buttonNum = picture.mainFrame.buttonListener.getNum();

        if (buttonNum == 1) {
            newVertex = new Vertex(e.getX(), e.getY());
            vertexLocation.add(newVertex);
            newVertex = null;
        }
        if (buttonNum == 2) {
            if (startPoint == null) {
                startPoint = new Vertex(e.getX(), e.getY());

	            for (Vertex v : vertexLocation) {
	                if (v.getShape().contains(startPoint.getX(), startPoint.getY())) {
	                    startPoint = v;
	                    startPoint.setVertexColor(Color.GREEN);
	                }
	            }
            }
            // used only if the start and end aren't the same selected vertices
            else if(endPoint == null &&
            		!startPoint.getShape().contains(e.getX(), e.getY())) {
                endPoint = new Vertex(e.getX(), e.getY());
                
                for (Vertex v : vertexLocation) {                	
                    if (v.getShape().contains(endPoint.getX(), endPoint.getY())) {
                        endPoint = v;
                        newEdge = new Edge(startPoint, endPoint);
                        edgeLocation.add(newEdge);
                        break;
                    }

                }

                startPoint.setVertexColor(Color.RED);
                endPoint.setVertexColor(Color.RED);
                startPoint = null;
                endPoint = null;
            }
            // changes vertex back to red if same vertex is selected
            else if (endPoint == null &&
            		startPoint.getShape().contains(e.getX(), e.getY())) {
            	startPoint.setVertexColor(Color.RED);
            	startPoint = null;
            }
            picture.repaint();

        }
        if (buttonNum == 3) {
        	// startPoint refers to the vertex you choose to move
            if (startPoint == null) {
                startPoint = new Vertex(e.getX(), e.getY());
                try {
                    for (Vertex v : vertexLocation) {
                    	// cycle through and see if a vertex does exist
                    	// if it does exist, assign startPoint to v and then set color
                        if (v.getShape().contains(startPoint.getX(), startPoint.getY())) {
                            startPoint = v;
                            startPoint.setVertexColor(Color.GREEN);
                            break;
                        }
                    }
                }
                catch (Exception example) {
                    System.out.println("No end vertex was clicked");
                    return;
                }
            }
            else if(endPoint == null) {
            	ArrayList<Edge> edgeContainer = new ArrayList<>();
            	ArrayList<Boolean> whichPoint = new ArrayList<>();
            	boolean check = false;
            	// sets location of moved vertex by using endPoints X/Y
                endPoint = new Vertex(e.getX(), e.getY());
                
                // checks for vertices with multiple edges
                for (Edge edges : edgeLocation) {
                	if (edges.getStartVertex() == startPoint) {
                		newEdge = edges;
                		check = true;
                		edgeContainer.add(newEdge);
                		whichPoint.add(check);
                	}
                	else if (edges.getEndVertex() == startPoint) {
                		newEdge = edges;
                		check = false;
                		edgeContainer.add(newEdge);
                		whichPoint.add(check);
                	}
                }
                startPoint.changeLocation(endPoint.getX(), endPoint.getY());
                // Used to only change either start or edge
                for (int i = 0; i < whichPoint.size(); i++) {
                	if (whichPoint.get(i)) edgeContainer.get(i).changeStartLocation(startPoint);
                    else edgeContainer.get(i).changeEndLocation(startPoint);
                }
                
                
                startPoint = null;
                endPoint = null;
                newEdge = null;
            }

            picture.repaint();

        }
        if (buttonNum == 4) {
            System.out.println("Shortest path pressed");
        }
    }

}
