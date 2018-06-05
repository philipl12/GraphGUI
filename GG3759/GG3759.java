//package GG3759;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by Philip Lin on 20 Apr 2018
 *
 *
 */

public class GG3759 extends JFrame {
    protected GraphPicturePanel picture;
    protected AddButtonListener buttonListener;

    JPanel buttonContainer;
    JRadioButton addVertex;
    JRadioButton addEdge;
    JRadioButton moveVertex;
    JRadioButton shortestPath;

    JButton addAllEdges;
    JButton randomWeight;
    JButton spanTree;
    JButton helpText;

    static int buttonNum = -1; //keeps track of which button is selected
    private static int vertices = 0; //tracking number of vertices

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager
                    .getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException e) {
        }
        new GG3759();
    }

    public GG3759() {
        super("Graph GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 575);
        setLayout(new GridLayout(0, 2));//try out gridBagLayout
        buttonListener = new AddButtonListener(this);
        picture = new GraphPicturePanel(this);

        buttonContainer = new JPanel(new GridLayout(9, 1));
        addVertex = new JRadioButton("Add vertex");
        addVertex.addActionListener(buttonListener);
        addEdge = new JRadioButton("Add edge");
        addEdge.addActionListener(buttonListener);
        moveVertex = new JRadioButton("Move vertex");
        moveVertex.addActionListener(buttonListener);
        shortestPath = new JRadioButton("Shortest path");
        shortestPath.addActionListener(buttonListener);
        OddButton changeWeight = new OddButton();

        //groups radio buttons together to allow only one to be clicked
        ButtonGroup radioButtons = new ButtonGroup();
        radioButtons.add(addVertex);
        radioButtons.add(addEdge);
        radioButtons.add(moveVertex);
        radioButtons.add(shortestPath);
        radioButtons.add(changeWeight.genericRB);


        //add JButton
        addAllEdges = new JButton("Add All Edges");
        addAllEdges.addActionListener(buttonListener);
        randomWeight = new JButton("Random Weights");
        randomWeight.addActionListener(buttonListener);
        spanTree = new JButton("Minimal Spanning Tree");
        spanTree.addActionListener(buttonListener);
        helpText = new JButton("Help");
        helpText.addActionListener(buttonListener);

        buttonContainer.add(addVertex);
        buttonContainer.add(addEdge);
        buttonContainer.add(moveVertex);
        buttonContainer.add(shortestPath);
        buttonContainer.add(changeWeight.container);

        buttonContainer.add(addAllEdges);
        buttonContainer.add(randomWeight);
        buttonContainer.add(spanTree);
        buttonContainer.add(helpText);


        add(buttonContainer);
        add(picture);

        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        picture.repaint();
        setVisible(true);
    }

    public class OddButton implements ActionListener {
        JPanel container;
        JRadioButton genericRB;
        JTextField genericText;

        public OddButton() {
            container = new JPanel();
            container.setLayout(new GridLayout(0, 2));
            genericRB = new JRadioButton("Change weight to:");
            genericText = new JTextField(10);
            genericRB.addActionListener(this);
            genericText.addActionListener(this);

            container.add(genericRB);
            container.add(genericText);
        }

        public void actionPerformed(ActionEvent clicked) {
            System.out.println("You have a text field to read.");
        }

    }

    public class AddButtonListener implements ActionListener {
        protected GG3759 mainFrame;
        final JFrame helpMessage = new JFrame();

        public AddButtonListener(GG3759 mainFrame) {
            this.mainFrame = mainFrame;
        }

        public int getNum() {
            return buttonNum;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (mainFrame.addVertex.isSelected()) {
                System.out.println("No vertexes");
                buttonNum = 1;
            } else if (mainFrame.addEdge.isSelected()) {
                System.out.println("No edge");
                buttonNum = 2;
            } else if (mainFrame.moveVertex.isSelected()) {
                System.out.println("No move vertex");
                buttonNum = 3;
            } else if (mainFrame.shortestPath.isSelected()) {
                System.out.println("No path");
                buttonNum = 4;
            } else if (mainFrame.addAllEdges.isSelected()) {//currently have a bug here with the JButtons returning only one action
                System.out.println("Can't add");
            } else if (mainFrame.randomWeight.isSelected()) {
                System.out.println("What weight");
            } else if (mainFrame.spanTree.isSelected()) {
                System.out.println("Damn tree");
            } else if (mainFrame.helpText.isSelected()) {
                JOptionPane.showMessageDialog(
                            helpMessage, "Begin by adding " +
                            "vertexes, then add edges, then " +
                            "add weights.\nAll the buttons don't work.");
            }
        }

    }

    public class GraphPicturePanel extends JPanel {
        protected GG3759 mainFrame;
        protected MouseClicker clicker;


        public GraphPicturePanel(GG3759 mainFrame) {
            this.mainFrame = mainFrame;
            clicker = new MouseClicker(this);
        }


        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.RED);
            for (Vertex vertices : clicker.vertexLocation) {
                g.fillOval(vertices.getX(), vertices.getY(), 10, 10);
                //System.out.println(points);
                //above line causes endless amounts of point to print to console
            }

            repaint();
        }

    }

    public class MouseClicker extends MouseAdapter {
        GraphPicturePanel picture;
        Vertex newVertex;
        ArrayList<Vertex> vertexLocation = new ArrayList<>();
        Point vertex1, vertex2;
        boolean vertexSelected = false;

        public MouseClicker(GraphPicturePanel picture) {
            this.picture = picture;
            picture.addMouseListener(this);
        }

        public void mousePressed(MouseEvent e) {
            if (picture.mainFrame.buttonListener.getNum() == 1) {
                newVertex = new Vertex(e.getX(), e.getY());
                vertexLocation.add(newVertex);
                System.out.println("Add vertex pressed");
            }
            if (picture.mainFrame.buttonListener.getNum() == 2) {
                vertex1 = new Point(e.getX(), e.getY());
                vertex2 = new Point(e.getX(), e.getY());
                vertexSelected = true;
                System.out.println("Add edge pressed");

            }
            if (picture.mainFrame.buttonListener.getNum() == 3) {
                System.out.println("Move vertex pressed");
            }
            if (picture.mainFrame.buttonListener.getNum() == 4) {
                System.out.println("Shortest path pressed");
            }
        }

    }

    public class Vertex {
        private int x, y;
        Color vertexColor;

        public Vertex(int x, int y) {
            vertices++;
            this.x = x;
            this.y = y;
            vertexColor = Color.RED;
        }

        public int getVertices() {
            return vertices;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }
        public void setY(int y) {
            this.y = y;
        }

        public Color getVertexColor() {
            return vertexColor;
        }
        public void setVertexColor(Color vertexColor) {
            this.vertexColor = vertexColor;
        }
    }

}
