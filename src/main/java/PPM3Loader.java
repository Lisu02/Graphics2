import lombok.SneakyThrows;

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

    public PixelImage blockReading(String filename) {
        FileReader fileReader = null;
        File myFile = new File(filename);
        try{
            fileReader = new FileReader(myFile);
        }catch (FileNotFoundException e) {
            System.out.println("NIE ZNALEZIONO PLIKU!!!!!");
            e.printStackTrace();
        }

        char[] buffer = new char[297483645];
        CharBuffer charbuffer = CharBuffer.wrap(buffer);
        BufferedReader myReader = new BufferedReader(fileReader);
        try{
            int amountOfCharacters = myReader.read(charbuffer);
            PixelImage pixelImage = new PixelImage(convertReadToObjects(buffer));
            return pixelImage;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            charbuffer.clear();
            buffer = null;
        }
    }

    private ArrayList<Pixel> convertReadToObjects(char[] content) {
        ArrayList<String> colorNumber = new ArrayList<String>();
        ArrayList<String> numberList = new ArrayList<String>();
        ArrayList<Pixel> pixelList = new ArrayList<>();
        Boolean isComment = false;
        Boolean isNumber = false;
        int width = 0,height = 0;
        int maxValue = 0;
        int r = -2,g = -2,b = -2;
        int x = 0,y = 0;
        for(int i = 2; i < content.length - 250; i++){
            if(content[i] == '#'){isComment = true;continue;}
            if(content[i] == '\n' && isComment  ){isComment = false;continue;}
            //No comment
            if(!isComment){
                //Check on width
                if(content[i] >= 48 && content[i] <= 57 && width == 0){
                    numberList.add(String.valueOf(content[i]));
                    if(content[i+1] == ' ' || content[i+1] == '\n'){
                        width = convertNumberListToSingleInt(numberList);
                        numberList.clear();
                        continue;
                    }
                    continue;
                }
                //Check on height
                if(content[i] >= 48 && content[i] <= 57 && height == 0){
                    numberList.add(String.valueOf(content[i]));
                    if(content[i+1] == ' ' || content[i+1] == '\n'){
                        height = convertNumberListToSingleInt(numberList);
                        numberList.clear();
                        continue;
                    }
                    continue;
                }
                //Check on maxValue
                if(content[i] >= 48 && content[i] <= 57 && maxValue == 0){
                    numberList.add(String.valueOf(content[i]));
                    if(content[i+1] == ' ' || content[i+1] == '\n'){
                        maxValue = convertNumberListToSingleInt(numberList);
                        numberList.clear();
                        continue;
                    }
                    continue;
                }

                //Standard number
                if(content[i] >= 48 && content[i] <= 57){ //jezeli jest liczba
                    isNumber = true;
                    colorNumber.add(String.valueOf(content[i]));
                }
                //End of a standard number
                if((content[i] == ' ' || content[i] == '\n') && isNumber){
                    if(r == -2){
                        r = convertNumberListToSingleInt(colorNumber);
                        colorNumber.clear();
                        isNumber = false;
                    } else if (g == -2) {g = convertNumberListToSingleInt(colorNumber); colorNumber.clear(); isNumber = false;
                    } else if (b == -2) {b = convertNumberListToSingleInt(colorNumber);
                        Pixel pixel = new Pixel(x,y,new Color(r,g,b));
                        //System.out.println("Pixel: " + pixel.getX() + "," + pixel.getY() + " color: " + pixel.getColor());
                        pixelList.add(pixel);

                        x++;
                        if(x >= width){x = 0; y++;}
                        if(y >= height){break;}
                        r = -2;g = -2;b = -2;
                        isNumber = false;
                        colorNumber.clear();
                    }
                }
            }
        }
        System.out.println("Width-> " + width + " Height-> " + height);
        return pixelList;
    }

    private int convertNumberListToSingleInt(ArrayList<String> arrayList){
        int power = arrayList.size() - 1;
        int result = 0;
        for (String number: arrayList){
            result += (int) (Integer.parseInt(number) *    Math.pow(10,power));
            power --;
        }
        return result;
    }



}
