import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MouseClicker extends MouseAdapter {
    private GraphPicturePanel picture;
    private Vertex startPoint, endPoint;
    private Edge newEdge;
    private static int vertexCount = 0;
    protected ArrayList<Vertex> vertexLocation = new ArrayList<>();
    protected ArrayList<Edge> edgeLocation = new ArrayList<>();

    public MouseClicker(GraphPicturePanel picture) {
        this.picture = picture;
        picture.addMouseListener(this);
    }

    public int minDistance(int numVertices, int distance[], Boolean shortPath[]) {
    	int min = Integer.MAX_VALUE, minIndex = -1;
    	
    	for (int i = 0; i < numVertices; i++) {
    		if (!shortPath[i] && distance[i] <= min) {
    			min = distance[i];
    			minIndex = i;
    		}
    	}
    	
    	return minIndex;
    }
    
    public void dijkstra(int source) {
    	int numVertices = vertexLocation.size();
    	int distance[] = new int[numVertices];
    	Boolean shortPath[] = new Boolean[numVertices];
    	
    	for (int i = 0; i < numVertices - 1; i++) {
    		distance[i] = Integer.MAX_VALUE;
    		shortPath[i] = false;
    	}    	
    	distance[source] = 0;
    	
    	for (int i = 0; i < numVertices; i++) {
    		int u = minDistance(numVertices, distance, shortPath);
    		shortPath[u] = true;
    		
    		for (int j = 0; j < numVertices; j++) {
    			
    		}
    	}
    }
    
    public void mousePressed(MouseEvent e) {
        int buttonNum = picture.mainFrame.buttonListener.getNum();

        // change code to not allow for vertices to touch
        if (buttonNum == 1) {
        	boolean insideVertex = false;
            
            
            for (Vertex v : vertexLocation) {
                if (v.getShape().contains(e.getX(), e.getY())) {
                	insideVertex = true;
                    break;
                }
            }
            
            if (!insideVertex) {
            	startPoint = new Vertex(e.getX(), e.getY(), ++vertexCount);
            	vertexLocation.add(startPoint);
                startPoint = null;
            }
            else {
            	startPoint = null;
            	System.out.println("Can't create vertex with start "
            			           + "point inside another vertex");
            }
                        
        }
        
        // need to catch a null pointer exception
        else if (buttonNum == 2) {
            if (startPoint == null) {

	            for (Vertex v : vertexLocation) {
	                if (v.getShape().contains(e.getX(), e.getY())) {
	                    startPoint = v;
	                    startPoint.setVertexColor(Color.GREEN);
	                }
	            }
            }
            // used only if the start and end aren't the same selected vertices
            else if(endPoint == null &&
            		!startPoint.getShape().contains(e.getX(), e.getY())) {
                
                for (Vertex v : vertexLocation) {                	
                	if (v.getShape().contains(e.getX(), e.getY())) {
                        endPoint = v;
                        newEdge = new Edge(startPoint, endPoint);
                        edgeLocation.add(newEdge);
                        break;
                    }

                }
                
                // condition needed for when user didn't select a vertex
                // prevents a null pointer exception
                if (endPoint != null) {
                	// add to hash map the values for vertex number and weight
                    startPoint.edgeValues.put(endPoint.getVertexNumber(), newEdge.getWeight());
                    
                    startPoint.setVertexColor(Color.RED);
                    endPoint.setVertexColor(Color.RED);
                    startPoint = null;
                    endPoint = null;
                    newEdge = null;
                }
                else {
                	startPoint.setVertexColor(Color.RED);
                	startPoint = null;
                    endPoint = null;
                    newEdge = null;
                }
                
            }
            // changes vertex back to red if same vertex is selected
            else if (endPoint == null &&
            		 startPoint.getShape().contains(e.getX(), e.getY())) {
            	startPoint.setVertexColor(Color.RED);
            	startPoint = null;
            }

            picture.repaint();

        }
        else if (buttonNum == 3) {
        	// startPoint refers to the vertex you choose to move
        	// issue with a vertex being moved too close to another
            if (startPoint == null) {

                for (Vertex v : vertexLocation) {
                	// cycle through and see if a vertex does exist
                	// if it does exist, assign startPoint to v and then set color
                    if (v.getShape().contains(e.getX(), e.getY())) {	
                        startPoint = v;
                        startPoint.setVertexColor(Color.GREEN);
                        break;
                    }
                }
            }
            // sets location of moved vertex by using endPoints X/Y
            else if(endPoint == null) {
            	boolean insideVertex = false;
            	
            	for (Vertex v : vertexLocation) {
                    if (v.getShape().contains(e.getX(), e.getY())) {	
                        insideVertex = true;
                        break;
                    }
                }
            	
            	if (!insideVertex) {
            		ArrayList<Edge> edgeContainer = new ArrayList<>();
                	ArrayList<Boolean> whichPoint = new ArrayList<>();
                	boolean check = false;
                	
                    
                    // checks for vertices with multiple edges
                    for (Edge edge : edgeLocation) {
                    	if (edge.getStartVertex() == startPoint) {
                    		newEdge = edge;
                    		check = true;
                    		edgeContainer.add(newEdge);
                    		whichPoint.add(check);
                    	}
                    	else if (edge.getEndVertex() == startPoint) {
                    		newEdge = edge;
                    		check = false;
                    		edgeContainer.add(newEdge);
                    		whichPoint.add(check);
                    	}
                    }
                    startPoint.changeLocation(e.getX(), e.getY());
                    // Used to only change either start or end
                    for (int i = 0; i < whichPoint.size(); i++) {
                    	if (whichPoint.get(i)) edgeContainer.get(i).changeStartLocation(startPoint);
                        else edgeContainer.get(i).changeEndLocation(startPoint);
                    }
                    
                    startPoint = null;
                    newEdge = null;
            	}
            	else {
            		startPoint.setVertexColor(Color.RED);
            		startPoint = null;
            		System.out.println("Can't move vertex with move "
     			           + "point inside another vertex");
            	}
            	
            	
            }
            picture.repaint();

        }
        else if (buttonNum == 4) {
        	if (edgeLocation.size() > 0) {
        		
        	}
        	else if (startPoint == null) {

	            for (Vertex v : vertexLocation) {
	            	if (v.getShape().contains(e.getX(), e.getY())) {
	                    startPoint = v;
	                    startPoint.setVertexColor(Color.GREEN);
	                }
	            }
	            
            }
        	
        	startPoint = null;
        }
        else if (buttonNum == 5){
        	System.out.println("Change weight pressed");
        }
    }

}
