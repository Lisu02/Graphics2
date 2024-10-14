import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class PPM6Loader {

    public static BufferedImage loadPPM6(String filename) throws IOException {
        File file = new File(filename);
        FileInputStream fis = new FileInputStream(file);
        Scanner scanner = new Scanner(fis);

        String format = scanner.next();
        if (!format.equals("P6")) {
            throw new IllegalArgumentException("Niepoprawny format pliku: " + format);
        }

        int width = 0;
        int height = 0;
        int maxVal = 0;
        while (scanner.hasNext()) {
            String line = scanner.next();
            if (line.startsWith("#")) {
                scanner.nextLine();
            } else if (width == 0) {
                width = Integer.parseInt(line);
            } else if (height == 0) {
                height = scanner.nextInt();
                scanner.next();
            } else if (maxVal == 0) {
                maxVal = scanner.nextInt();
                break;
            }
        }

        if (maxVal > 255) {
            throw new IllegalArgumentException("Maksymalna wartość koloru musi być mniejsza lub równa 255");
        }

        // Wczytaj dane pikseli
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        byte[] pixelData = new byte[width * height * 3];
        fis.read(pixelData);

        int index = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int r = pixelData[index++] & 0xFF;
                int g = pixelData[index++] & 0xFF;
                int b = pixelData[index++] & 0xFF;
                int rgb = new Color(r, g, b).getRGB();
                image.setRGB(x, y, rgb);
            }
        }

        fis.close();
        return image;
    }
}
