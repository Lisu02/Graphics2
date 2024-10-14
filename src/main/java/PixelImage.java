import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
@Getter
@Setter
@AllArgsConstructor
public class PixelImage extends JComponent{
    ArrayList<Pixel> pixels;

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        System.out.println(pixels.size());
        for (Pixel pixel : pixels) {
            g.setColor(pixel.getColor());
            g.fillRect(pixel.getX(), pixel.getY(), 1, 1);
        }
        System.gc();
    }
}
