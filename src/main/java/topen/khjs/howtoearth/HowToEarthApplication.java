package topen.khjs.howtoearth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class HowToEarthApplication {

    public static void main(String[] args) {
        SpringApplication.run(HowToEarthApplication.class, args);
        new File(FileUploadController.UPLOAD_DIR).mkdir();
    }


}