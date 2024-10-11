import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyPanel extends JPanel {

    MyImage myImage;

    public MyPanel() {
     PPM3Loader.loadPPM3TestImage();
     myImage = new MyImage();
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.setColor(Color.BLACK);
        //g.fillRect(0, 0, 600, 600);
        myImage.content = (ArrayList<String>) PPM3Loader.testImagePPM3List;
        myImage.paint(g);
    }
}
