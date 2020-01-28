import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;

import javax.swing.JPanel;

public class GraphPicturePanel extends JPanel {
    protected GG3759 mainFrame;
    private MouseClicker clicker;


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
        }

        for (Edge allEdges : clicker.edgeLocation) {

            if(allEdges.isComplete) {
                g2d.setColor(allEdges.getEdgeColor());
                Shape line = allEdges.getLine();
                g2d.draw(line);
                int weight = allEdges.getWeight();
                String temp = Double.toString(weight);
                g2d.drawString(temp, allEdges.getX(), allEdges.getY());
            }
        }

        repaint();
    }

}
