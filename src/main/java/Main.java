import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        MyFrame frame = new MyFrame();
        MyPanel panel = new MyPanel();
        frame.setName("Graphics 2");

        panel.setBackground(Color.BLACK);
        frame.setSize(1920,1080);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setVisible(true);
    }
}
