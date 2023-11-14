package topen.khjs.howtoearth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
public class FileUploadController {

    public static final String UPLOAD_DIR = Paths.get("").toAbsolutePath() + File.separator + "img";
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping(value = "/upload")
    public ResponseEntity<String> uploadFile(@RequestPart("file") MultipartFile file ,@RequestPart("time") String time) {
        // Check if the uploaded file is not empty
        logger.info("File Upload Try");
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a file to upload.");
        }

        try {

            String fileName = generate(file, time);


            // Return the file upload success response
            logger.info("File uploaded successfully: {}", fileName);
            return ResponseEntity.ok().build();

        } catch (IOException e) {
            logger.error("File upload failed");
            return ResponseEntity.status(500).build();
        }
    }

    private String generate(MultipartFile file, String time) throws IOException {

        // Copy the file to the upload directory
        Path filePath = Path.of(UPLOAD_DIR, time+".png");
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        return time;
    }
}


// 내가 좀 귀엽긴 해

