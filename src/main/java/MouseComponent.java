import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

public class MouseComponent extends MouseMotionAdapter implements MouseListener, MouseMotionListener {

    MyPanel myPanel;
    Component selectedComponent = null;

    public MouseComponent(MyPanel panel) {
        myPanel = panel;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("mouseClicked->" + e.getX() + " y-" + e.getY());
        // Przekształć współrzędne myszki na współrzędne obrazu
        int imageX = (int) ((e.getX() - myPanel.offsetX) / myPanel.zoomFactor);
        int imageY = (int) ((e.getY() - myPanel.offsetY) / myPanel.zoomFactor);

        // Sprawdź, czy współrzędne są w granicach obrazu
        if (imageX >= 0 && imageX < myPanel.bufferedImage.getWidth() &&
                imageY >= 0 && imageY < myPanel.bufferedImage.getHeight()) {
            // Zmień kolor piksela na obrazie
            myPanel.bufferedImage.setRGB(imageX, imageY, Color.MAGENTA.getRGB());
            myPanel.repaint(); // Odśwież panel po zmianie
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}