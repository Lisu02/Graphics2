import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class MyImage extends JComponent {



    ArrayList<String> content = null;
    private MyImageHeader myImageHeader = null;
    private int maxWidth = 0;
    private int maxHeight = 0;


    public void paint(Graphics g) {
        paintImagePPM3(g);
    }

    public void paintImagePPM3(Graphics g) {
        content = stripHeader(content);
        int x = 1;
        int y = 1;
        for(String color: content) {

            g.setColor(getColorFromString(color));
            g.fillRect(x,y,25,25);
            x += 25;
            if(color.contains("\n")){
                y+=25;
                x = 1;
            }
        }
    }

    private Color getColorFromString(String color){
        int r,g,b;
        System.out.println(color);
        System.out.println(color.split(" ")[0]);
        System.out.println(color.split(" ")[1]);
        System.out.println(color.split(" ")[2]);
        color = color.replace("\n","");
        r = Integer.parseInt(color.split(" ")[0]);
        g = Integer.parseInt(color.split(" ")[1]);
        b = Integer.parseInt(color.split(" ")[2]);

        return new Color(r,g,b);
    }

    public ArrayList<String> stripHeader(List<String> content) {
        myImageHeader = new MyImageHeader(content);
        maxHeight = myImageHeader.getImageHeight();
        maxWidth = myImageHeader.getImageWidth();
        return new ArrayList<>(content.subList(3, content.size()));
    }

    public void paintImagePPM6(Graphics g,String content) {

    }
}
