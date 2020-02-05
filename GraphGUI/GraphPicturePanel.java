import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;

import javax.swing.JPanel;

public class GraphPicturePanel extends JPanel {
    protected GG3759 mainFrame;
    protected MouseClicker clicker;


    public GraphPicturePanel(GG3759 mainFrame) {
        this.mainFrame = mainFrame;
        clicker = new MouseClicker(this);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (Vertex vertices : clicker.vertexLocation) {
            g2d.setColor(vertices.getVertexColor());
            Shape s = vertices.getShape();
            g2d.fill(s);
            String temp = Integer.toString(vertices.getVertexNumber());
            g2d.drawString(temp, vertices.getX(), vertices.getY());
        }

        for (Edge allEdges : clicker.edgeLocation) {

            if(allEdges.isComplete) {
                g2d.setColor(allEdges.getEdgeColor());
                Shape line = allEdges.getLine();
                g2d.draw(line);
                String temp = Integer.toString(allEdges.getWeight());
                g2d.drawString(temp, allEdges.getX(), allEdges.getY());
            }
        }

        repaint();
    }

}
