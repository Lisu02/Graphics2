    import javax.swing.*;
    import java.awt.*;
    import java.awt.image.BufferedImage;
    import java.util.ArrayList;

    public class MyPanel extends JPanel {

        MyImage myImage;
        PixelImage pixelImage;
        BufferedImage bufferedImage;
        PPM3Loader ppm3Loader = new PPM3Loader();

        private double zoomFactor = 1.0;
        private double offsetX = 0;
        private double offsetY = 0;

        public MyPanel() {
         PPM3Loader.loadPPM3TestImage();
         myImage = new MyImage();
         bufferedImage = ppm3Loader.blockReading("ppm-test-07-p3-big.ppm"); //ppm-test-07-p3-big.ppm ppm-test-02-p3-comments.ppm

            addMouseWheelListener(e -> {
                double oldZoomFactor = zoomFactor;
                if (e.getPreciseWheelRotation() < 0) {
                    zoomFactor *= 1.1; // przybliż
                } else {
                    zoomFactor /= 1.1; // oddal
                }

                // Oblicz współczynniki przeskalowania
                double scaleChange = zoomFactor / oldZoomFactor;

                // Uaktualnij przesunięcie, aby środek powiększenia był tam, gdzie kursor myszy
                int mouseX = e.getX();
                int mouseY = e.getY();
                offsetX = mouseX - (mouseX - offsetX) * scaleChange;
                offsetY = mouseY - (mouseY - offsetY) * scaleChange;

                repaint(); // Odśwież panel po zmianie
            });

        }

        public void setZoomFactor(double zoomFactor) {
            this.zoomFactor = zoomFactor;
            repaint(); // odśwież panel po zmianie poziomu powiększenia
        }




        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            // Przesunięcie i skalowanie obrazu
            g2d.translate(offsetX, offsetY);
            g2d.scale(zoomFactor, zoomFactor);
            //g.setColor(Color.BLACK);
            //g.fillRect(0, 0, 600, 600);
           // myImage.content = (ArrayList<String>) PPM3Loader.testImagePPM3List;
           // myImage.paint(g);
            g2d.drawImage(bufferedImage, 0, 0, this);
            //pixelImage.paintComponents(g2d);
        }
    }
