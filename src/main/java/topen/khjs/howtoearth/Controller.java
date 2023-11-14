package topen.khjs.howtoearth;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

@org.springframework.stereotype.Controller
public class Controller {
    @GetMapping("/")
    public String redirectToIndex() {
        return "redirect:/index.html";
    }

    @GetMapping("/image/{imageName}")
    public ResponseEntity<Resource> downloadImage(@PathVariable String imageName) throws IOException {
        Resource imageResource = new FileSystemResource("./img/" + imageName);

        if (imageResource.exists() && imageResource.isReadable()) {
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + imageName);

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(imageResource.contentLength())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(imageResource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}



// 유후 서버가 열린

