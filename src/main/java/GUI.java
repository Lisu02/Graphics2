import javax.swing.*;
import java.awt.*;

public class GUI extends JComponent {


    JDialog jDialog = new JDialog();
    JTextArea textArea = new JTextArea();
    JButton button = new JButton();


    public GUI(){
        jDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        jDialog.setTitle("GUI");
        jDialog.setAlwaysOnTop(true);
        jDialog.setLocationRelativeTo(null);
        jDialog.setVisible(true);
        jDialog.setBounds(0,0,200,200);
        textArea.setEditable(false);
        textArea.setVisible(true);
        textArea.setBounds(0,0,100,50);
        textArea.setText("X: " + "Y: ");
        //jDialog.add(textArea);
        button.setVisible(true);
        button.setBounds(0,50,50,50);
        jDialog.add(button);
        jDialog.add(textArea);
    }

    public void updateMousePositionForGUI(int x,int y){
        textArea.setText("X: " + x + " Y: " + y);
    }


    public void setCurrentColorOnCursor(int rgb) {
        button.setBackground(new Color(rgb));
    }
}
