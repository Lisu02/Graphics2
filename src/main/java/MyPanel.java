    import javax.swing.*;
    import java.awt.*;
    import java.util.ArrayList;

    public class MyPanel extends JPanel {

        MyImage myImage;
        PixelImage pixelImage;
        PPM3Loader ppm3Loader = new PPM3Loader();
        public MyPanel() {
         PPM3Loader.loadPPM3TestImage();
         myImage = new MyImage();
         pixelImage = ppm3Loader.blockReading("ppm-test-07-p3-big.ppm"); //ppm-test-07-p3-big.ppm ppm-test-02-p3-comments.ppm
        }


        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            //g.setColor(Color.BLACK);
            //g.fillRect(0, 0, 600, 600);
           // myImage.content = (ArrayList<String>) PPM3Loader.testImagePPM3List;
           // myImage.paint(g);
            pixelImage.paintComponents(g);
        }
    }
