import java.awt.*;
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
                try {
                    for (Vertex v : vertexLocation) {
                        if (v.getShape().contains(startPoint.getX(), startPoint.getY())) {
                            startPoint = v;
                            startPoint.setVertexColor(Color.GREEN);
                        }
                    }
                } catch (NullPointerException example) {
                    System.out.println("No start vertex was clicked");
                    return;
                }
            }
            else if(endPoint == null) {
                endPoint = new Vertex(e.getX(), e.getY());
                try {
                    for (Vertex v : vertexLocation) {
                        if (v.getShape().contains(endPoint.getX(), endPoint.getY())) {
                            endPoint = v;
                            newEdge = new Edge(startPoint, endPoint);
                            edgeLocation.add(newEdge);
                        }

                    }
                }
                catch (NullPointerException example) {
                    System.out.println("No end vertex was clicked");
                    return;
                }
                startPoint.setVertexColor(Color.RED);
                endPoint.setVertexColor(Color.RED);
                startPoint = null;
                endPoint = null;
            }
            picture.repaint();

        }
        if (buttonNum == 3) {
            if (startPoint == null) {
                startPoint = new Vertex(e.getX(), e.getY());
                try {
                    for (Vertex v : vertexLocation) {
                        if (v.getShape().contains(startPoint.getX(), startPoint.getY())) {
                            startPoint = v;
                            v.setVertexColor(Color.GREEN);
                        }
                    }
                }
                catch (Exception example) {
                    System.out.println("No end vertex was clicked");
                    return;
                }
            }
            else if(endPoint == null) {
                endPoint = new Vertex(e.getX(), e.getY());
                startPoint.changeLocation(endPoint.getX(), endPoint.getY());
                //startPoint.setVertexColor(Color.RED);
                try {
                    for (Edge edges : edgeLocation) {
                        if (edges.getStartShape().contains(startPoint.getX(), startPoint.getY())) {

                        }
                    }

                }
                catch (Exception example) {
                    System.out.println("No edges attached");
                    return;
                }
                startPoint = null;
                endPoint = null;
            }

            picture.repaint();

        }
        if (buttonNum == 4) {
            System.out.println("Shortest path pressed");
        }
    }

}
