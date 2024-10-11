import javax.swing.*;
import java.awt.*;

public class Pixel extends JComponent {
    private int x,y;
    private Color color;
    public Pixel(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, x, y);
    }


}
