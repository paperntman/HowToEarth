package topen.khjs.howtoearth;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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

    public static final String UPLOAD_DIR = Paths.get("").toAbsolutePath()+ File.separator+"img";

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestPart("file") MultipartFile file) {
        // Check if the uploaded file is not empty
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a file to upload.");
        }

        try {

            String fileName = generate(file);

            // Return the file upload success response
            return ResponseEntity.ok().body("File uploaded successfully: " + fileName);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to upload file. Please try again.");
        }
    }

    private String generate(MultipartFile file) throws IOException {
        // Generate a unique file name
        String originalFilename = file.getOriginalFilename();
        String fileExtension = StringUtils.getFilenameExtension(originalFilename);
        String fileName = System.currentTimeMillis() + "." + fileExtension;

        // Copy the file to the upload directory
        Path filePath = Path.of(UPLOAD_DIR, fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }
}


// 내가 좀 귀엽긴 해

