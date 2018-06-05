import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddRegularListener implements ActionListener {
    private GG3759 mainFrame;
    private final JFrame helpMessage = new JFrame();

    public AddRegularListener (GG3759 mainFrame){
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String clickedButton = e.getActionCommand();

        if (clickedButton.equals("Add All Edges")) {
            System.out.println("Can't add");
        } else if (clickedButton.equals("Random Weights")) {
            System.out.println("What weight");
        } else if (clickedButton.equals("Minimal Spanning Tree")) {
            System.out.println("Damn tree");
        } else if (clickedButton.equals("Help")) {
            JOptionPane.showMessageDialog(helpMessage, "Begin by adding vertexes. Add edges wherever you'd " +
                                          "like.\nThere is an option to move any vertex, so long as you select it. " +
                                          "You can add weights to any selected edges.\nCurrently cannot add " +
                                          "all edges, assign random weight, or create a minimal spanning tree.");
        }
    }
}
