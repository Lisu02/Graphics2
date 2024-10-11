import java.util.ArrayList;
import java.util.List;

public class PPM3Loader {


    static String testImagePPM3 = "P3\n " +
            "3 1\n" +
            "255\n" +
            "0 0 255" +
            " 255 0 0" +
            " 0 255 0\n";
    static List<String> testImagePPM3List = new ArrayList<String>();


    public static void loadPPM3TestImage() {
        testImagePPM3List.add("P3");
        testImagePPM3List.add("3 1");
        testImagePPM3List.add("255");
        testImagePPM3List.add("0 0 255");
        testImagePPM3List.add("255 0 0");
        testImagePPM3List.add("0 255 0");
        //testImagePPM3List.add("0 0 255 255 0 0 0 255 0\n");
    }


}
