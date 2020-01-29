import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class AddRegularListener implements ActionListener {
    @SuppressWarnings("unused")
	private GG3759 mainFrame;
    private final JFrame myMessage = new JFrame();

    public AddRegularListener (GG3759 mainFrame){
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String clickedButton = e.getActionCommand();

        if (clickedButton.equals("Add All Edges")) {
        	if (mainFrame.picture.clicker.edgeLocation.isEmpty()) {
        		JOptionPane.showMessageDialog(myMessage, "No edges to add.");
        	}
        	else {
        		int sum = 0;            	
            	for (Edge edges : mainFrame.picture.clicker.edgeLocation) sum += edges.getWeight();            	
            	JOptionPane.showMessageDialog(myMessage, "All edge weights added equals " + sum);
        	}
        	
        } else if (clickedButton.equals("Random Weights")) {
        	if (mainFrame.picture.clicker.edgeLocation.isEmpty()) {
        		JOptionPane.showMessageDialog(myMessage, "No edges on graph.");
        	}
        	else {
        		Random randWeight = new Random();
        		for (Edge edges : mainFrame.picture.clicker.edgeLocation) edges.setWeight(randWeight.nextInt(50));
        		mainFrame.picture.repaint();
        		JOptionPane.showMessageDialog(myMessage, "Random weights assigned.");
        	}
        } else if (clickedButton.equals("Minimal Spanning Tree")) {
            System.out.println("Damn tree");
        } else if (clickedButton.equals("Help")) {
            JOptionPane.showMessageDialog(myMessage, "Begin by adding vertexes. Add edges wherever you'd " +
                                          "like.\nThere is an option to move any vertex, so long as you select it. " +
                                          "You can add weights to any selected edges.");
        }
    }
}
