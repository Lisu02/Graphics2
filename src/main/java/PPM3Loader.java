import java.awt.*;
import java.io.*;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


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
        testImagePPM3List.add("0 255 0\n");
        testImagePPM3List.add("255 255 0");
        //testImagePPM3List.add("0 0 255 255 0 0 0 255 0\n");
    }

    public static void load2PPM3TestImage() {
        String test = "P3\n" +
                "5 5\n" +
                "255\n" +
                "255 255 255   255 255 255   255 255 255   255 255 255   255 255 255\n" +
                "255 255 255   0   0   0     0   0   0     0   0   0     255 255 255\n" +
                "255 255 255   0   0   0     255 255 255   0   0   0     255 255 255\n" +
                "255 255 255   0   0   0     0   0   0     0   0   0     255 255 255\n" +
                "255 255 255   255 255 255   255 255 255   255 255 255   255 255 255\n";
    }

    public void loadImage(String filename) {
        File myFile = new File(filename);
        ArrayList<String> fileContent = new ArrayList<String>();
        try{
            if(myFile.exists()){
                Scanner myReader = new Scanner(myFile);
                while(myReader.hasNextLine()){
                    fileContent.add(myReader.nextLine());
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void blockReading(String filename) {
        FileReader fileReader = null;
        try{
            fileReader = new FileReader(filename);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        char[] buffer = new char[1024];
        CharBuffer charbuffer = CharBuffer.wrap(buffer);
        BufferedReader myReader = new BufferedReader(fileReader);
        try{
            int amountOfCharacters = myReader.read(charbuffer);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void convertCharArrayToDrawable(char[] content) {
        ArrayList<String> drawing = new ArrayList<String>();
        if(!String.valueOf(content[0] + content[1]).equals("P3")){
            System.out.println("WRONG FILETYPE TO READ NOT A P3");
            throw new RuntimeException("WRONG FILETYPE TO READ NOT A P3");
        }

        boolean isComment = false;
        boolean isNumber = false;

        boolean isXSize = false;
        boolean isYSize = false;
        boolean isMaxValue = false;

        int xSize,ySize,maxValue;
        ArrayList<Integer> number = new ArrayList<Integer>();
        int x = 0,y = 0;
        int r = -2,g = -2,b = -2;

        ArrayList<Pixel> pixelArrayList = new ArrayList<>();
        for(int i = 0; i < content.length; i++){
            if(content[i] == '#'){isComment = true;continue;}
            if(content[i] == '\n'){isComment = false;continue;}
            //if(content[i] == ' '){continue;}
            //if(content[i] == '\t'){continue;}
            if(!isComment){




                if((content[i] == ' ' || content[i] == '\n') && isNumber){
                    pixelArrayList.add(new Pixel(x,y,new Color(r,g,b)));
                    r=-2;g=-2;b=-2;
                    isNumber = false;
                    continue;
                }
                if(content[i] >= 48 && content[i] <= 57){
                    isNumber = true;
                    if(isXSize == false){number.add((int) content[i]);}
                    if(r == -2){r = content[i];continue;}
                    if(g == -2){g = content[i];continue;}
                    if(b == -2){b = content[i];continue;}
                }

            }
        }
    }

    public ArrayList<String> convertStringImageToArrayList(String stringImage){
        stringImage = stringImage.replaceAll("   ", " ");
        stringImage = stringImage.replaceAll("  ", " ");


        return null;
    }


}
