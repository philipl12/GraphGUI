import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Philip Lin on 20 Apr 2018
 */

public class GG3759 extends JFrame {
    protected GraphPicturePanel picture;
    protected AddButtonListener buttonListener;
    protected AddRegularListener regularListener;

    protected JPanel buttonContainer;
    protected JRadioButton addVertex;
    protected JRadioButton addEdge;
    protected JRadioButton moveVertex;
    protected JRadioButton shortestPath;

    protected JButton addAllEdges;
    protected JButton randomWeight;
    protected JButton spanTree;
    protected JButton helpText;



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
        setLayout(new GridLayout(0, 2));
        buttonListener = new AddButtonListener(this);
        regularListener = new AddRegularListener(this);
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


        //add JButton and assign action listener
        addAllEdges = new JButton("Add All Edges");
        addAllEdges.addActionListener(regularListener);
        randomWeight = new JButton("Random Weights");
        randomWeight.addActionListener(regularListener);
        spanTree = new JButton("Minimal Spanning Tree");
        spanTree.addActionListener(regularListener);
        helpText = new JButton("Help");
        helpText.addActionListener(regularListener);

        //group all buttons together into container
        ButtonGroup justButtons = new ButtonGroup();
        justButtons.add(addAllEdges);
        justButtons.add(randomWeight);
        justButtons.add(spanTree);
        justButtons.add(helpText);

        buttonContainer.add(addVertex);
        buttonContainer.add(addEdge);
        buttonContainer.add(moveVertex);
        buttonContainer.add(shortestPath);
        buttonContainer.add(changeWeight.container);

        buttonContainer.add(addAllEdges);
        buttonContainer.add(randomWeight);
        buttonContainer.add(spanTree);
        buttonContainer.add(helpText);

        //add to frame
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
        	buttonListener.buttonNum = 5;
        	genericText.setText(" ");
            //int newWeight = Integer.parseInt(genericText.getText());
        }

    }
}
