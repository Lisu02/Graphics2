import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MyImageHeader {

    private String headerFileType = "";
    private String headerImageSize = "";
    private String headerMaximumColorSize = "";
    private int imageWidth = 0;
    private int imageHeight = 0;

    private void setImageSize(){
        imageWidth = Integer.parseInt(headerImageSize.split(" ")[0]);
        imageHeight = Integer.parseInt(headerImageSize.split(" ")[1]);
    }

    MyImageHeader(String headerFileType, String headerImageSize, String headerMaximumColorSize) {
        this.headerFileType = headerFileType;
        this.headerImageSize = headerImageSize;
        this.headerMaximumColorSize = headerMaximumColorSize;
        setImageSize();
    }
    MyImageHeader(List<String> content) {
        if(content.isEmpty()){
            throw new IllegalArgumentException("List is Empty");
        }
        this.headerFileType = content.get(0);
        this.headerImageSize = content.get(1);
        this.headerMaximumColorSize = content.get(2);
        setImageSize();
    }
}
