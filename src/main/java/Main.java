import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        MyFrame frame = new MyFrame();
        MyPanel panel = new MyPanel();
        frame.setName("Graphics 2");
        frame.setSize(900,700);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setVisible(true);
    }
}
