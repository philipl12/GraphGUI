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
            else if(endPoint == null) {
                endPoint = new Vertex(e.getX(), e.getY());
                
                for (Vertex v : vertexLocation) {
                	if (startPoint == endPoint) break;
                	
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
            	// sets location of moved vertex by using endPoints X/Y
                endPoint = new Vertex(e.getX(), e.getY());
                boolean check = false;
                // used for loop to figure out if an edge is connected to
                // the original start point from above
                
                for (Edge edges : edgeLocation) {
                	if (edges.getStartVertex() == startPoint) {
                		newEdge = edges;
                		check = true;
                		break;
                	}
                	else if (edges.getEndVertex() == startPoint) {
                		newEdge = edges;
                		break;
                	}
                }
                startPoint.changeLocation(endPoint.getX(), endPoint.getY());
                // Used to only change either start or edge
                if (check) newEdge.changeStartLocation(startPoint);
                else newEdge.changeEndLocation(startPoint);
                
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
