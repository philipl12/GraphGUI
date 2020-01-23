import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddButtonListener implements ActionListener {
    private GG3759 mainFrame;
    private static int buttonNum = -1;

    public AddButtonListener(GG3759 mainFrame) {
        this.mainFrame = mainFrame;
    }

    public int getNum() {
        return buttonNum;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (mainFrame.addVertex.isSelected()) {
            buttonNum = 1;
        } else if (mainFrame.addEdge.isSelected()) {
            buttonNum = 2;
        } else if (mainFrame.moveVertex.isSelected()) {
            buttonNum = 3;
        } else if (mainFrame.shortestPath.isSelected()) {
            System.out.println("No path");
            buttonNum = 4;
        }
    }
}
